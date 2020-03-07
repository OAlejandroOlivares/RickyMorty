package com.example.flinkTest.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
//TODO Objeto parceable para facilitar compartir datos entre actividades y salvar los mismos onsavedinstance
public class character implements Parcelable {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String origin_name;
    private String origin_url;
    private String location_name;
    private String location_url;
    private String image;
    private String url;
    private String created;

    public character(Integer id, String name, String status, String species, String type, String gender, String origin_name, String origin_url, String location_name, String location_url, String image, String url, String created) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin_name = origin_name;
        this.origin_url = origin_url;
        this.location_name = location_name;
        this.location_url = location_url;
        this.image = image;
        this.url = url;
        this.created = created;
    }

    protected character(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        status = in.readString();
        species = in.readString();
        type = in.readString();
        gender = in.readString();
        origin_name = in.readString();
        origin_url = in.readString();
        location_name = in.readString();
        location_url = in.readString();
        image = in.readString();
        url = in.readString();
        created = in.readString();
    }

    public static final Creator<character> CREATOR = new Creator<character>() {
        @Override
        public character createFromParcel(Parcel in) {
            return new character(in);
        }

        @Override
        public character[] newArray(int size) {
            return new character[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }

    public String getOrigin_url() {
        return origin_url;
    }

    public void setOrigin_url(String origin_url) {
        this.origin_url = origin_url;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_url() {
        return location_url;
    }

    public void setLocation_url(String location_url) {
        this.location_url = location_url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(status);
        dest.writeString(species);
        dest.writeString(type);
        dest.writeString(gender);
        dest.writeString(origin_name);
        dest.writeString(origin_url);
        dest.writeString(location_name);
        dest.writeString(location_url);
        dest.writeString(image);
        dest.writeString(url);
        dest.writeString(created);
    }
}
