package com.example.sunshine.weather1;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Leila on 5/18/2019.
 */
@Entity(tableName = "cityID")
public class JsonId {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "cid")
    private int i;

    @ColumnInfo(name = "name")
    private String j;

    /*public JsonId(int cid, String name) {
        this.i = cid;
        this.j = name;
    }*/

    public JsonId() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String name) {
        this.j = name;
    }

    public int getI() {
        return i;
    }

    public void setI(int cid) {
        this.i = cid;
    }
}
