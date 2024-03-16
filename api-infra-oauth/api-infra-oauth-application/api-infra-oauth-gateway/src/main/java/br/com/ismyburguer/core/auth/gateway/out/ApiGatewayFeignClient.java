package br.com.ismyburguer.core.auth.gateway.out;

public interface ApiGatewayFeignClient {
    <T> T createClient(Class<T> apiType);
}
