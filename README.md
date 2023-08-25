# TheOne API SDK

## Overview
This sdk supports java projects that require access to theOne api. Documentation
for the api can be found at https://the-one-api.dev/documentation

This SDK provides support for a subset of the api endpoints currently:
* ``` https://the-one-api.dev/v2/movie ```
* ``` https://the-one-api.dev/v2/movie/{id} ```
* ``` https://the-one-api.dev/v2/movie/{id}/quote ```
* ``` https://the-one-api.dev/v2/quote ```
* ``` https://the-one-api.dev/v2/quote/{id} ```

Java data transfer objects are returned from the sdk. For example, `listMovies()`
will return an array of `MovieDTO`.  `QuoteDTO` objects will be used for quote endpoint respectively.

## Installation

* Maven
  * Add the following dependency to your pom file
```
    <groupId>io.github.a-c-rodriguez</groupId>
    <artifactId>anthony_rodriguez-SDK</artifactId>
    <version>0.0.1</version>
```


## Usage

### `TheOneClient client = new TheOneClient(String baseUrl, String apiKey, Log log)`
To start, initialize a new client with the base url () and the api key you were given when you signed
up at the One api dev website. An optional log object can be added to the client to capture any logs 
generated in the sdk.

### `client.listMovies((Optional)NameValuePair params)`
This endpoint will return a list of movies. Optional query params can be passed to filter, sort, or paginate results.
Documentation on query params can be found here https://the-one-api.dev/documentation#5
Note: Regex query params cannot be used with other params.

### `client.findMovie(String movieId, (Optional)NameValuePair params)`
This endpoint will return a movie by id. Optional query params can be passed to filter, sort, or paginate results.
Documentation on query params can be found here https://the-one-api.dev/documentation#5
Note: Regex query params cannot be used with other params.

### `client.listQuotes((Optional)NameValuePair params)`
This endpoint will a list of quotes from all movies. Optional query params can be passed to filter, sort, or paginate results.
Documentation on query params can be found here https://the-one-api.dev/documentation#5
Note: Regex query params cannot be used with other params.

### `client.listMovieQuotes(String movieId, (Optional)NameValuePair params)`
This endpoint will return a list of quotes from a movie by id. Optional query params can be passed to filter, sort, or paginate results.
Documentation on query params can be found here https://the-one-api.dev/documentation#5
Note: Regex query params cannot be used with other params.

### `client.findQuote(String quoteId, (Optional)NameValuePair params)`
This endpoint will return a quote by id. Optional query params can be passed to filter, sort, or paginate results.
Documentation on query params can be found here https://the-one-api.dev/documentation#5
Note: Regex query params cannot be used with other params.
