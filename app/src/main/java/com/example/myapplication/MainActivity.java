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

        int lv = 1;

        final SimpleSweetHeartView simpleSweetHeartView0 = findViewById(R.id.sshv0);
        simpleSweetHeartView0.setLevel(lv);
        simpleSweetHeartView0.setProgress(47,true);
        simpleSweetHeartView0.setMax(100);

        SeekBar sb = findViewById(R.id.sb);
        final TextView tv = findViewById(R.id.tv);

        sb.setMax(100);
        sb.setProgress(47);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    simpleSweetHeartView0.setProgress(progress,true);
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
                simpleSweetHeartView0.setProgress(99,true);
            }
        });
    }

}
