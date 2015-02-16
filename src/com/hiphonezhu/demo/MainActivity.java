package com.hiphonezhu.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.hiphonezhu.demo.R;
import com.hiphonezhu.demo.lib.SectionListView;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionListView listView = (SectionListView)findViewById(R.id.list_view);
        Map<Integer, Integer> sectionPositionMap = new HashMap<Integer, Integer>();
        List<String> dataList = new ArrayList<String>();
        dataList.add("TextView");
        dataList.add("Button");
        dataList.add("TextView");
        dataList.add("Button");
        dataList.add("TextView");
        dataList.add("Button");
        
        dataList.add("b1");
        dataList.add("b2");
        dataList.add("b3");
        dataList.add("b4");
        dataList.add("b5");
        
        dataList.add("1");
        dataList.add("c2");
        dataList.add("放假了各位");
        dataList.add("c4");
        dataList.add("4");
        dataList.add("c6");
        dataList.add("晚上Dota");
        dataList.add("c8");
        dataList.add("c9");
        dataList.add("c10");
        dataList.add("c11");
        dataList.add("c12");
        
        sectionPositionMap.put(0, 6);
        sectionPositionMap.put(1, 5);
        sectionPositionMap.put(2, 12);
        
        MyAdapter myAdapter = new MyAdapter(sectionPositionMap, dataList, this);
        FrameLayout container  = (FrameLayout)findViewById(R.id.section_container);   
        listView.setAdapter(myAdapter, container);
    }
}
