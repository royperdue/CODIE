package com.codie.simulation.search.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codie.simulation.R;
import com.codie.simulation.search.models.ImageResult;

import java.util.List;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {
    private Context context;

    public ImageResultArrayAdapter (Context context, List<ImageResult> images){
        super(context, android.R.layout.simple_list_item_1, images);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageResult imageResult = getItem(position);
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_image_result, parent, false);
        }
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        ivImage.setImageResource(0);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

        tvTitle.setText(Html.fromHtml(imageResult.getTitle()));
        Glide.with(context).load(imageResult.getThumbUrl()).into(ivImage);
        return convertView;
    }
}
