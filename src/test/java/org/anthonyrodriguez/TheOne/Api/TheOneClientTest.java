package org.anthonyrodriguez.TheOne.Api;

import org.anthonyrodriguez.TheOne.Api.dto.MovieDTO;
import org.anthonyrodriguez.TheOne.Api.dto.QuoteDTO;
import org.anthonyrodriguez.TheOne.Api.helpers.OperatorNameMonad;
import org.anthonyrodriguez.TheOne.Api.helpers.OperatorNameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.MessageFormat;

public class TheOneClientTest {

    private final String movieId = "5cd95395de30eff6ebccde5a";
    private final String quoteId = "5cd96e05de30eff6ebcce7eb";
    private final String movieWithQuotesId = "5cd95395de30eff6ebccde5d";
    private final String theOneBaseUrl = "https://the-one-api.dev/v2";
    private final String testApiKey = "rJsI5xsfcv93LvIttc__";
    private TheOneClient theOneClient;

    @Before
    public void setUp() throws Exception {
        this.theOneClient = new TheOneClient(theOneBaseUrl, testApiKey, new TestLog());
    }

    @After
    public void tearDown() throws Exception {
        this.theOneClient = null;
    }

    @Test
    public void listMovies() {
        MovieDTO[] movies = theOneClient.listMovies();
        Assert.assertNotNull(movies);
        System.out.println("\n\n List Movies:");
        for (MovieDTO movie : movies) {
            System.out.println(MessageFormat.format("\t{0}", movie.toString()));
        }
    }

    @Test
    public void listMoviesWithMultiFilteringAndResults() {
        MovieDTO[] movies = theOneClient.listMovies(new BasicNameValuePair("limit", "1"), new OperatorNameValuePair("academyAwardWins", "0", ">"));
        Assert.assertNotNull(movies);
        Assert.assertEquals(1, movies.length);
        System.out.println("\n\n List Movies With MultiFiltering:");
        for (MovieDTO movie : movies) {
            System.out.println(MessageFormat.format("\t{0}", movie.toString()));
        }
    }

    @Test
    public void listMoviesWithFilteringAndNoResults() {
        MovieDTO[] movies = theOneClient.listMovies(new OperatorNameValuePair("academyAwardWins", "90", ">"));
        Assert.assertNull(movies);
    }

    @Test
    public void findMovie() {
        MovieDTO movie = theOneClient.findMovie(movieId);
        Assert.assertNotNull(movie);
        System.out.println("\n\n Find Movie:");
        System.out.println(MessageFormat.format("\t{0}", movie.toString()));
    }

    @Test
    public void findMovieWithNonMatchingMovieId() {
        MovieDTO movie = theOneClient.findMovie(quoteId);
        Assert.assertNull(movie);
    }

    @Test
    public void listQuotesWithLimit() {
        QuoteDTO[] quotes = theOneClient.listQuotes(new BasicNameValuePair("limit", "20"));
        Assert.assertNotNull(quotes);
        Assert.assertEquals(20, quotes.length);
        System.out.println("\n\n List Quotes:");
        for (QuoteDTO quote : quotes) {
            System.out.println(MessageFormat.format("\t{0}", quote.toString()));
        }
    }

    @Test
    public void listQuotesWithNameFilter() {
        QuoteDTO[] quotes = theOneClient.listQuotes(new OperatorNameMonad("name", "!"));
        Assert.assertNotNull(quotes);
        System.out.println("\n\n List Quotes With Name Filter:");
        for (QuoteDTO quote : quotes) {
            System.out.println(MessageFormat.format("\t{0}", quote.toString()));
        }
    }

    @Test
    public void listQuotesWithRegexFilteringAndResults() {
        QuoteDTO[] quotes = theOneClient.listQuotes(new OperatorNameValuePair("dialog", "/Frodo/i", "!="));
        Assert.assertNotNull(quotes);
        System.out.println("\n\n List Quotes With Regex Filter:");
        for (QuoteDTO quote : quotes) {
            System.out.println(MessageFormat.format("\t{0}", quote.toString()));
        }
    }

    @Test
    public void listQuotesWithRegexFilteringAndNoResults() {
        QuoteDTO[] quotes = theOneClient.listQuotes(new BasicNameValuePair("dialog", "/Rumplestilskin/i"));
        Assert.assertNull(quotes);
    }

    @Test
    public void listMovieQuotes() {
        QuoteDTO[] quotes = theOneClient.listMovieQuotes(movieWithQuotesId, new BasicNameValuePair("limit", "20"));
        Assert.assertNotNull(quotes);
        System.out.println("\n\n List Movie Quotes:");
        for (QuoteDTO quote : quotes) {
            System.out.println(MessageFormat.format("\t{0}", quote.toString()));
        }
    }

    @Test
    public void listMovieQuotesWithNoQuotes() {
        QuoteDTO[] quotes = theOneClient.listMovieQuotes(movieId, new BasicNameValuePair("limit", "20"));
        Assert.assertNull(quotes);
    }

    @Test
    public void findQuote() {
        QuoteDTO quote = theOneClient.findQuote(quoteId, new BasicNameValuePair("limit", "20"));
        Assert.assertNotNull(quote);
        System.out.println("\n\n Find Quote:");
        System.out.println(MessageFormat.format("\t{0}", quote.toString()));
    }

    @Test
    public void findNullQuote() {
        QuoteDTO quote = theOneClient.findQuote("a", new BasicNameValuePair("limit", "20"));
        Assert.assertNull(quote);
    }
}