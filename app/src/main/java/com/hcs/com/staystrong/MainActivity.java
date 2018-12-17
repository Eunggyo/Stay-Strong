package com.hcs.com.staystrong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView record = (ImageView)findViewById(R.id.write);
        ImageView part = (ImageView)findViewById(R.id.part);
        ImageView timer = (ImageView)findViewById(R.id.timer);
        ImageView info = (ImageView)findViewById(R.id.info);

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timer_Intent = new Intent(MainActivity.this, TimerActivity.class);
                MainActivity.this.startActivity(timer_Intent);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info_Intent = new Intent(MainActivity.this, InfoActivity.class);
                MainActivity.this.startActivity(info_Intent);
            }
        });

        part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent find_Intent = new Intent(MainActivity.this, FindExeActivity.class);
                MainActivity.this.startActivity(find_Intent);
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent record_Intent = new Intent(MainActivity.this,RecordActivity.class);
                MainActivity.this.startActivity(record_Intent);
            }
        });
    }
}
