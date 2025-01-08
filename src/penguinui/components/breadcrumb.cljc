(ns penguinui.components.breadcrumb
  (:require
   [penguinui.components.icon :refer [right-arrow-icon]]))

(defn breadcrumb
  "Breadcrumb component
   
   x-data: { breadcrumbItems: [{title: string, link: string}] }"
  [{:keys [separator]}]
  [:nav {:class "text-sm font-medium text-neutral-600 dark:text-neutral-300" :aria-label "breadcrumb"}
   [:template {:x-if "breadcrumbItems.length > 0"}
    [:ol {:class "flex flex-wrap items-center gap-1"}
     [:template {:x-for "(item, idx) in breadcrumbItems.slice(0, -1)" ":key" "item.title"}
      [:li {:class "flex items-center gap-1"}
       [:a {:x-bind:href "$data.breadcrumbItems[idx].link" :class "hover:text-neutral-900 dark:hover:text-white"}
        [:span {:x-text "item.title"}]]
       (if (= :slash separator)
         [:span {:aria-hidden "true"} "/"]
         right-arrow-icon)]]
     [:li {:class "flex items-center text-neutral-900 gap-1 font-bold dark:text-white" :aria-current "page"}
      [:span {:x-text "breadcrumbItems[breadcrumbItems.length - 1].title"}]]]]])

