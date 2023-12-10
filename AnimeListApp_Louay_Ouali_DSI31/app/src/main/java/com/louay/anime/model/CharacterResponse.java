package com.louay.anime.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CharacterResponse {

    @SerializedName("data")
    public List<CharacterModel> characters;
}
