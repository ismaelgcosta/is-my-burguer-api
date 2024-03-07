package br.com.ismyburguer.pagamento.web.api.response;

import br.com.ismyburguer.pagamento.entity.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ConsultaPagamentoPedidoResponse {

    private StatusPagamentoResponse statusPagamento;
    private FormaPagamentoResponse formaPagamento;
    private TipoPagamentoResponse tipoPagamento;

    @Setter
    private String qrCode;
}
