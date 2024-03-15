package br.com.ismyburguer.core.auth.signup;


import br.com.ismyburguer.core.auth.signup.request.SignUpRequest;
import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface LambdaOAuthSignUp {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /user/sign-up")
    Map<String, Object> signUp(SignUpRequest input);
}
