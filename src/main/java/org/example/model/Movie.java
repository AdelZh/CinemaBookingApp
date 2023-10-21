package org.example.model;

public class Movie {
    private Long id;
    private String title;
    private Genre genre;
    private int duration;

    public Movie(){

    }


    public Movie(Long id, String title, Genre genre, int duration) {
        this.id=id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getDuration() {
        return duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", duration=" + duration +
                '}';
    }
}
