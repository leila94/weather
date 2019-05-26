package com.example.sunshine.weather1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Leila on 4/15/2019.
 */

public class Response2 {

    @SerializedName("cod")
    public float cod;



    @SerializedName("list")
    @Expose
    public ArrayList<List1> list = new ArrayList<List1>();

}

class List1{
    @SerializedName("dt")
    public float dt;

    @SerializedName("main")
    public Main1 main;

    @SerializedName("wind")
    public Wind1 wind;

    @SerializedName("weather")
    public ArrayList<Weather1> weather = new ArrayList<Weather1>();



}

class Weather1 {
    @SerializedName("id")
    public int id;
    @SerializedName("main")
    @Expose
    public String main;
    @Expose
    @SerializedName("description")
    public String description;
    @SerializedName("icon")
    public String icon;
}

class Main1 {
    @SerializedName("temp")
    public float temp;
    @SerializedName("humidity")
    public float humidity;
    @SerializedName("pressure")
    public float pressure;
    @SerializedName("temp_min")
    public float temp_min;
    @SerializedName("temp_max")
    public float temp_max;
}

class Wind1 {
    @SerializedName("speed")
    public float speed;
    @SerializedName("deg")
    public float deg;
}
