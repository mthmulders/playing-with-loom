# Playing with Loom
Some sample code to accompany my blog post "[Playing with Loom](https://maarten.mulders.it/2022/03/playing-with-loom/)".

## Running the code
1. Point the `JAVA_HOME` environment variable to a [Project Loom Early-Access Builds](https://jdk.java.net/loom/).
1. Compile the code: `mvn compile`.
1. Run the applications:
    1. `java -m target/classes --enable-preview -p loom.playground/it.mulders.loom.playground.LoomApp`.
    1. `java -m target/classes --enable-preview -p loom.playground/it.mulders.loom.playground.ThreadedApp`.

## Disclaimer
The code in this repo is NOT production grade and lacks any automated testing.
I intentionally kept it as simple as possible (KISS).
Its primary purpose is demonstrating several some concepts from Loom and not being a full-fledged application that can be put into production as is.

You can in no way hold me liable for damage caused directly or indirectly by using this code.

## License
The code in this repository is licensed under the [MIT license](./LICENSE).
