# Primality Test

This service determines whether a number is prime or not. The input to the service is a number in the URL parameter "number", and the output is a JSON blob with the result, as well as any error messages. Currently, the implementation is using a library based on the Miller-Rabin test.

## Setup

This project is a Java Google App Engine project, developed using Intellij IDE. If you are interested in contributing code, please follow the guides at https://cloud.google.com/appengine/docs/standard/java/download. Of course, please request deployment permissions :)

## API

This service takes a `GET` request to `https://helloprime-207201.appspot.com/is_prime` with a parameter of `number`, which is expected to be an integer.

```
curl https://helloprime-207201.appspot.com/is_prime?number=7
```

The response is a JSON object like

```
{"result":true}
```

Note: you can also use your browser directly for the request.

## Testing

Tests are done using JUnit with support for GAE and mockito.

Please run the following command for the full suite of tests:

```
./gradlew test
```

See https://github.com/davidtian02/HelloPrime/blob/master/src/test/java/com/example/servlets/PrimeTestServletTest.java  for the servlet tests and https://github.com/davidtian02/HelloPrime/blob/master/src/test/java/com/example/utils/MathUtilsTest.java for functionality unit tests.
