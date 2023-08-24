package org.anthonyrodriguez.TheOne.Api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.anthonyrodriguez.TheOne.Api.dto.MovieDTO;
import org.anthonyrodriguez.TheOne.Api.dto.QuoteDTO;
import org.anthonyrodriguez.helpers.Utilities;
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

public class TheOneClient {

    private final String MOVIE_LIST_ENDPOINT = "{0}/movie";
    private final String MOVIE_ENDPOINT = "{0}/movie/{1}";
    private final String MOVIE_QUOTES_ENDPOINT = "{0}/movie/{1}/quote";
    private final String QUOTE_LIST_ENDPOINT = "{0}/quote";
    private final String QUOTE_ENDPOINT = "{0}/quote/{1}";

    private String baseUrl;
    private String apiKey;
    private HttpClient client;
    private Log log;
    public TheOneClient(String baseUrl, String apiKey, Log log) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.client = HttpClientBuilder.create()
                .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                .build();
        this.log = log;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

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
