package com.example.flinkTest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flinkTest.Models.character;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class CharacterDetail extends AppCompatActivity {
    private MaterialTextView status;
    private MaterialTextView gender;
    private MaterialTextView location;
    private MaterialTextView origin;
    private MaterialTextView species;
    private MaterialTextView type;
    private MaterialTextView created;
    private com.example.flinkTest.Models.character character;
    private ImageView image;
    private MaterialTextView url;
    private MaterialTextView name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_detail);
        if (savedInstanceState==null) {
            Intent intent = getIntent();
            if (!intent.hasExtra("character")) {
                finish();
            } else {
                character = intent.getParcelableExtra("character");
            }
        }else{
            character = savedInstanceState.getParcelable("saved");
        }
        status = findViewById(R.id.status_value);
        url = findViewById(R.id.url_value);
        name = findViewById(R.id.materialTextView);
        image = findViewById(R.id.imageView2);
        created = findViewById(R.id.created_value);
        gender = findViewById(R.id.gender_value);
        type = findViewById(R.id.type_value);
        location = findViewById(R.id.location_value);
        origin = findViewById(R.id.origin_value);
        species = findViewById(R.id.species_value);
        status.setText(character.getStatus());
        gender.setText(character.getGender());
        location.setText(character.getLocation_name());
        origin.setText(character.getOrigin_name());
        species.setText(character.getSpecies());
        type.setText(character.getType());
        created.setText(character.getCreated());
        name.setText(character.getName());
        url.setText(character.getUrl());
        //TODO Usamos la libreria picaso para el manejo de errores en caso de que falle la peticion para la imagen y nos ayuda a cachear las mismas
        Picasso.get().load(Uri.parse(character.getImage())).error(R.mipmap.noimageavailable).networkPolicy(NetworkPolicy.OFFLINE).into(image);
        getWindow().setEnterTransition(null);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("saved",character);
    }
}
