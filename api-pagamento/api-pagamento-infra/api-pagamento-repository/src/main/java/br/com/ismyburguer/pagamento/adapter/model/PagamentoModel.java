package br.com.ismyburguer.pagamento.adapter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "pagamento")
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoModel {

    @Id
    private UUID pagamentoId = UUID.randomUUID();

    @Column(name = "pedido_id", columnDefinition = "uuid references pedido(pedido_id)")
    private UUID pedidoId;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private BigDecimal valorTotal;

    private String qrCode;

    public PagamentoModel(UUID pedidoId, StatusPagamento statusPagamento, TipoPagamento tipoPagamento, FormaPagamento formaPagamento, BigDecimal valorTotal) {
        this.pedidoId = pedidoId;
        this.statusPagamento = statusPagamento;
        this.tipoPagamento = tipoPagamento;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
    }

    public PagamentoModel(UUID pedidoId, StatusPagamento statusPagamento, TipoPagamento tipoPagamento, FormaPagamento formaPagamento, BigDecimal valorTotal, String qrCode) {
        this.pedidoId = pedidoId;
        this.statusPagamento = statusPagamento;
        this.tipoPagamento = tipoPagamento;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
        this.qrCode = qrCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PagamentoModel that)) return false;

        return new EqualsBuilder().append(getPagamentoId(), that.getPagamentoId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPagamentoId()).toHashCode();
    }
}
