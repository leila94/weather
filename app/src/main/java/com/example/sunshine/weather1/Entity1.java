package com.example.sunshine.weather1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;

/**
 * Created by Leila on 4/24/2019.
 */

@Entity(tableName = "parms")
public class Entity1 {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "icon2")
    private String icon2;

    @ColumnInfo(name = "cityName")
    private String cityName;

    @ColumnInfo(name = "cityId")
    private int cityId;

    @ColumnInfo(name = "main")
    private String main;

    @ColumnInfo(name = "temp")
    private String temp;

    @ColumnInfo(name = "wind")
    private String wind;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "hum")
    private String hum;

    public Entity1(String icon2, String cityName, int cityId, String main, String temp, String wind, String hum, String date) {

        this.icon2 = icon2;
        this.cityName = cityName;
        this.cityId = cityId;
        this.main = main;
        this.temp = temp;
        this.wind = wind;
        this.date = date;
        this.hum = hum;
    }



    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getIcon2(){
        return icon2;
    }

    public void setIcon2(String icon){
        this.icon2=icon;
    }

    public String getCityName(){
        return cityName;
    }

    public void setCityName(String cityName){
        this.cityName=cityName;
    }

    public String getMain(){
        return main;
    }

    public void setMain(String main){
        this.main=main;
    }

    public String getTemp(){
        return temp;
    }

    public void setTemp(String temp){
        this.temp=temp;
    }

    public String getWind(){
        return wind;
    }

    public void setWind(String wind){
        this.wind=wind;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String press){
        this.date=date;
    }

    public String getHum(){
        return hum;
    }

    public void setHum(String hum){
        this.hum=hum;
    }


}
