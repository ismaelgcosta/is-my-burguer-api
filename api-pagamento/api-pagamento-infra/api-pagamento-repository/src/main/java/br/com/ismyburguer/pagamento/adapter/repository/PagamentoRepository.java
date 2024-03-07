package br.com.ismyburguer.pagamento.adapter.repository;

import br.com.ismyburguer.pagamento.adapter.model.PagamentoModel;
import br.com.ismyburguer.core.adapter.out.PersistenceDriver;
import br.com.ismyburguer.pagamento.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceDriver
public interface PagamentoRepository extends JpaRepository<PagamentoModel, UUID> {

    Optional<PagamentoModel> findByPedidoId(UUID pedidoId);
}
