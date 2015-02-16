package com.hiphonezhu.demo.lib;

import java.util.HashMap;
import java.util.Map;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 带分组功能的Adapter
 * @author hiphonezhu@gmail.com
 * @version [TestAndroid, 2015-2-16]
 */
public abstract class SectionAdapter extends BaseAdapter
{
    private Map<Integer, Integer> sectionPositionMap; // 存储分组编号及对应的改组内的条目数量
    private Map<Integer, Integer> sectionForPositionMap = new HashMap<Integer, Integer>(); // 存储position对应的分组编号
    private Map<Integer, Integer> positionForSectionMap = new HashMap<Integer, Integer>(); // 存储分组编号对应的第一个出现的positon位置
    public SectionAdapter(Map<Integer, Integer> sectionPositionMap)
    {
        this.sectionPositionMap = sectionPositionMap;
        // 初始化Position与Section对应关系数据
        int sectionSize = sectionPositionMap != null? sectionPositionMap.size() : 0;
        int position = 0;
        for(int i = 0; i < sectionSize; i++)
        {
            int size = sectionPositionMap.get(i) != null? sectionPositionMap.get(i) : 0;
            for(int j = 0; j < size; j++)
            {
                if (!positionForSectionMap.containsKey(i))
                {
                    positionForSectionMap.put(i, position);
                }
                sectionForPositionMap.put(position, i);
                position++;
            }
        }
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        int type = getItemViewType(position);
        return getView(type, position, convertView, parent);
    }
    
    /**
     * 加载每一行的View
     * @param section 分组编号
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    protected abstract View getView(int section, int position, View convertView, ViewGroup parent);
    
    /**
     * 根据position返回item的样式(每个分组一种样式)
     * @param position
     * @return
     * @see android.widget.BaseAdapter#getItemViewType(int)
     */
    @Override
    public int getItemViewType(int position)
    {
        return getSectionForPosition(position);
    }
    
    /**
     * 返回样式的总共数量(总计多少分组就多少个样式)
     * @return
     * @see android.widget.BaseAdapter#getViewTypeCount()
     */
    @Override
    public int getViewTypeCount()
    {
        return getSectionCount();
    }
    
    @Override
    public int getCount()
    {
        int sectionCount = getSectionCount();
        int sectionItemTotalCount = 0;
        for(int i=0; i<sectionCount; i++)
        {
            sectionItemTotalCount += getCountForSection(i);
        }
        return sectionItemTotalCount;
    }

    /**
     * 判断当前行是否要显示分组信息
     * @param position 下标
     * @return
     */
    public boolean sectionVisible(int position)
    {
        int section = getSectionForPosition(position);
        return position == getPositionForSection(section);
    }
    
    /**
     * 根据分组号加载分组View
     * @param section
     * @return
     */
    public abstract View getSectionView(int section);

    /**
     * 根据下标位置查询分组编号
     * @param position 下标
     * @return
     */
    public int getSectionForPosition(int position)
    {
        if (!sectionForPositionMap.containsKey(position))
        {
            return -1;
        }
        return sectionForPositionMap.get(position);
    }
    
    /**
     * 根据分组号查找第一个下标位置
     * @param section 分组编号
     * @return
     */
    public int getPositionForSection(int section)
    {
        if (!positionForSectionMap.containsKey(section))
        {
            return -1;
        }
        return positionForSectionMap.get(section);
    }
    
    /**
     * 返回分组数量
     * @return
     */
    protected int getSectionCount()
    {
        return sectionPositionMap != null? sectionPositionMap.size() : 0;
    }
    
    /**
     * 返回指定分组中的item数目
     * @param section 分组编号
     * @return
     */
    protected int getCountForSection(int section)
    {
        return sectionPositionMap != null? (sectionPositionMap.get(section) != null? sectionPositionMap.get(section) : 0) : 0;
    }
}
