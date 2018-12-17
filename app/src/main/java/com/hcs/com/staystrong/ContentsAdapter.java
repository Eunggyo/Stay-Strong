package com.hcs.com.staystrong;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;

public class ContentsAdapter extends BaseAdapter {
    private DBContactHelper dbContactHelper;
    private String[] contents;
    private LinkedHashSet<String> checkedItems;
    private Context mContext;
    private boolean checkMode;
    public ContentsAdapter(Context context, String[] strs) {
        this.mContext = context;
        contents = strs;
        checkedItems = new LinkedHashSet<>();
        dbContactHelper = new DBContactHelper(context);
    }

    @Override
    public int getCount() {
        return contents.length;
    }

    @Override
    public String getItem(int i) {
        return contents[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view == null) {
            view = View.inflate(mContext, R.layout.row_contents_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final String item = getItem(i);


        viewHolder.text.setText(item);

        if(checkMode) {
            viewHolder.check.setVisibility(View.VISIBLE); //체크모드 일때만 체크박스 보이도록..
            viewHolder.check.setChecked(contains(item)); //체크한 아이템이 있으면 체크상태로...
            viewHolder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) checkedItems.add(item);
                    else checkedItems.remove(item);
                    notifyDataSetChanged();
                }
            });
        } else {
            viewHolder.check.setVisibility(View.GONE);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkMode) {
                    boolean c = viewHolder.check.isChecked();
                    viewHolder.check.setChecked(!c);
                } else {
                    Intent bbp = new Intent(mContext, FindExe_BBPActivity.class);
                    mContext.startActivity(bbp);
                }
            }
        });
        return view;
    }

    public boolean isCheckMode() {
        return checkMode;
    }

    public boolean contains(String str) {
        for(String item : checkedItems) {
            if(TextUtils.equals(item, str)) {
                return true;
            }
        }
        return false;
    }

    public void setCheckMode(boolean checkMode) {
        this.checkMode = checkMode;
        if(checkMode == false) {
            checkedItems.clear();
        }
        notifyDataSetChanged();
    }

    public void save() {

        //오늘 날짜를 셋팅한다.
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String strYear = year + "";
        String strMonth;
        String strDay;

        if(month < 10) {
            strMonth = "0" + month;
        } else {
            strMonth = month + "";
        }

        if(day < 10) {
            strDay = "0" + day;
        } else {
            strDay = day + "";
        }

        List<String> list = dbContactHelper.get(strYear, strMonth, strDay);
        for(String str : checkedItems) {
            boolean b = false;

            //해당 날짜에 저장된 데이터가 있는지 검사(중복방지)
            for(String item : list) {
                if(TextUtils.equals(str, item)) {
                    b = true;
                    break;
                }
            }

            //해당날짜에 저장된값이 없으면 저장.
            if(b == false) {
                dbContactHelper.insert(str, strYear, strMonth, strDay);
            }
        }
    }

    class ViewHolder {
        CheckBox check;
        TextView text;
        ViewHolder(View view) {
            check = view.findViewById(R.id.check);
            text = view.findViewById(R.id.text);
        }
    }
}
