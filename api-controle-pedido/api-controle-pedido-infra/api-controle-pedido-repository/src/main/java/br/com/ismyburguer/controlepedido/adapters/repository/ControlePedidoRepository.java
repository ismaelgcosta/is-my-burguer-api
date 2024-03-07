package br.com.ismyburguer.controlepedido.adapters.repository;

import br.com.ismyburguer.controlepedido.adapters.model.ControlePedidoModel;
import br.com.ismyburguer.controlepedido.adapters.model.StatusControlePedido;
import br.com.ismyburguer.core.adapter.out.PersistenceDriver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceDriver
public interface ControlePedidoRepository extends JpaRepository<ControlePedidoModel, UUID> {

    Optional<ControlePedidoModel> findByPedidoId(UUID pedidoId);
    List<ControlePedidoModel> findAllByStatusControlePedidoNot(StatusControlePedido statusControlePedido);

}
