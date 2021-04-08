package com.ondemande.latihanretrofit;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String title = getIntent().getStringExtra("INTENT_TITLE");
        String image = getIntent().getStringExtra("INTENT_IMAGE");

        getSupportActionBar().setTitle(title);
        Picasso.get().load(image).into((ImageView) findViewById(R.id.ivDetail));
    }
}