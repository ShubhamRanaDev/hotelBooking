package com.srana.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class activity_hotel_detail extends AppCompatActivity {

    String myTitle[] = {"Westin Bear Mountain Resort", "Bond Place Hotel", "Cambridge Suites", "Toronto Don Valley", "Hotel Fairmont The Queen Elizabeth","Chelsea Hotel"};
    String myDescription[] = {"British Columbia", "Toronto", "Nova Scotia", "Toronto", "Montreal","Toronto"};
    int images[] = {R.drawable.hotel6, R.drawable.hotel5, R.drawable.hotel3, R.drawable.hotel4, R.drawable.hotel2,R.drawable.hotel1};
    String price[]={"549","449","696","231","271","331"};
    ImageView hotelImage;
    TextView hotelTitle;
    TextView hotelDesc;
    TextView hotelPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        hotelImage = findViewById(R.id.hotel_Image);
        hotelTitle = findViewById(R.id.hotel_title);
        hotelDesc = findViewById(R.id.hotel_detail);
        hotelPrice = findViewById(R.id.hotel_price);

        if(bundle != null)
        {
            int position = (int) bundle.get("position");
            hotelImage.setImageResource(images[position]);
            hotelTitle.setText(myTitle[position]);
            hotelDesc.setText("Destination : " + myDescription[position]);
            hotelPrice.setText("Price : $ " + price[position]);
        }
    }

    public void book_hotel(View view) {
        Toast.makeText(this, "Your Hotel is Booked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_logout){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(id == R.id.menu_contact){
            Intent intent = new Intent(this, contactActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
