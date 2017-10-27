package com.huutrung.docbaoturss;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 10/26/2017.
 */

public class NewsAdapter extends ArrayAdapter<NewsModel> {
    private LayoutInflater inflater;
    public NewsAdapter(@NonNull Context context, @LayoutRes int resource,
                       @NonNull ArrayList<NewsModel> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_news, null);
        }

        ImageView imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);

        //lay doi tuong ra
        NewsModel md = getItem(position);
        txtTitle.setText(md.getTitle());

        //view anh dung thu vien Picasso(app nhan F4 tim kiem picasso va add vao)
        // md luu cai URl

        Picasso.with(inflater.getContext()).load(md.getImageURL()).into(imgAvatar);
        return convertView;
    }
}
