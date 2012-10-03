(ns board-ultimatum.test.engine
  (:use clojure.test
        board-ultimatum.engine))

(deftest a-test
    (testing "FIXME, I fail."
        (is (= 0 1))))

(deftest foo-test
    (testing "Function foo"
        ( foo "test")))

(deftest query-test
    (testing "Get number of players of 'Checkers"
        (println first(query))))

(deftest query2-test
    (testing "Get game with 2 players"
        (println first(query2))))

(deftest query3-test
    (testing "Get game with 2 players and a tolerance of +/- 2 players"
        (println first(query3 2 2))))

(deftest query4-test
    (testing "Get game with dice rolling"
        (println first(query4))))

(deftest query5-test
    (testing "Get all games with 4 players that have dice and are 
              also are in the category 'chance' games"
        (println first(query5))))