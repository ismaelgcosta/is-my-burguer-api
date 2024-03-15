package br.com.ismyburguer.core.auth.signin;

import br.com.ismyburguer.core.adapter.in.WebAdapter;
import br.com.ismyburguer.core.auth.clientcredentials.ApiGatewayFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Tag(name = "Autenticação", description = "Autenticação do Usuário")
@WebAdapter
@RequestMapping("/user")
public class UserTokenAPI {
    private final ApiGatewayFeignClient client;
    public UserTokenAPI(ApiGatewayFeignClient client) {
        this.client = client;
    }

    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"), method = "Gerar Token", description = "Gerar Token")
    @PostMapping("/token")
    public Map<String, Object> login(@Valid @RequestBody AuthenticationRequest request) {
        return client.createClient(UserOAuthSignIn.class).signin(request);
    }

}