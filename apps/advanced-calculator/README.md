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
