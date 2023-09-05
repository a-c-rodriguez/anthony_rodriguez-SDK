package io.github.a_c_rodriguez.TheOne.Api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteDTO {
    String quoteId;
    String dialog;
    String movieId;
    String characterId;


    @JsonProperty("_id")
    public String getQuoteId() {
        return this.quoteId;
    }

    public void setQuoteId(String _id) {
        this.quoteId = _id;
    }

    @JsonProperty("dialog")
    public String getDialog() {
        return this.dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    @JsonProperty("movie")
    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    @JsonProperty("character")
    public String getCharacterId() {
        return this.characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }


    @JsonProperty("id")
    @JsonIgnore(true)
    private String getId() {
        return getQuoteId();
    }

    @Override
    public String toString() {
        return "QuoteDTO{" +
                "_id='" + quoteId + '\'' +
                ", dialog='" + dialog + '\'' +
                ", movieId='" + movieId + '\'' +
                ", characterId='" + characterId + '\'' +
                '}';
    }
}
