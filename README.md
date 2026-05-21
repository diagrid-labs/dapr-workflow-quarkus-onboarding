# Dapr Workflow onboarding for Quarkus

Onboarding example showing how to build a Dapr Workflow application in Java with Quarkus, using the `quarkus-dapr` extension from the Quarkiverse. It targets developers learning Dapr Workflows on Quarkus and is intended to be used alongside Dapr University or a Dapr Workshop lesson.

## Prerequisites

- Java 21 (Quarkus 3.31.x targets `maven.compiler.release=21`)
- Maven 3.9+
- Dapr CLI installed and initialized (`dapr init`)

## Run locally

1. Build the project:
   ```
   ./mvnw package
   ```
2. Start the app with a Dapr sidecar:
   <!-- VERIFY: confirm app-port — Quarkus default is 8080, no explicit override in application.properties -->
   ```
   dapr run --app-id workflows --app-port 8080 -- ./mvnw quarkus:dev
   ```
3. The Quarkus Dapr extension auto-registers workflows and activities at startup. Trigger a workflow via the REST endpoints exposed by the app and inspect logs to confirm execution.

## Project structure

- `pom.xml` — Maven build with the Quarkus BOM (`3.31.2`) and the `io.quarkiverse.dapr:quarkus-dapr` extension (`2.5.0`).
- `src/main/java/io/diagrid/workflows/` — Workflow, activity, and REST endpoint sources.
- `src/main/resources/application.properties` — enables `quarkus.dapr.workflow`.

## Related

- [Quarkiverse Dapr extension](https://docs.quarkiverse.io/quarkus-dapr/dev/)
- [Dapr Workflow docs](https://docs.dapr.io/developing-applications/building-blocks/workflow/)

---

Join the [Dapr Discord](https://diagrid.ws/dapr-discord) for Q&A and chat with other community members!
