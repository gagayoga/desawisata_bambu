package com.example.desawisatabambu.Model;

public class Rekomendasi {
    private int imageResource;
    private String text;

    public Rekomendasi(int imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }
}
