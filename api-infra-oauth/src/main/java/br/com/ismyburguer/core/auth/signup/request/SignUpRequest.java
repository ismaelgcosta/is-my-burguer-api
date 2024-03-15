package br.com.ismyburguer.core.auth.signup.request;

import br.com.ismyburguer.core.validation.Validation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest implements Validation {

    private String username;
    private String password;
    private String email;
    private String cpf;
    private String name;
}
