(ns penguinui.components.toast-notification
  (:require
   [penguinui.components.icon :refer [close-toast-icon toast-error-icon]]))

(defn toast-notification [_]
  [:div
   {:role "alert",
    :x-show "isVisible",
    :x-transition:leave-end
    "-translate-x-24 opacity-0 md:translate-x-24",
    :x-init
    "$nextTick(() => { isVisible = true }), (timeout = setTimeout(() => { isVisible = false, removeNotification(notification.id)}, displayDuration))",
    :x-data "{ isVisible: false, timeout: null }",
    :x-on:resume-auto-dismiss.window
    "timeout = setTimeout(() => {(isVisible = false), removeNotification(notification.id) }, displayDuration)",
    :x-transition:leave-start "translate-x-0 opacity-100",
    :x-cloak "",
    :x-transition:enter "transition duration-300 ease-out",
    :x-on:pause-auto-dismiss.window "clearTimeout(timeout)",
    :class
    "pointer-events-auto relative rounded-md border border-red-500 bg-white text-neutral-600 dark:bg-neutral-950 dark:text-neutral-300",
    :x-transition:enter-start "translate-y-8",
    :x-transition:enter-end "translate-y-0",
    :x-transition:leave "transition duration-300 ease-in"}
   [:div
    {:class
     "flex w-full items-center gap-2.5 bg-red-500/10 rounded-md p-4 transition-all duration-300"}
    [:div
     {:class "rounded-full bg-red-500/15 p-0.5 text-red-500",
      :aria-hidden "true"}
     toast-error-icon]
    [:div
     {:class "flex flex-col gap-2"}
     [:h3
      {:x-cloak "",
       :x-show "notification.title",
       :class "text-sm font-semibold text-red-500",
       :x-text "notification.title"}]
     [:p
      {:x-cloak "",
       :x-show "notification.message",
       :class "text-pretty text-sm",
       :x-text "notification.message"}]]
    [:button
     {:type "button",
      :class "ml-auto",
      :aria-label "dismiss notification",
      :x-on:click
      "(isVisible = false), removeNotification(notification.id)"}
     close-toast-icon]]])

(defn with-toast-notification [child]
  [:div {:x-data "{ notifications: [],
        displayDuration: 8000,
        soundEffect: false,

        addNotification({ variant = 'info', sender = null, title = null, message = null}) {
            const id = Date.now()
            const notification = { id, variant, sender, title, message }

            if (this.notifications.length >= 20) {
                this.notifications.splice(0, this.notifications.length - 19)
            }
                  
            this.notifications.push(notification)

            if (this.soundEffect) {
                const notificationSound = new Audio('https://res.cloudinary.com/ds8pgw1pf/video/upload/v1728571480/penguinui/component-assets/sounds/ding.mp3')
                notificationSound.play().catch((error) => {
                    console.error('Error playing the sound:', error)
                })
            }
        },
        removeNotification(id) {
            setTimeout(() => {
                this.notifications = this.notifications.filter(
                    (notification) => notification.id !== id,
                )
            }, 400);
        },
    }"
         :x-on:notify.window "addNotification({
            variant: $event.detail.variant,
            sender: $event.detail.sender,
            title: $event.detail.title,
            message: $event.detail.message,
        });"}
   child
   [:div
    {:x-on:mouseenter "$dispatch('pause-auto-dismiss')"
     :x-on:mouseleave "$dispatch('resume-auto-dismiss')"
     :class
     "group pointer-events-none fixed inset-x-8 top-0 z-99 flex max-w-full flex-col gap-2 bg-transparent px-6 py-6 md:bottom-0 md:left-[unset] md:right-0 md:top-[unset] md:max-w-sm"}
    [:template
     {:x-for "(notification, index) in notifications"
      :x-bind:key "notification.id"}
     [:div [:template {:x-if "notification.variant === 'danger'"}]
      [:div
       {:role "alert"
        :x-show "isVisible"
        :x-transition:leave-end
        "-translate-x-24 opacity-0 md:translate-x-24"
        :x-init
        "$nextTick(() => { isVisible = true }), (timeout = setTimeout(() => { isVisible = false, removeNotification(notification.id)}, displayDuration))"
        :x-data "{ isVisible: false, timeout: null }"
        :x-on:resume-auto-dismiss.window
        "timeout = setTimeout(() => {(isVisible = false), removeNotification(notification.id) }, displayDuration)"
        :x-transition:leave-start "translate-x-0 opacity-100"
        :x-cloak ""
        :x-transition:enter "transition duration-300 ease-out"
        :x-on:pause-auto-dismiss.window "clearTimeout(timeout)"
        :class
        "pointer-events-auto relative rounded-radius border border-info bg-surface text-on-surface dark:bg-surface-dark dark:text-on-surface-dark"
        :x-transition:enter-start "translate-y-8"
        :x-transition:enter-end "translate-y-0"
        :x-transition:leave "transition duration-300 ease-in"}
       (toast-notification nil)]]]]])