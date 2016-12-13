package it.besmartbeopen.gitlab.pagination;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Verify;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

/**
 * @author marco
 *
 */
public class PaginationDecoder implements Decoder {

  private final static String X_PER_PAGE = "X-Per-Page";
  private final static String X_TOTAL = "X-Total";
  private final static String X_TOTAL_PAGES = "X-Total-Pages";
  final static int DEFAULT_PER_PAGE = 20;
  final static int FIRST_PAGE = 1;

  private final Decoder decoder;

  public PaginationDecoder(Decoder decoder) {
    this.decoder = decoder;
  }

  private int getIntHeader(Response response, String header) {

    return Integer.parseInt(response.headers().get(header).iterator().next());
  }

  @SuppressWarnings("serial")
  static <T> TypeToken<List<T>> listOf(TypeToken<T> itemToken) {
    return new TypeToken<List<T>>() {}
      .where(new TypeParameter<T>() {}, itemToken);
  }

  @Override
  public Object decode(Response response, Type paramType)
      throws IOException, DecodeException, FeignException {

    if (paramType instanceof ParameterizedType) {

      final ParameterizedType type = (ParameterizedType) paramType;
      if (type.getRawType().equals(Page.class)) {
        final List<Type> args = Arrays.asList(type.getActualTypeArguments());
        Verify.verify(args.size() == 1, "Paginated must be parameterized");

        final Object obj = decoder.decode(response, listOf(TypeToken.of(args.get(0))).getType());
        final int total = getIntHeader(response, X_TOTAL);
        final int perPage = getIntHeader(response, X_PER_PAGE);
        final int totalPages = getIntHeader(response, X_TOTAL_PAGES);

        return new Page<>((List<?>) obj, total, totalPages, perPage);
      }
    }
    return decoder.decode(response, paramType);
  }
}
