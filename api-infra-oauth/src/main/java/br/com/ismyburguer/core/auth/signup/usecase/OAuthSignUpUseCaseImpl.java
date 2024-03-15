package br.com.ismyburguer.core.auth.signup.usecase;

import br.com.ismyburguer.core.auth.clientcredentials.ApiGatewayFeignClient;
import br.com.ismyburguer.core.auth.signup.LambdaOAuthSignUp;
import br.com.ismyburguer.core.auth.signup.request.SignUpRequest;
import br.com.ismyburguer.core.usecase.UseCase;

import java.util.Map;

@UseCase
public class OAuthSignUpUseCaseImpl implements OAuthSignUpUseCase {
    private final ApiGatewayFeignClient client;

    public OAuthSignUpUseCaseImpl(ApiGatewayFeignClient client) {
        this.client = client;
    }

    @Override
    public Map<String, Object> signUp(SignUpRequest input) {
        return client.createClient(LambdaOAuthSignUp.class).signUp(input);
    }
}
