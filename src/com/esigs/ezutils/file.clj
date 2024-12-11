(ns com.esigs.ezutils.file
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(defn file-exists? [f]
  (.exists (io/file f)))

(defn read-file [f]
  (if (file-exists? f)
    (slurp f)))

(defn read-file->edn [f]
  (edn/read-string (read-file f)))
