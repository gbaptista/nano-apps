### Media Player Control

Provides [Playerctl](https://github.com/altdesktop/playerctl) for controlling media players that implement the [MPRIS](https://specifications.freedesktop.org/mpris-spec/latest/) D-Bus Interface Specification.

Installing Playerctl:

```sh
sudo pacman -S playerctl # Arch / Manjaro
sudo apt-get install playerctl # Debian / Ubuntu / Raspberry Pi OS
sudo dnf install playerctl # Fedora / CentOS / RHEL
```

Examples:
```text
🤖> which players do i have?

media-player-control {"command":"list"}
chromium.instance12231
plasma-browser-integration

You have two media players available for control:

1. Chromium, with an instance ID of 12231.
2. Plasma Browser Integration.

These players can be controlled with play, pause,
stop, and various other media control commands.

🤖> what's playing on plasma?

media-player-control {"command":"metadata","player":"plasma-browser-integration"}
plasma-browser-integration kde:mediaSrc              blob:https://www.youtube.com/9c1414f1-5ae7-43d3-a7e2-45f519cdb0a9
plasma-browser-integration kde:pid                   12231
plasma-browser-integration mpris:artUrl              https://i.ytimg.com/vi/1JNmz17gnMw/hqdefault.jpg?sqp=-oaymwEcCNACELwBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLDr0ppBmCZeRzvIfsuDAY7BxKYeBQ
plasma-browser-integration mpris:length              350041000
plasma-browser-integration mpris:trackid             '/org/kde/plasma/browser_integration/1337'
plasma-browser-integration xesam:artist              Polyphia
plasma-browser-integration xesam:title               Polyphia - Ego Death feat. Steve Vai (Official Music Video)
plasma-browser-integration xesam:url                 https://www.youtube.com/watch?v=1JNmz17gnMw

Currently, on Plasma Browser Integration, the track
"Ego Death" by Polyphia featuring Steve Vai is playing.
This is the official music video on YouTube.
The artwork for the track is available as well, which
you would recognize from the video thumbnail on YouTube.

🤖> pause the song

media-player-control {"command":"pause","player":"plasma-browser-integration"}

The song has been paused on Plasma Browser Integration.
If you wish to resume playback, just let me know!

🤖> resume playing

media-player-control {"command":"play","player":"plasma-browser-integration"}

The song is now playing again on Plasma Browser Integration.
Enjoy the music!

🤖> go back to the beginning of the song

media-player-control {"command":"position","player":"plasma-browser-integration","option":"0"}

I've reset the song to the beginning for you.

🤖> turn up the volume to the maximum

media-player-control {"command":"volume","player":"plasma-browser-integration","option":"1.0"}

🤖> |
```
