package com.example.qghomeworkapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private SQLiteOpenHelper dpHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        dpHelper = new Database(this,"BookStore",null,1);
        editText = (EditText)findViewById(R.id.Edit_query);
        SQLiteDatabase db = dpHelper.getWritableDatabase();
        Cursor cursor = db.query("Book",null,null,null,null,null,null);
        String[] data2 = new String[]{};
        if(cursor.moveToFirst())
            do {
                String data = cursor.getString(cursor.getColumnIndex("English"));
                String data1[] = new String[]{data};
                int strLen1 = data1.length;
                int strLen2 = data2.length;
                data2 = Arrays.copyOf(data2,strLen1+strLen2);
            } while (cursor.moveToNext());
        cursor.close();
        String op;
        for (int i = 0; i <data2.length/2 ; i++) {
            op=data2[data2.length-i-1];
            data2[data2.length-i-1]=data2[i];
            data2[i]=op;
            System.out.println(data2.length);
            System.out.println(op);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        MainActivity.this, android.R.layout.simple_expandable_list_item_1, data2);
        ListView listView = (ListView) findViewById(R.id.history_view);
        listView.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.Search_badge);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = editText.getText().toString();
//                Intent intent = new Intent(MainActivity.this,myE.class);
//                intent.putExtra("word_v",inputText);
//                startActivity(intent);
                Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();
                SQLiteDatabase db = dpHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("English",inputText);
                db.insert("Book",null,values);
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                String[] data2 = new String[]{};
                if(cursor.moveToFirst())
                    do {
                        String data = cursor.getString(cursor.getColumnIndex("English"));
                        String data1[] = new String[]{data};
                        int strLen1 = data1.length;
                        int strLen2 = data2.length;
                        data2 = Arrays.copyOf(data2,strLen1+strLen2);

                    } while (cursor.moveToNext());
                cursor.close();
                String op;
                for (int i = 0; i <data2.length/2 ; i++) {
                    op=data2[data2.length-i-1];
                    data2[data2.length-i-1]=data2[i];
                    data2[i]=op;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        MainActivity.this, android.R.layout.simple_expandable_list_item_1, data2);
                ListView listView = (ListView) findViewById(R.id.history_view);
                listView.setAdapter(adapter);
            }
        });
    }
}
