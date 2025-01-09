(ns penguinui.components.style)

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
