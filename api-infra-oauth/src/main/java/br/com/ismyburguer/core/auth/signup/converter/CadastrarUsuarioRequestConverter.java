package br.com.ismyburguer.core.auth.signup.converter;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.in.WebConverter;
import br.com.ismyburguer.core.auth.signup.request.CadastrarUsuarioRequest;
import br.com.ismyburguer.core.auth.signup.request.SignUpRequest;
import jakarta.validation.ConstraintViolationException;

import java.util.Optional;

@WebConverter
public class CadastrarUsuarioRequestConverter implements Converter<CadastrarUsuarioRequest, Cliente> {

    public Cliente convert(CadastrarUsuarioRequest source) {
        if(source == null) {
            throw new ConstraintViolationException("Não foi informado o corpo da requisição", null);
        }

        if(source != null) {
            source.validate();
        }

        return new Cliente(
                new Cliente.Nome(source.getNome()),
                new Cliente.Email(source.getEmail()),
                Optional.of(source.getCpf()).map(Cliente.CPF::new).orElse(null)
        );
    }
}
