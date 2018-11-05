package com.zecollokaris.cryptocurrency;

import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    //TEXT VIEW TO HOLD DOT SLIDERS!
    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    //Buttons
    private Button mNextBtn;
    private Button mBackBtn;

    private int mCurrentPage;

    private TextView mSplashText;


//##################################################################################################


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //VIEWSLIDERS
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        //BUTTONS
        mNextBtn = (Button) findViewById(R.id.nxtBtn);
        mBackBtn = (Button) findViewById(R.id.prevBtn);

        //FONTS FOR SPLASH PAGE!
//        mSplashText = (TextView) findViewById(R.id.splashText);
//        Typeface googleFont = Typeface.createFromAsset(getAssets(), "fonts/limelight.ttf");
//        mSplashText.setTypeface(googleFont);

        //SLIDER ADAPTER
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        //Page listener for Shift in position of Pages
        mSlideViewPager.addOnPageChangeListener(viewListener);


        //OnClickListeners
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
            
        });

    }

//  ADDS DOT SLIDER FOR CODE!
    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for(int i = 0; i < mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

                addDotsIndicator(i);

            mCurrentPage = i;

            if(i == 0){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("");

            } else if (i == mDots.length -1 ) {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Finish");
                mBackBtn.setText("Back");

            } else {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }


        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}