package com.srana.hotel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class hotel_list extends AppCompatActivity {
    ListView listView;
    String myTitle[] = {"Westin Bear Mountain Resort", "Bond Place Hotel", "Cambridge Suites", "Toronto Don Valley", "Hotel Fairmont The Queen Elizabeth","Chelsea Hotel"};
    String myDescription[] = {"British Columbia", "Toronto", "Nova Scotia", "Toronto", "Montreal","Toronto"};
    int images[] = {R.drawable.hotel6, R.drawable.hotel5, R.drawable.hotel3, R.drawable.hotel4, R.drawable.hotel2,R.drawable.hotel1};

    AnimatorSet set;
    ImageView imageView;
    int imageResources[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner5};
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        imageView = findViewById(R.id.hotel_list_imageView);
        set = (AnimatorSet) AnimatorInflater.loadAnimator(hotel_list.this, R.animator.slideshow);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(index < imageResources.length){
                    imageView.setImageResource(imageResources[index]);
                    index++;
                    set.start();
                }
            }
        });
        set.start();

        listView = findViewById(R.id.main_listView);
        MyAdapter adapter = new MyAdapter(this, myTitle, myDescription, images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(hotel_list.this, myTitle[position], Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), activity_hotel_detail.class);
                i.putExtra("position",position);
                startActivity(i);
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        String rDescription[];
        int rImages[];

        MyAdapter(Context c, String title[], String description[], int images[]){
            super(c, R.layout.custom_layout, R.id.main_textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImages = images;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.custom_layout, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTtitle = row.findViewById(R.id.main_textView1);
            TextView myDescription = row.findViewById(R.id.main_textView2);

            images.setImageResource(rImages[position]);
            myTtitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);
            return row;
        }
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


