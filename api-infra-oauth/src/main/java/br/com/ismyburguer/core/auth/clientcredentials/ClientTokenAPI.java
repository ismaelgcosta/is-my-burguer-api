package br.com.ismyburguer.core.auth.clientcredentials;

import br.com.ismyburguer.core.adapter.in.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Tag(name = "Autenticação", description = "Autenticação do Usuário")
@WebAdapter
@RequestMapping("/auth")
public class ClientTokenAPI {
    private final ApiGatewayFeignClient client;
    public ClientTokenAPI(ApiGatewayFeignClient client) {
        this.client = client;
    }

    @Operation(method = "Gerar Token", description = "Gerar Token para Client")
    @PostMapping("/token")
    public Map<String, Object> login(@Valid @RequestBody ClientCredentialsRequest request) {
        return client.createClient(ClientOAuthSignIn.class).token(request);
    }

}