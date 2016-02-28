![selenium camp logo](http://seleniumcamp.com/wp-content/themes/selenium/images/logo-color.svg "Selenium Camp 2016")
# Accurate Selenium Grid nodes' identification

This module provides an implementation of a custom capability matcher for routing requests to any required node by specified identifier.

To build `selenium-server-standalone.jar` with `CustomCapabilityMatcher` create a separate maven run configuration with the following goals:
```cmd
clean install
```

Please note that `NODE_ID` key must be set both on nodes' and client sides as a custom capability.
