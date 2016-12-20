package com.example.vishnubk.sharjahcharity.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vishnubk.sharjahcharity.R;

public class DetailPageActivity extends AppCompatActivity {
    TextView title;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailPageActivity.super.onBackPressed();

            }
        });
        imageView=(ImageView)findViewById(R.id.imageThumbnail);
        String content=getIntent().getStringExtra("title");
       // Uri imageUri = (Uri) getIntent().getParcelableExtra("image");
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        this.setTitle(content);
        imageView.setImageBitmap(bmp);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabDonate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailPageActivity.this, DonatorDetailActivity.class);
                startActivity(intent);
            }
        });
    }

}
