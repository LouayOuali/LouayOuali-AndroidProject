package com.louay.anime.model;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

public class Anime {

    @SerializedName("mal_id")
    public int malId;

    //@SerializedName("images")
    public Images images;

    @SerializedName("title_english")
    public String titleEnglish;

    public String type;
    public String source;
    public int episodes;
    public float score;
    public int rank;
    public String synopsis;



}
