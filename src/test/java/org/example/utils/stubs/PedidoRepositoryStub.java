package org.example.utils.stubs;

import org.example.model.Cliente;
import org.example.model.Pedido;
import org.example.repository.PedidoRepository;

import java.util.List;

public class PedidoRepositoryStub implements PedidoRepository {

    private Pedido pedidoStub;
    private  Pedido pedidoStubPadrao = new Pedido( new Cliente("Nome Padrão", true));
    public void setPedidoStub(Pedido pedido){
        this.pedidoStub =pedido;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        return pedido; //Compotamento padrão
    }

    @Override
    public Pedido buscarPorCodigo(String codigo) {
        return pedidoStub;
    }

    @Override
    public List<Pedido> buscarPorCliente(Cliente cliente) {
        return List.of(pedidoStub);// Simular o resultado.
    }
}
