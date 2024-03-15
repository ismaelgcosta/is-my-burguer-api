package br.com.ismyburguer.core.auth.signup.request;

import br.com.ismyburguer.core.validation.Validation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
public class CadastrarUsuarioRequest implements Validation {

    @NotBlank(message = "Informe o password")
    private String password;

    @NotBlank(message = "Informe o e-mail")
    @Email
    private String email;

    @NotBlank(message = "Informe o CPF")
    @CPF
    private String cpf;

    @NotBlank(message = "Informe o Nome")
    private String nome;

    private String sobrenome;

}
