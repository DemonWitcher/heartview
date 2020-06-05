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
//        final SimpleSweetHeartView simpleSweetHeartView1 = findViewById(R.id.sshv1);
//        final SimpleSweetHeartView simpleSweetHeartView2 = findViewById(R.id.sshv2);
//        final SimpleSweetHeartView simpleSweetHeartView3 = findViewById(R.id.sshv3);
//        final SimpleSweetHeartView simpleSweetHeartView4 = findViewById(R.id.sshv4);
//        final SimpleSweetHeartView simpleSweetHeartView5 = findViewById(R.id.sshv5);
//        final SimpleSweetHeartView simpleSweetHeartView6 = findViewById(R.id.sshv6);
//        final SimpleSweetHeartView simpleSweetHeartView7 = findViewById(R.id.sshv7);
//        final SimpleSweetHeartView simpleSweetHeartView8 = findViewById(R.id.sshv8);
//        final SimpleSweetHeartView simpleSweetHeartView9 = findViewById(R.id.sshv9);
//        final SimpleSweetHeartView simpleSweetHeartView10 = findViewById(R.id.sshv10);
        simpleSweetHeartView0.setLevel(lv);
        simpleSweetHeartView0.setProgress(47);
        simpleSweetHeartView0.setMax(100);

//        simpleSweetHeartView1.setProgress(7);
//        simpleSweetHeartView1.setMax(100);
//        simpleSweetHeartView1.setLevel(lv);
//
//        simpleSweetHeartView2.setProgress(12);
//        simpleSweetHeartView2.setMax(100);
//        simpleSweetHeartView2.setLevel(lv);
//
//        simpleSweetHeartView3.setProgress(22);
//        simpleSweetHeartView3.setMax(100);
//        simpleSweetHeartView3.setLevel(1);
//
//        simpleSweetHeartView4.setProgress(32);
//        simpleSweetHeartView4.setMax(100);
//        simpleSweetHeartView4.setLevel(lv);
//
//        simpleSweetHeartView5.setProgress(42);
//        simpleSweetHeartView5.setMax(100);
//        simpleSweetHeartView5.setLevel(lv);
//
//        simpleSweetHeartView6.setProgress(52);
//        simpleSweetHeartView6.setMax(100);
//        simpleSweetHeartView6.setLevel(lv);
//
//        simpleSweetHeartView7.setProgress(62);
//        simpleSweetHeartView7.setMax(100);
//        simpleSweetHeartView7.setLevel(lv);
//
//        simpleSweetHeartView8.setProgress(72);
//        simpleSweetHeartView8.setMax(100);
//        simpleSweetHeartView8.setLevel(lv);
//
//        simpleSweetHeartView9.setProgress(82);
//        simpleSweetHeartView9.setMax(100);
//        simpleSweetHeartView9.setLevel(lv);
//
//        simpleSweetHeartView10.setProgress(92);
//        simpleSweetHeartView10.setMax(100);
//        simpleSweetHeartView10.setLevel(lv);

//        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                simpleSweetHeartView0.setProgress(47);
//                simpleSweetHeartView1.setProgress(7);
//                simpleSweetHeartView2.setProgress(12);
//                simpleSweetHeartView3.setProgress(22);
//                simpleSweetHeartView4.setProgress(32);
//                simpleSweetHeartView5.setProgress(42);
//                simpleSweetHeartView6.setProgress(52);
//                simpleSweetHeartView7.setProgress(62);
//                simpleSweetHeartView8.setProgress(72);
//                simpleSweetHeartView9.setProgress(82);
//                simpleSweetHeartView10.setProgress(92);
//            }
//        });
//
        SeekBar sb = findViewById(R.id.sb);
        final TextView tv = findViewById(R.id.tv);

        sb.setMax(100);
        sb.setProgress(47);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
//                    L.i("progress:"+progress);
                    simpleSweetHeartView0.setProgress(progress);
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
                simpleSweetHeartView0.test(1,10);
            }
        });
    }

}
