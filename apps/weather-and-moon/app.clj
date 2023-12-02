(require '[babashka.curl :as curl])
(require '[cheshire.core :as json])
(import '[java.net URLEncoder])

(defn fetch-weather [location]
  (try
    (let [url (str
               "https://wttr.in/"
               (if location (URLEncoder/encode location "UTF-8") "")
               "?format=j1")
          response (curl/get url)]
      (json/parse-string (:body response) true))
    (catch Exception e
      (let [exception-data (ex-data e)]
        (cond-> {:error "Error performing request."}
          (:status exception-data) (assoc :status-code (:status exception-data))
          (:err exception-data) (assoc :details (:err exception-data)))))))

(let [location (get parameters "location")
      response (fetch-weather location)]
  (if (:error response)
    response
    (if response
      (update response :weather (partial map (fn [day] (dissoc day :hourly))))
      {:error "Error fetching or parsing weather data"})))
