package bmtestv4.android.mohalim.bmtestv4.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.cachapa.expandablelayout.ExpandableLayout;

import bmtestv4.android.mohalim.bmtestv4.QuizSessionActivity;
import bmtestv4.android.mohalim.bmtestv4.R;


public class ProductFragments extends Fragment implements View.OnClickListener{
    String category;
    ExpandableLayout expandableLayoutMoney, expandableLayoutCar,
            expandableLayoutMortgage ;

    LinearLayout linearLayoutMoney, linearLayoutCar, linearLayoutMortgage, linearLayoutGoods;


    TextView unsecureMoney, secureMoney, mortgage1, mortgage2, mortgage3;



    public ProductFragments() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.product_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        expandableLayoutMoney = view.findViewById(R.id.expandable_layout_money);
        expandableLayoutCar = view.findViewById(R.id.expandable_layout_car);
        expandableLayoutMortgage = view.findViewById(R.id.expandable_layout_mortgage);

        linearLayoutMoney = (LinearLayout) view.findViewById(R.id.money_expand_button);
        linearLayoutCar = (LinearLayout) view.findViewById(R.id.car_expand_button);
        linearLayoutMortgage = (LinearLayout) view.findViewById(R.id.mortgage_expand_button);
        linearLayoutGoods = (LinearLayout) view.findViewById(R.id.goods_expand_button);


        unsecureMoney = (TextView) view.findViewById(R.id.unsecureMoney);
        secureMoney = (TextView) view.findViewById(R.id.secureMoney);
        mortgage1 = (TextView) view.findViewById(R.id.mortgage1);
        mortgage2 = (TextView) view.findViewById(R.id.mortgage2);
        mortgage3 = (TextView) view.findViewById(R.id.mortgage3);

        expandableLayoutMoney.setDuration(100);
        expandableLayoutCar.setDuration(100);
        expandableLayoutMortgage.setDuration(100);

        expandableLayoutCar.setExpanded(false);
        expandableLayoutMoney.setExpanded(false);
        expandableLayoutMortgage.setExpanded(false);


        linearLayoutMoney.setOnClickListener(this);
        linearLayoutCar.setOnClickListener(this);
        linearLayoutMortgage.setOnClickListener(this);
        linearLayoutGoods.setOnClickListener(this);


        category = "category";


        // القروض النقدية بدون ضمانات عينية
        unsecureMoney.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuizSessionActivity.class);
                intent.putExtra(category,4001);
                startActivity(intent);

            }
        });

        // القروض النقدية بضمانات عينية
        secureMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuizSessionActivity.class);
                intent.putExtra(category,4001);
                startActivity(intent);            }
        });


        // قروض التمويل العقاري للافراد
        mortgage1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuizSessionActivity.class);
                intent.putExtra(category,2525);
                startActivity(intent);

            }
        });

        // قروض التمويل العقاري التشطيب
        mortgage2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuizSessionActivity.class);
                intent.putExtra(category,2527);
                startActivity(intent);

            }
        });



        // قروض التمويل العقاري التشطيب
        mortgage3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuizSessionActivity.class);
                intent.putExtra(category,2526);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View view){

        if (view.equals(linearLayoutMoney)){
            if (expandableLayoutMoney.isExpanded()){
                expandableLayoutMoney.collapse();
            }
            else{
                expandableLayoutMoney.expand();
            }
        }
        else if (view.equals(linearLayoutCar)){
            Toast.makeText(getContext(), "Toast", Toast.LENGTH_SHORT).show();
        }
        else if (view.equals(linearLayoutMortgage)){
            if (expandableLayoutMortgage.isExpanded()){
                expandableLayoutMortgage.collapse();
            }
            else{
                expandableLayoutMortgage.expand();
            }
        }
        else if (view.equals(linearLayoutGoods)){
            Intent intent = new Intent(getActivity(), QuizSessionActivity.class);
            intent.putExtra(category,4004);
            startActivity(intent);

        }
    }

}