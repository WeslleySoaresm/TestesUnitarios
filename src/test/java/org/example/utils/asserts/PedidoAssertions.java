package org.example.utils.asserts;

import org.example.enums.StatusPedido;
import org.example.model.Pedido;
import org.junit.jupiter.api.Assertions;

public class PedidoAssertions {
    public static void asserStatus(Pedido pedido, StatusPedido esperado){
        Assertions.assertEquals(esperado, pedido.getStatus(), () -> String.format("Esperava status <%s>, mas era <%a>", esperado, pedido.getStatus()));
    }

    public static void asserTotal(Pedido pedido, double esperado){
        Assertions.assertEquals(esperado, pedido.getTotal(), () -> String.format(" Esperava total %.2f, mas era %.2f", esperado, pedido.getStatus()));
    }

    public static void assertPedido(Pedido pedido, StatusPedido  statusEsperado, double totalEsperado){
        Assertions.assertAll("Validando pedido",
        ()->asserStatus(pedido, statusEsperado),
                ()-> asserTotal(pedido, totalEsperado));

    }


}
