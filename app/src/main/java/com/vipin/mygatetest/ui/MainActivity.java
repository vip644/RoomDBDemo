package com.vipin.mygatetest.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.vipin.mygatetest.R;
import com.vipin.mygatetest.model.DataClass;
import com.vipin.mygatetest.repository.DataRepository;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 1888;
    private RecyclerAdapter mRecyclerAdapter;
    private List<DataClass> mList;
    private DataRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Test App - Vipin Chauhan");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
            setUpPermission();
        }

        mRepository = new DataRepository(this);
        mRecyclerAdapter = new RecyclerAdapter(this, mList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mRecyclerAdapter);

        updateView();
    }

    private void setUpPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                },
                101
        );
    }

    private void updateView() {
        mRepository.fetchDbData().observe(this, new Observer<List<DataClass>>() {
            @Override
            public void onChanged(@Nullable List<DataClass> dataClasses) {
                mRecyclerAdapter.setList(dataClasses);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST) {

            if (data == null) return;

            Bitmap image = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if (image != null) {
                image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            }
            byte[] imageInByte = stream.toByteArray();
            mRepository.insertData(getRandomNumber(), imageInByte);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    private void refreshListView() {

        mRepository.fetchDbData().observe(this, new Observer<List<DataClass>>() {
            @Override
            public void onChanged(@Nullable List<DataClass> dataClasses) {
                mRecyclerAdapter.setList(dataClasses);
            }
        });
    }


    @SuppressLint("DefaultLocale")
    public int getRandomNumber() {
        Random r = new Random(System.currentTimeMillis());
        return 100000 + r.nextInt(99999);
    }

}
