#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn slugify [text]
  (-> text
      (clojure.string/lower-case)
      (clojure.string/replace " " "-")
      (clojure.string/replace #"[^a-z0-9\-]" "")))

(defn remove-code-blocks [content]
  (let [code-block-regex #"(?s)```.*?```"]
    (clojure.string/replace content code-block-regex "")))

(defn process-line [line]
  (when-let [[_ hashes title] (re-find #"^(\#{2,}) (.+)" line)]
    (let [link (slugify title)]
      {:level (count hashes) :title title :link link})))

(defn create-index [content]
  (let [processed-content (remove-code-blocks content)
        processed-lines (->> processed-content
                             clojure.string/split-lines
                             (map process-line)
                             (remove nil?))]
    (->> processed-lines
         (map (fn [{:keys [level title link]}]
                (str (apply str (repeat (* 4 (- level 2)) " "))
                     "- ["
                     title
                     "](#"
                     link
                     ")")))
         (clojure.string/join "\n"))))

(defn create-nano-apps []
  (->> (fs/glob "./apps/" "**/README.md")
       (map str)
       (sort)
       (filter #(not (str/includes? % "@")))
       (map slurp)
       (str/join "\n")))

(let [content         (slurp "template.md")
      nano-apps       (create-nano-apps)
      with-nano-apps  (clojure.string/replace content "{nano-apps}" nano-apps)
      index           (create-index with-nano-apps)
      updated-content (clojure.string/replace with-nano-apps "{index}" index)]
  (spit "README.md" updated-content)
  (println "README.md successfully generated."))