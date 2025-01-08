(ns sample.core
  (:gen-class)
  (:require
   [hiccup2.core :refer [html]]
   [compojure.core :refer [defroutes GET]]
   [compojure.route :as route]
   [penguinui.components.combobox :refer [combobox]]
   [penguinui.components.icon :refer [icon1 icon2 icon3 icon4 icon5 icon6
                                      icon7 payment-icon penguin-logo settings-icon sign-out-icon
                                      person-icon loading-icon penguin-logo plus-icon]]
   [penguinui.components.sidebar :refer [sidebar]]
   [penguinui.core :refer [button]]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
   [ring.util.response :refer [content-type response]]))

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
  [:div.flex.flex-col.gap-4 {:x-data "{ options: [{value:'Agriculture', label:'Agriculture'}, {value:'Construction', label:'Construction'}]}"}
   [:h1.text-2xl.font-bold "Combobox"]
   (combobox "Industry")])

(defn layout [{:keys [title link view]}]
  [:html
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
    [:link {:rel "preconnect" :href "https://fonts.googleapis.com"}]
    [:link {:rel "preconnect" :href "https://fonts.gstatic.com" :crossorigin true}]
    [:link {:href "https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
            :rel "stylesheet"}]
    [:script {:src "https://unpkg.com/htmx.org@2.0.4"
              :integrity "sha384-HGfztofotfshcF7+8n44JQL2oJmowVChPTg48S+jvZoztPfvwD79OC/LTtG6dMp+"
              :crossorigin "anonymous"}]
    [:script {:src "https://cdn.tailwindcss.com"}]
    [:script {:src "https://cdn.jsdelivr.net/npm/@alpinejs/focus@3.x.x/dist/cdn.min.js" :defer true}]
    [:script {:src "//unpkg.com/alpinejs" :defer true}]]
   [:body {:style "font-family: 'Lato', sans-serif;"}
    [:div {:x-data (str "{ sidebarSelectedItem: {title: '" title "', link: '" link "' } }")}
     [:div {:x-data "{ breadcrumbItems: [] }"
            :x-effect "breadcrumbItems = [sidebarSelectedItem]"}
      [:div {:x-data "{ searchQuery:'' }"
             :x-effect "console.log(searchQuery)"}
       (sidebar
        {:logo {:link "/" :el penguin-logo}
         :side-menu [{:link "/" :icon icon1 :title "Home"}
                     {:link "/ai-interface" :icon icon2 :title "AI Interface"}
                     {:link "/display" :icon icon3 :title "Display"}
                     {:link "/feedback" :icon icon4 :title "Feedback"}
                     {:link "/inputs" :icon icon5 :title "Input"}
                     {:link "/navigation" :icon icon6 :title "Navigation"}]
         :profile-menu {:groups [{:items [{:link "#" :icon person-icon :title "Profile"}]}
                                 {:items [{:link "#" :icon settings-icon :title "Settings"}
                                          {:link "#" :icon payment-icon :title "Payments"}]}
                                 {:items [{:link "#" :icon sign-out-icon :title "Sign Out"}]}]}
         :profile {:avatar-url "https://penguinui.s3.amazonaws.com/component-assets/avatar-7.webp"
                   :name "Alex Martinez"
                   :username "@alexmartinez"}
         :body view})]]]]])

(defn render [req page]
  (-> (if (get-in req [:headers "hx-request"])
        (:view page)
        (layout page))
      html str response (content-type "text/html")))

(def home-page
  [:h1 "Home"])

(def ai-interface-page
  [:h1 "AI Interface"])

(def display-page
  [:h1 "Display"])

(def feedback-page
  [:h1 "Feedback"])

(def input-page
  [:div.flex.flex-col.gap-12
   (buttons)
   (outline-buttons)
   (ghost-buttons)
   (button-with-icon)
   (button-with-icon-right)
   (button-with-icon-loading)
   (comboboxes)])

(def navigation-page
  [:h1 "Navigation"])

(def pages
  {:home {:title "Home" :link "/" :view home-page}
   :ai-interface {:title "AI Interface" :link "/ai-interface" :view ai-interface-page}
   :display {:title "Display" :link "/display" :view display-page}
   :feedback {:title "Feedback" :link "/feedback" :view feedback-page}
   :input {:title "Input" :link "/inputs" :view input-page}
   :navigation {:title "Navigation" :link "/navigation" :view navigation-page}})

(defroutes app
  (GET (-> (pages :home) :link) req (render req (pages :home)))
  (GET (-> (pages :ai-interface) :link) req (render req (pages :ai-interface)))
  (GET (-> (pages :display) :link) req (render req (pages :display)))
  (GET (-> (pages :feedback) :link) req (render req (pages :feedback)))
  (GET (-> (pages :input) :link) req (render req (pages :input)))
  (GET (-> (pages :navigation) :link) req (render req (pages :navigation)))
  (route/not-found "Not Found"))

(defn -main []
  (run-jetty (->
              #'app
              (wrap-defaults site-defaults)
              (wrap-reload  {:dirs ["src" "../src"]}))
             {:port 3000}))
