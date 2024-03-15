package br.com.ismyburguer.core.auth.interceptor;

import br.com.ismyburguer.core.auth.clientcredentials.ClientCredentialsRequest;
import br.com.ismyburguer.core.auth.clientcredentials.ClientOAuthSignIn;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

@Component
public class OAuth2ClientCredentialsFeignInterceptor implements RequestInterceptor {

    @Value("${aws.api-gateway}")
    private String apiGateway;

    @Value("${aws.cognito.client-id}")
    private String clientId;

    @Value("${aws.cognito.client-secret}")
    private String clientSecret;

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER_TOKEN = "Bearer {0}";

    public void apply(RequestTemplate template) {
        Map<String, Object> token = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(ClientOAuthSignIn.class, apiGateway)
                .token(new ClientCredentialsRequest(clientId, clientSecret));

        template.header(AUTHORIZATION, MessageFormat.format(BEARER_TOKEN, token.get("access_token")));
    }
}