package br.com.ismyburguer.cliente.web.api.request;

import br.com.ismyburguer.core.validation.Validation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Base64Utils;

import java.util.Optional;

@Getter
@Setter
public class CriarClienteRequest implements Validation {

    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
    public Optional<String> getCpf() {
        return Optional.ofNullable(cpf);
    }

}
