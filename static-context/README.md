![selenium camp logo](http://seleniumcamp.com/wp-content/themes/selenium/images/logo-color.svg "Selenium Camp 2016")
# Static context: friend or enemy?

This module provides some basics about static variables misusing in a multi-threaded environment.
To run a common example you need to create a separate maven run configuration for this particular module with the following goals:
```cmd
clean test
```
Please note that you need to setup Selenium Grid environment with at least 2 Chrome nodes by your own to be able to start the demo.

`StaticWebDriverRunner` is a default runner for race condition replication.
`ThreadLocalWebDriverRunner` and `ConcurrentHashMapWebDriverRunner` provide the way of how this issue could be resolved.