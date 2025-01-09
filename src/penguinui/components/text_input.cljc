(ns penguinui.components.text-input)

(defn text-input
  "Text input component"
  []
  [:div
   {:class
    "flex w-full max-w-xs flex-col gap-1 text-neutral-600 dark:text-neutral-300"}
   [:label
    {:for "inputError",
     :class "flex w-fit items-center gap-1 pl-0.5 text-sm text-red-500"}
    [:svg
     {:xmlns "http://www.w3.org/2000/svg",
      :viewBox "0 0 16 16",
      :aria-hidden "true",
      :fill "currentColor",
      :class "w-4 h-4"}
     [:path
      {:d
       "M5.28 4.22a.75.75 0 0 0-1.06 1.06L6.94 8l-2.72 2.72a.75.75 0 1 0 1.06 1.06L8 9.06l2.72 2.72a.75.75 0 1 0 1.06-1.06L9.06 8l2.72-2.72a.75.75 0 0 0-1.06-1.06L8 6.94 5.28 4.22Z"}]]
    "Name"]
   [:input
    {:id "inputError",
     :type "text",
     :class
     "w-full rounded-md border border-red-500 bg-neutral-50 px-2 py-2 text-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black disabled:cursor-not-allowed disabled:opacity-75 dark:bg-neutral-900/50 dark:focus-visible:outline-white",
     :name "inputStates"}]
   [:small
    {:class "pl-0.5 text-red-500"}
    "Error: Name field is required"]]
  (comment "Input with success")
  [:div
   {:class
    "flex w-full max-w-xs flex-col gap-1 text-neutral-600 dark:text-neutral-300"}
   [:label
    {:for "inputSuccess",
     :class
     "flex w-fit items-center gap-1 pl-0.5 text-sm text-green-500"}
    [:svg
     {:xmlns "http://www.w3.org/2000/svg",
      :viewBox "0 0 16 16",
      :aria-hidden "true",
      :fill "currentColor",
      :class "w-4 h-4"}
     [:path
      {:fill-rule "evenodd",
       :d
       "M12.416 3.376a.75.75 0 0 1 .208 1.04l-5 7.5a.75.75 0 0 1-1.154.114l-3-3a.75.75 0 0 1 1.06-1.06l2.353 2.353 4.493-6.74a.75.75 0 0 1 1.04-.207Z",
       :clip-rule "evenodd"}]]
    "Name"]
   [:input
    {:id "inputSuccess",
     :type "text",
     :class
     "w-full rounded-md border border-green-500 bg-neutral-50 px-2 py-2 text-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black disabled:cursor-not-allowed disabled:opacity-75 dark:bg-neutral-900/50 dark:focus-visible:outline-white",
     :value "John",
     :name "inputStates"}]])