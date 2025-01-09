(ns sample.page.display
  (:require
   [penguinui.components.table :refer [table]]))

(def display-page
  [:div.flex.flex-col.gap-12.h-full.pl-2
   (table {:cols [{:id "CustomerID" :label "CustomerID"}
                  {:id "Name" :label "Name"}
                  {:id "Email" :label "Email"}
                  {:id "Membership" :label "Membership"}]
           :rows [{"CustomerID" "2335", "Name" "Alice Brown" "Email" "alice.brown@gmail.com" "Membership" "Silver"}
                  {"CustomerID" "2338", "Name" "Bob Johnson" "Email" "johnson.bob@outlook.com" "Membership" "Gold"}]})])