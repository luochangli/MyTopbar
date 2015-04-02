package com.dong.mytop;

import com.dong.mytop.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author luochangdong E-mail: 18873688664@163.com
 * @version 创建时间：2015年4月1日 下午4:16:25 自定义View： 
 * 1、设计需要的属性 在values中的attrs中添加
 * 2、实现一个我们的“View”类 
 * 3、引用我们的View
 */
public class Topbar extends RelativeLayout {

	private final String TAG = "Topbar";

	// 回调接口
	public interface topbarClickListener {
		void leftClick();

		void rightClick();
	}

	private topbarClickListener listener;

	// 添加Topbar的点击事件
	public void setOnTopbarClickListener(topbarClickListener listener) {
		this.listener = listener;
	}

	private Button btnRightButton, btnLeftButton;
	private TextView tvTitle;

	private int rightTextColor;
	private Drawable rightBackground;
	private String rightText;

	private int leftTextColor;
	private Drawable leftBackground;
	private String leftText;

	private int titleTextColor;
	private float titleTextSize;
	private String titleText;
	// 布局参数
	private LayoutParams leftParams, rightParams, titleParams;

	public Topbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.Topbar);

		initView(context, ta);
	}

	// 添加左按钮是否显示
	public void setLeftButtonVisiable(Boolean isVisiable) {

		if (isVisiable) {
			btnLeftButton.setVisibility(View.VISIBLE);
		} else {
			btnLeftButton.setVisibility(View.GONE);
		}

	}

	@SuppressLint("NewApi")
	private void initView(Context context, TypedArray ta) {
		// 从自定义属性的值存到了对应的控件中
		rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
		rightBackground = ta
				.getDrawable(R.styleable.Topbar_rightTextBackground);
		rightText = ta.getString(R.styleable.Topbar_rightText);

		leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
		leftBackground = ta.getDrawable(R.styleable.Topbar_leftTextBackground);
		leftText = ta.getString(R.styleable.Topbar_leftText);

		titleText = ta.getString(R.styleable.Topbar_titleText);
		titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor, 0);
		titleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize, 0);
		// 资源回收
		ta.recycle();
		// 创建三个view的对象，用来设置属性
		btnLeftButton = new Button(context);
		btnRightButton = new Button(context);
		tvTitle = new TextView(context);
		// 将自定义的值设置传给对象
		btnLeftButton.setTextColor(leftTextColor);
		btnLeftButton.setBackground(leftBackground);
		btnLeftButton.setText(leftText);

		btnRightButton.setTextColor(rightTextColor);
		btnRightButton.setBackground(rightBackground);
		btnRightButton.setText(rightText);

		tvTitle.setTextColor(titleTextColor);
		tvTitle.setTextSize(titleTextSize);
		tvTitle.setText(titleText);
		tvTitle.setGravity(Gravity.CENTER);

		setBackgroundColor(0xFFF59563);
		// 在Topbar中用LayoutParams来进行布局
		leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
		addView(btnLeftButton, leftParams);

		rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
		addView(btnRightButton, rightParams);

		titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
		addView(tvTitle, titleParams);

		// 添加点击事件

		btnLeftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				listener.leftClick();
				Log.v(TAG, "Left Click");
			}
		});

		btnRightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				listener.rightClick();
				Log.v(TAG, "Right Click");
			}
		});
	}

}
