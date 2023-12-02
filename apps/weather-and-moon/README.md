### Weather and Moon

Provides weather forecasts and current conditions, including temperature, wind speed, and humidity, from [wttr.in](https://github.com/chubin/wttr.in). It also offers astronomical data, such as the times of sunrise and sunset, and details about the moon.

Examples:
```text
🤖> What's the current temperature in New York?

weather {"location":"New York"}
{:current_condition [{:FeelsLikeC 4, :windspeedMiles...

The current temperature in New York is 6°C (43°F).

🤖> Tell me about the moon in Cairo today.

weather {"location":"Cairo"}
{:current_condition [{:FeelsLikeC 25, :windspeedMiles...

In Cairo today, the moon is in a Waning Gibbous phase with 79% illumination.
Moonrise is at 9:30 PM and moonset is at 10:57 AM (local time).

🤖> Should I wear sunglasses or carry an umbrella in Rome today?

weather {"location":"Rome"}
{:current_condition [{:FeelsLikeC 12, :windspeedMiles...

You should wear sunglasses in Rome today, as the weather is partly cloudy with
a 0.0 mm precipitation forecast, indicating no need for an umbrella.

🤖> What's the likelihood of me seeing a rainbow this afternoon in Rio de Janeiro?

weather {"location":"Rio de Janeiro"}
{:current_condition [{:FeelsLikeC 29, :windspeedMiles...

The likelihood of seeing a rainbow this afternoon in Rio de Janeiro is
low as the weather is partly cloudy with no precipitation reported.
Rainbows typically require rain combined with sunshine.

🤖> Is it a good day for kite flying in Sydney based on wind conditions?

weather {"location":"Sydney"}
{:current_condition [{:FeelsLikeC 18, :windspeedMiles...

Yes, it's a good day for kite flying in Sydney.
The weather is clear with wind speeds of 9 km/h (6 miles per hour).

🤖> |
```
