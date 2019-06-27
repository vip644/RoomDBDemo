package com.vipin.mygatetest.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vipin.mygatetest.R;
import com.vipin.mygatetest.model.DataClass;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by vipin.c on 22/06/2019
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<DataClass> mList;

    RecyclerAdapter(Context context, List<DataClass> list) {
        mContext = context;
        mList = list;
    }

    void setList(List<DataClass> dataList) {
        mList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
            int position) {
        return new ViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_view, viewGroup, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder viewHolder, int position) {

        viewHolder.userName.setText("User " + mList.get(position).getId());
        viewHolder.randomNum.setText("# " + mList.get(position).getRandomNum());
        byte[] outImage = mList.get(position).getImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        viewHolder.mImageView.setImageBitmap(theImage);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView userName;
        TextView randomNum;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_id);
            userName = itemView.findViewById(R.id.user_name);
            randomNum = itemView.findViewById(R.id.random_no);
        }
    }
}
