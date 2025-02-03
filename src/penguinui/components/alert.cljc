(ns penguinui.components.alert
  (:require
   [penguinui.components.icon :refer [close-alert-icon error-icon]]))

(defn alert [{:keys [title desc]}]
  [:div
   {:class
    "relative w-full overflow-hidden rounded-md border border-red-500 bg-white text-neutral-600 dark:bg-neutral-950 dark:text-neutral-300"
    :role "alert"}
   [:div
    {:class "flex w-full items-center gap-2 bg-red-500/10 p-4"}
    [:div
     {:class "bg-red-500/15 text-red-500 rounded-full p-1"
      :aria-hidden "true"}
     error-icon]
    [:div
     {:class "ml-2"}
     [:h3
      {:class "text-sm font-semibold text-red-500"}
      title]
     [:p
      {:class "text-xs font-medium sm:text-sm"}
      desc]]
    [:button
     {:class "ml-auto", :aria-label "dismiss alert"}
     close-alert-icon]]])