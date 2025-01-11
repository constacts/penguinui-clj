(ns sample.core
  (:gen-class)
  (:require
   [compojure.core :refer [defroutes GET]]
   [compojure.route :as route]
   [hiccup2.core :refer [html]]
   [penguinui.components.icon :refer [icon1 icon2 icon3 icon4 icon5 icon6
                                      payment-icon penguin-logo penguin-logo
                                      person-icon settings-icon sign-out-icon]]
   [penguinui.components.sidebar :refer [sidebar]]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.util.response :refer [content-type response]]
   [sample.page.ai-interface :refer [ai-interface-page]]
   [sample.page.display :refer [display-page]]
   [sample.page.feedback :refer [feedback-page]]
   [sample.page.home :refer [home-page]]
   [sample.page.input :refer [input-page]]
   [sample.page.navigation :refer [navigation-page]]))

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
      [:div {:x-data "{ sideMenuSearchQuery:'' }"
             :x-init "$watch('sideMenuSearchQuery', value => console.log(value))"}
       (sidebar
        {:logo {:link "/" :el penguin-logo}
         :side-menu [{:icon icon1 :title "Home" :props {:hx-get "/" :hx-push-url "true" :hx-target "#body"}}
                     {:icon icon2 :title "AI Interface" :props {:hx-get "/ai-interface" :hx-push-url "true" :hx-target "#body"}}
                     {:icon icon3 :title "Display" :props {:hx-get "/display" :hx-push-url "true" :hx-target "#body"}}
                     {:icon icon4 :title "Feedback" :props {:hx-get "/feedback" :hx-push-url "true" :hx-target "#body"}}
                     {:icon icon5 :title "Input" :props {:hx-get "/inputs" :hx-push-url "true" :hx-target "#body"}}
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
