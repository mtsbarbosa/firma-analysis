# firma-analysis
`JSON_STORAGE_BIN_ACC_ID=<your json-storage-account-id> JSON_STORAGE_EVENTS_BIN_ID=<your json-storage events bin id> JSON_STORAGE_PART_BIN_ID=<your json-storage participation bin id> JSON_STORAGE_API_KEY=<your json-storage-api-key> lein run`
`JSON_STORAGE_BIN_ACC_ID=<?> JSON_STORAGE_EVENTS_BIN_ID=<?> JSON_STORAGE_PART_BIN_ID=<?> JSON_STORAGE_API_KEY=<?> lein with-profile dev run --migrate`
`JSON_STORAGE_BIN_ACC_ID=<?> JSON_STORAGE_EVENTS_BIN_ID=<?> JSON_STORAGE_PART_BIN_ID=<?> JSON_STORAGE_API_KEY=<?> lein with-profile dev run`
`curl -X POST -H "Content-Type: application/json" -H "x-token: dobrou noc" http://localhost:8080/ingest`

## Getting Started

:exclamation: Change usernames and passwords for PROD or internet-facing environments!

1. Start postgresql `docker run -itd -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -v ~/work/postgres:/var/lib/postgresql/data --name postgresql postgres`
2. `$ docker exec -it postgresql bash`
3. <pre>PGPASSWORD=admin psql -U admin</pre>"
4. <pre>CREATE DATABASE firma;</pre>
5. <pre>\c firma;</pre>
6. <pre>CREATE EXTENSION IF NOT EXISTS "uuid-ossp";</pre>
7. Start the application: `lein with-profile dev run`
8. Start the application with migration: `lein with-profile dev run --migrate`

## deploy
lein deploy clojars
lein with-profile uberjar deploy clojars org.clojars.mtsbarbosa/firma-analysis ? firma-analysis-?-standalone.jar pom.xml

## local test with jar
lein uberjar
mv target/firma-analysis-?-standalone.jar target/firma-analysis.jar
JSON_STORAGE_BIN_ACC_ID=? JSON_STORAGE_EVENTS_BIN_ID=? JSON_STORAGE_PART_BIN_ID=? JSON_STORAGE_API_KEY=? java -Dresource.config.edn=dev-config.edn -jar target/firma-analysis.jar
