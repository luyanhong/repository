package com.leolu.listviewanimation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * ClassName: MainActivity <br/>
 * Function: TODO ListView动画选择页面. <br/>
 * date: 2013-11-19 上午11:14:29 <br/>
 *
 * @author LeoLu
 * @version 
 * @since JDK 1.6
 */
public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}

	/**
	 * init:初始化列表 <br/>
	 *
	 * @author LeoLu
	 * @since JDK 1.6
	 */
	private void init() {
		ListView listView = (ListView)findViewById(R.id.activity_main_listview);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> hashMap;
		String[] array = this.getResources().getStringArray(R.array.activity_main_listview);
		for (String string : array) {
			hashMap = new HashMap<String, String>();
			hashMap.put("name", string);
			list.add(hashMap);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.activity_main_listview_item, 
				new String[]{"name"}, new int[]{R.id.activity_main_listview_item_text});
		listView.setAdapter(simpleAdapter);
		listView.setOnItemClickListener(new ItemListener());
	}

	/**
	 * ClassName: ItemListener <br/>
	 * Function: TODO 列表ITEM点击监听. <br/>
	 * date: 2013-11-19 下午12:04:02 <br/>
	 *
	 * @author LeoLu
	 * @version MainActivity
	 * @since JDK 1.6
	 */
	private class ItemListener implements OnItemClickListener{

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				startActivity(new Intent(MainActivity.this,GoogleCards.class));
				break;

			default:
				break;
			}
		}
	}
}
