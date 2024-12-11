(ns com.esigs.ezutils.env
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [com.esigs.ezutils.file :as file]))

(defn keywordize [s]
  (when s
    (-> (str/lower-case s)
        (str/replace "_" "-")
        (str/replace "." "-")
        (keyword))))

(defn read-system-env []
  (->> (System/getenv)
       (map (fn [[k v]] [(keywordize k) v]))
       (into {})))

(defn read-system-props []
  (->> (System/getProperties)
       (map (fn [[k v]] [(keywordize k) v]))
       (into {})))

(defn read-file->edn [f]
  (edn/read-string (file/read-file f)))

(defn read-secrets-manager []
  (let [a (:secrets-manager-id (read-system-props))
        s (str (:user-home (read-system-props)) "/.secrets-manager/" a ".edn")]
    (if a 
      (file/read-file->edn s))))

(defn config []
  (merge 
    (read-secrets-manager)
    (read-system-props)
    (read-system-env)))
