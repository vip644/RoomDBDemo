package com.vipin.mygatetest.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vipin.mygatetest.model.DataClass;

import java.util.List;

/**
 * Created by vipin.c on 26/06/2019
 */

@Dao
public interface DataClassDao {

    @Query("SELECT * FROM dataclass ORDER BY id ASC")
    LiveData<List<DataClass>> getAllData();

    @Insert
    void addData(DataClass dataClass);
}
