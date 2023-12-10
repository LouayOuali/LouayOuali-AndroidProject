package com.louay.anime;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.louay.anime.model.Anime;
import com.louay.anime.model.CharacterModel;
import com.louay.anime.model.Images;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    List<Anime> animes;
    Context context;
    //Context context;

    List<CharacterModel> character;

    GridViewAdapter(Context context, List<Anime> animes) {

        this.animes = animes;
        this.context = context;

    }



    @Override
    public int getCount() {
        return animes.toArray().length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View root =  layoutInflater.inflate(R.layout.grid_view_items,viewGroup,false);
        LinearLayout linearLayout = root.findViewById(R.id.grisViewItems);
        ImageView imageView = root.findViewById(R.id.animeImage);
        TextView textView = root.findViewById(R.id.animeTitle);
        textView.setText(animes.get(pos).titleEnglish);
        Picasso.get().load(animes.get(pos).images.jpg.imageUrl).into(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ConsultAnimePage.class);
                i.putExtra("title",animes.get(pos).titleEnglish);
                i.putExtra("rank",animes.get(pos).rank);
                i.putExtra("eps",String.valueOf(animes.get(pos).episodes));
                i.putExtra("score",String.valueOf(animes.get(pos).score));
                i.putExtra("id",animes.get(pos).malId);
                i.putExtra("source",animes.get(pos).source);
                i.putExtra("synopsis",animes.get(pos).synopsis);
                i.putExtra("type",animes.get(pos).type);
                i.putExtra("imageUrl",animes.get(pos).images.jpg.imageUrl);

                view.getContext().startActivity(i);
            }
        });

        return root;
    }

    public View getViewChar(int pos, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View root =  layoutInflater.inflate(R.layout.grid_view_items,viewGroup,false);
        LinearLayout linearLayout = root.findViewById(R.id.grisViewItems);
        ImageView imageView = root.findViewById(R.id.animeImage);
        TextView textView = root.findViewById(R.id.animeTitle);
        textView.setText(animes.get(pos).titleEnglish);
        Picasso.get().load(animes.get(pos).images.jpg.imageUrl).into(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ConsultAnimePage.class);
                i.putExtra("title",animes.get(pos).titleEnglish);
                i.putExtra("rank",animes.get(pos).rank);
                i.putExtra("eps",String.valueOf(animes.get(pos).episodes));
                i.putExtra("score",String.valueOf(animes.get(pos).score));
                i.putExtra("id",animes.get(pos).malId);
                i.putExtra("source",animes.get(pos).source);
                i.putExtra("synopsis",animes.get(pos).synopsis);
                i.putExtra("type",animes.get(pos).type);
                i.putExtra("imageUrl",animes.get(pos).images.jpg.imageUrl);

                view.getContext().startActivity(i);
            }
        });

        return root;
    }
}
