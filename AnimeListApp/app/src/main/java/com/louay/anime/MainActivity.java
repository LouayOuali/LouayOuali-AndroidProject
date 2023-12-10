package com.louay.anime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.louay.anime.model.Anime;
import com.louay.anime.model.AnimeResponse;
import com.louay.anime.service.AnimeServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText searchField;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button searchButton;
    GridView gridView;
    List data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchField = findViewById(R.id.searchAnime);
        searchButton = findViewById(R.id.find);
        gridView = findViewById(R.id.gridView);
        radioGroup = findViewById(R.id.radioGroup);

        GridViewAdapter gridViewAdapter = new GridViewAdapter(getApplicationContext(),data);
        gridView.setAdapter(gridViewAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String query=String.valueOf(searchField.getText());
                AnimeServiceAPI animeServiceAPI = retrofit.create(AnimeServiceAPI.class);


                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                if(radioButton.isChecked())
                    query = query + "&"+radioButton.getText().toString();

                Call<AnimeResponse> callAnime = animeServiceAPI.searchAnime(query);
                callAnime.enqueue(new Callback<AnimeResponse>() {
                    @Override
                    public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                        if(!response.isSuccessful()) {
                            Log.i("info",String.valueOf(response.code()));


                        }
                        AnimeResponse animeResponse = response.body();
                        data.clear();
                        for (Anime anime : animeResponse.animes){
                            /*
                            data.add(anime.images.jpg.imageUrl);
                            data.add(anime.malId);
                            data.add(anime.episodes);
                            data.add(anime.rank);
                            data.add(anime.score);
                            data.add(anime.type);
                            data.add(anime.source);
                            data.add(anime.titleEnglish);
                            */

                            data.add(anime);
                        }

                        gridViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<AnimeResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

}