# firma-analysis
`JSON_STORAGE_BIN_ACC_ID=<your json-storage-account-id> JSON_STORAGE_EVENTS_BIN_ID=<your json-storage events bin id> JSON_STORAGE_PART_BIN_ID=<your json-storage participation bin id> JSON_STORAGE_API_KEY=<your json-storage-api-key> lein run`
`JSON_STORAGE_BIN_ACC_ID=<?> JSON_STORAGE_EVENTS_BIN_ID=<?> JSON_STORAGE_PART_BIN_ID=<?> JSON_STORAGE_API_KEY=<?> lein with-profile dev run --migrate`
`JSON_STORAGE_BIN_ACC_ID=<?> JSON_STORAGE_EVENTS_BIN_ID=<?> JSON_STORAGE_PART_BIN_ID=<?> JSON_STORAGE_API_KEY=<?> lein with-profile dev run`
`curl -X POST -H "Content-Type: application/json" -H "x-token: dobrou noc" http://localhost:8080/ingest`


lein deploy clojars
lein deploy-uberjar
