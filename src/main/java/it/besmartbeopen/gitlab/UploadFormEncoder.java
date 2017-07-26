package it.besmartbeopen.gitlab;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.FormDataProcessor;

public class UploadFormEncoder implements Encoder {

  private final Encoder delegate;
  protected final FormDataProcessor processor;

  /**
   * @param delegate
   */
  public UploadFormEncoder(Encoder delegate) {
    this.delegate = delegate;
    this.processor = new UploadMultipartEncodedDataProcessor();
  }

  /**
   * from feign.form.MultipartEncodedDataProcessor.
   *
   * @see feign.codec.Encoder#encode(java.lang.Object, java.lang.reflect.Type, feign.RequestTemplate)
   */
  @Override
  public void encode(Object object, Type bodyType, RequestTemplate template)
      throws EncodeException {

    if (!(MAP_STRING_WILDCARD.equals(bodyType))) {
      this.delegate.encode(object, bodyType, template);
      return;
    }

    String formType = "";
    for (Map.Entry<?,?> entry : template.headers().entrySet()) {
      if (!entry.getKey().equals("Content-Type")) {
        continue;
      }
      for (Object contentType : (Collection<?>) entry.getValue()) {
        if (this.processor.getSupportetContentType().equals(contentType)) {
          formType = (String) contentType;
          break;
        }
      }
      if (!(formType.isEmpty())) {
        break;
      }
    }

    if (formType.isEmpty()) {
      this.delegate.encode(object, bodyType, template);
      return;
    }

    @SuppressWarnings("unchecked")
    final Map<String, Object> data = (Map<String, Object>) object;
    this.processor.process(data, template);
  }
}

