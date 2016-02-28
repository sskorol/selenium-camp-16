![selenium camp logo](http://seleniumcamp.com/wp-content/themes/selenium/images/logo-color.svg "Selenium Camp 2016")
# Environment Watcher

This module provides an implementation of REST client and services for remote environment control.

**Prerequisites:**

 - Download and install [webp-imageio](https://bitbucket.org/luciad/webp-imageio) library into local maven repository.
 - Create a separate maven run configuration for this particular project with the following goals:
```cmd
 clean install
```

**Main features:**

 - Killing remote java tasks: selenium hub / nodes, browsermob proxy, har storage, sikulix.
 - Killing remote common tasks: browsers (chrome, firefox, ie) and corresponding drivers.
 - Taking remote screenshot via `CmdCapture` tool.
 - Loseless images compression via `webp-imageio` library.
 
Please note that by default `CmdCapture.exe` and `webp-imageio.dll` resources are configured to be unpacked into Windows temporary folder for further usage.

To be able to compress images `webp-imageio.dll` should be loaded on server startup (see `loadLibrary` usages).

**Usage:**

 - Build all jars by executing above maven run configuration.
 - Start `env-watcher-server` (`-port` can be custom):
```cmd
java -jar env-watcher-server-1.0-jar-with-dependencies.jar -port 4041
```
 - Use `WatcherClient` for establishing connection with raised server (see `main` method's sample).
 - Use `env-watcher-client-1.0-jar-with-dependencies.jar` as a dependency to integrate client and core features into your own project.
