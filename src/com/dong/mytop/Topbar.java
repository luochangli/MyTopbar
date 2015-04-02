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
 * @version ����ʱ�䣺2015��4��1�� ����4:16:25 �Զ���View�� 
 * 1�������Ҫ������ ��values�е�attrs�����
 * 2��ʵ��һ�����ǵġ�View���� 
 * 3���������ǵ�View
 */
public class Topbar extends RelativeLayout {

	private final String TAG = "Topbar";

	// �ص��ӿ�
	public interface topbarClickListener {
		void leftClick();

		void rightClick();
	}

	private topbarClickListener listener;

	// ���Topbar�ĵ���¼�
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
	// ���ֲ���
	private LayoutParams leftParams, rightParams, titleParams;

	public Topbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.Topbar);

		initView(context, ta);
	}

	// �����ť�Ƿ���ʾ
	public void setLeftButtonVisiable(Boolean isVisiable) {

		if (isVisiable) {
			btnLeftButton.setVisibility(View.VISIBLE);
		} else {
			btnLeftButton.setVisibility(View.GONE);
		}

	}

	@SuppressLint("NewApi")
	private void initView(Context context, TypedArray ta) {
		// ���Զ������Ե�ֵ�浽�˶�Ӧ�Ŀؼ���
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
		// ��Դ����
		ta.recycle();
		// ��������view�Ķ���������������
		btnLeftButton = new Button(context);
		btnRightButton = new Button(context);
		tvTitle = new TextView(context);
		// ���Զ����ֵ���ô�������
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
		// ��Topbar����LayoutParams�����в���
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

		// ��ӵ���¼�

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
