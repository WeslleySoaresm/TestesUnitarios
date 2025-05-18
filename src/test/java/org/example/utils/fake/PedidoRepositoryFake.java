package org.example.utils.fake;

import org.example.model.Cliente;
import org.example.model.Pedido;
import org.example.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoRepositoryFake implements PedidoRepository{


    private final Map<String, Pedido> pedidos = new HashMap<>();
    @Override
    public Pedido salvar(Pedido pedido) {
        pedidos.put(pedido.getCodigo(), pedido);
        return pedido;

    }

    @Override
    public Pedido buscarPorCodigo(String codigo) {
        Pedido peido = pedidos.get(codigo);
        if (peido == null){
            throw new IllegalArgumentException("Pedido não encontrado");

        }
        return peido;
    }

    @Override
    public List<Pedido> buscarPorCliente(Cliente cliente) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido pedidos : pedidos.values()){
            if (pedidos.getCliente().equals(cliente)){
                resultado.add(pedidos);
            }
        }
        return resultado;
    }
}
