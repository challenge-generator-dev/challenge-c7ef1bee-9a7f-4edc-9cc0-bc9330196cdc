package com.pragma.integration.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.hamcrest.Matcher;

import java.util.Map;

public class PedidoFuePersistidoQuestion implements Question<Boolean> {

    private final String idPedido;
    private final Matcher<? super Object> matcher;

    public PedidoFuePersistidoQuestion(String idPedido) {
        this.idPedido = idPedido;
        this.matcher = null;
    }

    public static PedidoFuePersistidoQuestion enDynamoDB(String idPedido) {
        return new PedidoFuePersistidoQuestion(idPedido);
    }

    public static PedidoFuePersistidoQuestion conEstado(String idPedido, String estado) {
        return new PedidoFuePersistidoQuestion(idPedido) {
            @Override
            public Boolean answeredBy(Actor actor) {
                Map<String, Object> response = LastResponse.received().answeredBy(actor);
                if (response == null || response.isEmpty()) {
                    return false;
                }
                String estadoActual = (String) response.get("estado");
                return estado.equals(estadoActual);
            }
        };
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Map<String, Object> response = LastResponse.received().answeredBy(actor);
        
        if (response == null || response.isEmpty()) {
            return false;
        }

        String idRecibido = (String) response.get("idPedido");
        if (idRecibido == null) {
            return false;
        }

        return idRecibido.equals(idPedido);
    }

    public static class Builder {
        private String idPedido;
        private String estadoEsperado;
        private Double totalMinimo;
        private Double totalMaximo;

        public Builder conId(String idPedido) {
            this.idPedido = idPedido;
            return this;
        }

        public Builder conEstado(String estado) {
            this.estadoEsperado = estado;
            return this;
        }

        public Builder conTotalEntre(Double min, Double max) {
            this.totalMinimo = min;
            this.totalMaximo = max;
            return this;
        }

        public PedidoFuePersistidoQuestion build() {
            return new PedidoFuePersistidoQuestion(idPedido) {
                @Override
                public Boolean answeredBy(Actor actor) {
                    Map<String, Object> response = LastResponse.received().answeredBy(actor);
                    
                    if (response == null || response.isEmpty()) {
                        return false;
                    }

                    String idRecibido = (String) response.get("idPedido");
                    if (idRecibido == null || !idRecibido.equals(idPedido)) {
                        return false;
                    }

                    if (estadoEsperado != null) {
                        String estadoActual = (String) response.get("estado");
                        if (!estadoEsperado.equals(estadoActual)) {
                            return false;
                        }
                    }

                    if (totalMinimo != null || totalMaximo != null) {
                        Object totalObj = response.get("total");
                        if (totalObj == null) {
                            return false;
                        }
                        double total = totalObj instanceof Number 
                            ? ((Number) totalObj).doubleValue() 
                            : Double.parseDouble(totalObj.toString());
                        
                        if (totalMinimo != null && total < totalMinimo) {
                            return false;
                        }
                        if (totalMaximo != null && total > totalMaximo) {
                            return false;
                        }
                    }

                    return true;
                }
            };
        }
    }

    public static Builder verificar() {
        return new Builder();
    }
}