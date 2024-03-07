package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.ConsultarClienteRepository;
import br.com.ismyburguer.core.exception.EntityNotFoundException;
import br.com.ismyburguer.core.usecase.UseCase;

import java.util.UUID;

@UseCase
public class ConsultarClienteUseCaseImpl implements ConsultarClienteUseCase {

    private final ConsultarClienteRepository repository;

    public ConsultarClienteUseCaseImpl(ConsultarClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente buscar(ConsultaCliente query) {
        return repository.obterPeloEmail(query.email())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não foi encontrado"));
    }
    @Override
    public Cliente buscarPorId(ConsultaClientePorId query) {
        return repository.obterPeloClienteId(UUID.fromString(query.clienteId()))
                .orElseThrow(() -> new EntityNotFoundException("Cliente não foi encontrado"));
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}
