/**
 * Project Name:ListViewAnimation
 * File Name:GoogleCard.java
 * Package Name:com.leolu.listviewanimation
 * Date:2013-11-19下午2:01:42
 * Copyright (c) 2013, LeoLu All Rights Reserved.
 *
*/
/**
 * Project Name:ListViewAnimation
 * File Name:GoogleCard.java
 * Package Name:com.leolu.listviewanimation
 * Date:2013-11-19下午2:01:42
 * Copyright (c) 2013, LeoLu All Rights Reserved.
 *
 */

package com.leolu.listviewanimation;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.haarman.listviewanimations.ArrayAdapter;
import com.haarman.listviewanimations.itemmanipulation.OnDismissCallback;

/**
 * ClassName:GoogleCard卡片飞上效果 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-11-19 下午2:01:42 <br/>
 * 需要的库有：android-support-v4.jar,nineoldandroids-2.4.0.jar,com.haarman.listviewanimaitons-2.5.2.jar
 * @author   LeoLu  效果原创作者：haarman
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class GoogleCards extends Activity implements OnDismissCallback{

	private GoogleCardsAdapter mGoogleCardsAdapter;
	private static LayoutAnimationController bottomUp;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_googlecards);
		
		ListView listView = (ListView) findViewById(R.id.activity_googlecards_listview);
		bottomUp = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.bottom_up));
		mGoogleCardsAdapter = new GoogleCardsAdapter(this);
		
//		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new SwipeDismissAdapter(mGoogleCardsAdapter, this));
//		swingBottomInAnimationAdapter.setInitialDelayMillis(300);
//		swingBottomInAnimationAdapter.setAbsListView(listView);
//
//		listView.setAdapter(swingBottomInAnimationAdapter);
		listView.setAdapter(mGoogleCardsAdapter);
		listView.setLayoutAnimation(bottomUp);

		mGoogleCardsAdapter.addAll(getItems());
	}
	
	private ArrayList<Integer> getItems() {
		ArrayList<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			items.add(i);
		}
		return items;
	}
	
	private static class GoogleCardsAdapter extends ArrayAdapter<Integer> {
		
		private Context mContext;
		private LruCache<Integer, Bitmap> mMemoryCache;
		
		public GoogleCardsAdapter(Context context) {
			mContext = context;

			//查看最大可用内存
			final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

			//使用可用内存的
			final int cacheSize = maxMemory;
			//使用LruCache缓存图片
			mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize) {
				protected int sizeOf(Integer key, Bitmap bitmap) {
					//返回bitmap的内存占用大小
					return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
				}
			};
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			View view = convertView;
			if (view == null) {
				view = LayoutInflater.from(mContext).inflate(R.layout.activity_googlecards_card, parent, false);

				viewHolder = new ViewHolder();
				viewHolder.textView = (TextView) view.findViewById(R.id.activity_googlecards_card_textview);
				view.setTag(viewHolder);

				viewHolder.imageView = (ImageView) view.findViewById(R.id.activity_googlecards_card_imageview);
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}

			viewHolder.textView.setText("This is card " + (getItem(position) + 1));
			setImageView(viewHolder, position);
			return view;
		}
		
		private void setImageView(ViewHolder viewHolder, int position) {
			int imageResId;
			switch (getItem(position) % 5) {
			case 0:
				imageResId = R.drawable.img_nature1;
				break;
			case 1:
				imageResId = R.drawable.img_nature2;
				break;
			case 2:
				imageResId = R.drawable.img_nature3;
				break;
			case 3:
				imageResId = R.drawable.img_nature4;
				break;
			default:
				imageResId = R.drawable.img_nature5;
			}

			//通过key=ID获取内存缓存里的图片
			Bitmap bitmap = getBitmapFromMemCache(imageResId);
			//如果内存里的图片不存在，刚把ID记录到内存缓存
			if (bitmap == null) {
				bitmap = BitmapFactory.decodeResource(mContext.getResources(), imageResId);
				addBitmapToMemoryCache(imageResId, bitmap);
			}
			viewHolder.imageView.setImageBitmap(bitmap);
		}

		//向内存缓存里增加内容
		private void addBitmapToMemoryCache(int key, Bitmap bitmap) {
			if (getBitmapFromMemCache(key) == null) {
				mMemoryCache.put(key, bitmap);
			}
		}
		
		//通过KEY向内存缓存里获取内容
		private Bitmap getBitmapFromMemCache(int key) {
			return mMemoryCache.get(key);
		}
	}

	private static class ViewHolder {
		TextView textView;
		ImageView imageView;
	}
	
	public void onDismiss(AbsListView listView, int[] reverseSortedPositions) {
		for (int position : reverseSortedPositions) {
			mGoogleCardsAdapter.remove(position);
		}
	}
}

