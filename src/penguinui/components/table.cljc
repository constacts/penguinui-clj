(ns penguinui.components.table
  (:require
   [clojure.string :as str]
   [penguinui.components.icon :refer [checkbox-icon]]))

(defn check-all-th [rows]
  [:th {:x-id "['checkAll']" :scope "col" :class "p-4"}
   [:label {":for" "$id('checkAll')"
            :class "flex items-center cursor-pointer text-neutral-600 dark:text-neutral-300"}
    [:div {:class "relative flex items-center"}
     [:input {:type "checkbox"
              ":id" "$id('checkAll')"
              "@click" (str "if($event.target.checked) { 
                               checkedRows = [" (str/join "," (map #(str "'" (:id %) "'") rows)) "]; 
                             } else { 
                               checkedRows = []; 
                             }")
              :class "before:content[''] peer relative size-4 cursor-pointer appearance-none overflow-hidden rounded border border-neutral-300 bg-white before:absolute before:inset-0 checked:border-black checked:before:bg-black focus:outline focus:outline-2 focus:outline-offset-2 focus:outline-neutral-800 checked:focus:outline-black active:outline-offset-0 dark:border-neutral-700 dark:bg-neutral-900 dark:checked:border-white dark:checked:before:bg-white dark:focus:outline-neutral-300 dark:checked:focus:outline-white"
              ":checked" (str "checkedRows.length === " (count rows))}]
     checkbox-icon]]])

(defn check-td [row-id]
  [:td {:x-id "['check']" :class "p-4"}
   [:label
    {":for" "$id('check')"
     :class
     "flex items-center cursor-pointer text-neutral-600 dark:text-neutral-300"}
    [:div
     {:class "relative flex items-center"}
     [:input
      {:type "checkbox"
       ":id" "$id('check')"
       "@click" (str "if($event.target.checked) { 
                        checkedRows.push('" row-id "'); 
                      } else { 
                        checkedRows.splice(checkedRows.indexOf('" row-id "'), 1); 
                      }")
       :class "before:content[''] peer relative size-4 cursor-pointer appearance-none overflow-hidden rounded border border-neutral-300 bg-white before:absolute before:inset-0 checked:border-black checked:before:bg-black focus:outline focus:outline-2 focus:outline-offset-2 focus:outline-neutral-800 checked:focus:outline-black active:outline-offset-0 dark:border-neutral-700 dark:bg-neutral-900 dark:checked:border-white dark:checked:before:bg-white dark:focus:outline-neutral-300 dark:checked:focus:outline-white"
       ":checked" (str "checkedRows.includes('" row-id "')")}]
     checkbox-icon]]])

(defn table
  "Table component
  
   arg keys:
   - cols: [{:id string :label string} ...]
   - rows: [{:id string col-id any, col-id any ...}]
   - checkable?: boolean (default false)

   x-data:
   - checkedRows: [row id(string) ...]
   "
  [{:keys [cols rows checkable?]}]
  [:div {:class "overflow-hidden w-full overflow-x-auto rounded-md border border-neutral-300 dark:border-neutral-700"}
   [:table {:class "w-full text-left text-sm text-neutral-600 dark:text-neutral-300"}
    [:thead {:class "border-b border-neutral-300 bg-neutral-50 text-sm text-neutral-900 dark:border-neutral-700 dark:bg-neutral-900 dark:text-white"}
     [:tr
      (when checkable?
        (check-all-th rows))
      (for [col cols]
        [:th {:scope "col" :class "p-4"} (:label col)])]]
    [:tbody {:class "divide-y divide-neutral-300 dark:divide-neutral-700"}
     (for [row rows]
       [:tr
        (when checkable?
          (check-td (:id row)))
        (for [col cols]
          [:td {:class "p-4"} (get row (:id col))])])]]])

