package com.example.springmongodb;
import java.util.List;

public class Movie {
    String plot;

    public Movie() {
    }

    List<String> genres;
    String title;

    public Movie(String plot, List<String> genres, String title) {
        this.plot = plot;
        this.genres = genres;
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }
    public void setPlot(String plot) {
        this.plot = plot;
    }
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "Movie [\n  plot=" + plot + ",\n  genres=" + genres + ",\n  title=" + title + "\n]";
    }
}
