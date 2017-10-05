package com.popland.pop.aboutjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hai on 27/06/2016.
 */
public class CustomBaseAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<sinhVien> items;
    public CustomBaseAdapter(Context context, int layout,List<sinhVien> items){
        myContext = context;
        myLayout = layout;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
class ViewHolder{
    TextView tvTen, tvMaso, tvLop;
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(v==null){
            v = inflater.inflate(myLayout,null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvTen = (TextView)v.findViewById(R.id.TVten);
            viewHolder.tvLop = (TextView)v.findViewById(R.id.TVlop);
            viewHolder.tvMaso = (TextView)v.findViewById(R.id.TVmaso);
            v.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) v.getTag();
        holder.tvTen.setText(items.get(position).ten);
        holder.tvMaso.setText(items.get(position).maso+"");
        holder.tvLop.setText(items.get(position).lop);
        return v;
    }
}
