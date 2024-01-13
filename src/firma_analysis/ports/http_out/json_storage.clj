(ns firma-analysis.ports.http-out.json-storage
  (:require [clj-data-adapter.core :as data-adapter]
            [clojure.data.json :as cjson]
            [firma-analysis.adapters.events :as a.events]
            [firma-analysis.adapters.members :as a.members]
            [org.httpkit.client :as hk-client]
            [outpace.config :refer [defconfig]]))

(defconfig bin-account-id)
(defconfig events-bin-id)
(defconfig part-bin-id)
(defconfig api-key)

(def raw-config
  {:bin-account-id bin-account-id
   :events-bin-id events-bin-id
   :part-bin-id   part-bin-id
   :api-key       api-key})

(def events-url
  (str "https://api.jsonstorage.net/v1/json/" (:bin-account-id raw-config) "/" (:events-bin-id raw-config) "?apiKey=" (:api-key raw-config)))
(def participation-url
  (str "https://api.jsonstorage.net/v1/json/" (:bin-account-id raw-config) "/" (:part-bin-id raw-config) "?apiKey=" (:api-key raw-config)))

(defn fetch-events!
  []
  (let [resp (hk-client/request {:url events-url
                                 :method :get})
        {:keys [events availabilities]} (-> @resp
                                            :body
                                            (cjson/read-str :key-fn data-adapter/snake-str->kebab-key))]
    {:events/events (a.events/storage-> events)
     :events/availabilitites availabilities}))

(defn fetch-participation!
  []
  (let [resp (hk-client/request {:url participation-url
                                 :method :get})
        {:keys [votes members]} (-> @resp
                                    :body
                                    (cjson/read-str :key-fn data-adapter/snake-str->kebab-key))]
    {:participation/votes votes
     :participation/members (map a.members/storage-> members)}))
