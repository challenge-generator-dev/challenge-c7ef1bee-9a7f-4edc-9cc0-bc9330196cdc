package com.pragma.integration.tasks;

import com.pragma.integration.interactions.DynamoDBInteraction;
import com.pragma.integration.models.Pedido;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.serenitybdd.screenplay.questions.Value;
import org.hamcrest.Matcher;

import java.util.Map;

public class LecturaPedidoTask implements Task {

    private final String idPedido;
    private Pedido resultado;

    public LecturaPedidoTask(String idPedido) {
        this.idPedido = idPedido;
    }

    public static LecturaPedidoTask conId(String idPedido) {
        return new LecturaPedidoTask(idPedido);
    }

    public static LecturaPedidoTask conId(Long idPedido) {
        return new LecturaPedidoTask(String.valueOf(idPedido));
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            DynamoDBInteraction.leer(idPedido)
        );
    }

    public Pedido getResultado() {
        return resultado;
    }

    public static class RespuestaLectura {
        private final Map<String, Object> datos;

        public RespuestaLectura(Map<String, Object> datos) {
            this.datos = datos;
        }

        public boolean existe() {
            return datos != null && !datos.isEmpty();
        }

        public String getIdPedido() {
            return datos != null ? (String) datos.get("idPedido") : null;
        }

        public String getEstado() {
            return datos != null ? (String) datos.get("estado") : null;
        }

        public Double getTotal() {
            if (datos == null) return null;
            Object total = datos.get("total");
            if (total instanceof Number) {
                return ((Number) total).doubleValue();
            }
            return total != null ? Double.parseDouble(total.toString()) : null;
        }

        public String getClienteId() {
            return datos != null ? (String) datos.get("clienteId") : null;
        }
    }
}