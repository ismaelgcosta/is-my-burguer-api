package br.com.ismyburguer.core.auth.confirmsignup.usecase;

import br.com.ismyburguer.core.auth.clientcredentials.ApiGatewayFeignClient;
import br.com.ismyburguer.core.auth.confirmsignup.LambdaOAuthConfirmSignUp;
import br.com.ismyburguer.core.auth.confirmsignup.request.ConfirmSignUpRequest;
import br.com.ismyburguer.core.usecase.UseCase;

import java.util.Map;

@UseCase
public class OAuthConfirmSignUpUseCaseImpl implements OAuthConfirmSignUpUseCase {
    private final ApiGatewayFeignClient client;

    public OAuthConfirmSignUpUseCaseImpl(ApiGatewayFeignClient client) {
        this.client = client;
    }

    @Override
    public Map<String, Object> confirmSignUp(ConfirmSignUpRequest input) {
        return client.createClient(LambdaOAuthConfirmSignUp.class).confirmSignUp(input);
    }
}
