(ns firma-analysis.ports.core
  (:require [firma-analysis.ports.http-in.core :as http.c]
            [firma-analysis.ports.sql.core :as sql.c]))

(defn start-ports-dev
  [migrate?]
  (sql.c/start migrate?)
  (http.c/start-dev))

(defn start-ports
  [migrate?]
  (sql.c/start migrate?)
  (http.c/start))
