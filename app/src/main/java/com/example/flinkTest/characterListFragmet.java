package com.example.flinkTest;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flinkTest.Models.character;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class characterListFragmet extends Fragment implements CharacterAdapter.CharacterClickListener {
    private List<character> characterList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CharacterAdapter myAdapter;
    private fragmentInterface mCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.character_list, container, false);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        DisplayMetrics displaysize = getContext().getResources().getDisplayMetrics();
        float displaywith = displaysize.widthPixels / displaysize.density;
        float columnWidthDp = 200;
        int columns = (int) (displaywith / columnWidthDp + 0.5);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),columns);
        mRecyclerView = rootView.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        myAdapter = new CharacterAdapter(getContext(),characterList,this);
        mRecyclerView.setAdapter(myAdapter);
        mCallback = (fragmentInterface) getContext();
        if (savedInstanceState==null) {
            getCharacters();
        }else{
            characterList = savedInstanceState.getParcelableArrayList("saved");
            if (characterList.size()==0){
                getCharacters();
            }
            myAdapter.setCharacters(characterList);
            //mCallback.onCharactersLoaded(true);
        }
        return rootView;
    }

    private void getCharacters() {
        MyAsyncTask myAsyncTask =  new MyAsyncTask(getContext(),this);
        myAsyncTask.execute();
    }

    @Override
    public void onItemClick(character character,View view) {
        mCallback.onItemSelected(character,view);
    }

    public void populateList(JSONArray arrayList) {
        if (arrayList==null){
            Toast.makeText(getContext(),"No hay internet, intentelo denuevo mas tarde",Toast.LENGTH_LONG).show();
            return;
        }
        for (int i = 0; i<arrayList.length();i++){
            try {
                JSONObject json = arrayList.getJSONObject(i);
                JSONObject tmp = json.getJSONObject("origin");
                JSONObject tmp2 = json.getJSONObject("location");
                characterList.add(new character(
                        json.getInt("id"),
                        json.getString("name"),
                        json.getString("status"),
                        json.getString("species"),
                        json.getString("type"),
                        json.getString("gender"),
                        tmp.getString("name"),
                        tmp.getString("url"),
                        tmp2.getString("name"),
                        tmp2.getString("url"),
                        json.getString("image"),
                        json.getString("url"),
                        json.getString("created")
                        ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        myAdapter.setCharacters(characterList);
        mCallback.onCharactersLoaded(true);
    }

    public void search(String filter) {
       //TODO recorremos todos los datos en busca de los matches, solucion practica para pocos datos, en caso de que fuera mas grande pondria una base de datos y hacer un query "LIKE"
        List<character> filtered = new ArrayList<character>();
        for(int i=0 ;i<characterList.size();i++){
            if(characterList.get(i).getName().toUpperCase().contains(filter.toUpperCase())) {
                filtered.add(characterList.get(i));
            }
        }
        if(filtered.size()>0){
            myAdapter.setCharacters(filtered);
        }else{
            Toast.makeText(getContext(),"No se encontraron coincidencias",Toast.LENGTH_LONG).show();
        }
    }

    public interface fragmentInterface{
        void onCharactersLoaded(Boolean flag);
        void onItemSelected(character character, View view);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("saved",new ArrayList<Parcelable>(characterList));
    }
}
