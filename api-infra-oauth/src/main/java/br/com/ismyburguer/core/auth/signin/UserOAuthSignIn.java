package br.com.ismyburguer.core.auth.signin;


import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface UserOAuthSignIn {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /user/token")
    Map<String, Object> signin(AuthenticationRequest input);
}
