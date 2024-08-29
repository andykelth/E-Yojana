package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class intro_page_Adepter extends PagerAdapter {


    Context context;

    int images[] = {
            R.drawable.intro1,
            R.drawable.intro2,
            R.drawable.intro3,
    };

    int heading[] ={
            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three,
    };

    int description[] = {
            R.string.text_one,
            R.string.text_two,
            R.string.text_three,
    };

    public intro_page_Adepter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slidetitalimage = (ImageView) view.findViewById(R.id.intro_photo_1);
        TextView slideheading = (TextView) view.findViewById(R.id.intro_headline_1);
        TextView slidedesciption = (TextView) view.findViewById(R.id.intro_text_1);

        slidetitalimage.setImageResource(images[position]);
        slideheading.setText(heading[position]);
        slidedesciption.setText(description[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

}
