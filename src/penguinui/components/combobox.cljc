(ns penguinui.components.combobox
  (:require
   [clojure.string :as str]
   [penguinui.components.icon :refer [check-icon open-arrow-icon]]
   [penguinui.components.text-input :as text-input]))

(def x-data
  "{
     isOpen: false,
     openedWithKeyboard: false,
     setSelectedOption(option) {
         this.selectedOption = option
         this.isOpen = false
         this.openedWithKeyboard = false
         this.$refs.hiddenTextField.value = option.value
     },
     highlightFirstMatchingOption(pressedKey) {
         const option = this.options.find((item) =>
             item.label.toLowerCase().startsWith(pressedKey.toLowerCase()),
         )
         if (option) {
             const index = this.options.indexOf(option)
             const allOptions = document.querySelectorAll('.combobox-option')
             if (allOptions[index]) {
                 allOptions[index].focus()
             }
         }
     },
   }")

(defn combobox
  "Combobox component
  
   x-data: { 
     options: [{value: string, label: string }],
     selectedOption: {value: string, label: string}
   }"
  ([]
   (combobox nil))
  ([{:keys [id name] :as opts}]
   [:div {:x-data x-data
          :x-init "if (selectedOption) { $refs.hiddenTextField.value = selectedOption.value; }"
          :class "w-full flex flex-col gap-1"
          :x-on:keydown "highlightFirstMatchingOption($event.key)"
          :x-on:keydown.esc.window "isOpen = false, openedWithKeyboard = false"}
    (text-input/layout
     opts
     [:div {:class "relative"}
      [:button {:role "combobox"
                :x-bind:aria-label "selectedOption ? selectedOption.label : 'Please Select'"
                :type "button"
                :x-on:keydown.enter.prevent "openedWithKeyboard = true"
                :x-on:keydown.down.prevent "openedWithKeyboard = true"
                :x-bind:aria-expanded "isOpen || openedWithKeyboard"
                :class "inline-flex w-full items-center justify-between gap-2 whitespace-nowrap border-neutral-300 bg-neutral-50 px-4 py-2 text-sm font-medium tracking-wide text-neutral-600 transition hover:opacity-75 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black dark:border-neutral-700 dark:bg-neutral-900/50 dark:text-neutral-300 dark:focus-visible:outline-white rounded-md border"
                :x-on:click "isOpen = ! isOpen"
                :aria-haspopup "listbox"
                :x-on:keydown.space.prevent "openedWithKeyboard = true"}
       [:span {:class "text-sm font-normal"
               :x-text "selectedOption ? selectedOption.label : 'Please Select'"}]
       open-arrow-icon]
      [:input {:id id :name name :type "text" :x-ref "hiddenTextField" :hidden true}]
      [:ul {:role "listbox"
            :x-show "isOpen || openedWithKeyboard"
            :x-trap "openedWithKeyboard"
            :x-on:keydown.up.prevent "$focus.wrap().previous()"
            :x-cloak true
            :x-transition ""
            :x-on:keydown.down.prevent "$focus.wrap().next()"
            :x-on:click.outside "isOpen = false, openedWithKeyboard = false"
            :class "absolute z-10 left-0 top-11 flex max-h-44 w-full flex-col overflow-hidden overflow-y-auto border-neutral-300 bg-neutral-50 py-1.5 dark:border-neutral-700 dark:bg-neutral-900 rounded-md border"
            :aria-label "list"}
       [:template {:x-for "(item, index) in options"
                   :x-bind:key "item.value"}
        [:li {:class "combobox-option inline-flex cursor-pointer justify-between gap-6 bg-neutral-50 px-4 py-2 text-sm text-neutral-600 hover:bg-neutral-900/5 hover:text-neutral-900 focus-visible:bg-neutral-900/5 focus-visible:text-neutral-900 focus-visible:outline-none dark:bg-neutral-900 dark:text-neutral-300 dark:hover:bg-neutral-50/5 dark:hover:text-white dark:focus-visible:bg-neutral-50/10 dark:focus-visible:text-white"
              :role "option"
              :x-from-template "true"
              :x-on:click "setSelectedOption(item)"
              :x-on:keydown.enter "setSelectedOption(item)"
              :x-bind:id "'option-' + index"
              :tabindex "0"}
         [:span {:x-bind:class "selectedOption == item ? 'font-medium' : null" :x-text "item.label"}]
         [:span {:class "sr-only" :x-text "selectedOption == item ? 'selected' : null"}]
         check-icon]]]])]))

(defn option [{:keys [value label]}]
  (if value
    (str "{value: '" value "', label: '" label "'}")
    "null"))


(defn options [xs]
  (str "[" (str/join "," (map option xs)) "]"))
