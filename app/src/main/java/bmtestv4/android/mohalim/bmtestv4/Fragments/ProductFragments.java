package bmtestv4.android.mohalim.bmtestv4.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.cachapa.expandablelayout.ExpandableLayout;

import bmtestv4.android.mohalim.bmtestv4.R;


public class ProductFragments extends Fragment implements View.OnClickListener{
    ExpandableLayout expandableLayoutMoney, expandableLayoutCar,
            expandableLayoutSla, expandableLayoutCards, expandableLayoutBuild ;

    LinearLayout linearLayoutMoney, linearLayoutCar,
                    linearLayoutSla, linearLayoutCards, linearLayoutBuild;

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

        linearLayoutMoney = (LinearLayout) view.findViewById(R.id.money_expand_button);
        linearLayoutCar = (LinearLayout) view.findViewById(R.id.car_expand_button);

        expandableLayoutMoney.setDuration(200);
        expandableLayoutCar.setDuration(200);

        expandableLayoutCar.setExpanded(false);
        expandableLayoutMoney.setExpanded(false);


        linearLayoutMoney.setOnClickListener(this);
        linearLayoutCar.setOnClickListener(this);


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
            if (expandableLayoutCar.isExpanded()){
                expandableLayoutCar.collapse();
            }
            else{
                expandableLayoutCar.expand();
            }
        }


    }
}