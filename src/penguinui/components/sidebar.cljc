(ns penguinui.components.sidebar
  (:require
   [clojure.string :as str]
   [penguinui.components.icon :refer [sidebar-toggle-icon]]))

(defn logo [{:keys [link el]}]
  [:a {:href link :class "ml-2 w-fit text-2xl font-medium text-neutral-900 dark:text-white"}
   [:span {:class "sr-only"} "homepage"]
   el])

(defn sidemenu-toggle-button []
  [:button {:type "button"
            :class "md:hidden inline-block text-neutral-600 dark:text-neutral-300"
            :x-on:click "sidebarIsOpen = true"}
   sidebar-toggle-icon
   [:span {:class "sr-only"} "sidebar toggle"]])

(defn sidebar-menu-item
  "Sidebar menu item component
   
   x-data: { selectedItem: {:title string, :link string} }"
  [{:keys [props icon title active?]}]
  [:button (merge {:x-data (str "{ active() { return selectedItem && selectedItem.title === '" title "' } }")
                  ;;  "@click" (str "selectedItem = {title: '" title "' }")
                   ":class" "active() ? 'bg-black/10' : 'hover:bg-black/5 hover:text-neutral-900'"
                   :class
                   (str/join " "
                             ["flex items-center rounded-md gap-2 px-2 py-1.5 text-sm font-medium text-neutral-600 underline-offset-2 focus-visible:underline focus:outline-none dark:text-neutral-300 dark:hover:bg-white/5 dark:hover:text-white"
                              (if active? "bg-black/10" "hover:bg-black/5 hover:text-neutral-900")])}
                  props)
   icon
   [:span title]
   [:div {:x-show "active()"}
    [:span {:class "sr-only"} "active"]]])