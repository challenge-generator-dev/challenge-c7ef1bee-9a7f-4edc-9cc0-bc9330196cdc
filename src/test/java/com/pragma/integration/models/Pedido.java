package com.pragma.integration.models;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pedido {

    private String id;
    private String clienteId;
    private List<Producto> productos;
    private Double total;
    private String estado;
    private Instant fechaCreacion;
    private Instant fechaActualizacion;

    public Pedido() {
    }

    public Pedido(String id, String clienteId, List<Producto> productos, Double total, String estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.productos = productos;
        this.total = total;
        this.estado = estado;
        this.fechaCreacion = Instant.now();
        this.fechaActualizacion = Instant.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        this.fechaActualizacion = Instant.now();
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Map<String, software.amazon.awssdk.services.dynamodb.model.AttributeValue> toDynamoDBItem() {
        Map<String, software.amazon.awssdk.services.dynamodb.model.AttributeValue> item = new java.util.HashMap<>();
        
        item.put("id", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s(id).build());
        item.put("clienteId", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s(clienteId).build());
        item.put("total", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().n(total.toString()).build());
        item.put("estado", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s(estado).build());
        item.put("fechaCreacion", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s(fechaCreacion.toString()).build());
        item.put("fechaActualizacion", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s(fechaActualizacion.toString()).build());
        
        if (productos != null && !productos.isEmpty()) {
            List<Map<String, software.amazon.awssdk.services.dynamodb.model.AttributeValue>> productosList = productos.stream()
                    .map(Producto::toDynamoDBItem)
                    .collect(Collectors.toList());
            item.put("productos", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().l(productosList).build());
        }
        
        return item;
    }

    public static Pedido fromDynamoDBItem(Map<String, software.amazon.awssdk.services.dynamodb.model.AttributeValue> item) {
        if (item == null || item.isEmpty()) {
            return null;
        }

        Pedido pedido = new Pedido();
        pedido.setId(item.get("id").s());
        pedido.setClienteId(item.get("clienteId").s());
        pedido.setTotal(Double.parseDouble(item.get("total").n()));
        pedido.setEstado(item.get("estado").s());
        
        if (item.containsKey("fechaCreacion") && item.get("fechaCreacion").s() != null) {
            pedido.setFechaCreacion(Instant.parse(item.get("fechaCreacion").s()));
        }
        if (item.containsKey("fechaActualizacion") && item.get("fechaActualizacion").s() != null) {
            pedido.setFechaActualizacion(Instant.parse(item.get("fechaActualizacion").s()));
        }

        if (item.containsKey("productos") && item.get("productos").l() != null) {
            List<Producto> productos = item.get("productos").l().stream()
                    .map(Producto::fromDynamoDBItem)
                    .collect(Collectors.toList());
            pedido.setProductos(productos);
        }

        return pedido;
    }

    public static class Producto {
        private String productoId;
        private String nombre;
        private Integer cantidad;
        private Double precioUnitario;

        public Producto() {
        }

        public Producto(String productoId, String nombre, Integer cantidad, Double precioUnitario) {
            this.productoId = productoId;
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
        }

        public String getProductoId() {
            return productoId;
        }

        public void setProductoId(String productoId) {
            this.productoId = productoId;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        public Double getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(Double precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public Map<String, software.amazon.awssdk.services.dynamodb.model.AttributeValue> toDynamoDBItem() {
            Map<String, software.amazon.awssdk.services.dynamodb.model.AttributeValue> productoMap = new java.util.HashMap<>();
            productoMap.put("productoId", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s(productoId).build());
            productoMap.put("nombre", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s(nombre).build());
            productoMap.put("cantidad", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().n(cantidad.toString()).build());
            productoMap.put("precioUnitario", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().n(precioUnitario.toString()).build());
            return productoMap;
        }

        public static Producto fromDynamoDBItem(software.amazon.awssdk.services.dynamodb.model.AttributeValue attributeValue) {
            if (attributeValue == null || !attributeValue.m().containsKey("productoId")) {
                return null;
            }

            Map<String, software.amazon.awssdk.services.dynamodb.model.AttributeValue> productoMap = attributeValue.m();
            return new Producto(
                    productoMap.get("productoId").s(),
                    productoMap.get("nombre").s(),
                    Integer.parseInt(productoMap.get("cantidad").n()),
                    Double.parseDouble(productoMap.get("precioUnitario").n())
            );
        }
    }
}