package com.louay.anime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.louay.anime.model.Character;
import com.louay.anime.model.CharacterModel;
import com.louay.anime.model.CharacterResponse;
import com.louay.anime.service.AnimeServiceAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultAnimePage extends AppCompatActivity {
    List<CharacterModel> data = new ArrayList<>();
    TextView titleView,animeType,animeScore,animeStatus,animeEpisode,animeSynopsis;

    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_anime_page);
        titleView = findViewById(R.id.animeTitle);
        animeType = findViewById(R.id.animeType);
        animeScore = findViewById(R.id.animeScore);
        animeEpisode = findViewById(R.id.animeEpisode);
        animeSynopsis = findViewById(R.id.animeSynopsis);

        gridView = findViewById(R.id.gridView);
        ImageView image = findViewById(R.id.imageView);
        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String imageUrl = intent.getStringExtra("imageUrl");
        String type = intent.getStringExtra("type");
        String status = intent.getStringExtra("status");
        String episode = intent.getStringExtra("eps");
        String score = intent.getStringExtra("score");
        String syn = intent.getStringExtra("synopsis");
        int id = intent.getIntExtra("id",1);
        Log.i("anime_id",String.valueOf(id));

        Picasso.get().load(imageUrl).into(image);
        titleView.setText(title);
        animeType.setText("Type : "+type);
        //animeStatus.setText("Status : "+status);
        animeEpisode.setText("Episode : "+episode);
        animeScore.setText("Score : "+score);
        animeSynopsis.setText("Description : "+syn);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnimeServiceAPI animeServiceAPI = retrofit.create(AnimeServiceAPI.class);
        Call<CharacterResponse> callCharacters = animeServiceAPI.getCharchters(id);
        callCharacters.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                if(!response.isSuccessful()) {
                    Log.i("RESPONSE CODE",String.valueOf(response.code()));
                    return;

                }
                CharacterResponse characterResponse = response.body();
                for (CharacterModel characters : characterResponse.characters){
                    data.add(characters);

                }
                GridViewAdapterCharacter gridViewAdapterCharacter = new GridViewAdapterCharacter(getApplicationContext(),data);
                gridView.setAdapter(gridViewAdapterCharacter);

                gridViewAdapterCharacter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {

            }
        });

    }
}