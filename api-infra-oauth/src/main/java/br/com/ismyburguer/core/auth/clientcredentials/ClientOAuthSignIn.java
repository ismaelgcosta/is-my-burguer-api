package br.com.ismyburguer.core.auth.clientcredentials;


import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface ClientOAuthSignIn {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /auth/token")
    Map<String, Object> token(ClientCredentialsRequest input);
}
