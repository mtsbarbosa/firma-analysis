(ns firma-analysis.adapters.members-test
  (:require [clojure.test :refer :all]
            [firma-analysis.adapters.members :as a.members]))

(deftest storage->-test
  (testing "members storage-> is converted to internal"
    (is (= {:members/id         "3001"
            :members/first-name "Carlos"
            :members/last-name  "Silva"
            :members/username   nil
            :members/city       "diadema"} (a.members/storage-> ":Carlos:Silva:3001:diadema")))
    (is (= {:members/id         "4001"
            :members/first-name "Juan"
            :members/last-name  nil
            :members/username   "juancarlos2"
            :members/city       "scs"} (a.members/storage-> "juancarlos2:Juan::4001:scs")))))
