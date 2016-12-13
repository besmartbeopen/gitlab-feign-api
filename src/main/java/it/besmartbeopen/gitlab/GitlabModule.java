package it.besmartbeopen.gitlab;
//package helpers.gitlab;
//
//import com.google.inject.Binder;
//import com.google.inject.Module;
//import com.google.inject.Provides;
//import com.google.inject.name.Named;
//
//import helpers.gitlab.pagination.PaginationDecoder;
//
//import injection.AutoRegister;
//
//import feign.Feign;
//import feign.jackson.JacksonDecoder;
//import feign.jackson.JacksonEncoder;
//import feign.okhttp.OkHttpClient;
//import feign.slf4j.Slf4jLogger;
//
//@AutoRegister
//public class GitlabModule implements Module {
//
//  @Provides
//  public Gitlab gitlabService(@Named("issues.url") String url,
//      @Named("issues.token") String token) {
//
////    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
////    logging.setLevel(Level.BODY);
//    return Feign.builder()
//        .decoder(new PaginationDecoder(new JacksonDecoder()))
//        .encoder(new UploadFormEncoder(new JacksonEncoder()))
//        .client(new OkHttpClient(/* new okhttp3.OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build() */))
//        .requestInterceptor(new GitlabInterceptor(token))
//        .target(Gitlab.class, url);
//  }
//
//  @Override
//  public void configure(Binder binder) {
//  }
//}
