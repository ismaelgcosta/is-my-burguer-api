package br.com.ismyburguer.core.auth.usecase.impl.client;

import br.com.ismyburguer.core.auth.gateway.out.LambdaClientOAuthSignIn;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiGatewayFeignClient implements br.com.ismyburguer.core.auth.gateway.out.ApiGatewayFeignClient {

    @Value("${aws.api-gateway}")
    private String apiGateway;

    private final OAuth2ClientCredentialsFeignInterceptor interceptor;

    public ApiGatewayFeignClient(OAuth2ClientCredentialsFeignInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public <T> T createClient(Class<T> apiType) {
        Feign.Builder builder = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder());

        if(!apiType.isAssignableFrom(LambdaClientOAuthSignIn.class)) {
            builder.requestInterceptor(interceptor);
        }

        return builder
                .target(apiType, apiGateway);
    }
}
