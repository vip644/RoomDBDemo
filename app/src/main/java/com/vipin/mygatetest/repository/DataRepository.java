package com.vipin.mygatetest.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.vipin.mygatetest.db.AppDatabase;
import com.vipin.mygatetest.model.DataClass;

import java.util.List;

/**
 * Created by vipin.c on 26/06/2019
 */
public class DataRepository {

    private static final String DB_NAME = "mydatabase.db";

    private static AppDatabase mAppDatabase;

    public DataRepository(Context context) {
        mAppDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public void insertData(int randomNum, byte[] imageData){
        DataClass dataClass = new DataClass();
        dataClass.setRandomNum(randomNum);
        dataClass.setImage(imageData);

        insertTask(dataClass);
    }

    public static void insertTask(final DataClass dataClass) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mAppDatabase.mDataClassDao().addData(dataClass);
                return null;
            }
        }.execute();
    }

    public LiveData<List<DataClass>> fetchDbData(){
        return mAppDatabase.mDataClassDao().getAllData();
    }
}
