(ns firma-analysis.logic.events-test
  (:require [clojure.test :refer :all]
            [firma-analysis.logic.events :as l.events]))

(deftest missing-events-test
  (testing "missing events"
    (let [stored-data [{:events/poll-id 1}
                       {:events/poll-id 2}
                       {:events/poll-id 3}
                       {:events/poll-id 4}
                       {:events/poll-id 5}]
          db-data {1 {:events/poll-id 1}
                   2 {:events/poll-id 2}
                   3 {:events/poll-id 3}
                   4 {:events/poll-id 4}}]
      (is (= [{:events/poll-id 5}] (l.events/missing-events stored-data db-data))))))
