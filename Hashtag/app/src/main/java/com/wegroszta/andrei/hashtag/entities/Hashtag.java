package com.wegroszta.andrei.hashtag.entities;

public class Hashtag {
    private int id;
    private String name;

    public Hashtag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hashtag(String name) {
        this.name = name;
    }

    public Hashtag() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
