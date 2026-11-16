# Automatización de Pruebas de Integración con DynamoDB

El equipo de calidad de software necesita automatizar pruebas de integración para validar la persistencia de pedidos en una tabla DynamoDB. El objetivo es asegurar que los pedidos se lean y escriban correctamente en la base de datos. Los pedidos son generados por el sistema de procesamiento de órdenes y persistidos por el servicio de almacenamiento. El sistema debe manejar escenarios donde los pedidos no se persisten correctamente y verificar que los pedidos persistidos se puedan leer sin problemas.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Automatizacion de pruebas de integracion contra DynamoDB |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y entregará cada archivo con su ruta.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y entregará cada archivo con su ruta. Guárdalos en la carpeta donde quieras trabajar, respetando las rutas.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no entrega todos los archivos al terminar el análisis, escríbele: "entrégame todos los archivos con su ruta".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del Entorno de Pruebas

**Objetivo:** Configurar el entorno de pruebas para ejecutar pruebas de integración contra DynamoDB.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configurar el proyecto con las dependencias necesarias para usar Serenity BDD y el patrón Screenplay.
- Establecer la conexión con DynamoDB y asegurar que las esperas sean explícitas, evitando el uso de Thread.sleep.

**Entregable:** Proyecto configurado con las dependencias y conexión a DynamoDB lista para pruebas.

<details>
<summary>Pistas de conocimiento</summary>

- Configuración de proyectos con Maven.
- Uso de anotaciones en Serenity BDD.

</details>

### Fase 2: Desarrollo de Pruebas de Escritura

**Objetivo:** Desarrollar pruebas automatizadas para verificar la escritura de pedidos en DynamoDB.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Crear una prueba que simule la persistencia de un pedido en DynamoDB.
- Verificar que el pedido se ha persistido correctamente en la base de datos.

**Entregable:** Prueba automatizada que verifica la escritura de un pedido en DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Creación de escenarios de prueba en Serenity BDD.
- Uso de assertions para verificar resultados.

</details>

### Fase 3: Desarrollo de Pruebas de Lectura

**Objetivo:** Desarrollar pruebas automatizadas para verificar la lectura de pedidos desde DynamoDB.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Crear una prueba que simule la lectura de un pedido desde DynamoDB.
- Verificar que el pedido se ha leído correctamente de la base de datos.

**Entregable:** Prueba automatizada que verifica la lectura de un pedido desde DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Creación de escenarios de prueba en Serenity BDD.
- Uso de assertions para verificar resultados.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es Serenity BDD y cómo se usa en el contexto de pruebas de integración con DynamoDB?
- **paraQueSirve**: ¿Para qué sirve el patrón Screenplay en el desarrollo de pruebas de integración?
- **comoSeUsa**: ¿Cómo se usa DynamoDB en el contexto de pruebas de integración?
- **erroresComunes**: ¿Cuáles son los errores comunes al desarrollar pruebas de integración con DynamoDB y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica el uso de esperas explícitas en lugar de Thread.sleep en las pruebas de integración?

## Criterios de Evaluacion

- Configuración correcta del proyecto con las dependencias y conexión a DynamoDB.
- Uso de Serenity BDD y el patrón Screenplay en las pruebas.
- Esperas explícitas en lugar de Thread.sleep.
- Prueba automatizada que verifica la escritura de un pedido en DynamoDB.
- Prueba automatizada que verifica la lectura de un pedido desde DynamoDB.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT) si no tenes un agente en el IDE. Declara lo mismo que AGENTS.md; los archivos que te devuelva el chat los guardas en esta carpeta respetando las rutas.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Mapa de componentes

El repo tiene una carpeta por componente. Cada uno se instala y verifica dentro de la suya.

| Componente | Carpeta | Rol | Capa | Track | Tecnologias |
|---|---|---|---|---|---|
| `app` | (raiz) | practice, chapter | testing | Automatizador | - |
| `infra-local` | (raiz) | support | infra | Compose local | DynamoDB |

## Orden de arranque

1. `docker compose up -d` (en la raiz) — levanta la infraestructura local.
2. `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` — componente `app` (practice, chapter).

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
