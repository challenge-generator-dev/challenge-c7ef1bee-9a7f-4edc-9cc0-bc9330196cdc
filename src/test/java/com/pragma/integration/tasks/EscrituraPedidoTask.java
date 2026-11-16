// TODO(reto): archivo stubbeado; implementa los cuerpos.
package com.pragma.integration.tasks;

import com.pragma.integration.interactions.DynamoDBInteraction;
import com.pragma.integration.models.Pedido;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class EscrituraPedidoTask implements Task {

    private final Pedido pedido;

    public EscrituraPedidoTask(Pedido pedido) {
        this.pedido = pedido;
    }

    public static EscrituraPedidoTask conDatos(Pedido pedido) {
      throw new UnsupportedOperationException("TODO(reto): conDatos");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
      throw new UnsupportedOperationException("TODO(reto): performAs");
    }
}