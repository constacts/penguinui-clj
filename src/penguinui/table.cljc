(ns penguinui.table)

(defn table
  "Table component
  
   data: {:cols [{:id string :label string} ...] :rows [{col-id any, col-id any ...}]}"
  [data]
  [:div
   {:class
    "overflow-hidden w-full overflow-x-auto rounded-md border border-neutral-300 dark:border-neutral-700"}
   [:table
    {:class
     "w-full text-left text-sm text-neutral-600 dark:text-neutral-300"}
    [:thead
     {:class
      "border-b border-neutral-300 bg-neutral-50 text-sm text-neutral-900 dark:border-neutral-700 dark:bg-neutral-900 dark:text-white"}
     [:tr
      (for [col (:cols data)]
        [:th {:scope "col", :class "p-4"} (:label col)])]]
    [:tbody
     {:class "divide-y divide-neutral-300 dark:divide-neutral-700"}
     (for [row (:rows data)]
       [:tr
        (for [col (:cols data)]
          [:td {:class "p-4"} (get row (:id col))])])]]])