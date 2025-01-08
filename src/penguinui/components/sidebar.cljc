(ns penguinui.components.sidebar
  (:require
   [clojure.string :as str]
   [penguinui.components.breadcrumb :refer [breadcrumb]]
   [penguinui.components.icon :refer [search-icon
                                      sidebar-toggle-icon]]))

(defn search
  "Search component
   
   x-data: { searchQuery: string }"
  []
  [:div {:class "relative my-4 flex w-full max-w-xs flex-col gap-1 text-neutral-600 dark:text-neutral-300"}
   search-icon
   [:input {:type "search"
            :class "w-full border border-neutral-300 rounded-md bg-white px-2 py-1.5 pl-9 text-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black disabled:cursor-not-allowed disabled:opacity-75 dark:border-neutral-700 dark:bg-neutral-950/50 dark:focus-visible:outline-white"
            :name "search"
            :x-model "searchQuery"
            :aria-label "Search"
            :placeholder "Search"}]])

(defn logo [{:keys [link el]}]
  [:a {:href link, :class "ml-2 w-fit text-2xl font-bold text-neutral-900 dark:text-white"}
   [:span {:class "sr-only"} "homepage"]
   el])

(defn sidemenu-toggle-button []
  [:button {:type "button"
            :class "md:hidden inline-block text-neutral-600 dark:text-neutral-300"
            :x-on:click "sidebarIsOpen = true"}
   sidebar-toggle-icon
   [:span {:class "sr-only"} "sidebar toggle"]])

(defn profile [{:keys [avatar-url name username]}]
  [:button {:type "button"
            :class "flex w-full cursor-pointer items-center rounded-md gap-2 p-2 text-left text-neutral-600 hover:bg-black/5 hover:text-neutral-900 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black dark:text-neutral-300 dark:hover:bg-white/5 dark:hover:text-white dark:focus-visible:outline-white"
            :x-bind:class "userDropdownIsOpen ? 'bg-black/10 dark:bg-white/10' : ''"
            :aria-haspopup "true"
            :x-on:click "userDropdownIsOpen = ! userDropdownIsOpen"
            :x-bind:aria-expanded "userDropdownIsOpen"}
   [:img {:src avatar-url
          :class "size-8 object-cover rounded-md"
          :alt "avatar"
          :aria-hidden "true"}]
   [:div {:class "hidden md:flex flex-col"}
    [:span {:class "text-sm font-bold text-neutral-900 dark:text-white"} name]
    [:span {:class "text-xs" :aria-hidden "true"} username]
    [:span {:class "sr-only"} "profile settings"]]])

(defn profile-menu-item [{:keys [link icon title]}]
  [:a {:href link
       :class "flex items-center gap-2 px-2 py-1.5 text-sm font-medium text-neutral-600 underline-offset-2 hover:bg-black/5 hover:text-neutral-900 focus-visible:underline focus:outline-none dark:text-neutral-300 dark:hover:bg-white/5 dark:hover:text-white"
       :role "menuitem"}
   icon
   [:span title]])

(defn profile-menu [{:keys [groups]}]
  [:div {:role "menu"
         :x-show "userDropdownIsOpen"
         :x-trap "userDropdownIsOpen"
         :x-on:keydown.up.prevent "$focus.wrap().previous()"
         :x-cloak true
         :x-transition ""
         :x-on:keydown.down.prevent "$focus.wrap().next()"
         :x-on:click.outside "userDropdownIsOpen = false"
         :class "absolute top-14 right-0 z-20 h-fit w-48 border divide-y divide-neutral-300 border-neutral-300 bg-white dark:divide-neutral-700 dark:border-neutral-700 dark:bg-neutral-950 rounded-md"}
   (for [group groups]
     [:div {:class "flex flex-col py-1.5"}
      (for [item (:items group)]
        (profile-menu-item item))])])

(defn navbar [opts]
  [:nav {:class "sticky top-0 z-10 flex items-center justify-between border-b border-neutral-300 bg-neutral-50 px-4 py-2 dark:border-neutral-700 dark:bg-neutral-900"
         :aria-label "top navibation bar"}
   (sidemenu-toggle-button)
   (breadcrumb {})
   [:div {:x-data "{ userDropdownIsOpen: false }"
          :class "relative"
          :x-on:keydown.esc.window "userDropdownIsOpen = false"}
    (profile (:profile opts))
    (profile-menu (:profile-menu opts))]])

(defn sidebar-menu-item
  "Sidebar menu item component
   
   x-data: { sidebarSelectedItem: {:title string, :link string} }"
  [{:keys [link icon title active?]}]
  [:button {:hx-get link
            :hx-push-url "true"
            :hx-target "#body"
            :x-data (str "{ active() { return sidebarSelectedItem.title === '" title "' } }")
            "@click" (str "sidebarSelectedItem = {title: '" title "', link: '" link "' }")
            ":class" "active() ? 'bg-black/10' : 'hover:bg-black/5 hover:text-neutral-900'"
            :class
            (str/join " "
                      ["flex items-center rounded-md gap-2 px-2 py-1.5 text-sm font-medium text-neutral-600 underline-offset-2 focus-visible:underline focus:outline-none dark:text-neutral-300 dark:hover:bg-white/5 dark:hover:text-white"
                       (if active? "bg-black/10" "hover:bg-black/5 hover:text-neutral-900")])}
   icon
   [:span title]
   [:div {:x-show "active()"}
    [:span {:class "sr-only"} "active"]]])

(defn side-menu [{:keys [items] :as opts}]
  [:nav {:x-cloak true
         :class "fixed left-0 z-30 flex h-svh w-60 shrink-0 flex-col border-r border-neutral-300 bg-neutral-50 p-4 transition-transform duration-300 md:w-64 md:translate-x-0 md:relative dark:border-neutral-700 dark:bg-neutral-900"
         :x-bind:class "sidebarIsOpen ? 'translate-x-0' : '-translate-x-60'"
         :aria-label "sidebar navigation"}
   (logo (:logo opts))
   (search)
   [:div {:class "flex flex-col gap-2 overflow-y-auto pb-6"}
    (for [item items]
      (sidebar-menu-item item))]])

(defn sidebar
  "Sidebar component"
  [{:keys [body logo profile profile-menu] :as opts}]
  [:div {:x-data (str "{ sidebarIsOpen: false }") :class "relative flex w-full flex-col md:flex-row"}
   [:a {:class "sr-only" :href "#main-content"}
    "skip to the main content"]
   [:div {:x-cloak true
          :x-show "sidebarIsOpen"
          :class "fixed inset-0 z-20 bg-neutral-950/10 backdrop-blur-sm md:hidden"
          :aria-hidden "true"
          :x-on:click "sidebarIsOpen = false"
          :x-transition.opacity ""}]
   (side-menu {:logo logo
               :items (:side-menu opts)})
   [:div {:class "h-svh w-full overflow-y-auto bg-white dark:bg-neutral-950"}
    (navbar {:profile profile
             :profile-menu profile-menu})
    [:div {:id "main-content" :class "p-4"}
     [:div#body {:class "overflow-y-auto"}
      body]]]])