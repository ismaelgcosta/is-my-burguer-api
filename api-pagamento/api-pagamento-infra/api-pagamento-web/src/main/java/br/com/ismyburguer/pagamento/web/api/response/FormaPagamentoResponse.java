package br.com.ismyburguer.pagamento.web.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FormaPagamentoResponse {

    private String valor;
    private String descricao;
}
