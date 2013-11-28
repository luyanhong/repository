/**
 * Project Name:ListViewAnimation
 * File Name:GoogleCard.java
 * Package Name:com.leolu.listviewanimation
 * Date:2013-11-19����2:01:42
 * Copyright (c) 2013, LeoLu All Rights Reserved.
 *
*/
/**
 * Project Name:ListViewAnimation
 * File Name:GoogleCard.java
 * Package Name:com.leolu.listviewanimation
 * Date:2013-11-19����2:01:42
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
 * ClassName:GoogleCard��Ƭ����Ч�� <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-11-19 ����2:01:42 <br/>
 * ��Ҫ�Ŀ��У�android-support-v4.jar,nineoldandroids-2.4.0.jar,com.haarman.listviewanimaitons-2.5.2.jar
 * @author   LeoLu  Ч��ԭ�����ߣ�haarman
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

			//�鿴�������ڴ�
			final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

			//ʹ�ÿ����ڴ��
			final int cacheSize = maxMemory;
			//ʹ��LruCache����ͼƬ
			mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize) {
				protected int sizeOf(Integer key, Bitmap bitmap) {
					//����bitmap���ڴ�ռ�ô�С
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

			//ͨ��key=ID��ȡ�ڴ滺�����ͼƬ
			Bitmap bitmap = getBitmapFromMemCache(imageResId);
			//����ڴ����ͼƬ�����ڣ��հ�ID��¼���ڴ滺��
			if (bitmap == null) {
				bitmap = BitmapFactory.decodeResource(mContext.getResources(), imageResId);
				addBitmapToMemoryCache(imageResId, bitmap);
			}
			viewHolder.imageView.setImageBitmap(bitmap);
		}

		//���ڴ滺������������
		private void addBitmapToMemoryCache(int key, Bitmap bitmap) {
			if (getBitmapFromMemCache(key) == null) {
				mMemoryCache.put(key, bitmap);
			}
		}
		
		//ͨ��KEY���ڴ滺�����ȡ����
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

