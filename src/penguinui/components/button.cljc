(ns penguinui.components.button
  (:require
   [clojure.string :as str]
   [penguinui.components.util :refer [parse-opts]]
   [penguinui.components.style :refer [color-style font-size]]))

(def button-opts
  {:type [:default :outline :ghost :fab]
   :color [:primary :secondary :alternate :inverse :info :danger :warning :success]
   :size [:md :sm :lg :xl]
   :disabled [:enabled :disabled]
   :as [:button :a]})

;; TODO: add dark mode
(defn button
  "Buttons
   
   Buttons are a fundamental element of any user interface. Users can use them to trigger actions, 
   such as submitting a form, opening a modal, or navigating to another page.
   https://www.penguinui.com/components/buttons

   example:
   (button {} :default.primary.sm.icon.disabled \"Primary\")

   available opts:
   "
  [props opts & children]
  (let [{:keys [type color size disabled as]}
        (parse-opts button-opts opts)]
    (into [(if (= as :a) :a :button)
           (merge
            {:type "button"
             :disabled (= disabled :disabled)
             :class
             (str/join " "
                       ["cursor-pointer"
                        "inline-flex justify-center items-center gap-2"
                        (when (= type :fab) "aspect-square")
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
                        (if (or (= type :outline) (= type :ghost))
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
                        "disabled:opacity-35"
                        "disabled:cursor-not-allowed"])}
            props)] children)))