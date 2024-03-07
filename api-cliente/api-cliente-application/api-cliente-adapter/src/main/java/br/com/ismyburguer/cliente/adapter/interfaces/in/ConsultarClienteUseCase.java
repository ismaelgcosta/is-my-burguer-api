package br.com.ismyburguer.cliente.adapter.interfaces.in;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.adapter.ExistsByIdUseCase;

public interface ConsultarClienteUseCase extends ExistsByIdUseCase<Cliente> {

    Cliente buscar(ConsultaCliente query);
    Cliente buscarPorId(ConsultaClientePorId query);

    record ConsultaCliente(String email) {

    }
    record ConsultaClientePorId(String clienteId) {

    }
}
