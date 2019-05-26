package com.example.sunshine.weather1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Leila on 5/18/2019.
 */
@Entity(tableName = "cityID")
public class JsonId {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "cid")
    private int cid;

    @ColumnInfo(name = "name")
    private String name;

    public JsonId(int cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
