package br.com.ismyburguer.core.auth.confirmsignup.usecase;

import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.auth.confirmsignup.request.ConfirmSignUpRequest;
import br.com.ismyburguer.core.exception.BusinessException;
import br.com.ismyburguer.core.usecase.UseCase;
import jakarta.validation.constraints.NotBlank;

@UseCase
public class ConfirmSignUpUseCase {
    private final ConsultarClienteUseCase consultarClienteUseCase;
    private final OAuthConfirmSignUpUseCase oAuthConfirmSignUpUseCase;

    public ConfirmSignUpUseCase(ConsultarClienteUseCase consultarClienteUseCase, OAuthConfirmSignUpUseCase oAuthConfirmSignUpUseCase) {
        this.consultarClienteUseCase = consultarClienteUseCase;
        this.oAuthConfirmSignUpUseCase = oAuthConfirmSignUpUseCase;
    }

    public void confirmarNovoUsuario(@NotBlank(message = "Informe o cpf") String cpf,
                                     @NotBlank(message = "Informe o password") String password,
                                     @NotBlank(message = "Informe o código de verificação") String code) {
        Cliente cliente = consultarClienteUseCase.buscarPorCpf(new ConsultarClienteUseCase.ConsultaClientePorCpf(cpf));
        String username = cliente.getUsername().map(Cliente.Username::getUsername)
                .orElseThrow(() -> new BusinessException("Não foi encontrado um Username associado ao Cliente informado"));
        oAuthConfirmSignUpUseCase.confirmSignUp(new ConfirmSignUpRequest(
                username,
                password,
                code,
                cpf.replaceAll("[^\\d]", "")
        ));
    }
}
