(ns penguinui.components.modal
  (:require
   [penguinui.components.icon :refer [close-icon]]))

(defn modal
  "Modal component
  
   x-data: { modalIsOpen: boolean }"
  [{:keys [title content buttons]}]
  [:div
   {:role "dialog"
    :x-trap.inert.noscroll "modalIsOpen"
    :x-show "modalIsOpen"
    :x-transition.opacity.duration.200ms ""
    "@keydown.esc.window" "modalIsOpen = false"
    :aria-labelledby "defaultModalTitle"
    "@click.self" "modalIsOpen = false"
    :x-cloak ""
    :aria-modal "true"
    :class
    "fixed inset-0 z-30 flex items-end justify-center bg-black/20 p-4 pb-8 backdrop-blur-md sm:items-center lg:p-8"}
   [:div
    {:x-show "modalIsOpen"
     :x-transition:enter
     "transition ease-out duration-200 delay-100 motion-reduce:transition-opacity"
     :x-transition:enter-start "opacity-0 scale-50"
     :x-transition:enter-end "opacity-100 scale-100"
     :class
     "flex max-w-lg flex-col gap-4 overflow-hidden rounded-md border border-neutral-300 bg-white text-neutral-600 dark:border-neutral-700 dark:bg-neutral-900 dark:text-neutral-300"}
    [:div
     {:class
      "flex items-center justify-between border-b border-neutral-300 bg-neutral-50/60 p-4 dark:border-neutral-700 dark:bg-neutral-950/20"}
     [:h3
      {:id "defaultModalTitle"
       :class
       "font-semibold tracking-wide text-neutral-900 dark:text-white"}
      title]
     [:button
      {"@click" "modalIsOpen = false" :aria-label "close modal"}
      close-icon]]
    [:div
     {:class "px-4 py-8"}
     [:p
      content]]
    [:div
     {:class
      "flex flex-col-reverse justify-between gap-2 border-t border-neutral-300 bg-neutral-50/60 p-4 dark:border-neutral-700 dark:bg-neutral-950/20 sm:flex-row sm:items-center md:justify-end"}
     (for [button buttons]
       button)]]])