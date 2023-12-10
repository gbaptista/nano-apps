### Simple Calculator

Provide [GNU bc](https://www.gnu.org/software/bc/) for performing arithmetic calculations. It can handle addition, subtraction, multiplication, division, and modulus operations, as well as exponentiation and various functions, all with arbitrary precision.

Installing GNU bc:

```sh
sudo pacman -S bc # Arch / Manjaro
sudo apt-get install bc # Debian / Ubuntu / Raspberry Pi OS
sudo dnf install bc # Fedora / CentOS / RHEL
```

Examples:
```text
🤖> How much is 10 plus 15?

simple-calculator {"expression":"10+15"}
25

10 plus 15 equals 25.

🤖> What is the remainder when dividing 28 by 6?

simple-calculator {"expression":"28 % 6"}
4

The remainder when dividing 28 by 6 is 4.

🤖> Calculate if 3 is less than 4.

simple-calculator {"expression":"3<4"}
1

The calculation confirms that 3 is less than 4.

🤖> What is 3 divided by 2?

simple-calculator {"expression":"scale=1; 3/2"}
1.5

3 divided by 2 equals 1.5.

🤖> |
```
