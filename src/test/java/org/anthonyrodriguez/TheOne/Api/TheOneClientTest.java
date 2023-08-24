package org.anthonyrodriguez.TheOne.Api;

import org.anthonyrodriguez.TheOne.Api.dto.MovieDTO;
import org.anthonyrodriguez.TheOne.Api.dto.QuoteDTO;
import org.anthonyrodriguez.helpers.OperatorNameValuePair;
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
    private TheOneClient theOneClient;

    @Before
    public void setUp() throws Exception {
        this.theOneClient = new TheOneClient("https://the-one-api.dev/v2", "rJsI5xsfcv93LvIttc__", new TestLog());
    }

    @After
    public void tearDown() throws Exception {
        this.theOneClient = null;
    }

    @Test
    public void listMovies() {
        MovieDTO[] movies = theOneClient.listMovies(new BasicNameValuePair("limit", "20"), new OperatorNameValuePair("academyAwardWins", "0", ">"));
        Assert.assertNotNull(movies);
        System.out.println("\n\n List Movies:");
        for (MovieDTO movie : movies) {
            System.out.println(MessageFormat.format("\t{0}", movie.toString()));
        }
    }

    @Test
    public void findMovie() {
        MovieDTO movie = theOneClient.findMovie(movieId);
        Assert.assertNotNull(movie);
        System.out.println("\n\n Find Movie:");
        System.out.println(MessageFormat.format("\t{0}", movie.toString()));
    }

    @Test
    public void listQuotes() {
        QuoteDTO[] quotes = theOneClient.listQuotes(new BasicNameValuePair("limit", "20"));
        Assert.assertNotNull(quotes);
        System.out.println("\n\n List Quotes:");
        for (QuoteDTO quote : quotes) {
            System.out.println(MessageFormat.format("\t{0}", quote.toString()));
        }
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