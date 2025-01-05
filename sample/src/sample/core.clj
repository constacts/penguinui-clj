(ns sample.core
  (:gen-class)
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :refer [response content-type]]
            [ring.middleware.reload :refer [wrap-reload]]
            [hiccup2.core :refer [html]]
            [penguin.core :refer [button]]))

(defn buttons []
  [:div.flex.flex-col.gap-4
   [:h1.text-2xl.font-bold "Buttons"]
   [:div.flex.gap-4
    (button "Primary")
    (button :secondary "Secondary")
    (button :alternate "Alternate")
    (button :inverse "Inverse")
    (button :info "Info")
    (button :danger "Danger")
    (button :warning "Warning")
    (button :success "Success")]])

(defn outline-buttons []
  [:div.flex.flex-col.gap-4
   [:h1.text-2xl.font-bold "Outline Buttons"]
   [:div.flex.gap-4
    (button :outline "Primary")
    (button :secondary.outline "Secondary")
    (button :alternate.outline "Alternate")
    (button :inverse.outline "Inverse")
    (button :info.outline "Info")
    (button :danger.outline "Danger")
    (button :warning.outline "Warning")
    (button :success.outline "Success")]])

(def view
  [:html
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
    [:script {:src "https://cdn.tailwindcss.com"}]
    [:script {:src "//unpkg.com/alpinejs" :defer true}]]
   [:body
    [:div.mx-auto.max-w-screen-md.pt-12.flex.flex-col.gap-12
     (buttons)
     (outline-buttons)]]])

(defn app [_]
  (-> view html str response (content-type "text/html")))

(defn -main []
  (run-jetty (wrap-reload app {:dirs ["src" "../src"]}) {:port 3000}))
