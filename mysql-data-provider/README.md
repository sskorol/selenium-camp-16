![selenium camp logo](http://seleniumcamp.com/wp-content/themes/selenium/images/logo-color.svg "Selenium Camp 2016")
# Generic DataProvider with Object Pool

This module provides an implementation of a generic DB data provider.

Prerequisites:

 - Install MySql server 5.7.
 - Import `automation.sql` dump.
 - Update `mysql-data-source.xml` with valid `connection.url`, `connection.username` and `connection.password`.
 - Create a separate maven run configuration for this particular module with the following goals:
```cmd
clean test
```

Technical details:

 - DB operations are managed via `Hibernate` ORM.
 - Users pool is managed by `vibur-object-pool` library.
 - Users' injections are handled by `AspectJ` library.
 
Please note that actual data is hardcoded, so you'll see expected assertion errors (one of them is random, depending on pulled user).