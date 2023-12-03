# Nano Apps

Nano Apps are tiny applications that can be embedded in [Nano Bots](https://spec.nbots.io). Just as humans can amplify their capabilities with access to tools such as a wrench, a calculator, a screen reader, or internet access, a Nano Bot can enhance its capabilities when equipped with Nano Apps.

Nano Apps can be written in [Lua](https://www.lua.org/about.html), [Fennel](https://fennel-lang.org), or [Clojure](https://clojure.org). This specific repository has chosen to use Clojure for its Nano Apps.

- [Quick Start](#quick-start)
- [Disclaimer](#disclaimer)
- [All Nano Apps](#all-nano-apps)
    - [Advanced Calculator](#advanced-calculator)
    - [Date and Time](#date-and-time)
    - [Random Numbers](#random-numbers)
    - [Shell Commands](#shell-commands)
    - [Simple Calculator](#simple-calculator)
    - [Weather and Moon](#weather-and-moon)
- [Development](#development)
    - [Creating a Nano App](#creating-a-nano-app)
    - [Generating and Using Cartridges](#generating-and-using-cartridges)
    - [Running Tests](#running-tests)
    - [Updating the README](#updating-the-readme)
    - [Code Formatting (cljfmt)](#code-formatting-cljfmt)
    - [Private Nano Apps](#private-nano-apps)
- [Writing Great Nano Apps](#writing-great-nano-apps)
    - [Principles](#principles)
    - [Prompt Engineering](#prompt-engineering)
    - [Concise Outputs](#concise-outputs)
    - [Output Fidelity](#output-fidelity)
    - [Handling Errors](#handling-errors)
    - [Known Pitfalls](#known-pitfalls)
- [References](#references)
    - [Nano Bots](#nano-bots)
    - [Prompt Engineering](#prompt-engineering)
    - [Tools (Functions) Documentation](#tools-functions-documentation)
    - [Specifications](#specifications)

## Quick Start

Install and set up [Nano Bots CLI](https://github.com/icebaker/ruby-nano-bots) and [Babashka](https://github.com/babashka/babashka), then:

```sh
git clone https://github.com/gbaptista/nano-apps.git

cd nano-apps

bb tasks/generate-cartridge.clj cartridges/template.yml cartridge.yml
# Cartridge successfully generated at cartridge.yml

nb cartridge.yml - repl
```

```text
🤖> What day of the week is it today?

date-and-time {}
{:date-time 2023-12-02T14:19:18-03:00, :timezone America/Sao_Paulo}

Today is Saturday.

🤖> |
```

## Disclaimer

This is an experimental, early-stage project. Nano Apps may be dangerous, so be careful with what you try to build; they could execute destructive actions on your computer. Also, be mindful of your budget: Ensure you monitor and budget whatever provider you are using. Nano Apps may produce unexpectedly lengthy content or infinite/too-long loops that could cause your costs to skyrocket.

This software is distributed under the [MIT License](https://github.com/gbaptista/nano-apps/blob/main/LICENSE), which includes a disclaimer of warranty. Furthermore, the authors assume no responsibility for any damage or costs that may arise from the use of this experimental, early-stage project. Use Nano Apps at your own risk.

## All Nano Apps

### Advanced Calculator

Provides [GNU Octave](https://octave.org) for arithmetic and symbolic math, including basic operations and complex calculations such as derivatives and integrals.

Installing GNU Octave:

```sh
sudo apt install octave
sudo pacman -S octave
sudo yum install octave
sudo dnf install octave
```

After installation, you need to install the `symbolic` package: `pkg install -forge symbolic`

```sh
octave
```
```text
GNU Octave, version 6.4.0
octave:1> pkg install -forge symbolic
For information about changes from previous versions of the symbolic package, run 'news symbolic'.
octave:2> 
```

Derivative Examples:
```text
🤖> What is the derivative of the function f(x) = 3x^2 + 5x + 2 with respect to x?

advanced-calculator {"expression":"syms x; diff(3*x^2 + 5*x + 2)"}
Symbolic pkg v3.1.1: Python communication link active, SymPy v1.11.1.
ans = (sym) 6⋅x + 5

The derivative of the function f(x) = 3x^2 + 5x + 2 with respect to x is 6x + 5.

🤖> Find the derivative of the function g(x) = e^(2x) – cos(x) with respect to x.

advanced-calculator {"expression":"syms x; diff(exp(2*x) - cos(x))"}
Symbolic pkg v3.1.1: Python communication link active, SymPy v1.11.1.
ans = (sym)

     2⋅x         
  2⋅ℯ    + sin(x)

The derivative of the function g(x) = e^(2x) – cos(x) with respect to x is 2e^(2x) + sin(x).

🤖> |
```

Integral Examples:
```text
🤖> What is the indefinite integral of the function f(x) = 1/(x^2 + 1) with respect to x?

advanced-calculator {"expression":"syms x; int(1/(x^2 + 1))"}
Symbolic pkg v3.1.1: Python communication link active, SymPy v1.11.1.
ans = (sym) atan(x)

The indefinite integral of the function f(x) = 1/(x^2 + 1) with respect to x is atan(x) + C,
where C is the constant of integration.

🤖> Calculate the definite integral of g(x) from x = 0 to x = pi of g(x) = sin^2(x).

advanced-calculator {"expression":"syms x; int(sin(x)^2, 0, pi)"}
Symbolic pkg v3.1.1: Python communication link active, SymPy v1.11.1.
ans = (sym)

  π
  ─
  2

The definite integral of g(x) = sin^2(x) from x = 0 to x = pi is pi/2.

🤖> |
```

### Date and Time

Provides basic access to the clock, allowing the Nano Bot to access date and time.

Examples:
```text
🤖> What time is it?

date-and-time {}
{:date-time 2023-12-02T12:22:01-03:00, :timezone America/Sao_Paulo}

The current time is 12:22:01 PM in the timezone America/Sao Paulo.

🤖> What day of the week is it today?

Today is Saturday.

🤖> |
```

### Random Numbers

It provides the capability to generate random numbers.

Examples:
```text
🤖> Choose a random number.

random-number {"from":1,"to":100}
53

The random number chosen is 53.

🤖> I would like a random number between 350 and 480.

random-number {"from":350,"to":480}
382

The random number chosen between 350 and 480 is 382.

🤖> |
```

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

### Simple Calculator

Provide [GNU bc](https://www.gnu.org/software/bc/) for performing arithmetic calculations. It can handle addition, subtraction, multiplication, division, and modulus operations, as well as exponentiation and various functions, all with arbitrary precision.

Installing GNU bc:

```sh
sudo apt install bc
sudo pacman -S bc
sudo yum install bc
sudo dnf install bc
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


## Development

### Creating a Nano App

Just copy and paste any folder inside `/apps` that resembles what you are trying to do. The structure is:

- `app.clj`: The Nano App source code.
- `specification.yml`: The Nano App specification for the Nano Bot.
- `test.clj`: Tests for the Nano App.

Additionally:

- `README.md`: A README for humans, not used elsewhere, just to enhance this README.

### Generating and Using Cartridges

From a base template, generate a cartridge with all Nano Apps included:

```sh
bb tasks/generate-cartridge.clj [CARTRIDGE-TEMPLATE] [OUTPUT-FILE]

bb tasks/generate-cartridge.clj cartridges/template.yml cartridge.yml
# => cartridge.yml
```

After generating a cartridge, considering that you have [Nano Bots CLI](https://github.com/icebaker/ruby-nano-bots), you can try it right away:

```
nb cartridge.yml - repl
```

```text
🤖> what time is it?

date-and-time {}
{:date-time 2023-12-02T14:10:05Z, :timezone UTC}

The current time in UTC is 14:10:05 on December 2, 2023.

🤖> |
```

### Running Tests

Running all tests:
```sh
bb tasks/run-tests.clj
```

Running individual tests:
```sh
bb apps/simple-calculator/test.clj
```

### Updating the README

Update the `template.md` file and then:

```sh
bb tasks/generate-readme.clj
```

Trick for automatically updating the `README.md` when `template.md` changes:

```sh
sudo apt install inotify-tools

while inotifywait -e modify -e create template.md apps/*/README.md; do bb tasks/generate-readme.clj; done
```

Trick for Markdown Live Preview:
```sh
pip install -U markdown_live_preview

mlp README.md -p 8076
```

### Code Formatting (cljfmt)

```sh
bb tasks/cljfmt-fix.clj
```

### Private Nano Apps

You can create private Nano Apps that will not be committed to the repository by using the pattern `user@app`:
```text
apps/your-name@your-app/app.clj
```

The same applies to private cartridge templates:
```text
cartridges/your-name@your-cartridge-template.yml
```

## Writing Great Nano Apps

### Principles
Nano Apps are designed as part of Nano Bots, so it is important to familiarize yourself with the Nano Bots' specification and principles: [Nano Bots Specification](https://spec.nbots.io/#/README?id=nano-bots)

### Prompt Engineering
A Nano App is only as good as the AI provider's capability to leverage it. Therefore, as a core principle, everything you have learned about _Prompt Engineering_ should be considered and applied to the development of Nano Apps, especially when writing their specifications:

- [Prompt Engineering Guide](https://www.promptingguide.ai)
- [OpenAI Prompt Engineering Guide](https://platform.openai.com/docs/guides/prompt-engineering)
- [ChatGPT Prompt Engineering for Developers](https://www.deeplearning.ai/short-courses/chatgpt-prompt-engineering-for-developers/)

### Concise Outputs

Although LLMs are becoming increasingly capable of handling substantial amounts of data, we need to consider the following when it comes to [tokens](https://platform.openai.com/tokenizer):

- Excessively large outputs may lead to a decrease in model accuracy during reasoning about content.
- Generating large outputs incurs costs since payment is typically on a per-token basis.
- An excessive number of tokens may overload a model's capacity, leading to errors.

Avoid providing unnecessarily lengthy outputs; seize the opportunity to make your outputs concise. Be careful when building Nano Apps that query databases or run commands, as they can produce unexpectedly lengthy outputs.

### Output Fidelity

When using Nano Apps as a proxy for popular software, ensure that the output is faithful to the actual software. Models reason better when the output is exactly what it would expect from the program.

Example, if your Nano App is a proxy for [GNU Octave](https://octave.org) software, the expected output for operations would be:

```
ans = 6
```

It may be tempting to shorten it to return only `6` instead of `ans = 6`. Don't do that; keep the original output, as it was what the model learned to expect during its training.

### Handling Errors

Keep errors consise ([Concise Outputs](#concise-outputs)) and helpfull ([Prompt Engineering](#prompt-engineering)).

When using Nano Apps as a proxy for popular software, prioritize the sharing of the original error output from the software ([Output Fidelity](#output-fidelity)). Some software generates lengthy error stack traces. In this scenario, it is best to provide alternative custom short messages or restrict the output size without sacrificing necessary detail. Balance [Output Fidelity](#output-fidelity) with [Concise Outputs](#concise-outputs).

### Known Pitfalls

Returning `()` as the output of a function can cause OpenAI models to request that the function run again, potentially leading to an [infinite loop](https://en.wikipedia.org/wiki/Infinite_loop).

## References

### Nano Bots
- [Nano Bots Specification](https://spec.nbots.io/#/README?id=nano-bots)
- [Nano Bots CLI](https://github.com/icebaker/ruby-nano-bots)
- [Nano Bots API](https://github.com/icebaker/nano-bots-api)
- [Nano Bots for Sublime Text](https://github.com/icebaker/sublime-nano-bots)
- [Nano Bots for Visual Studio Code](https://github.com/icebaker/vscode-nano-bots)

### Prompt Engineering
- [Prompt Engineering Guide](https://www.promptingguide.ai)
- [OpenAI Prompt Engineering Guide](https://platform.openai.com/docs/guides/prompt-engineering)
- [ChatGPT Prompt Engineering for Developers](https://www.deeplearning.ai/short-courses/chatgpt-prompt-engineering-for-developers/)

### Tools (Functions) Documentation
- [Nano Bots Tools (Functions)](https://spec.nbots.io/#/README?id=tools-functions-2)
- [Function calling](https://platform.openai.com/docs/guides/function-calling)
- [Function-calling with an OpenAPI specification](https://cookbook.openai.com/examples/function_calling_with_an_openapi_spec)

### Specifications
- [JSON Schema](https://json-schema.org)
- [OpenAPI Specification](https://swagger.io/specification/)
