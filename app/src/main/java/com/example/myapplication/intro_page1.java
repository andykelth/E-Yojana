package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;


public class intro_page1 extends AppCompatActivity {

    ImageView imageView;
    ViewPager intro_slideviewpager;
    LinearLayout intro_dotlayout;
    Button intro_back_btn, intro_next_btn, intro_skip_btn;

    TextView[] dots;
    intro_page_Adepter introPageAdepter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro_page1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        intro_back_btn = findViewById(R.id.intro_btn_back);
        intro_next_btn = findViewById(R.id.intro_btn_next);
        intro_skip_btn = findViewById(R.id.intro_btn_skip);

        intro_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) > 0) {
                    intro_slideviewpager.setCurrentItem(getitem(-1), true);
                }
            }
        });

        intro_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0) < 2) {
                    intro_slideviewpager.setCurrentItem(getitem(1), true);
                } else {
                    Intent i = new Intent(intro_page1.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });


        intro_skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(intro_page1.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        intro_slideviewpager = (ViewPager) findViewById(R.id.slideviewpage);
        intro_dotlayout = (LinearLayout) findViewById(R.id.indicator_layout);

        introPageAdepter = new intro_page_Adepter(this);

        intro_slideviewpager.setAdapter(introPageAdepter);

        setUpindicator(0);
        intro_slideviewpager.addOnPageChangeListener(viewlistener);

    }

    public void setUpindicator(int position) {
        dots = new TextView[3];
        intro_dotlayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            /*  dots[i].setText(Html.fromHtml());   colour mate*/
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.white, getApplicationContext().getTheme()));
            intro_dotlayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.black, getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position > 0) {

                intro_back_btn.setVisibility(View.VISIBLE);
            } else {
                intro_back_btn.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i) {
        return intro_slideviewpager.getCurrentItem() + i;
    }

}
