package com.example.flinkTest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentManager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.flinkTest.Models.character;

public class MainActivity extends AppCompatActivity implements characterListFragmet.fragmentInterface {
    private ProgressBar progressBar;
    private characterListFragmet fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.pb);
        FragmentManager fm = getSupportFragmentManager();
        fragment = (characterListFragmet)fm.findFragmentById(R.id.master_list_fragment);
        getWindow().setExitTransition(null);
        if (savedInstanceState!=null){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCharactersLoaded(Boolean flag) {
        if (flag){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemSelected(character character, View view) {
        Intent intent = new Intent(this,CharacterDetail.class);

        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this,view,"image").toBundle();
        intent.putExtra("character",character);
        startActivity(intent,bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fragment.search(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("pb",false);
    }
}
