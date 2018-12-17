package com.hcs.com.staystrong;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FindExe_BBPActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbp);

        TextView title1 = (TextView)findViewById(R.id.title1);
        ImageView img1 = (ImageView)findViewById(R.id.bbpImg);
        TextView bbpc1 = (TextView)findViewById(R.id.bbp_t1);
    }
}
