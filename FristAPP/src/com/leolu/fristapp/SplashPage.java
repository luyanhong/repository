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
		
		//��ȡactivity_splash_page.xml����
		View view =  View.inflate(this, R.layout.activity_splash_page, null);
		//���ز���
		setContentView(view);
		
		//���ö��������Խ�������AlphaAnimation,���ζ�����RotateAnimation,λ�ƶ�����TranslateAnimation
		//��һ������ֵ0.3fΪ��ʼ��͸����Ϊ30%���ڶ�������ֵ1.0fΪ������͸����Ϊ100%������͸����
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
		
		//���������ó���ʱ�䣬��������ã���ʱ��Ϊ0�������Ϳ�����Ч��
		alphaAnimation.setDuration(2000);
		
		//�����ǵı������ж���
		view.startAnimation(alphaAnimation);
		
		//���ö�������
		alphaAnimation.setAnimationListener(new AnimationListener() {
			
			@Override  //����һ��ʼ��ִ�����·���
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override //�����ظ�ʱִ�����·���
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override //��������ʱִ�����·���
			public void onAnimationEnd(Animation animation) {
				
			}
		});
		
	}

	
}
