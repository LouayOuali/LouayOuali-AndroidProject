package com.louay.anime.service;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.louay.anime.model.AnimeResponse;
import com.louay.anime.model.CharacterResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface AnimeServiceAPI {
    @GET("/v4/anime")
    Call<AnimeResponse> searchAnime(@Query("q") String query);

    @GET("/v4/anime/{id}/characters")
    Call<CharacterResponse> getCharchters(@Path("id") int id);



}
