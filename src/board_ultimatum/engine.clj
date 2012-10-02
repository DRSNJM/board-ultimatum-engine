(ns board-ultimatum.engine
    (:refer-clojure :exclude [==])
    (:use [clojure.core.logic]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defrel num-players game n)

(facts num-players [['Checkers 2]
                    ['Chess 2]
                    ['Solitaire 1]])

(defn query []
    (run* [q]
        (num-players 'Checkers q)))

(defn query2 []
    (run* [q]
        (num-players q 2)))