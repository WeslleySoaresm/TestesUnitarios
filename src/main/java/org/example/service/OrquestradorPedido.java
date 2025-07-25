package org.example.service;

import org.example.enums.FormaPagamento;
import org.example.enums.StatusPedido;
import org.example.model.CarrinhoCompras;
import org.example.model.Cliente;
import org.example.model.ItemPedido;
import org.example.model.Pedido;

import java.time.LocalDateTime;

public class OrquestradorPedido {

    private final ValidadorPedidoService validador;
    private final EnviarEmailService emailService;

    public OrquestradorPedido(ValidadorPedidoService validador, EnviarEmailService emailService) {
        this.validador = validador;
        this.emailService = emailService;
    }

    public Pedido processarPedido(Cliente cliente, CarrinhoCompras carrinho, FormaPagamento formaPagamento) {
        validador.validarCliente(cliente);
        validador.validarCliente(cliente);
        validador.validarCarrinho(carrinho);

        Pedido pedido = new Pedido(cliente);
        for (ItemPedido item : carrinho.getItens()) {
            validador.validarEstoque(item);
            pedido.adicionarItem(item);
            item.getProduto().baixarEstoque(item.getQuantidade());
        }

        pedido.definirEnderecoEntrega(cliente.getEnderecos().getFirst());
        pedido.realizarPagamento(formaPagamento);

        if (!pedido.getPagamento().isAprovado()) {
            throw new IllegalStateException("Pagamento não aprovado.");
        }

        pedido.atualizarStatus(StatusPedido.EM_PREPARACAO);
        pedido.setDataAtualizacao(LocalDateTime.now());

        emailService.enviarConfirmacao(cliente.getEmail(), pedido);

        return pedido;
    }
}
