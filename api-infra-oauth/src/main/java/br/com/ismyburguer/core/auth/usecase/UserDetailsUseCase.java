package br.com.ismyburguer.core.auth.usecase;

import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.usecase.UseCase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@UseCase
public class UserDetailsUseCase implements UserDetailsService {
    private final ConsultarClienteUseCase consultarClienteUseCase;

    public UserDetailsUseCase(ConsultarClienteUseCase consultarClienteUseCase) {
        this.consultarClienteUseCase = consultarClienteUseCase;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = consultarClienteUseCase.buscarPorUsername(new ConsultarClienteUseCase.ConsultaClientePorUsername(username));
        return org.springframework.security.core.userdetails.User.builder()
                .username(cliente.getEmail().getEndereco())
                .build();
    }
}