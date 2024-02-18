(ns firma-analysis.adapters.availabilities-test
  (:require [clojure.test :refer :all]
            [firma-analysis.adapters.availabilities :as a.availabilities])
  (:import [java.sql Timestamp]))

(deftest date-time->timestamp-test
  (testing "date-time->timestamp converts date-time to timestamp"
    (is (= (Timestamp/valueOf "2024-02-13 15:00:00")
           (a.availabilities/date-time->timestamp "2024-02-13 15:00")))))

(deftest storage->-test
  (testing "availabilities storage-> data is converted"
    (is (= [#:availabilities{:created-at       #inst "2024-01-12T05:36:00.000000000-00:00"
                             :dates            [#inst "2024-02-13T18:00:00.000000000-00:00" #inst "2024-02-14T18:00:00.000000000-00:00"]
                             :description      "oooopa"
                             :id               #uuid "3e21a076-0583-41c3-aecc-93b207cdc643"
                             :name             "oooopa"
                             :options-length   2
                             :poll-id          4972069693373284503
                             :title            "oooopa"}]
           (a.availabilities/storage-> [{:id "3e21a076-0583-41c3-aecc-93b207cdc643"
                                         :name "oooopa"
                                         :date-time "2024-01-12 02:36"
                                         :dates ["2024-02-13 15:00" "2024-02-14 15:00"]
                                         :poll-id 4972069693373284503
                                         :created-at "2024-01-11 23:36"}])))))
