package br.com.ismyburguer.core.auth.clientcredentials;

import br.com.ismyburguer.core.auth.interceptor.OAuth2ClientCredentialsFeignInterceptor;
import feign.Feign;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiGatewayFeignClient {

    @Value("${aws.api-gateway}")
    private String apiGateway;

    private final OAuth2ClientCredentialsFeignInterceptor interceptor;

    public ApiGatewayFeignClient(OAuth2ClientCredentialsFeignInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public <T> T createClient(Class<T> apiType) {
        Feign.Builder builder = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder());

        if(!apiType.isAssignableFrom(ClientOAuthSignIn.class)) {
            builder.requestInterceptor(interceptor);
        }

        return builder
                .target(apiType, apiGateway);
    }
}
