package com.example.drestaurant.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drestaurant.Adapters.Adapter;
import com.example.drestaurant.Models.Model;
import com.example.drestaurant.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View view;
    ViewPager viewPager;
    Adapter adapter;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<Model> models;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.chinese,"Chinese","A Chinese meal is consisted of two parts: staple food, normally made of rice, noodles or steamed buns, and ts'ai, vegetable and meat dishes. ... The primary eating utensils are chopsticks (for solid foods) and ceramic spoon (for soups and congees)."));
        models.add(new Model(R.drawable.maharashtraian_food,"Maharashtrian","Maharashtrian cuisine includes mild and spicy dishes. Wheat, rice, jowar, bajri, vegetables, lentils and fruit are dietary staples. Peanuts and cashews are often served with vegetables."));
        models.add(new Model(R.drawable.italian,"Italian","In the North of Italy, fish, potatoes, rice, sausages, pork and different types of cheeses are the most common ingredients. Pasta dishes with tomatoes are popular, as are many kinds of stuffed pasta, polenta and risotto."));
        models.add(new Model(R.drawable.panjabi,"Panjabi","The main Traditional Punjabi food are - Sarson ka saag, Shahi paneer, Dal makhni, Rajma, Chole, Aloo, Chicken karahi, Chicken Tandori, makki di Roti, Naan, Phulka, Puri, Papad, Lassi, Kheer, rabri. Culture and Tradition of Punjab – The culture of Punjab is the richest culture in the world."));
        models.add(new Model(R.drawable.other,"Others",""));

        adapter = new Adapter(models,getActivity());

        viewPager = getView().findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100,0,100,0);

        Integer[] color_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };


        colors = color_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() -1) && position < (colors.length - 1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(positionOffset,colors[position],colors[position + 1]));
                }else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
