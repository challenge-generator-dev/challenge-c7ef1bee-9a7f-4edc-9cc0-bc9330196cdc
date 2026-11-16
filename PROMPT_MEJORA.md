# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener el proyecto completo y arrancable. Guarda los archivos que te devuelva en la
carpeta del repo, respetando exactamente las rutas.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/pragma/integration/config/DynamoDBConfig.java` — La fase "Configuración del Entorno de Pruebas" se implementa aqui: este archivo es el ejercicio.
- `src/test/java/com/pragma/integration/tasks/EscrituraPedidoTask.java` — La fase "Desarrollo de Pruebas de Escritura" se implementa aqui: este archivo es el ejercicio.

## Mapa de componentes

El repo tiene una carpeta por componente. Cada uno se instala y verifica dentro de la suya.

| Componente | Carpeta | Rol | Capa | Track | Tecnologias |
|---|---|---|---|---|---|
| `app` | (raiz) | practice, chapter | testing | Automatizador | - |
| `infra-local` | (raiz) | support | infra | Compose local | DynamoDB |

## Orden de arranque

1. `docker compose up -d` (en la raiz) — levanta la infraestructura local.
2. `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` — componente `app` (practice, chapter).

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **XSteps.java** — Sin los step definitions, los pasos Gherkin no tienen implementacion y Cucumber falla con "step undefined".

### Imports sin uso — no rompen la compilacion

Limpieza opcional: el proyecto compila igual con ellos:

- `src/test/java/com/pragma/integration/tasks/EscrituraPedidoTask.java` — `DynamoDBInteraction`

## Como saber que terminaste

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología Java Selenium, Advanced L2

### Brecha de conocimiento
Automatizar pruebas de integracion sobre una tabla DynamoDB

### Misión / candidato
Cubrir el flujo de persistencia de pedidos

### Reto
- Tema: Automatizacion de pruebas de integracion contra DynamoDB
- Seniority: advanced-l2
- Tipo: practical
- Título: Automatización de Pruebas de Integración con DynamoDB
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del Entorno de Pruebas — objetivo: Configurar el entorno de pruebas para ejecutar pruebas de integración contra DynamoDB. — entregable (NO resolver): Proyecto configurado con las dependencias y conexión a DynamoDB lista para pruebas.
- Fase 2: Desarrollo de Pruebas de Escritura — objetivo: Desarrollar pruebas automatizadas para verificar la escritura de pedidos en DynamoDB. — entregable (NO resolver): Prueba automatizada que verifica la escritura de un pedido en DynamoDB.
- Fase 3: Desarrollo de Pruebas de Lectura — objetivo: Desarrollar pruebas automatizadas para verificar la lectura de pedidos desde DynamoDB. — entregable (NO resolver): Prueba automatizada que verifica la lectura de un pedido desde DynamoDB.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (entrega de archivos).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Entrega de archivos
Entregá cada archivo con su ruta completa, respetando exactamente la estructura de rutas
indicada por los marcadores: un bloque por archivo, precedido por su ruta. No generes ZIPs
ni archivos adjuntos.
La entrega debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: Entregá los archivos completos al finalizar. No preguntes si el usuario
quiere recibirlos. Simplemente entregalos; No debes desplegar en el chat el resumen de lo que arreglaste, solo entregá los archivos.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del último archivo entregado
No preguntes si el usuario quiere los archivos: simplemente entregalos siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.13</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>reto</artifactId>
    <version>1.0.0</version>
    <name>reto</name>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>8.1.0</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>8.1.0</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <version>3.9.8</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
            <version>3.9.8</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay</artifactId>
            <version>3.9.8</version>
        </dependency>
        <dependency>
            <groupId>org.awaitility</groupId>
            <artifactId>awaitility</artifactId>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>aws-core</artifactId>
            <version>2.59.1</version>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>dynamodb</artifactId>
            <version>2.59.1</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay-rest</artifactId>
            <version>3.9.8</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>dynamodb-enhanced</artifactId>
            <version>2.59.1</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/example/reto/Application.java ===
package com.example.reto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// === ARCHIVO: src/test/java/resources/features/persistencia_pedidos.feature ===
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

// === ARCHIVO: src/test/java/com/pragma/integration/runners/RunCucumberTest.java ===
package com.pragma.integration.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.pragma.integration.steps",
        "com.pragma.integration.tasks",
        "com.pragma.integration.questions",
        "com.pragma.integration.interactions"
    },
    plugin = {
        "pretty",
        "html:target/serenity-reports/serenity-html-report.html",
        "json:target/serenity-reports/serenity-json-report.json",
        "junit:target/serenity-reports/serenity-junit-report.xml"
    },
    tags = "@integration",
    dryRun = false,
    strict = true
)
public class RunCucumberTest {
}

// === ARCHIVO: src/test/java/com/pragma/integration/tasks/EscrituraPedidoTask.java ===
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

// === ARCHIVO: src/test/java/com/pragma/integration/tasks/LecturaPedidoTask.java ===
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

// === ARCHIVO: src/test/java/com/pragma/integration/questions/PedidoFuePersistidoQuestion.java ===
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

// === ARCHIVO: src/test/java/com/pragma/integration/interactions/DynamoDBInteraction.java ===
package com.pragma.integration.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import org.openqa.selenium.WebDriver;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.regions.Region;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DynamoDBInteraction implements Interaction {

    private final String operation;
    private final String tableName;
    private final Map<String, AttributeValue> item;
    private final String keyName;
    private final String keyValue;
    private Actor actor;

    public DynamoDBInteraction(String operation, String tableName, Map<String, AttributeValue> item, String keyName, String keyValue) {
        this.operation = operation;
        this.tableName = tableName;
        this.item = item;
        this.keyName = keyName;
        this.keyValue = keyValue;
    }

    public static DynamoDBInteractionBuilder withTable(String tableName) {
        return instrumented(DynamoDBInteractionBuilder.class, tableName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        this.actor = actor;
        DynamoDbClient dynamoDBClient = getDynamoDBClient(actor);

        switch (operation.toUpperCase()) {
            case "PUT":
                performPutItem(dynamoDBClient);
                break;
            case "GET":
                performGetItem(dynamoDBClient);
                break;
            case "DELETE":
                performDeleteItem(dynamoDBClient);
                break;
            default:
                throw new IllegalArgumentException("Operación no soportada: " + operation);
        }
    }

    private void performPutItem(DynamoDbClient client) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        client.putItem(request);
    }

    private void performGetItem(DynamoDbClient client) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(keyName, AttributeValue.builder().s(keyValue).build());

        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        GetItemResponse response = client.getItem(request);
        
        if (response.hasItem()) {
            actorStoreItem(response.item());
        }
    }

    private void performDeleteItem(DynamoDbClient client) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(keyName, AttributeValue.builder().s(keyValue).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        client.deleteItem(request);
    }

    private DynamoDbClient getDynamoDBClient(Actor actor) {
        String endpoint = System.getenv().getOrDefault("DYNAMODB_ENDPOINT", "http://localhost:8000");
        String region = System.getenv().getOrDefault("AWS_REGION", "us-east-1");

        return DynamoDbClient.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(() -> software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create(
                        System.getenv().getOrDefault("AWS_ACCESS_KEY_ID", "dummy"),
                        System.getenv().getOrDefault("AWS_SECRET_ACCESS_KEY", "dummy")
                ))
                .build();
    }

    private void actorStoreItem(Map<String, AttributeValue> item) {
        if (actor != null) {
            actor.remember("lastRetrievedItem", item);
        }
    }

    public static class DynamoDBInteractionBuilder {
        private String tableName;
        private String operation;
        private Map<String, AttributeValue> item = new HashMap<>();
        private String keyName;
        private String keyValue;

        public DynamoDBInteractionBuilder(String tableName) {
            this.tableName = tableName;
        }

        public DynamoDBInteractionBuilder withOperation(String operation) {
            this.operation = operation;
            return this;
        }

        public DynamoDBInteractionBuilder withItem(Map<String, AttributeValue> item) {
            this.item = item;
            return this;
        }

        public DynamoDBInteractionBuilder withKey(String keyName, String keyValue) {
            this.keyName = keyName;
            this.keyValue = keyValue;
            return this;
        }

        public DynamoDBInteraction build() {
            return new DynamoDBInteraction(operation, tableName, item, keyName, keyValue);
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/integration/models/Pedido.java ===
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

// === ARCHIVO: src/test/java/com/pragma/integration/config/DynamoDBConfig.java ===
package com.pragma.integration.config;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

public class DynamoDBConfig {

    private static final String DYNAMODB_LOCAL_ENDPOINT = System.getenv("DYNAMODB_ENDPOINT");
    private static final String AWS_REGION = System.getenv().getOrDefault("AWS_REGION", "us-east-1");
    private static final String AWS_ACCESS_KEY = System.getenv().getOrDefault("AWS_ACCESS_KEY_ID", "test");
    private static final String AWS_SECRET_KEY = System.getenv().getOrDefault("AWS_SECRET_ACCESS_KEY", "test");

    private static DynamoDbClient dynamoDbClient;
    private static DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public static DynamoDbClient getDynamoDbClient() {
        if (dynamoDbClient == null) {
            dynamoDbClient = buildDynamoDbClient();
        }
        return dynamoDbClient;
    }

    public static DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        if (dynamoDbEnhancedClient == null) {
            dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(getDynamoDbClient())
                    .build();
        }
        return dynamoDbEnhancedClient;
    }

    private static DynamoDbClient buildDynamoDbClient() {
        var builder = DynamoDbClient.builder()
                .region(Region.of(AWS_REGION))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(AWS_ACCESS_KEY, AWS_SECRET_KEY)
                ));

        // TODO(reto): Configurar endpoint personalizado para DynamoDB local
        // El endpoint debe obtenerse de la variable de entorno DYNAMODB_ENDPOINT
        // Si no está configurada, usar valor por defecto para desarrollo local

        if (DYNAMODB_LOCAL_ENDPOINT != null && !DYNAMODB_LOCAL_ENDPOINT.isBlank()) {
            builder.endpointOverride(URI.create(DYNAMODB_LOCAL_ENDPOINT));
        }

        return builder.build();
    }

    public static void reset() {
        if (dynamoDbClient != null) {
            dynamoDbClient.close();
        }
        if (dynamoDbEnhancedClient != null) {
            dynamoDbEnhancedClient.close();
        }
        dynamoDbClient = null;
        dynamoDbEnhancedClient = null;
    }
}

// === ARCHIVO: docker-compose.yml ===
# Infraestructura local del reto. SOLO desarrollo: credenciales de ejemplo (local-dev-only), puertos en loopback.
# Levantar: docker compose up -d --wait
name: infra-local
services:
  dynamodb-local:
    image: "amazon/dynamodb-local:2.5.2"
    command: ["-jar","DynamoDBLocal.jar","-sharedDb","-inMemory"]
    ports:
      - "127.0.0.1:8000:8000"
    healthcheck:
      test: ["CMD-SHELL","bash -c ':> /dev/tcp/127.0.0.1/8000' || exit 1"]
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 5s


// === ARCHIVO: infra-local/README.md ===
# Infraestructura local

Servicios de soporte para el reto, definidos en `docker-compose.yml` (raiz del repo).

```bash
docker compose up -d --wait
```

| Servicio | Imagen | Puertos (host) |
|---|---|---|
| DynamoDB Local | `amazon/dynamodb-local:2.5.2` | 8000 |

Credenciales: solo de desarrollo local (`local-dev-only`, `local`). No son secretos ni deben reutilizarse.
Los scripts de `infra-local/` (tablas, topics) se ejecutan con el compose en estado healthy.


// === ARCHIVO: infra-local/dynamodb/create-tables.sh ===
#!/usr/bin/env bash
# Crea las tablas en DynamoDB Local (idempotente). Credenciales dummy: DynamoDB Local no valida firmas.
set -euo pipefail

export AWS_ACCESS_KEY_ID=local
export AWS_SECRET_ACCESS_KEY=local
export AWS_REGION=us-east-1
ENDPOINT="${DYNAMODB_ENDPOINT:-http://localhost:8000}"

create_table() {
  local name="$1" pk="$2" sk="${3:-}"
  if aws dynamodb describe-table --table-name "$name" --endpoint-url "$ENDPOINT" >/dev/null 2>&1; then
    echo "Tabla $name ya existe"
    return
  fi
  local attrs="AttributeName=$pk,AttributeType=S"
  local keys="AttributeName=$pk,KeyType=HASH"
  if [ -n "$sk" ]; then
    attrs="$attrs AttributeName=$sk,AttributeType=S"
    keys="$keys AttributeName=$sk,KeyType=RANGE"
  fi
  # shellcheck disable=SC2086
  aws dynamodb create-table --table-name "$name" --attribute-definitions $attrs --key-schema $keys \
    --billing-mode PAY_PER_REQUEST --endpoint-url "$ENDPOINT" >/dev/null
  echo "Tabla $name creada"
}

create_table "app-items" "id"

```
