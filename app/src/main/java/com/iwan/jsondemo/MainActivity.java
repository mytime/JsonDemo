package com.iwan.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iwan.jsondemo.Bean.Book;
import com.iwan.jsondemo.adapter.BookListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Gson Fast-json的实际应用案例
 *
 * 网络请求权限
 *
 * 图书列表
 *
 */


public class MainActivity extends AppCompatActivity {
    
    
    //url对象,www.juhe.cn/docs/api/id/50
    private String url = "http://apis.juhe.cn/goodbook/catalog?key=a2ac7f8df926e8d57cc978caOf1b12b&dtype=json";
    private ListView lv;
    private BookListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        
        //获取接口数据
        getData();
    }

    private void getData() {
        //
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //输出请求结果
                Log.i("info",s);

                //解析json数据
                try {
                    dealData(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }, new Response.ErrorListener() {
            //请求失败回调
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        //将请求加入Volley请求队列
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

    private void dealData(String s) throws JSONException {

        /**
         * Fast-json用法
         *
         */
//        JSONObject object = new JSONObject(s);
//
//        ArrayList<Book> books = (ArrayList<Book>) JSON.parseArray(object.getString("result"),Book.class);
//
//        //解析后实施adapte
//        adapter = new BookListAdapter(this,books);
//        lv.setAdapter(adapter);

        /**********************************************/
        /**
         * Gson 用法
         */
        //Gson对象
        Gson gson = new Gson();

        //集合
        Type listType = new TypeToken<ArrayList<Book>>(){
        }.getType();
        JSONObject object = new JSONObject(s);

        ArrayList<Book> books = gson.fromJson(object.getString("result"),listType);

        //解析后实施adapte
        adapter = new BookListAdapter(this,books);
        lv.setAdapter(adapter);
    }
}
