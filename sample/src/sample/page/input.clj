(ns sample.page.input
  (:require
   [penguinui.components.button :refer [button]]
   [penguinui.components.combobox :refer [combobox]]
   [penguinui.components.icon :refer [loading-icon plus-icon]]
   [penguinui.components.search-input :refer [search-input]]
   [penguinui.components.text-input :refer [text-input textarea]]))

(defn buttons []
  [:div.flex.flex-col.gap-4
   [:h1.text-2xl.font-bold "Buttons"]
   [:div.flex.gap-4
    (button {:onClick "console.log('clicked')"} :default "Primary")
    (button {} :secondary "Secondary")
    (button {} :alternate "Alternate")
    (button {} :inverse "Inverse")
    (button {} :info "Info")
    (button {} :danger "Danger")
    (button {} :warning "Warning")
    (button {} :success "Success")]])

(defn outline-buttons []
  [:div.flex.flex-col.gap-4
   [:h1.text-2xl.font-bold "Outline Buttons"]
   [:div.flex.gap-4
    (button {} :outline "Primary")
    (button {} :secondary.outline "Secondary")
    (button {} :alternate.outline "Alternate")
    (button {} :inverse.outline "Inverse")
    (button {} :info.outline "Info")
    (button {} :danger.outline "Danger")
    (button {} :warning.outline "Warning")
    (button {} :success.outline "Success")]])

(defn ghost-buttons []
  [:div.flex.flex-col.gap-4
   [:h1.text-2xl.font-bold "Ghost Buttons"]
   [:div.flex.gap-4
    (button {} :ghost "Primary")
    (button {} :secondary.ghost "Secondary")
    (button {} :alternate.ghost "Alternate")
    (button {} :inverse.ghost "Inverse")
    (button {} :info.ghost "Info")
    (button {} :danger.ghost "Danger")
    (button {} :warning.ghost "Warning")
    (button {} :success.ghost "Success")]])

(defn button-with-icon []
  [:div.flex.flex-col.gap-4
   [:h1.text-2xl.font-bold "Button with Icon"]
   [:div.flex.gap-4
    (button {} :default "Primary")
    (button {} :secondary "Secondary")
    (button {} :alternate "Alternate")
    (button {} :inverse "Inverse")
    (button {} :info "Info")
    (button {} :danger "Danger")
    (button {} :warning "Warning")
    (button {} :success plus-icon "Success")]])

(defn button-with-icon-right []
  [:div.flex.flex-col.gap-4
   [:h1.text-2xl.font-bold "Button with Icon Right"]
   [:div.flex.gap-4
    (button {} :icon-plus "Primary" plus-icon)
    (button {} :secondary plus-icon "Secondary")
    (button {} :alternate plus-icon "Alternate")
    (button {} :inverse plus-icon "Inverse")
    (button {} :info plus-icon "Info")
    (button {} :danger plus-icon "Danger")
    (button {} :warning plus-icon "Warning")
    (button {} :success plus-icon "Success")]])

(defn button-with-icon-loading []
  [:div.flex.flex-col.gap-4
   [:h1.text-2xl.font-bold "Button with Icon Loading"]
   [:div.flex.gap-4
    (button {} :icon-loading loading-icon "Primary")
    (button {} :secondary "Secondary")
    (button {} :alternate "Alternate")
    (button {} :inverse "Inverse")
    (button {} :info "Info")
    (button {} :danger "Danger")
    (button {} :warning "Warning")
    (button {} :success "Success")]])

(defn fab-button []
  [:div.flex.flex-col.gap-4
   [:h1.text-2xl.font-bold "FAB Button"]
   [:div.flex.gap-4
    (button {} :fab loading-icon "Primary")
    (button {} :secondary.fab loading-icon "Secondary")
    (button {} :alternate.fab loading-icon "Alternate")
    (button {} :inverse.fab loading-icon "Inverse")
    (button {} :info.fab loading-icon "Info")
    (button {} :danger.fab loading-icon "Danger")
    (button {} :warning.fab loading-icon "Warning")
    (button {} :success.fab loading-icon "Success")]])

(defn comboboxes []
  [:div.flex.flex-col.gap-4 {:x-data "{ comboboxOptions: [{value:'Agriculture', label:'Agriculture'}, {value:'Construction', label:'Construction'}]}"}
   [:h1.text-2xl.font-bold "Combobox"]
   (combobox "Industry")])

(defn text-inputs []
  [:div.flex.flex-col.gap-4
   [:h1.text-2xl.font-bold "Text Input"]
   [:div {:x-data "{ text: '', desc: '', state: 'default' }"
          :x-init "$watch('text', value => { 
                     console.log(state);
                     if (value.length > 5) { 
                       state = 'error';
                       desc = 'Error: Name is too long';
                     }
                     else if (value.length > 3) {
                       state = 'success';
                       desc = 'This is a success description';
                     }
                     else { 
                       state = 'default';
                     }
                   })"}
    (text-input {:name "sample"
                 :label "Name"
                 :placeholder "Enter your name"})]
   [:div {:x-data "{ text: '', desc: '', state: 'default' }"}
    (text-input {:name "password"
                 :label "Password"
                 :placeholder "Enter your password"
                 :password? true})]
   [:div {:x-data "{ text: '', desc: '', state: 'default' }"}
    (textarea {:name "textarea"
               :label "Textarea"
               :placeholder "Enter your text"
               :rows 5})]
   [:div {:x-data "{ searchQuery: '' }"}
    (search-input)]])

(def input-page
  [:div.flex.flex-col.gap-12.h-full.pl-2
   (buttons)
   (outline-buttons)
   (ghost-buttons)
   (button-with-icon)
   (button-with-icon-right)
   (button-with-icon-loading)
   (comboboxes)
   (text-inputs)])