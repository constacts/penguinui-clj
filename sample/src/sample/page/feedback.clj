(ns sample.page.feedback
  (:require
   [penguinui.components.modal :refer [modal]]))

(defn modal-sample []
  [:div {:x-data "{modalIsOpen: false}"}
   [:button
    {"@click" "modalIsOpen = true"
     :type "button"
     :class
     "cursor-pointer whitespace-nowrap rounded-md bg-black px-4 py-2 text-center text-sm font-medium tracking-wide text-neutral-100 transition hover:opacity-75 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black active:opacity-100 active:outline-offset-0 dark:bg-white dark:text-black dark:focus-visible:outline-white"}
    "Open Modal"]
   (modal {:title "Special Offer"
           :content "As a token of appreciation, we have an exclusive offer just for you. Upgrade your account now to unlock premium features and enjoy a seamless experience."
           :buttons [[:button
                      {"@click" "modalIsOpen = false"
                       :type "button"
                       :class
                       "cursor-pointer whitespace-nowrap rounded-md px-4 py-2 text-center text-sm font-medium tracking-wide text-neutral-600 transition hover:opacity-75 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black active:opacity-100 active:outline-offset-0 dark:text-neutral-300 dark:focus-visible:outline-white"}
                      "Remind me later"]
                     [:button
                      {"@click" "modalIsOpen = false"
                       :type "button"
                       :class
                       "cursor-pointer whitespace-nowrap rounded-md bg-black px-4 py-2 text-center text-sm font-medium tracking-wide text-neutral-100 transition hover:opacity-75 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black active:opacity-100 active:outline-offset-0 dark:bg-white dark:text-black dark:focus-visible:outline-white"}
                      "Upgrade Now"]]})])

(def feedback-page
  [:div.flex.flex-col.gap-12.h-full.pl-2
   (modal-sample)])