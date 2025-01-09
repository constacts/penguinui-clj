(ns penguinui.components.util
  (:require
   [clojure.string :as str]))

(defn- default-opts [available-opts]
  (into {} (map (fn [[k v]] [k (first v)]) available-opts)))

(defn- not-nil? [x]
  (not (nil? x)))

(defn- split-inline-opts [inline-opts]
  (map keyword (str/split (name inline-opts) #"\.")))

(defn- parse-list-opts [available-opts opts]
  (let [opt-types (reduce #(merge %1 (zipmap (filter not-nil? (val %2)) (repeat (key %2))))
                          {}
                          available-opts)]
    (reduce #(if-let [type (get opt-types %2)]
               (assoc %1 type %2)
               %1)
            {}
            opts)))

(defn parse-opts [available-opts opts]
  (let [opts' (cond
                (keyword? opts) (parse-list-opts available-opts (split-inline-opts opts))
                (vector? opts) (parse-list-opts available-opts opts)
                (map? opts) opts
                :else (throw (ex-info "opts must be a keyword or a map" {:opts opts})))]
    (merge (default-opts available-opts) opts')))
