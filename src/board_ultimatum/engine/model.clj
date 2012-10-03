(ns board-ultimatum.engine.model
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.query :as mq])
  (:use monger.operators))

;; This namespace contains all functions related to manipulating the
;; applications "model" (which is mostly mongo).

(defn ensure-indexes
  "Ensures the existence of several indexes to use mongo effectively."
  []
  (mc/ensure-index "games" {:id 1})
  (mc/ensure-index "games" {:name 1}))

(defn connect
  "Connect to mongo based on the given connection information."
  [connection-info]
  (if (:uri connection-info)
    (mg/connect-via-uri! (:uri connection-info))
    (let [db-name (:db-name connection-info)]
      (mg/connect!)
      (mg/authenticate db-name
                       (:username connection-info)
                       (into-array Character/TYPE (:password connection-info)))
      (mg/set-db! (mg/get-db db-name))))
  ; Set up the indexes necessary for decent performance.
  (ensure-indexes))

(defn add-games
  "Batch inserts the given sequence of games into mongo."
  [games]
  (mc/insert-batch "games" games))

(defn load-game
  "Takes a path to a BGG game XML file and returns a map describing the game."
  [xml-file-path]
  {})
