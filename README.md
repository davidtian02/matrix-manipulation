# Square Matrix Rotation Service

This service takes in a square matrix (in the form of `[[1,2,3],[4,5,6],[7,8,9]]`) and returns a JSON blob of the right-rotated matrix, or an exception if the input was invalid.

![Matrix Rotation Example](https://github.com/davidtian02/matrix-manipulation/blob/803adc4c1ffb8ea9a485167ddf47a87c29e3ad23/Screen%20Shot%202020-09-10%20at%209.48.18%20PM.png?raw=true)

## Setup

This project is a Java Google App Engine project, developed using Intellij IDE. If you are interested in contributing code, please follow the guides at https://cloud.google.com/appengine/docs/standard/java/download. Of course, please request deployment permissions :)

## API

This service takes a `GET` request to `https://matrix-manipulation.uc.r.appspot.com/rotate` with a parameter of `matrix`, which is expected to be a matrix.

```
curl -g 'https://matrix-manipulation.uc.r.appspot.com/rotate?matrix=[[1,2,3],[4,5,6],[7,8,9]]'
```

The response is a JSON object like

```
{"result":"[[7, 4, 1],[8, 5, 2],[9, 6, 3]]"}
```

Note: you can also use your browser directly for the request.

## Testing

Tests are done using JUnit with support for GAE and mockito.

Please run the following command for the full suite of tests:

```
./gradlew test
```

See [here](https://github.com/davidtian02/matrix-manipulation/blob/master/src/test/java/com/matrix/utils/MatrixUtilsTest.java) and [here](https://github.com/davidtian02/matrix-manipulation/blob/master/src/test/java/com/matrix/servlets/RotateServletTest.java) for the unit and functional tests
