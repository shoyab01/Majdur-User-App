package adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.majdurapp.R;


import java.util.List;

import model.slider_model;

public class slider_adapter extends PagerAdapter {

    private List<slider_model> slider_models;


    LayoutInflater layoutInflater;

    public slider_adapter(List<slider_model> slider_models) {
        this.slider_models = slider_models;
    }

    @Override
    public int getCount() {
        return slider_models.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //  layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout, container, false);
        ImageView banner = view.findViewById(R.id.banner);
        banner.setImageResource(slider_models.get(position).getBanner());
        container.addView(view, 0);
        ConstraintLayout bannerContainer = view.findViewById(R.id.banner_constraint);
      //  bannerContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(slider_models.get(position).getBackgroundColor())));

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
