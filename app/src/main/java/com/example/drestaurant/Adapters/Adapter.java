package com.example.drestaurant.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.drestaurant.FoodDetailActivity;
import com.example.drestaurant.MahaMenuActivity;
import com.example.drestaurant.Models.Model;
import com.example.drestaurant.R;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item,container,false);

        ImageView image = (ImageView) view.findViewById(R.id.image1);
        TextView title = view.findViewById(R.id.title);
        TextView desp = view.findViewById(R.id.desp);

        image.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desp.setText(models.get(position).getDesp());
        desp.setMovementMethod(new ScrollingMovementMethod());
        
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (position == 0){
                    Toast.makeText(context,"Chinese",Toast.LENGTH_SHORT).show();

                }else if (position == 1){
                    Intent i1 = new Intent(context,MahaMenuActivity.class);
                    context.startActivity(i1);
                }
                else if (position == 2){
                    Toast.makeText(context,"Italian",Toast.LENGTH_SHORT).show();
                }
                else if (position == 3){
                    Toast.makeText(context,"Panjabi",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context,"Others",Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        
        

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
