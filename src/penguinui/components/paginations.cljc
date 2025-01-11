(ns penguinui.components.paginations
  (:require
   [penguinui.components.icon :refer [ellipsis-icon left-arrow-icon
                                      right-arrow-icon]]))

""
"disabled:cursor-not-allowed"

(defn- page-button [{:keys [page page-change-event]}]
  [:li
   [:button
    {"@click" (str "currentPage = page; $dispatch('" page-change-event "', { page: page });")
     :class "flex size-6 items-center justify-center rounded-md p-1 text-neutral-600 dark:text-neutral-300 dark:hover:text-white"
     ":class" (str "currentPage === page ? 'bg-black font-bold text-neutral-100' : 'hover:text-black'")
     :aria-label "page 1"}
    [:span {:x-text "page"}]]])

(defn generate-pagination [max-visible-pages]
  (str "generatePagination(totalPages, currentPage) {
    const displayPageCount = " max-visible-pages ";
    const pages = [];
    let startPage = Math.max(currentPage - Math.floor(displayPageCount / 2), 1);
    let endPage = startPage + displayPageCount - 1;
    if (endPage > totalPages) {
        endPage = totalPages;
        startPage = Math.max(endPage - displayPageCount + 1, 1);
    }
    for (let i = startPage; i <= endPage; i++) {
        pages.push(i);
    }
    return pages;
}"))
(defn pagination
  "Pagination component
   
   arg keys:
   - page-count: number
   - prev-event: string (lowercase)
   - next-event: string (lowercase)
   - page-change-event: string (lowercase)
   
   x-data:
   - currentPage: number"
  [{:keys [page-count prev-event next-event page-change-event max-visible-pages]}]
  (let [max-visible-pages (or max-visible-pages 10)]
    [:nav {:x-data (str "{ " (generate-pagination max-visible-pages) " }")
           :aria-label "pagination"}
     [:div {:x-data (str "{ get pages() { return generatePagination(" page-count ", currentPage); } }")}
      [:ul {:class "flex flex-shrink-0 items-center gap-2 text-sm font-medium"}
       [:li
        [:button {"@click" (str "if (currentPage > 1) { currentPage--; }; $dispatch('" prev-event "', { page: currentPage });")
                  :class "flex items-center rounded-md p-1 text-neutral-600"
                  ":disabled" "currentPage === 1 ? true : false"
                  ":class" "currentPage === 1 ? 'opacity-75 cursor-not-allowed' : 'hover:text-black'"
                  :aria-label "previous page"}
         left-arrow-icon
         "Previous"]]
       [:template {:x-if "pages[0] !== 1"
                   :x-data "{ page: 1 }"}
        (page-button {:page-change-event page-change-event})]
       [:template {:x-if "pages[0] !== 1"}
        [:li ellipsis-icon]]
       [:template {:x-for (str "page in pages")}
        (page-button {:page-change-event page-change-event})]
       [:template {:x-if (str "pages[pages.length - 1] !== " page-count)}
        [:li ellipsis-icon]]
       [:template {:x-if (str "pages[pages.length - 1] !== " page-count)
                   :x-data (str "{ page: " page-count " }")}
        (page-button {:page-change-event page-change-event})]
       [:li
        [:button {"@click" (str "if (currentPage < " page-count ") { currentPage++; }; $dispatch('" next-event "', { page: currentPage });")
                  :class "flex items-center rounded-md p-1 text-neutral-600"
                  ":disabled" (str "currentPage  >= " page-count " ? true : false")
                  ":class" (str "currentPage  >= " page-count " ? 'opacity-75 cursor-not-allowed' : 'hover:text-black'")
                  :aria-label "next page"}
         "Next"
         right-arrow-icon]]]]]))