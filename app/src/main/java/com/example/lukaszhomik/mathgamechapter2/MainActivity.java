package com.example.lukaszhomik.mathgamechapter2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonPlay =
                (Button)findViewById(R.id.btnPlay);
        buttonPlay.setOnClickListener(this);

        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.image));

    }

    @Override
    public void onClick(View view) {
        Intent i;
        i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
        }
