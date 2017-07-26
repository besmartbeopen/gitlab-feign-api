package it.besmartbeopen.gitlab;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.name.Named;


import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import it.besmartbeopen.gitlab.pagination.PaginationDecoder;

/**
 * @author marco
 *
 */
public class GitlabModule implements Module {

  @Provides
  public Gitlab gitlabService(@Named("issues.url") String url,
      @Named("issues.token") String token) {

    return Feign.builder()
        .decoder(new PaginationDecoder(new JacksonDecoder()))
        .encoder(new UploadFormEncoder(new JacksonEncoder()))
        .client(new OkHttpClient(/* new okhttp3.OkHttpClient.Builder()
            .addInterceptor(logging)
            .build() */))
        .requestInterceptor(new GitlabInterceptor(token))
        .target(Gitlab.class, url);
  }

  @Override
  public void configure(Binder binder) {
  }
}
