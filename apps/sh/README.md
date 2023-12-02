### Shell Commands

> ☠️ **WARNING:** This Nano App may execute dangerous and destructive operations on a system; be careful with it.

Provides a means to interact with the computer system and data by executing shell commands, similar to those used in a Linux terminal. It can send commands to software, extract information, and perform various actions.


Examples:
```text
🤖> How much space do I have on my disk?

sh {"command":["df","-h","--output=avail","/"]}
Avail
 478G

You have 478 GB of available space on your disk.

🤖> How many cores does my CPU have?

sh {"command":["nproc"]}
8

Your CPU has 8 cores.

🤖> Am I connected to the Internet?

sh {"command":["ping","-c","1","8.8.8.8"]}
PING 8.8.8.8 (8.8.8.8) 56(84) bytes of data.
64 bytes from 8.8.8.8: icmp_seq=1 ttl=58 time=16.1 ms

--- 8.8.8.8 ping statistics ---
1 packets transmitted, 1 received, 0% packet loss, time 0ms
rtt min/avg/max/mdev = 16.126/16.126/16.126/0.000 ms

Yes, you are connected to the Internet. I successfully pinged the
IP address 8.8.8.8 (Google's DNS server) and received a response.

🤖> |
```
