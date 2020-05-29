package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majdurapp.R;


import java.util.List;

import model.horizontal_product_scroller;

public class grid_product_view_adapter extends BaseAdapter {
    List<horizontal_product_scroller> horizontalProductScrollerList;

    public grid_product_view_adapter(List<horizontal_product_scroller> horizontalProductScrollerList) {
        this.horizontalProductScrollerList = horizontalProductScrollerList;
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, null);

            //   view.setElevation(0);


            TextView txt1 = view.findViewById(R.id.txt1);
            TextView txt2 = view.findViewById(R.id.txt2);
            TextView txt3 = view.findViewById(R.id.textView3);
            ImageView product_grid_img = view.findViewById(R.id.horizontal_img);
            product_grid_img.setImageResource(horizontalProductScrollerList.get(position).getImage_View());
            txt1.setText((horizontalProductScrollerList.get(position).getProducttxt1()));
            txt2.setText((horizontalProductScrollerList.get(position).getTxt2()));
            txt3.setText((horizontalProductScrollerList.get(position).getTxt3()));
        } else {
            view = convertView;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), horizontalProductScrollerList.get(position).getProducttxt1().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
