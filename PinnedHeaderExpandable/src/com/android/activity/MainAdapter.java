package com.android.activity;

import com.android.R;
import com.android.pinnedheader.PinnedHeaderExpandableListView;

import android.content.Context;
import android.opengl.Visibility;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;
import android.widget.Toast;

//https://github.com/younfor/PinnedAndPulledHeaderListView
public class MainAdapter extends  BaseExpandableListAdapter {
	private String[][] childrenData;
	public String[] groupData;
	private Context context;
	private PinnedHeaderExpandableListView listView;
	private LayoutInflater inflater;
	
	
	public MainAdapter(String[][] childrenData,String[] groupData
			,Context context,PinnedHeaderExpandableListView listView){
		this.groupData = groupData; 
		this.childrenData = childrenData;
		this.context = context;
		this.listView = listView;
		inflater = LayoutInflater.from(this.context);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childrenData[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = null;  
        if (convertView != null) {  
            view = convertView;  
        } else {  
            view = inflater.inflate(R.layout.child, null);
        }  
        TextView text = (TextView)view.findViewById(R.id.childto);
        text.setText(childrenData[groupPosition][childPosition]); 
        return view;    
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if(groupPosition<0)
			return 0;
		return childrenData[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupData[groupPosition];
	}

	@Override
	public int getGroupCount() {
		return groupData.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = null; 
		//menu
		if(groupPosition==0)
    	{
    		view = inflater.inflate(R.layout.menu, null);
    		view.setLayoutParams(new LayoutParams(
    				ViewGroup.LayoutParams.MATCH_PARENT, 80));
    		view.setTag(1);
    		TextView btn1=(TextView)view.findViewById(R.id.btn1);
    		btn1.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View v) {
					Toast.makeText(context, "特别关心", Toast.LENGTH_SHORT).show();
				}
			});
    		return view;
    	}
		//普通分组
        if (convertView != null&&(Integer)convertView.getTag()==0)  
        {
            view = convertView;  
        } else {  
        	view = inflater.inflate(R.layout.group, null);
        	view.setTag(0);
        }
        TextView text = (TextView)view.findViewById(R.id.groupto);
        if (isExpanded) {
        	text.setText("- "+groupData[groupPosition]);  
		}
		else{
			text.setText("+ "+groupData[groupPosition]);  
		}
        return view;  
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	
}
