(ns penguinui.components.breadcrumb
  (:require
   [penguinui.components.icon :refer [right-arrow-icon]]))

(defn breadcrumb [{:keys [items separator]}]
  [:nav {:class "text-sm font-medium text-neutral-600 dark:text-neutral-300" :aria-label "breadcrumb"}
   [:ol {:class "flex flex-wrap items-center gap-1"}
    (for [item (drop-last items)]
      [:li {:class "flex items-center gap-1"}
       [:a {:href (:link item), :class "hover:text-neutral-900 dark:hover:text-white"}
        (:title item)]
       (if (= :slash separator)
         [:span {:aria-hidden "true"} "/"]
         right-arrow-icon)])
    [:li {:class "flex items-center text-neutral-900 gap-1 font-bold dark:text-white" :aria-current "page"}
     (:title (last items))]]])

