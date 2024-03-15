package br.com.ismyburguer.core.auth.confirmsignup;


import br.com.ismyburguer.core.auth.confirmsignup.request.ConfirmSignUpRequest;
import br.com.ismyburguer.core.auth.signup.request.SignUpRequest;
import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface LambdaOAuthConfirmSignUp {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /user/sign-up/confirm")
    Map<String, Object> confirmSignUp(ConfirmSignUpRequest input);
}
