package it.besmartbeopen.gitlab;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author marco
 */
public class GitlabInterceptor implements RequestInterceptor {

  private static final String PRIVATE_TOKEN = "PRIVATE-TOKEN";

  private final String token;

  /**
   * @param token
   */
  public GitlabInterceptor(String token) {
    this.token = token;
  }

  @Override
  public void apply(RequestTemplate template) {
    template.header(PRIVATE_TOKEN, token);
  }
}
