package com.hcs.com.staystrong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class IntroActivity extends Activity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intro);

        handler = new Handler();
        handler.postDelayed(r,1500);

    }

    Runnable r = new Runnable() {

        @Override
        public void run() {
            Intent intent = new Intent(IntroActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        handler.removeCallbacks(r);
    }

}
