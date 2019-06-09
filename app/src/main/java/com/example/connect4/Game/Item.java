package com.example.connect4.Game;

public class Item {

    private String title;
    private String description;
    private String temps;

    public Item() {
        super();
    }

    public Item(String title, String description, String temps) {
        super();
        this.title = title;
        this.description = description;
        this.temps = temps;
    }

    public Item(String title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTittle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String tempsdescription) {
        this.temps = temps;
    }

}

