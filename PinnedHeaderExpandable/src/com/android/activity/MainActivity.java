package com.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

import com.android.R;
import com.android.pinnedheader.PinnedHeaderExpandableListView;

public class MainActivity extends Activity{
	private PinnedHeaderExpandableListView explistview;
	private SwipeRefreshLayout swipeRefreshLayout;
	private String[][] childrenData = new String[10][10];
	private String[] groupData = new String[5];
	private int expandFlag = -1;//控制列表的展开  
	private MainAdapter adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		initView();
		initData();
	}
	
	/**
	 * 初始化VIEW
	 */
	private void initView() {
		explistview = (PinnedHeaderExpandableListView)findViewById(R.id.explistview);
		swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
		//设置刷新时动画的颜色，可以设置4个
		swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
		              
		              @Override
		              public void onRefresh() {
		                 new Handler().postDelayed(new Runnable() {
		            
		                     @Override
		                     public void run() {
		                         swipeRefreshLayout.setRefreshing(false);
		                     }
		                 }, 6000);
		             }
		         });
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		for(int i=0;i<5;i++){
			groupData[i] = " group "+i;
		}
		for(int i=1;i<5;i++){
			for(int j=0;j<10;j++){
				childrenData[i][j] = "qq "+j;
			}
		}
		//设置悬浮头部VIEW
		explistview.setHeaderView(View.inflate(MainActivity.this,R.layout.group,
				null));
		adapter = new MainAdapter(childrenData, groupData, getApplicationContext(),explistview);
		explistview.setAdapter(adapter);
		explistview.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(MainActivity.this, "分组:"+groupData[groupPosition]+",好友:"+childrenData[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}
}
