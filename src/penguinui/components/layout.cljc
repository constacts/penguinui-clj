(ns penguinui.components.layout
  (:require
   [penguinui.components.sidebar :refer [sidemenu-toggle-button]]))

(defn layout
  "Layout component
   
   arg keys:
   - sidebar-items: [element]
   - navbar-items: [element]
   - body: element
   "
  [{:keys [sidebar-items navbar-items body]}]
  [:div {:x-data (str "{ sidebarIsOpen: false }") :class "relative flex w-full flex-col md:flex-row"}
   [:a {:class "sr-only" :href "#main-content"}
    "skip to the main content"]
   [:div {:x-cloak true
          :x-show "sidebarIsOpen"
          :class "fixed inset-0 z-20 bg-neutral-950/10 backdrop-blur-sm md:hidden"
          :aria-hidden "true"
          :x-on:click "sidebarIsOpen = false"
          :x-transition.opacity ""}]
   [:nav {:x-cloak true
          :class "fixed left-0 z-30 flex h-svh w-60 shrink-0 flex-col border-r border-neutral-300 bg-neutral-50 p-4 transition-transform duration-300 md:w-64 md:translate-x-0 md:relative dark:border-neutral-700 dark:bg-neutral-900"
          :x-bind:class "sidebarIsOpen ? 'translate-x-0' : '-translate-x-60'"
          :aria-label "sidebar navigation"}
    [:div {:class (str "flex flex-col gap-2 overflow-y-auto pb-6")}
     (for [item sidebar-items]
       item)]]
   [:div {:class "h-svh w-full overflow-y-auto bg-white dark:bg-neutral-950"}
    [:nav {:class "sticky top-0 z-10 flex items-center justify-between border-b border-neutral-300 bg-neutral-50 px-4 py-2 dark:border-neutral-700 dark:bg-neutral-900"
           :aria-label "top navibation bar"}
     (sidemenu-toggle-button)
     (for [item navbar-items]
       item)]
    [:div {:id "main-content" :class "p-4"}
     [:div#body {:class "overflow-y-auto"}
      body]]]])