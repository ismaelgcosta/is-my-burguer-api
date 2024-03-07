package br.com.ismyburguer.pedido.web.api.controller;


import br.com.ismyburguer.controlepedido.adapter.interfaces.in.PrepararControlePedidoUseCase;
import br.com.ismyburguer.controlepedido.entity.ControlePedido;
import br.com.ismyburguer.core.adapter.in.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Controle de Pedidos", description = "Controle de Pedidos")
@WebAdapter
@RequestMapping("/controle-pedidos")
public class PrepararControlePedidoAPI {
    private final PrepararControlePedidoUseCase useCase;

    public PrepararControlePedidoAPI(PrepararControlePedidoUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(description = "Preparar Pedido")
    @PutMapping("/{pedidoId}/em-preparacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void prepararPedido(
            @PathVariable @Valid @UUID(message = "O código do pedido informado está num formato inválido") String pedidoId
    ) {
        useCase.preparar(new ControlePedido.PedidoId(java.util.UUID.fromString(pedidoId)));
    }

}
