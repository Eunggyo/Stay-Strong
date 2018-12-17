package com.hcs.com.staystrong;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        ListView listview;
        Info_ListViewAdapter adapter;


        // Adapter 생성
        adapter = new Info_ListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kidari),
                "키다리형", "웨이트 트레이닝을 다루는 유투버 입니다. ");

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.malwang),
                "말왕TV", "웨이트 부터 종합운동까지 다루는 유투버 입니다 .");

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.gilbro),
                "길브로", "Natural athletes 정봉길 선수 유튜브 입니다.");

        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.naver),
                "네이버 건강정보","네이버에서 운영하는 운동정보 페이지 입니다.");


      listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                String titleStr = item.getTitle();

                //클릭 시 해당 페이지로 이동
                switch(titleStr){
                    case "말왕TV":
                    {
                        Intent mal = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/MrRagoona88"));
                        startActivity(mal);
                    }

                    case "키다리형":
                    {
                        Intent kidari = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/user/TeamAirwave"));
                        startActivity(kidari);
                    }

                    case "길브로" :
                    {
                        Intent gilbro = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/channel/UCybXiin_8m9Mxtmuv1wpARw"));
                        startActivity(gilbro);
                    }

                    case "네이버 건강정보":
                    {
                        Intent naver = new Intent(Intent.ACTION_VIEW,Uri.parse("https://terms.naver.com/list.nhn?cid=51030&categoryId=51030"));
                        startActivity(naver);
                    }
                }


            }
        });

    }
}