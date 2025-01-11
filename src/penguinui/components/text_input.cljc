(ns penguinui.components.text-input
  (:require
   [penguinui.components.icon :refer [error-cross-icon success-check-icon]]))

(def text-color
  "if (state == 'success') {
     return 'text-green-500';
   } else if (state == 'error') {
     return 'text-red-500';
   } else {
     return 'text-neutral-500';
   }")

(def border-color
  "if (state == 'success') {
     return 'border-green-500';
   } else if (state == 'error') {
     return 'border-red-500';
   } else {
     return 'border-neutral-300';
   }")

(defn text-input
  "Text input component
   
   arg keys:
   - name: string
   - label: string
   - placeholder: string
   - password?: boolean
   
   x-data:
   - state: 'default' | 'success' | 'error'
   - text: string
   - desc: string"
  [{:keys [name label placeholder password?]}]
  [:div {:x-id "['text-input']"
         :class "flex w-full max-w-xs flex-col gap-1 text-neutral-600"}
   (when label
     [:label
      {":for" "$id('text-input')"
       :class "flex w-fit items-center gap-1 pl-0.5 text-sm"
       ":class" text-color}
      [:template {:x-if "state == 'success'"}
       success-check-icon]
      [:template {:x-if "state == 'error'"}
       error-cross-icon]
      label])
   [:input
    {":id" "$id('text-input')"
     :type (if password? "password" "text")
     :x-model "text"
     :placeholder placeholder
     :class "w-full rounded-md border bg-neutral-50 px-2 py-2 text-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black disabled:cursor-not-allowed disabled:opacity-75 "
     ":class" border-color
     :name name}]
   [:template {:x-if "desc !== ''"}
    [:small
     {:class "pl-0.5"
      ":class" text-color}
     [:span {:x-text "desc"}]]]])