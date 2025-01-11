(ns penguinui.components.search-input
  (:require
   [penguinui.components.icon :refer [search-icon]]))

(defn search-input
  "Search input component"
  [{:keys [x-model]}]
  [:div {:class "relative my-4 flex w-full max-w-xs flex-col gap-1 text-neutral-600 dark:text-neutral-300"}
   search-icon
   [:input {:type "search"
            :class "w-full border border-neutral-300 rounded-md bg-white px-2 py-1.5 pl-9 text-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black disabled:cursor-not-allowed disabled:opacity-75 dark:border-neutral-700 dark:bg-neutral-950/50 dark:focus-visible:outline-white"
            :name "search"
            :x-model x-model
            :aria-label "Search"
            :placeholder "Search"}]])