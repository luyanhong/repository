package com.leolu.fristapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class SplashPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//获取activity_splash_page.xml布局
		View view =  View.inflate(this, R.layout.activity_splash_page, null);
		//加载布局
		setContentView(view);
		
		//设置动画，渐显渐隐可用AlphaAnimation,变形动画用RotateAnimation,位移动画用TranslateAnimation
		//第一个参数值0.3f为开始的透明度为30%，第二个参数值1.0f为结束的透明度为100%，即不透明。
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
		
		//给动画设置持续时间，如果不设置，则时间为0，动画就看不到效果
		alphaAnimation.setDuration(2000);
		
		//给我们的背景运行动画
		view.startAnimation(alphaAnimation);
		
		//设置动画监听
		alphaAnimation.setAnimationListener(new AnimationListener() {
			
			@Override  //动画一开始就执行以下方法
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override //动画重复时执行以下方法
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override //动画结束时执行以下方法
			public void onAnimationEnd(Animation animation) {
				
			}
		});
		
	}

	
}
