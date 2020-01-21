package com.example.seminarskipdf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStart = (Button)findViewById(R.id.button);
        GradientDrawable gd1 = (GradientDrawable) buttonStart.getBackground();
        gd1.setColor(Color.argb(220,18,227,140));
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetectionActivity.class);
                startActivity(intent);
            }
        });

        Button buttonInfo = (Button)findViewById(R.id.button2);
        GradientDrawable gd2 = (GradientDrawable) buttonInfo.getBackground();
        gd2.setColor(Color.argb(220,227,220,130));
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Nikola-Radovanovic-576-2016/SeminarskiPdf"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.google.android.chrome");
                startActivity(intent);
            }
        });
    }
}
