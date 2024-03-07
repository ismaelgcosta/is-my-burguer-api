package br.com.ismyburguer.pagamento.web.api.controller;

import br.com.ismyburguer.core.adapter.in.WebAdapter;
import br.com.ismyburguer.pagamento.adapter.interfaces.in.ConsultarPagamentoPorPedidoUseCase;
import br.com.ismyburguer.pagamento.web.api.converter.ConsultaPagamentoPedidoConverter;
import br.com.ismyburguer.pagamento.web.api.response.ConsultaPagamentoPedidoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Pedido", description = "Gerenciamento de Pedidos")
@WebAdapter
@RequestMapping("/pedidos")
public class ConsultarPagamentoPedidoAPI {

    private final ConsultarPagamentoPorPedidoUseCase consultarPagamentoPorPedidoUseCase;
    private final ConsultaPagamentoPedidoConverter consultaPagamentoPedidoConverter;

    public ConsultarPagamentoPedidoAPI(ConsultarPagamentoPorPedidoUseCase consultarPagamentoPorPedidoUseCase,
                                       ConsultaPagamentoPedidoConverter consultaPagamentoPedidoConverter) {
        this.consultarPagamentoPorPedidoUseCase = consultarPagamentoPorPedidoUseCase;
        this.consultaPagamentoPedidoConverter = consultaPagamentoPedidoConverter;
    }

    @Operation(description = "Consultar Pagamento")
    @GetMapping("/{pedidoId}/pagamento")
    public ConsultaPagamentoPedidoResponse obter(@Valid @UUID @PathVariable(name = "pedidoId") String pedidoId) {
        return consultaPagamentoPedidoConverter.convert(consultarPagamentoPorPedidoUseCase.consultar(pedidoId));
    }

}
