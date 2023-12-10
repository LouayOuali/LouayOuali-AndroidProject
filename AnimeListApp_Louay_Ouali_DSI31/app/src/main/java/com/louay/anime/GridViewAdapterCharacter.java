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
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridViewAdapterCharacter extends BaseAdapter {
    List<CharacterModel> character;
    Context context;
    //Context context;

    //List<CharacterModel> character;

    GridViewAdapterCharacter(Context context, List<CharacterModel> character) {

        this.character = character;
        this.context = context;

    }


    @Override
    public int getCount() {
        return character.toArray().length;
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
        View root = layoutInflater.inflate(R.layout.grid_view_items, viewGroup, false);
        LinearLayout linearLayout = root.findViewById(R.id.grisViewItems);
        ImageView imageView = root.findViewById(R.id.animeImage);
        TextView textView = root.findViewById(R.id.animeTitle);
        textView.setText(character.get(pos).character.name);
        Picasso.get().load(character.get(pos).character.images.jpg.imageUrl).into(imageView);



        return root;
    }

}