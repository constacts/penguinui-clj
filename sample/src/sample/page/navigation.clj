(ns sample.page.navigation
  (:require
   [penguinui.components.breadcrumb :refer [breadcrumb]]
   [penguinui.components.paginations :refer [pagination]]))

(def navigation-page
  [:div.flex.flex-col.gap-12.h-full.pl-2
   [:div.flex.flex-col.gap-4
    [:h1.text-2xl.font-medium "Breadcrumb"]
    [:div {:x-data "{ items: [{title: 'Home', link: '/'}, {title: 'Navigation', link: '/navigation'}] }"}
     (breadcrumb {})]]
   [:div.flex.flex-col.gap-4
    [:h1.text-2xl.font-medium "Breadcrumb slash"]
    [:div {:x-data "{ items: [{title: 'Home', link: '/'}, {title: 'Navigation', link: '/navigation'}] }"}
     (breadcrumb {:separator :slash})]]
   [:div.flex.flex-col.gap-4
    [:h1.text-2xl.font-medium "Pagination"]
    [:div {:x-data "{ currentPage: 10 }"
           "@onpagechange" "console.log($event.detail.page);"
           "@onprev" "console.log($event.detail.page);"
           "@onnext" "console.log($event.detail.page);"}
     (pagination {:page-count 20
                  :prev-event "onprev"
                  :next-event "onnext"
                  :page-change-event "onpagechange"})]]])
