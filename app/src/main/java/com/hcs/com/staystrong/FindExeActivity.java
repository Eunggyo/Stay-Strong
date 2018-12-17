package com.hcs.com.staystrong;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FindExeActivity extends TabActivity {

    //private FloatingActionButton fab;

    static final String[] Pec_Menu = {"바벨 벤치프레스", "덤벨 벤치프레스", "크로스 오버", "딥스", "푸쉬 업", "인클라인 벤치프레스",
            "디클라인 벤치프레스", "덤벨플라이", "펙덱 플라이",};

    static final String[] Sho_Menu = {"숄더 프레스", "덤벨 숄더 프레스", "비하인드 넥 프레스", "스미스머신 프레스",
            "업라이트 로우", "사이드 레터럴 레이즈", "머신 레이털 레이즈", "케이블 레터럴스", "벤트-오버 레티럴 레이즈",};

    static final String[] Back_Menu = {"케이블 풀 다운", "비하인드 넥 풀 다운", "케이블 로우", "T바 로우", "원암 덤벨 로우",
            "풀 업", "데드 리프트", "덤벨 풀 오버"};

    static final String[] Low_Menu = {"스쿼트", "스미스머신 백 스쿼트", "레그 프레스", "레그 익스텐션", "라잉 레그 컬"};

    static final String[] Biceps_Menu = {"바벨 컬", "덤벨 컬", "컨센트레이션 컬", "해머 컬", "바벨 리버스 컬", "인클라인 얼티네이트 덤벨 컬"};

    static final String[] Triceps_Menu = {"클로즈-그립 바벨 프레스", "클로즈-그립 스미스 머신 프레스", "덤벨 킥 백",
            "스탠딩 트라이셉스 익스텐션", "얼티네이트 덤벨 익스텐션"};

    private DBContactHelper dbContactHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        dbContactHelper = new DBContactHelper(this);

        ContentsAdapter adapter = new ContentsAdapter(this, Pec_Menu);
        final ListView p_listview =  findViewById(R.id.pectoralisView);
        p_listview.setAdapter(adapter);

        adapter = new ContentsAdapter(this, Back_Menu);
        final ListView b_listview =  findViewById(R.id.backView);
        b_listview.setAdapter(adapter);

        adapter = new ContentsAdapter(this, Sho_Menu);
        final ListView s_listview =  findViewById(R.id.shoulderView);
        s_listview.setAdapter(adapter);

        adapter = new ContentsAdapter(this, Biceps_Menu);
        final ListView bb_listview =  findViewById(R.id.bicepsView);
        bb_listview.setAdapter(adapter);

        adapter = new ContentsAdapter(this, Triceps_Menu);
        final ListView t_listview =  findViewById(R.id.tricepsView);
        t_listview.setAdapter(adapter);

        adapter = new ContentsAdapter(this, Low_Menu);
        final ListView l_listview =  findViewById(R.id.lowerView);
        l_listview.setAdapter(adapter);
        
        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpecFirst = tabHost.newTabSpec("first").setIndicator("가슴");
        //tag : 구분자 , indicator : 탭의 제목
        tabSpecFirst.setContent(R.id.pectoralisView);
        //setContent 해당 탭이 선택될 때 보여질 화면

        TabHost.TabSpec tabSpecSecond = tabHost.newTabSpec("second").setIndicator("어깨");
        tabSpecSecond.setContent(R.id.shoulderView);

        TabHost.TabSpec tabSpecThird = tabHost.newTabSpec("third").setIndicator("등");
        tabSpecThird.setContent(R.id.backView);

        TabHost.TabSpec tabSpecFourth = tabHost.newTabSpec("fourth").setIndicator("이두");
        tabSpecFourth.setContent(R.id.bicepsView);

        TabHost.TabSpec tabSpecFifth = tabHost.newTabSpec("fifth").setIndicator("삼두");
        tabSpecFifth.setContent(R.id.tricepsView);
        
        TabHost.TabSpec tabSpecSixth = tabHost.newTabSpec("sixth").setIndicator("하체");
        tabSpecSixth.setContent(R.id.lowerView);

        tabHost.addTab(tabSpecFirst);
        tabHost.addTab(tabSpecSecond);
        tabHost.addTab(tabSpecThird);
        tabHost.addTab(tabSpecFourth);
        tabHost.addTab(tabSpecFifth);
        tabHost.addTab(tabSpecSixth);
        
        final Button addbtn1 = findViewById(R.id.addbtn1);
        final Button savebtn1 = findViewById(R.id.savebtn1);

        addbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savebtn1.setVisibility(View.VISIBLE);
                ContentsAdapter adapter1 = (ContentsAdapter) p_listview.getAdapter();
                ContentsAdapter adapter2 = (ContentsAdapter) b_listview.getAdapter();
                ContentsAdapter adapter3 = (ContentsAdapter) s_listview.getAdapter();
                ContentsAdapter adapter4 = (ContentsAdapter) bb_listview.getAdapter();
                ContentsAdapter adapter5 = (ContentsAdapter) t_listview.getAdapter();
                ContentsAdapter adapter6 = (ContentsAdapter) l_listview.getAdapter();

                adapter1.setCheckMode(true);
                adapter2.setCheckMode(true);
                adapter3.setCheckMode(true);
                adapter4.setCheckMode(true);
                adapter5.setCheckMode(true);
                adapter6.setCheckMode(true);
            }
        });

        savebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);



                //각 리스트의 아답터를 가져온다.
                ContentsAdapter adapter1 = (ContentsAdapter) p_listview.getAdapter();
                ContentsAdapter adapter2 = (ContentsAdapter) b_listview.getAdapter();
                ContentsAdapter adapter3 = (ContentsAdapter) s_listview.getAdapter();
                ContentsAdapter adapter4 = (ContentsAdapter) bb_listview.getAdapter();
                ContentsAdapter adapter5 = (ContentsAdapter) t_listview.getAdapter();
                ContentsAdapter adapter6 = (ContentsAdapter) l_listview.getAdapter();

                //각 리스트의 체크된 값을 저장한다.
                adapter1.save();
                adapter2.save();
                adapter3.save();
                adapter4.save();
                adapter5.save();
                adapter6.save();

                //각 리스트에 체크되어 있는 항목들을 초기화
                adapter1.setCheckMode(false);
                adapter2.setCheckMode(false);
                adapter3.setCheckMode(false);
                adapter4.setCheckMode(false);
                adapter5.setCheckMode(false);
                adapter6.setCheckMode(false);
            }
        });
    }
}
