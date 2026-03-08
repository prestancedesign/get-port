# get-port

A small Clojure library to find an available TCP port.

Useful for tests, development servers, or any situation where a free port is needed dynamically.

The latest version is available on Clojars:

[![Clojars Project](https://clojars.org/prestancedesign/get-port/latest-version.svg)](https://clojars.org/prestancedesign/get-port)

## Installation

For `deps.edn`:

```clojure
{:deps {prestancedesign/get-port {:mvn/version "0.2.0"}}}
```

For `project.clj`:

```clojure
[prestancedesign/get-port "0.2.0"]
```

## API

- `get-port` — Find an available TCP port
- `make-range` — Create an inclusive port range

## Examples

```clojure
(ns hello-world.core
  (:require [prestancedesign.get-port :refer [get-port make-range]]
            [ring.adapter.jetty :as server]))

...

(server/run-jetty handler {:port (get-port)}) ; Get a random available port
```

Try a preferred port:

```clojure
(get-port {:port 3000})
```

Pass in a vector of preferred ports:

```clojure
(get-port {:port [3000 3004 3010]})
```

Use the `make-range` helper to search within an inclusive port range:

```clojure
(get-port {:port (make-range 3000 3005)})
```

Use `:fallback true` to return a random available port when none of the preferred ports are available.
Otherwise `nil` is returned.

```clojure
(get-port {:port 3000 :fallback true})
(get-port {:port [3000 3004 3010] :fallback true})
```

## License

Copyright © 2020-2024 Michaël SALIHI

Distributed under the Eclipse Public License, the same as Clojure.
