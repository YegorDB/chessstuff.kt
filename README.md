# Kotlin chess stuff

## Start dev
```bash
docker compose up
docker compose exec app bash
```

## Build and run
```bash
kotlinc chess.kt -include-runtime -d chess.jar
java -jar chess.jar
```
