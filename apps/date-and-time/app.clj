(import '[java.time ZonedDateTime ZoneId]
        '[java.time.format DateTimeFormatter])

(let [parameter (get parameters "timezone")
      timezone  (if (and parameter (not= parameter "")) parameter (str (ZoneId/systemDefault)))
      formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd'T'HH:mm:ssXXX")
      date-time (-> (ZonedDateTime/now (ZoneId/of timezone))
                    (.format formatter))]
  {:date-time date-time :timezone timezone})
