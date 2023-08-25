package org.anthonyrodriguez.TheOne.Api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.anthonyrodriguez.TheOne.Api.dto.MovieDTO;
import org.anthonyrodriguez.TheOne.Api.dto.QuoteDTO;
import org.anthonyrodriguez.TheOne.Api.helpers.Utilities;
import org.apache.commons.logging.Log;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/***
 * Client to expose TheOne api endpoints to your Java application. TheOne api documentation can be found
 * here: https://the-one-api.dev/documentation
 *
 * Currently, this client exposes 5 endpoints:
 * <ul>
 *  <li>/movie</li>
 *  <li>/movie/{id}</li>
 *  <li>/movie/{id}/quote</li>
 *  <li>/quote</li>
 *  <li>/quote/{id}</li>
 * </ul>
 *
 *  Full query string parameter support is also included in the client. List of query parameters
 *  can be found here: <a href="https://the-one-api.dev/documentation#5">https://the-one-api.dev/documentation#5</a>
 *
 * @implNote Most query parameters can be used safely with each other. The only query parameter that can only be used
 * by itself is the regex parameter. Regex parameters make other parameters obsolete. This is an api implementation and
 * not the result of this sdk.
 */
public class TheOneClient {

    // Message Templates for API Endpoints
    private final String MOVIE_LIST_ENDPOINT = "{0}/movie";
    private final String MOVIE_ENDPOINT = "{0}/movie/{1}";
    private final String MOVIE_QUOTES_ENDPOINT = "{0}/movie/{1}/quote";
    private final String QUOTE_LIST_ENDPOINT = "{0}/quote";
    private final String QUOTE_ENDPOINT = "{0}/quote/{1}";

    private String baseUrl;
    private String apiKey;
    private HttpClient client;
    private Log log;

    /**
     * Constuctor requires baseUrl and apiKey in order to make valid calls to the endpoints.
     * Optional Log object can be passed in to log exceptions from endpoints.
     * @param baseUrl
     * @param apiKey
     * @param log - optional
     */
    public TheOneClient(String baseUrl, String apiKey, Log log) {

        setBaseUrl(baseUrl);
        setApiKey(apiKey);

        this.client = HttpClientBuilder.create()
                .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                .build();
        this.log = log;
    }

    public void setBaseUrl(String baseUrl) {
        if(null == baseUrl || baseUrl.isEmpty()) {
            throw new IllegalArgumentException("Valid baseUrl is requried");
        }
        this.baseUrl = baseUrl;
    }

    public void setApiKey(String apiKey) {
        if(null == apiKey || apiKey.isEmpty()) {
            throw new IllegalArgumentException("Valid apiKey is requried");
        }
        this.apiKey = apiKey;
    }

    /**
     * Endpoint will list all LOTR movies with details.
     * Optional query parameters can be added to filter, sort, and paginate results:
     * <a href="https://the-one-api.dev/documentation#5">https://the-one-api.dev/documentation#5</a>
     *
     * @param params - optional list on name value pairs for query string
     * @return list of LOTR movies. null if no movies found.
     */
    public MovieDTO[] listMovies(NameValuePair... params) {
        MovieDTO [] movies = null;
        try {
            HttpGet request = buildRequest(params, MOVIE_LIST_ENDPOINT);
            movies = client.execute(request, response -> {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode tree = objectMapper.readTree(response.getEntity().getContent());
                JsonNode docs = tree.get("docs");
                if(null == docs || docs.isEmpty()) {
                    return null;
                }
                return objectMapper.treeToValue(docs, MovieDTO[].class);
            });
        } catch (Exception e) {
            if(null != log) {
                log.error("List Movies Exception", e);
            }
            throw new RuntimeException(e);
        }

        return movies;
    }

    /**
     * Endpoint will return LOTR movie matching movie id passed in.
     * Optional query parameters can be added to filter, sort, and paginate results:
     * <a href="https://the-one-api.dev/documentation#5">https://the-one-api.dev/documentation#5</a>
     *
     * @param movieId
     * @param params - optional list on name value pairs for query string
     * @return single movie matching movie id. null if no movie found
     */
    public MovieDTO findMovie(String movieId, NameValuePair... params) {
        MovieDTO movie = null;
        try {
            HttpGet request = buildRequest(params, MOVIE_ENDPOINT, movieId);
            movie = client.execute(request, response -> {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode tree = objectMapper.readTree(response.getEntity().getContent());
                JsonNode docs = tree.get("docs");
                if(null == docs || docs.isEmpty()) {
                    return null;
                }
                return objectMapper.treeToValue(docs.get(0), MovieDTO.class);
            });
        } catch (Exception e) {
            if(null != log) {
                log.error("Find Movie Exception", e);
            }
            throw new RuntimeException(e);
        }
        return movie;
    }

