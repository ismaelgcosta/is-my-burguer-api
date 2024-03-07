package br.com.ismyburguer.cliente.gateway.out;

import br.com.ismyburguer.cliente.entity.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ConsultarClienteRepository {
    Optional<Cliente> obterPeloClienteId(UUID clienteId);
    Optional<Cliente> obterPeloEmail(String email);
    boolean existsById(UUID clienteId);
}
