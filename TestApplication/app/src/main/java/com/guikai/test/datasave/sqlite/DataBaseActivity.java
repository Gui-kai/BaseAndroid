package com.guikai.test.datasave.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.guikai.test.R;

/**
 * Description:
 * Crete by Anding on 2019-10-29
 */
public class DataBaseActivity extends AppCompatActivity implements View.OnClickListener {

    private MyDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        database = new MyDatabase(this, "BookStore.db", null, 3);

        TextView tv_create = findViewById(R.id.create);
        Button bt_add = findViewById(R.id.add);
        Button bt_delete = findViewById(R.id.delete);
        Button bt_update = findViewById(R.id.update);
        Button bt_search = findViewById(R.id.search);

        tv_create.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_update.setOnClickListener(this);
        bt_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                database.getWritableDatabase();
                break;
            case R.id.add:
                add();
                break;
            case R.id.delete:
                delete();
                break;
            case R.id.update:
                update();
                break;
            case R.id.search:
                search();
                break;
            default:
                break;
        }
    }

    private void add() {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        //开始组装第一条数据
        values.put("name","Java Think");
        values.put("author","guikai");
        values.put("price","188");
        values.put("pages","555");
        db.insert("Book",null,values);
        values.clear();
        values.put("name","算法导论");
        values.put("author","guikai");
        values.put("price","188");
        values.put("pages","555");
        db.insert("Book",null,values);
        values.clear();
        Toast.makeText(this, "插入2条数据", Toast.LENGTH_LONG).show();
    }

    private void delete() {
        SQLiteDatabase db = database.getReadableDatabase();
        db.delete("Book","pages > ?",new String[]{"500"});
        Toast.makeText(this, "删除成功", Toast.LENGTH_LONG).show();
    }

    private void update() {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("author","爷爷");
        db.update("Book",values,"name = ?",new String[]{"Java Think"});
        Toast.makeText(this, "更新书名Java Think字段", Toast.LENGTH_LONG).show();
    }

    private void search() {
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.query("Book",null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.d("xxx","书名： "+name);
                Log.d("xxx","作者： "+author);
                Log.d("xxx","页数： "+pages);
                Log.d("xxx","价格： "+price);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

}
