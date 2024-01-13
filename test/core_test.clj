(ns core-test
  (:require [clojure.test :refer :all]
            [firma-analysis.ports.http-in.core :as service]
            [firma-analysis.ports.sql.core :as sql.c]
            [io.pedestal.http :as bootstrap]))

(def service
  (::bootstrap/service-fn (bootstrap/create-servlet service/service)))

(defn setup
  []
  (sql.c/migrate-test))

(defn teardown
  []
  (sql.c/teardown))

(defn test-fixture [f]
  (setup)
  (f)
  (teardown))
