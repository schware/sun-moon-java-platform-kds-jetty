# sun-moon-java-platform-kds

Kitchen Display System (KDS) — part of the `sun-moon-java-platform` family
(alongside [`-order`](https://github.com/schware/sun-moon-java-platform-order)
and [`-delivery`](https://github.com/schware/sun-moon-java-platform-delivery),
tied together as git submodules under
[`sun-moon-java-platform`](https://github.com/schware/sun-moon-java-platform)).

Spring Boot, deployed as a WAR to the shared Jetty instance, same pattern
as `-order` (see that repo's `docs/adr/0004` and `0005` for the
Spring/Jetty/WAR deployment reasoning and a classpath bug worth knowing
about before touching this repo's dependencies).

## Why Redis

Tickets are a small, hot, constantly-updated working set — "what's
currently in the kitchen queue." No need for a durable document store;
Redis fits the access pattern (fast key lookups, natural TTL/expiry for
completed tickets later). See the parent repo's ADR for the full
per-service database reasoning.

## API

- `POST /tickets` — create a kitchen ticket (`{"orderId": "..."}`) in
  `RECEIVED` status.

## Build & run

Requires JDK 21+, and Redis reachable at `localhost:6379` (see
`src/main/resources/application.yml`).

```
./gradlew test
./gradlew bootWar
```

Deploy: copy `build/libs/kds-0.1.0.war` to the Jetty `webapps/` directory
as `kds.war`. Needs the same kind of external deployment descriptor as
`-order` (see [`Debian-Setting/docs/jetty.md`](https://github.com/schware/Debian-Setting/blob/master/docs/jetty.md)).

```
curl http://localhost:8080/kds/actuator/health
curl -X POST http://localhost:8080/kds/tickets \
  -H "Content-Type: application/json" \
  -d '{"orderId":"order-1"}'
```

Swagger UI: `http://localhost:8080/kds/swagger-ui/index.html`
