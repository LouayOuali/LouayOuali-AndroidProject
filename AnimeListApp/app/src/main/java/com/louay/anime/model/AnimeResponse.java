package com.louay.anime.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AnimeResponse {
    @SerializedName("data")
    public List<Anime> animes= new ArrayList<>();

}
