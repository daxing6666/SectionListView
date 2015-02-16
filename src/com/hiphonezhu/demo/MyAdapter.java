package com.hiphonezhu.demo;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hiphonezhu.demo.R;
import com.hiphonezhu.demo.lib.SectionAdapter;

public class MyAdapter extends SectionAdapter
{
    private List<String> dataList;
    public MyAdapter(Map<Integer, Integer> sectionPositionMap, List<String> dataList, Context context)
    {
        super(sectionPositionMap);
        this.dataList = dataList;
        this.context = context;
    }

    private Context context;

    @Override
    public String getItem(int position)
    {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int type, int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            switch (type)
            {
                case 0:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item1, null);  
                    break;
                case 1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item2, null);  
                    break;
                case 2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item3, null);  
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        // 根据每种样式初始化不同数据
        switch (type)
        {
            case 0:
                View sectionView = convertView.findViewById(R.id.section_view);
                TextView valueTxt = (TextView)convertView.findViewById(R.id.value_txt);
                valueTxt.setText(getItem(position));  
                if (sectionVisible(position)) {  
                    sectionView.setVisibility(View.VISIBLE);  
                } else {  
                    sectionView.setVisibility(View.GONE);  
                } 
                break;
            case 1:
                sectionView = convertView.findViewById(R.id.section_view);
                if (sectionVisible(position)) {  
                    sectionView.setVisibility(View.VISIBLE);  
                } else {  
                    sectionView.setVisibility(View.GONE);  
                } 
                break;
            case 2:
                sectionView = convertView.findViewById(R.id.section_view);
                valueTxt = (TextView)convertView.findViewById(R.id.value_txt);
                valueTxt.setText(getItem(position));  
                if (sectionVisible(position)) {  
                    sectionView.setVisibility(View.VISIBLE);  
                } else {  
                    sectionView.setVisibility(View.GONE);  
                } 
                break;
            default:
                throw new RuntimeException();
        }
        return convertView;
    }
    
    @Override
    public View getSectionView(int section)
    {
        if (section == 0)
        {
            return LayoutInflater.from(context).inflate(R.layout.section1, null); 
        }
        else if (section == 1)
        {
            return LayoutInflater.from(context).inflate(R.layout.section2, null); 
        }
        else if (section == 2)
        {
            return LayoutInflater.from(context).inflate(R.layout.section3, null); 
        }
        return null;
    }
}
