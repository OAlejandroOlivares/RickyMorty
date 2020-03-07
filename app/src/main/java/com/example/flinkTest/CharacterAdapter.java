package com.example.flinkTest;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flinkTest.Models.character;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewHolder> {
    private Context context;
    private List<character> characterList;
    private CharacterClickListener listener;

    public CharacterAdapter(Context context, List<character> characterList, CharacterClickListener listener) {
        this.context = context;
        this.characterList = characterList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder view = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.character_item,parent,false));
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        character character = characterList.get(position);
        //TODO Usamos la libreria picaso para el manejo de errores en caso de que falle la peticion para la imagen y nos ayuda a cachear las mismas
        Picasso.get().load(Uri.parse(character.getImage())).error(R.mipmap.noimageavailable).networkPolicy(NetworkPolicy.OFFLINE).into(holder.image);
        holder.name.setText(character.getName());
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public void setCharacters(List<character> characterList) {
        this.characterList = characterList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private MaterialTextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(characterList.get(getAdapterPosition()),image);
                }
            });
        }
    }

    public interface CharacterClickListener{
        void onItemClick(character character,View view);
    }
}
