(ns penguinui.components.text-input
  (:require
   [penguinui.components.icon :refer [error-cross-icon success-check-icon]]))

(defn text-color [state-data]
  (str "if (" state-data " == 'success') {
         return 'text-green-500';
        } else if (" state-data " == 'error') {
         return 'text-red-500';
        } else {
         return 'text-neutral-500';
        }"))

(defn border-color [state-data]
  (str "if (" state-data " == 'success') {
         return 'border-green-500';
        } else if (" state-data " == 'error') {
         return 'border-red-500';
        } else {
         return 'border-neutral-300';
        }"))

(defn text-input
  "Text input component"
  [{:keys [state-data id name label placeholder x-model desc-data]}]
  [:div
   {:class
    "flex w-full max-w-xs flex-col gap-1 text-neutral-600"}
   (when label
     [:label
      {:for id
       :class "flex w-fit items-center gap-1 pl-0.5 text-sm"
       ":class" (text-color state-data)}
      [:template {:x-if (str state-data " == 'success'")}
       success-check-icon]
      [:template {:x-if (str state-data " == 'error'")}
       error-cross-icon]
      label])
   [:input
    {:id id
     :type "text"
     :x-model x-model
     :placeholder placeholder
     :class "w-full rounded-md border bg-neutral-50 px-2 py-2 text-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black disabled:cursor-not-allowed disabled:opacity-75 "
     ":class" (border-color state-data)
     :name name}]
   [:template {:x-if (str desc-data " !== ''")}
    [:small
     {:class "pl-0.5"
      ":class" (text-color state-data)}
     [:span {:x-text desc-data}]]]])