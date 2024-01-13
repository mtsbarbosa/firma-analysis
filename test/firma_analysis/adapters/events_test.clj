(ns firma-analysis.adapters.events-test
  (:require [clojure.test :refer :all]
            [firma-analysis.adapters.events :as a.events]))

(def events
  [{:id "3e21a076-0583-41c3-aecc-93b207cdc643", :event-name "oooopa",
    :date-time "2024-02-13 15:00", :location "SA - Centro", :type "Ato regional",
    :poll-message-id 170, :poll-id 4972069693373284503, :created-at "2024-01-11 23:36"
    :total-options 2}])

(deftest storage->-test
  (testing "events storage-> data is converted"
    (is (= [#:events{:city           "SA"
                     :created-at     #inst "2024-01-12T02:36:00.000000000-00:00"
                     :description    "oooopa"
                     :id             #uuid "3e21a076-0583-41c3-aecc-93b207cdc643"
                     :name           "oooopa"
                     :options-length 2
                     :poll           true
                     :poll-id        4972069693373284503
                     :scheduled-to   #inst "2024-02-13T18:00:00.000000000-00:00"
                     :title          "oooopa"
                     :type           "Ato regional"}]
           (a.events/storage-> events)))))
