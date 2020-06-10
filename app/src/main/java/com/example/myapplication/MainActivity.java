package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int lv = 0;

        final SweetHeartView simpleSweetHeartView0 = findViewById(R.id.sshv0);
        final AnimSweetHeartView animSweetHeartView = findViewById(R.id.s2);

        simpleSweetHeartView0.setLevel(lv);
        simpleSweetHeartView0.setProgress(100,47,true,false);

        SeekBar sb = findViewById(R.id.sb);
        final TextView tv = findViewById(R.id.tv);

        sb.setMax(100);
        sb.setProgress(47);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    simpleSweetHeartView0.setProgress(100,progress,false,false);
                    animSweetHeartView.setProgress(100,progress,false,false);
                }
                tv.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleSweetHeartView0.setLevel(0);
            }
        });
        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleSweetHeartView0.setLevel(1);
            }
        });
        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleSweetHeartView0.setLevel(2);
            }
        });
        findViewById(R.id.bt4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleSweetHeartView0.setLevel(3);
            }
        });

        findViewById(R.id.bt5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleSweetHeartView0.setProgress(100,40,true,true);
                animSweetHeartView.setProgress(100,100,true,false);
            }
        });

        animSweetHeartView.setLevel(0);
        animSweetHeartView.setProgress(100,50,false,false);

       final AddSweetHeartView addSweetHeartView = findViewById(R.id.add);

        findViewById(R.id.btanim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animSweetHeartView.setProgress(100,99,true,false);
                addSweetHeartView.add();
            }
        });
    }

}
