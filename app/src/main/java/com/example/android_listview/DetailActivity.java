package com.example.android_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    ImageButton back;
    TextView nameLaptop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        connectID();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        nameLaptop.setText(bundle.getString("nameLaptop"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void connectID() {
        back = findViewById(R.id.back);
        nameLaptop = findViewById(R.id.nameLaptop);
    }
}