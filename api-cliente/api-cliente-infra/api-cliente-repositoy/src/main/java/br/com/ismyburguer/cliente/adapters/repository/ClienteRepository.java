package br.com.ismyburguer.cliente.adapters.repository;

import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.core.adapter.out.PersistenceDriver;
import org.apache.el.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@PersistenceDriver
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {
    Optional<ClienteModel> findByEmail(String email);

    Optional<ClienteModel> findByCpf(String cpf);

    Optional<ClienteModel> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByCpf(String cpf);
}
