# language: es
Característica: Persistencia de Pedidos en DynamoDB
  Como analista de calidad de software
  Quiero automatizar pruebas de integración para validar la persistencia de pedidos
  Para asegurar que los pedidos se lean y escriban correctamente en DynamoDB

  # Fase 2: Desarrollo de Pruebas de Escritura
  Escenario: Escritura exitosa de un pedido en DynamoDB
    Dado que el sistema de procesamiento de órdenes genera un nuevo pedido
    Cuando el servicio de almacenamiento persiste el pedido en DynamoDB
    Entonces el pedido debe ser recoverable desde la tabla de pedidos

  Escenario: Escritura de pedido con todos los campos obligatorios
    Dado que el sistema de procesamiento de órdenes genera un pedido con los siguientes datos:
      | campo          | valor                           |
      | idPedido       | PED-2024-001                   |
      | clienteId      | CLI-12345                      |
      | producto       | Laptop Gaming Pro               |
      | cantidad       | 2                               |
      | precioUnitario | 1500.00                        |
      | estado         | PENDIENTE                      |
      | fechaCreacion  | 2024-01-15T10:30:00Z           |
    Cuando el servicio de almacenamiento persiste el pedido en DynamoDB
    Entonces la consulta del pedido por ID debe retornar los mismos datos

  # Fase 3: Desarrollo de Pruebas de Lectura
  Escenario: Lectura exitosa de un pedido existente
    Dado que existe un pedido persistido en DynamoDB con ID "PED-2024-002"
    Cuando consulto el pedido por su identificador
    Entonces debo obtener los datos completos del pedido

  Escenario: Lectura de pedido inexistente
    Dado que no existe ningún pedido con ID "PED-INEXISTENTE-999"
    Cuando consulto el pedido por su identificador
    Entonces debo recibir una respuesta vacía o null

  Escenario: Verificar que los pedidos persistidos mantienen su estado
    Dado que el sistema persiste múltiples pedidos con diferentes estados:
      | idPedido       | estado     |
      | PED-ESTADO-001 | PENDIENTE  |
      | PED-ESTADO-002 | PROCESANDO |
      | PED-ESTADO-003 | COMPLETADO |
      | PED-ESTADO-004 | CANCELADO  |
    Cuando consulto cada pedido por su identificador
    Entonces cada pedido debe mantener su estado original

  # Escenarios de validación de integridad
  Escenario: Verificar integridad de datos numéricos en pedido persistido
    Dado que el sistema persiste un pedido con cantidad "5" y precio unitario "299.99"
    Cuando leo el pedido desde DynamoDB
    Entonces los valores numéricos deben coincidir exactamente sin pérdida de precisión

  Escenario: Verificar persistencia de fecha de creación
    Dado que el sistema persiste un pedido con fecha de creación "2024-06-20T14:45:30Z"
    Cuando leo el pedido desde DynamoDB
    Then la fecha de creación debe ser exactamente "2024-06-20T14:45:30Z"

  # Escenarios de manejo de errores
  Escenario: Fallo al persistir pedido con datos inválidos
    Dado que el sistema intenta persistir un pedido con ID vacío
    Cuando el servicio de almacenamiento procesa la solicitud
    Then debe lanzar una excepción de validación o error de DynamoDB

  Escenario: Verificar que no se persisten pedidos duplicados con mismo ID
    Dado que ya existe un pedido con ID "PED-DUP-001" en DynamoDB
    Cuando intento persistir otro pedido con el mismo identificador
    Then DynamoDB debe reemplazar el registro existente (operación put)
    Y al consultar debo obtener únicamente el último pedido persistido