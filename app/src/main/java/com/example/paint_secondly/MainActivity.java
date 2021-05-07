package com.example.paint_secondly;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{
    private Button erase, start;
    private MyCanvas canvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        canvas = findViewById(R.id.drawing);
        Button erase = findViewById(R.id.erase_btn);
        Button start = findViewById(R.id.start_btn);
        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.erasing();
                canvas.setBrushSize(canvas.getBrushSize());
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.startNew();
            }
        });

    }
    public void paintClicked(View view){
        String color = view.getTag().toString();
        canvas.setColor(color);
    }
}