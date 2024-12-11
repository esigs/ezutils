(ns com.esigs.ezutils.file
  (:import  [java.io File])
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(defn file-exists? [f]
  (.exists (io/file f)))

(defn read-file [f]
  (if (file-exists? f)
    (slurp f)))

(defn read-file->edn [f]
  (edn/read-string (read-file f)))

(defn dir-spit [f content]
    (let [file (File. f)]
      (when-let [parent (.getParentFile file)]
       (.mkdirs parent))
     (spit f content))) 