    /**
     * Endpoint will list all quotes from all LOTR movies.
     * Optional query parameters can be added to filter, sort, and paginate results:
     * <a href="https://the-one-api.dev/documentation#5">https://the-one-api.dev/documentation#5</a>
     *
     * @param params - optional list on name value pairs for query string
     * @return list of all quotes from all movies. null if no quotes are found.
     */
    public QuoteDTO[] listQuotes(NameValuePair... params) {
        QuoteDTO[] quotes = null;
        try {
            HttpGet request = buildRequest(params, QUOTE_LIST_ENDPOINT);
            quotes = client.execute(request, response -> {
               ObjectMapper objectMapper = new ObjectMapper();
                JsonNode tree = objectMapper.readTree(response.getEntity().getContent());
                JsonNode docs = tree.get("docs");
                if(null == docs || docs.isEmpty()) {
                    return null;
                }
                return objectMapper.treeToValue(docs, QuoteDTO[].class);
            });
        } catch (Exception e) {
            if(null != log) {
                log.error("List Quotes Exception", e);
            }
            throw new RuntimeException(e);
        }
        return quotes;
    }

    /**
     * Endpoint will return all quotes from a LOTR movie matching the movie id passed in.
     * Optional query parameters can be added to filter, sort, and paginate results:
     * <a href="https://the-one-api.dev/documentation#5">https://the-one-api.dev/documentation#5</a>
     *
     * @param movieId
     * @param params - optional list on name value pairs for query string
     * @return list of all quotes for single matching movie. null if no quotes found.
     */
    public QuoteDTO[] listMovieQuotes(String movieId, NameValuePair... params) {
        QuoteDTO[] movieQuotes = null;
        try {
            HttpGet request = buildRequest(params, MOVIE_QUOTES_ENDPOINT, movieId);
            movieQuotes = client.execute(request, response -> {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode tree = objectMapper.readTree(response.getEntity().getContent());
                JsonNode docs = tree.get("docs");
                if(null == docs || docs.isEmpty()) {
                    return null;
                }
                return objectMapper.treeToValue(docs, QuoteDTO[].class);
            });
        } catch (Exception e) {
            if(null != log) {
                log.error("List Movie Quotes Exception", e);
            }
            throw new RuntimeException(e);
        }
        return movieQuotes;
    }

    /**
     * Endpoint will return single quotes matching quote id.
     * Optional query parameters can be added to filter, sort, and paginate results:
     * <a href="https://the-one-api.dev/documentation#5">https://the-one-api.dev/documentation#5</a>
     *
     * @param quoteId
     * @param params - optional list on name value pairs for query string
     * @return single quote for matching quote id. null if no quote found.
     */
    public QuoteDTO findQuote(String quoteId, NameValuePair... params) {
        QuoteDTO quote = null;
        try {
            HttpGet request = buildRequest(params, QUOTE_ENDPOINT, quoteId);
            quote = client.execute(request, response -> {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode tree = objectMapper.readTree(response.getEntity().getContent());
                JsonNode docs = tree.get("docs");
                if(null == docs || docs.isEmpty()) {
                    return null;
                }
                return objectMapper.treeToValue(docs.get(0), QuoteDTO.class);
            });
        } catch (Exception e) {
            if(null != log) {
                log.error("FindQuote Exception", e);
            }
            throw new RuntimeException(e);
        }
        return quote;
    }

    private HttpGet buildRequest(NameValuePair[] params, String endpoint, String... pathDirs) throws URISyntaxException {
        MessageFormat messageFormat = new MessageFormat(endpoint);
        String[] result = Utilities.concatWithArrayCopy(new String[]{this.baseUrl}, pathDirs);
        String format = messageFormat.format(result);
        HttpGet request = new HttpGet(format);
        if(null != params) {
            URI uri = new URIBuilder(request.getURI()).addParameters(Arrays.stream(params).collect(Collectors.toList())).build();
            request.setURI(uri);
        }
        request.addHeader("Authorization", "Bearer "+this.apiKey);
        return request;
    }

}
