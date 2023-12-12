# Nano Apps

Nano Apps are tiny applications that can be embedded in [Nano Bots](https://spec.nbots.io). Just as humans can amplify their capabilities with access to tools such as a wrench, a calculator, a screen reader, or internet access, a Nano Bot can enhance its capabilities when equipped with Nano Apps.

Nano Apps can be written in [Lua](https://www.lua.org/about.html), [Fennel](https://fennel-lang.org), or [Clojure](https://clojure.org). This specific repository has chosen to use Clojure for its Nano Apps.

{index}

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

You can exit the REPL by typing `exit`.

## All Nano Apps

{nano-apps}

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

# If you don't want to include your private Nano Apps:
bb tasks/generate-cartridge.clj [CARTRIDGE-TEMPLATE] [OUTPUT-FILE] --no-private


bb tasks/generate-cartridge.clj cartridges/template.yml cartridge.yml
# => cartridge.yml

bb tasks/generate-cartridge.clj cartridges/template.yml cartridge.yml --no-private
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

You can exit the REPL by typing `exit`.

### Running Tests

Running all tests:
```sh
bb tasks/run-tests.clj
```

Running individual tests:
```sh
bb apps/simple-calculator/test.clj
```

Running all tests, excluding private Nano Apps:
```sh
bb tasks/run-tests.clj --no-private 
```

### Updating the README

Update the `template.md` file and then:

```sh
bb tasks/generate-readme.clj
```

Trick for automatically updating the `README.md` when `template.md` changes:

```sh
sudo pacman -S inotify-tools # Arch / Manjaro
sudo apt-get install inotify-tools # Debian / Ubuntu / Raspberry Pi OS
sudo dnf install inotify-tools # Fedora / CentOS / RHEL

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

You can exclude your private Nano Apps from cartridge generation with:
```sh
bb tasks/generate-cartridge.clj cartridges/template.yml cartridge.yml --no-private
```

Running all tests, excluding private Nano Apps:
```sh
bb tasks/run-tests.clj --no-private 
```

## Writing Great Nano Apps

### Principles
Nano Apps are designed as part of Nano Bots, so it is important to familiarize yourself with the Nano Bots' specification and principles: [Nano Bots Specification](https://spec.nbots.io/#/README?id=nano-bots)

### Prompt Engineering
A Nano App is only as good as the AI provider's capability to leverage it. Therefore, as a core principle, everything you have learned about _Prompt Engineering_ should be considered and applied to the development of Nano Apps, especially when writing their specifications:

- [Prompt Engineering Guide](https://www.promptingguide.ai)
- [OpenAI Prompt Engineering Guide](https://platform.openai.com/docs/guides/prompt-engineering)
- [ChatGPT Prompt Engineering for Developers](https://www.deeplearning.ai/short-courses/chatgpt-prompt-engineering-for-developers/)

### Give It Room

Sometimes you may feel like, "this is too complex," or "it will not be useful," or "why would it use this," etc. You may be surprised by the creative ways Nano Apps are used by models.

For a concrete example, the [Media Player Control](#media-player-control) has commands that you may think aren't worth providing. But, if you spend some time playing around, you'll have many "wow" moments about how the model uses it to achieve what you are asking for.

So, _give it room_ to be creative and explore, don't hold back on functionality just because you feel unsure.

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

Keep errors concise ([Concise Outputs](#concise-outputs)) and helpfull ([Prompt Engineering](#prompt-engineering)).

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
- [Nano Bots](https://spec.nbots.io/#/README?id=nano-bots)
- [JSON Schema](https://json-schema.org)

## Disclaimer

This is an experimental, early-stage project. Nano Apps may be dangerous, so be careful with what you try to build; they could execute destructive actions on your computer. Also, be mindful of your budget: Ensure you monitor and budget whatever provider you are using. Nano Apps may produce unexpectedly lengthy content or infinite/too-long loops that could cause your costs to skyrocket.

This software is distributed under the [MIT License](https://github.com/gbaptista/nano-apps/blob/main/LICENSE), which includes a disclaimer of warranty. Furthermore, the authors assume no responsibility for any damage or costs that may arise from the use of this experimental, early-stage project. Use Nano Apps at your own risk.
