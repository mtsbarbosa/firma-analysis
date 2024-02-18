(ns firma-analysis.logic.availabilities-test
  (:require [clojure.test :refer :all]
            [firma-analysis.logic.availabilities :as l.availabilities]))

(deftest missing-test
  (testing "missing returns the elements that are in stored-data but not in db-data"
    (is (= [#:availabilities{:created-at       #inst "2024-01-12T05:36:00.000000000-00:00"
                             :dates            [#inst "2024-02-13T18:00:00.000000000-00:00" #inst "2024-02-14T18:00:00.000000000-00:00"]
                             :description      "oooopa"
                             :id               #uuid "3e21a076-0583-41c3-aecc-93b207cdc643"
                             :name             "oooopa"
                             :options-length   2
                             :poll-id          4972069693373284503
                             :title            "oooopa"}]
           (l.availabilities/missing [#:availabilities{:created-at       #inst "2024-01-12T05:36:00.000000000-00:00"
                                                       :dates            [#inst "2024-02-13T18:00:00.000000000-00:00" #inst "2024-02-14T18:00:00.000000000-00:00"]
                                                       :description      "oooopa"
                                                       :id               #uuid "3e21a076-0583-41c3-aecc-93b207cdc643"
                                                       :name             "oooopa"
                                                       :options-length   2
                                                       :poll-id          4972069693373284503
                                                       :title            "oooopa"}]
                                     {5072069693373284503 {:availabilities/poll-id 5072069693373284503}})))
    (is (= [] (l.availabilities/missing [#:availabilities{:created-at       #inst "2024-01-12T05:36:00.000000000-00:00"
                                                          :dates            [#inst "2024-02-13T18:00:00.000000000-00:00" #inst "2024-02-14T18:00:00.000000000-00:00"]
                                                          :description      "oooopa"
                                                          :id               #uuid "3e21a076-0583-41c3-aecc-93b207cdc643"
                                                          :name             "oooopa"
                                                          :options-length   2
                                                          :poll-id          4972069693373284503
                                                          :title            "oooopa"}]
                                       {4972069693373284503 {:availabilities/poll-id 4972069693373284503}})))
    (is (= [] (l.availabilities/missing [] {})))))
