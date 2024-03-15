package br.com.ismyburguer.core.auth.signin;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest implements Serializable {

    @NotBlank(message = "Informe o username")
    private String username;

    @NotBlank(message = "Informe o password")
    private String password;

}
