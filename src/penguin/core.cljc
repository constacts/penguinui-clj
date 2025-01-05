(ns penguin.core
  (:require [clojure.string :as str]))

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

(def button-opts
  {:type [:default :outline :ghost :fab]
   :color [:primary :secondary :alternate :inverse :info :danger :warning :success]
   :size [:md :sm :lg :xl]
   :icon [nil :icon-plus :icon-loading]
   :icon-position [:icon-left :icon-right]
   :disabled [:enabled :disabled]})

(defn- parse-opts [available-opts opts]
  (let [opts' (cond
                (keyword? opts) (parse-list-opts available-opts (split-inline-opts opts))
                (vector? opts) (parse-list-opts available-opts opts)
                (map? opts) opts
                :else (throw (ex-info "opts must be a keyword or a map" {:opts opts})))]
    (merge (default-opts available-opts) opts')))


(def color-style
  {:primary {:bg "bg-black"
             :text "text-black"
             :text-with-bg "text-neutral-100"
             :border "border-black"
             :focus-outline "outline-black"
             :bordered-focus-outline "outline-black"}
   :secondary {:bg "bg-neutral-800"
               :text "text-neutral-800"
               :text-with-bg "text-white"
               :border "border-neutral-800"
               :focus-outline "outline-neutral-800"
               :bordered-focus-outline "outline-neutral-800"}
   :alternate {:bg "bg-neutral-50"
               :text "text-neutral-300"
               :text-with-bg "text-neutral-900"
               :border "border-neutral-300"
               :focus-outline "outline-neutral-50"
               :bordered-focus-outline "outline-neutral-300"}
   :inverse {:bg "bg-neutral-950"
             :text "text-neutral-950"
             :text-with-bg "text-neutral-300"
             :border "border-neutral-950"
             :focus-outline "outline-neutral-950"
             :bordered-focus-outline "outline-neutral-950"}
   :info {:bg "bg-sky-500"
          :text "text-sky-500"
          :text-with-bg "text-white"
          :border "border-sky-500"
          :focus-outline "outline-sky-500"
          :bordered-focus-outline "outline-sky-500"}
   :danger {:bg "bg-red-500"
            :text "text-red-500"
            :text-with-bg "text-white"
            :border "border-red-500"
            :focus-outline "outline-red-500"
            :bordered-focus-outline "outline-red-500"}
   :warning {:bg "bg-amber-500"
             :text "text-amber-500"
             :text-with-bg "text-white"
             :border "border-amber-500"
             :focus-outline "outline-amber-500"
             :bordered-focus-outline "outline-amber-500"}
   :success {:bg "bg-green-500"
             :text "text-green-500"
             :text-with-bg "text-white"
             :border "border-green-500"
             :focus-outline "outline-green-500"
             :bordered-focus-outline "outline-green-500"}})

(defn font-size [size]
  (case size
    :sm "text-xs"
    :md "text-sm"
    :lg "text-base"
    :xl "text-lg"))

;; TODO: add dark mode
(defn button
  "Buttons
   
   Buttons are a fundamental element of any user interface. Users can use them to trigger actions, 
   such as submitting a form, opening a modal, or navigating to another page.
   https://www.penguinui.com/components/buttons

   example:
   (button :default.primary.sm.icon.disabled \"Primary\")

   available opts:
   - color: primary, secondary, alternate, inverse, info, danger, warning, success
   - size: sm, md, lg, xl
   - type: default, outline, ghost
   - preset icon: loading
   - disabled: enabled, disabled"
  ([child]
   (button (default-opts button-opts) child))
  ([opts child]
   (let [{:keys [type color size icon icon-position disabled]}
         (parse-opts button-opts opts)]
     [:button {:type "button"
               :disabled (= disabled :disabled)
               :class
               (str/join " "
                         ["cursor-pointer"
                          "whitespace-nowrap"
                          (when (= type :outline) "bg-transparent")
                          "rounded-md"
                          (when (= type :outline) "border")
                          (when (= type :outline) (get-in color-style [color :border]))
                          (when (= type :default) (get-in color-style [color :bg]))
                          "px-4"
                          "py-2"
                          (font-size size)
                          "font-medium"
                          "tracking-wide"
                          (if (= type :outline)
                            (get-in color-style [color :text])
                            (get-in color-style [color :text-with-bg]))
                          "transition"
                          "hover:opacity-75"
                          "text-center"
                          "focus-visible:outline"
                          "focus-visible:outline-2"
                          "focus-visible:outline-offset-2"
                          (if (= type :outline)
                            (str " focus-visible:" (get-in color-style [color :bordered-focus-outline]))
                            (str " focus-visible:" (get-in color-style [color :focus-outline])))
                          "active:opacity-100"
                          "active:outline-offset-0"
                          "disabled:opacity-75"
                          "disabled:cursor-not-allowed"])}
      child])))