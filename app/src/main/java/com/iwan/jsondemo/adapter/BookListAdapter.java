package com.iwan.jsondemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iwan.jsondemo.Bean.Book;
import com.iwan.jsondemo.R;

import java.util.ArrayList;

/**
 * Created by iwan on 16/1/6.
 */
public class BookListAdapter extends BaseAdapter{

    //属性
    private Context c;
    private ArrayList<Book> list;


    //构造方法

    public BookListAdapter(Context context,ArrayList<Book> books) {
        this.c = context;
        this.list = books;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //载入ViewHolder
        ViewHolder holder = null;
        if (holder==null){
            convertView=View.inflate(c, R.layout.list_item,null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            //设置布局Tag，
            convertView.setTag(holder);

        }else{
            //布局存在，直接获取布局Tag
            holder = (ViewHolder) convertView.getTag();
        }

        Book b = list.get(position);
        holder.tv.setText(b.getCatalog() + "/n"+ b.getId());
        return convertView;
    }

    //新建一个ViewHolder
    class ViewHolder{
        TextView tv;
    }
}
