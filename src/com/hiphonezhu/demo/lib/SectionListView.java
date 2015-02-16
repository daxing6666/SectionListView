package com.hiphonezhu.demo.lib;

import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
/**
 * 带分组表头的ListView
 * @author hiphonezhu@gmail.com
 * @version [TestAndroid, 2015-2-16]
 */
public class SectionListView extends ListView
{
    private SectionAdapter sectionAdapter;
    private ViewGroup sectionContainer;
    public SectionListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }
    
    public SectionListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    
    public SectionListView(Context context)
    {
        super(context);
    }
    
    /**
     * 绑定数据适配器
     * @param sectionAdapter
     * @param sectionContainer
     */
    public void setAdapter(SectionAdapter sectionAdapter, ViewGroup sectionContainer)
    {
        super.setAdapter(sectionAdapter);
        this.sectionAdapter = sectionAdapter;
        this.sectionContainer = sectionContainer;
        setOnScrollListener(scrollListener);
    }
    
    private OnScrollListener scrollListener = new OnScrollListener()
    {
        private int lastFirstVisibleItem = -1; 
        final Map<Integer, View> sectionViewMap = new HashMap<Integer, View>();
        @Override
        public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
        {}
        
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,  
                int totalItemCount)
        {
            int section = sectionAdapter.getSectionForPosition(firstVisibleItem);
            int nextSecPosition = sectionAdapter.getPositionForSection(section + 1); 
            View sectionView = sectionViewMap.get(section);
            if (sectionView == null)
            {
                sectionView = sectionAdapter.getSectionView(section);
                sectionViewMap.put(section, sectionView);
                sectionContainer.addView(sectionView);
            }
            else
            {
                int count = sectionContainer.getChildCount();
                for(int i = 0; i < count; i++)
                {
                    if (sectionContainer.getChildAt(i) != sectionView)
                    {
                        sectionContainer.getChildAt(i).setVisibility(View.GONE);
                    }
                    else
                    {
                        sectionContainer.getChildAt(i).setVisibility(View.VISIBLE);
                    }
                }
            }
            
            if (firstVisibleItem != lastFirstVisibleItem) 
            {  
                MarginLayoutParams params = (MarginLayoutParams) sectionContainer.getLayoutParams();  
                params.topMargin = 0;  
                sectionContainer.setLayoutParams(params);  
            }  
            if (nextSecPosition - firstVisibleItem < visibleItemCount) // 下一个分组在当前屏幕可见范围
            {
                // 下一个分组的View
                View childView = view.getChildAt(nextSecPosition - firstVisibleItem);  
                if (childView != null) 
                {  
                    int titleHeight = sectionContainer.getHeight();  
                    int top = childView.getTop();  
                    MarginLayoutParams params = (MarginLayoutParams) sectionContainer  
                    .getLayoutParams();  
                    if (top < titleHeight) 
                    {  
                        float pushedDistance = top - titleHeight;  
                        params.topMargin = (int) pushedDistance;  
                        sectionContainer.setLayoutParams(params);  
                    } 
                    else 
                    {  
                        if (params.topMargin != 0) 
                        {  
                            params.topMargin = 0;  
                            sectionContainer.setLayoutParams(params);  
                        }  
                    }  
                }  
            }
            lastFirstVisibleItem = firstVisibleItem;  
        }
    };
}
