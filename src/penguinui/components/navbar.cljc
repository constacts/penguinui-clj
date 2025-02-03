(ns penguinui.components.navbar)

(defn profile-button [{:keys [avatar-url name username]}]
  [:button {:type "button"
            :class "flex w-full cursor-pointer items-center rounded-md gap-2 p-2 text-left text-neutral-600 hover:bg-black/5 hover:text-neutral-900 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black dark:text-neutral-300 dark:hover:bg-white/5 dark:hover:text-white dark:focus-visible:outline-white"
            :x-bind:class "userDropdownIsOpen ? 'bg-black/10 dark:bg-white/10' : ''"
            :aria-haspopup "true"
            :x-on:click "userDropdownIsOpen = ! userDropdownIsOpen"
            :x-bind:aria-expanded "userDropdownIsOpen"}
   (when avatar-url
     [:img {:src avatar-url
            :class "size-8 object-cover rounded-md"
            :alt "avatar"
            :aria-hidden "true"}])
   [:div {:class "hidden md:flex flex-col"}
    [:span {:class "text-sm font-medium text-neutral-900 dark:text-white"} name]
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

(defn profile [{:keys [menu-items] :as opts}]
  [:div {:x-data "{ userDropdownIsOpen: false }"
         :class "relative"
         :x-on:keydown.esc.window "userDropdownIsOpen = false"}
   (profile-button opts)
   (profile-menu menu-items)])