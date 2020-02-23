package com.example.otppinview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends BaseAdapter {

    int[] icon;
    String [] options;
    Context context;
    private LayoutInflater inflater;

    CustomAdapter(Context context,String[] options, int[] icon){

        this.context = context;
        this.options = options;
        this.icon = icon;
    }

    @Override
    public int getCount() {
        return options.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
           inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.sample_list_view,parent,false);
        }
        ImageView imageView = convertView.findViewById(R.id.image_view_option);
        TextView textView = convertView.findViewById(R.id.text_view_option);
        ImageButton imageButton = convertView.findViewById(R.id.image_button_arrow);

        imageView.setImageResource(icon[position]);
        textView.setText(options[position]);
        return convertView;
    }
}
