package vn.techmaster.films.films.model;

public class Film {
    private String title;
    private String description;
    private String director;
    private int year;

    public Film(String title, String description, String director, int year) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.year = year;
    }

    public Film(String title, String director) {
        this.title = title;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
