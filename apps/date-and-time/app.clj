(import '[java.time ZonedDateTime ZoneId]
        '[java.time.format DateTimeFormatter])

(let [timezone (or (get parameters "timezone") (str (ZoneId/systemDefault)))
      formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd'T'HH:mm:ssXXX")
      date-time (-> (ZonedDateTime/now (ZoneId/of timezone))
                    (.format formatter))]
  {:date-time date-time :timezone timezone})
