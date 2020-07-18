package com.srana.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText name;
    EditText password;
    EditText email;
    EditText region;
    EditText phone;
    SQLiteDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name=findViewById(R.id.register_name);
        password=findViewById(R.id.register_password);
        email=findViewById(R.id.register_email);
        region =findViewById(R.id.register_region);
        phone=findViewById(R.id.register_phone);
        mydb = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
    }

    public void register_submit(View view) {
        if(!(name.getText().toString().equals("") ||password.getText().toString().equals("") ||email.getText().toString().equals("") || region.getText().toString().equals("") || phone.getText().toString().equals("")))
        {
            name.getText().toString();
            mydb.execSQL("INSERT INTO users(name,password,email,region,phone) VALUES ('"+name.getText().toString()+"','"+password.getText().toString()+"','"+email.getText().toString()+"','"+region.getText().toString()+"',"+phone.getText().toString()+")");
            Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show();
        }
    }

    public void register_clear(View view) {
        name.setText("");
        password.setText("");
        email.setText("");
        region.setText("");
        phone.setText("");
    }
}
