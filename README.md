# get-port

Get an available TCP port for Clojure.

The latest versions on Clojar

[![Clojars Project](https://clojars.org/prestancedesign/get-port/latest-version.svg)](https://clojars.org/prestancedesign/get-port)

## Installation

You can add `get-port` to your project with either:

```clojure
prestancedesign/get-port {:mvn/version "1.0.0"}
```
for `deps.edn` or:

```clojure
[prestancedesign/get-port "1.0.0"]
```
for `project.clj` or `build.boot`.

## Usage

FIXME: write usage documentation!

Invoke a library API function from the command-line:

    $ clojure -X prestancedesign.get-port/foo :a 1 :b '"two"'
    {:a 1, :b "two"} "Hello, World!"

Run the project's tests (they'll fail until you edit them):

    $ clojure -M:test:runner

Build a deployable jar of this library:

    $ clojure -M:jar

Install it locally:

    $ clojure -M:install

Deploy it to Clojars -- needs `CLOJARS_USERNAME` and `CLOJARS_PASSWORD` environment variables:

    $ clojure -M:deploy

## License

Copyright © 2020 Michaël SALIHI

Distributed under the Eclipse Public License, the same as Clojure.
