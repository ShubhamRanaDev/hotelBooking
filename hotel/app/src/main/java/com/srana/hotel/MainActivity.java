package com.srana.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase mydb;
    private Log log;
    EditText userName ;
    EditText password;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.main_username);
        password = findViewById(R.id.main_password);
        mydb = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS  users (name VARCHAR, password VARCHAR,email VARCHAR,region VARCHAR,phone INT(10))");
       // mydb.execSQL("INSERT INTO users(name,password,email,region,phone) VALUES ('Shubham','123456','shubhamr174@gmail.com','India',4383640486)");
        imageView=findViewById(R.id.imageView);
        imageView.animate().rotationX(360).setDuration(2000);
    }

    public void loginonclick(View view) {
        Cursor c=mydb.rawQuery("SELECT * FROM users WHERE name='"+userName.getText()+"'",null);
        int nameIndex=c.getColumnIndex("name");
        int passIndex=c.getColumnIndex("password");
        c.moveToFirst();
        if(c.getString(passIndex).equals(password.getText().toString()))
        {
            Intent i = new Intent(getApplicationContext(), hotel_list.class);
            startActivity(i);
            Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Wrong name or password", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearonclick(View view) {
        userName.setText("");
        password.setText("");
    }

    public void register(View view) {
        Intent i = new Intent(getApplicationContext(), register.class);
        startActivity(i);
    }
}
