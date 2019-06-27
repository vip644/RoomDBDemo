package com.vipin.mygatetest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by vipin.c on 22/06/2019
 */

@Entity(tableName = "dataclass")
public class DataClass {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "passcode")
    private int randomNum;

    @ColumnInfo(name = "imagedata")
    private byte[] image;

    public DataClass() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
