# get-port

Get an available TCP port for Clojure.

The latest versions on Clojar

[![Clojars Project](https://clojars.org/prestancedesign/get-port/latest-version.svg)](https://clojars.org/prestancedesign/get-port)

## Installation

For `deps.edn`:

```clojure
prestancedesign/get-port {:mvn/version "0.1.0"}
```
For `project.clj`:

```clojure
[prestancedesign/get-port "0.1.0"]
```

## Usage

```clojure
(ns hello-world.core
  (:require [prestancedesign.get-port :refer [get-port make-range]]
            [ring.adapter.jetty :as server]))

...

(server/run-jetty handler {:port (get-port)}) ; Get a random available port
```

Pass in a preferred port:

```clojure
(get-port {:port 3000})
```

Pass in an array of preferred ports:

```clojure
(get-port {:port [3000 3004 3010]})
```

Use the `make-range` helper in case you need a port in a certain (inclusive) range:

```clojure
(get-port {:port (make-range 3000 3005)})
```

Copyright © 2020 Michaël SALIHI

Distributed under the Eclipse Public License, the same as Clojure.
