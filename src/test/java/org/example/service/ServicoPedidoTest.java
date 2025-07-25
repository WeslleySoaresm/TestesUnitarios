package org.example.service;
import org.example.enums.StatusPedido;
import org.example.model.Cliente;
import org.example.model.Pedido;
import org.example.repository.PedidoRepository;
import org.example.utils.fake.PedidoRepositoryFake;
import org.example.utils.stubs.PedidoRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import  org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Provider;

import static org.junit.jupiter.api.Assertions.*;

class ServicoPedidoTest {
private Cliente dummyCliente;
    @BeforeEach
    void setup(){
        dummyCliente = new Cliente("cleinte Dummy", true);
    }
    @Test
    void deveCriarPedidoComClienteAtivo(){
        //Dummy: só para satisfazer o parametro, sem lógica extra.

        Cliente dummyCliente = new Cliente("cleinte Dummy", true);

        PedidoRepositoryFake repositoryFake = new PedidoRepositoryFake();
        ServicoPedido servicoPedido = new ServicoPedido(repositoryFake);
        Pedido pedido = servicoPedido.criarPedido(dummyCliente);

        assertNotNull(pedido);
        assertEquals(dummyCliente, pedido.getCliente());
        assertNotNull(pedido.getCodigo());
    }

    @Test
    void deveCriarPersistyirPedidoComFakeRespository(){
        // Fake injetado no lugar de um banco real
        PedidoRepositoryFake repositoryFake = new PedidoRepositoryFake();
        ServicoPedido service = new ServicoPedido(repositoryFake);

        Pedido pedidoCriado = service.criarPedido(dummyCliente);

        Pedido pedidoBuscado = service.buscarPedido(pedidoCriado.getCodigo());
        assertEquals(pedidoCriado, pedidoBuscado);
        assertEquals(dummyCliente, pedidoBuscado.getCliente());
    }


    @Test
    void deveAtulizarStatusPedidoUsandoStub(){
        Pedido pedido = new Pedido(dummyCliente);
        pedido.atualizarStatus(StatusPedido.CRIADO);

        PedidoRepositoryStub stub = new PedidoRepositoryStub();
        stub.setPedidoStub(pedido);
        ServicoPedido servicoPedido = new ServicoPedido(stub);

        servicoPedido.atualizarStatusPedido(pedido.getCodigo(), StatusPedido.ENVIADO);

        assertEquals(StatusPedido.ENVIADO, pedido.getStatus());
    }


    @Test
    void deveChamarSalvarAoActulizarStatus(){
        Pedido pedido = new Pedido(dummyCliente);
        PedidoRepositoryFake repositoryFake =new PedidoRepositoryFake();
        repositoryFake.salvar(pedido);

        PedidoRepository spy = Mockito.spy(repositoryFake);
        ServicoPedido servicoPedido = new ServicoPedido(spy);

        servicoPedido.atualizarStatusPedido(pedido.getCodigo(), StatusPedido.ENVIADO);

        Mockito.verify(spy).salvar(pedido);
        Mockito.verify(spy).buscarPorCodigo(pedido.getCodigo());
    }
}