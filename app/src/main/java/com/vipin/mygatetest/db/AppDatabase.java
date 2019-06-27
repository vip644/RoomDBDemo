package com.vipin.mygatetest.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.vipin.mygatetest.db.dao.DataClassDao;
import com.vipin.mygatetest.model.DataClass;

/**
 * Created by vipin.c on 26/06/2019
 */
@Database(entities = {DataClass.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DataClassDao mDataClassDao();
}
