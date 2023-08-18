package org.acme.examples.model;

public class Movie {

    private String name;
    private int releaseYear;
    private Rating rating;
    private MovieGenre genre;

    public String getName() {
        return name;
    }

    public Movie setName(String name) {
        this.name = name;
        return this;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Movie setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public Rating getRating() {
        return rating;
    }

    public Movie setRating(Rating rating) {
        this.rating = rating;
        return this;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public Movie setGenre(MovieGenre genre) {
        this.genre = genre;
        return this;
    }
}
