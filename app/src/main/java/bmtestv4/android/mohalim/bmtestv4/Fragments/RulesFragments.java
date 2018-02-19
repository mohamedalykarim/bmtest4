package bmtestv4.android.mohalim.bmtestv4.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bmtestv4.android.mohalim.bmtestv4.Adapters.MainRecyclerViewAdapter;
import bmtestv4.android.mohalim.bmtestv4.Link;
import bmtestv4.android.mohalim.bmtestv4.QuizSessionActivity;
import bmtestv4.android.mohalim.bmtestv4.R;


public class RulesFragments extends Fragment implements MainRecyclerViewAdapter.ListItemClickListener{
    RecyclerView mRecyclerView;
    MainRecyclerViewAdapter mAdapter;

    public RulesFragments() {
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
        return inflater.inflate(R.layout.rule_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_rule);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        ArrayList<Link> mRecyclerArraylist = new ArrayList<>();
        mAdapter = new MainRecyclerViewAdapter(mRecyclerArraylist,this.getContext(),this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerArraylist.add(new Link("ميثاق الاخلاقيات والشرف المصرفي","هذا القسم مخصص لميثاق الاخلاقيات والشرف المصرفي", 0));
        mRecyclerArraylist.add(new Link("المخاطر","هذا القسم مخصص للمخاطر", 0));

        mAdapter.setLinkData(mRecyclerArraylist);

    }

    @Override
    public void onListItemClick(int checkedItemIndex) {
        String category = "category";

        // ميثاق الاخلاق
        if (checkedItemIndex == 0){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,3202);
            startActivity(SME);
        }

        // المخاطر
        else if (checkedItemIndex == 1){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,3201);
            startActivity(SME);
        }

    }
}