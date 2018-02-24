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


public class SkillsFragment extends Fragment implements MainRecyclerViewAdapter.ListItemClickListener{
    RecyclerView mRecyclerView;
    MainRecyclerViewAdapter mAdapter;

    public SkillsFragment() {
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
        return inflater.inflate(R.layout.skills_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_documentation);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        ArrayList<Link> mRecyclerArraylist = new ArrayList<>();
        mAdapter = new MainRecyclerViewAdapter(mRecyclerArraylist,this.getContext(),this);
        mRecyclerView.setAdapter(mAdapter);



        mRecyclerArraylist.add(new Link("مهارات العمل فى فريق","هذا القسم مخصص لمهارة العمل فى فريق عمل", R.drawable.ricon_team));
        mRecyclerArraylist.add(new Link("مهارات التواصل الفعال","هذا القسم مخصص لمهارات التواصل الفعال وعملية الاتصال", R.drawable.ricon_communication));
        mRecyclerArraylist.add(new Link("مهارات خدمة العملاء","هذا القسم مخصص لمهارات خدمة العملاء وكيفية التعامل مع العملاء", R.drawable.ricon_customer_service));
        mRecyclerArraylist.add(new Link("مهارات ادارة الوقت","هذا القسم مخصص لمهارات ادارة الوقت ", R.drawable.ricon_time_management));
        mRecyclerArraylist.add(new Link("مهارات حل المشكلات","هذا القسم مخصص لمهارات حل المشكلات والتفكير الابداعي ", R.drawable.reicon_problem_solving));
        mRecyclerArraylist.add(new Link("المهارات الاشرافية","هذا القسم مخصص للمهارات الاشرافية ", R.drawable.ricon_supervisor));
        mRecyclerArraylist.add(new Link("المهارات الادارية","هذا القسم مخصص للمهارات الادارية ", R.drawable.ricon_management));
        mAdapter.setLinkData(mRecyclerArraylist);
    }

    @Override
    public void onListItemClick(int checkedItemIndex) {
        String category = "category";

        // فريق العمل
        if (checkedItemIndex == 0){
            Intent intent = new Intent(this.getActivity(), QuizSessionActivity.class);
            intent.putExtra(category,3001);
            startActivity(intent);

            // التواصل الفعال
        } else if (checkedItemIndex == 1){
            Intent intent = new Intent(this.getActivity(), QuizSessionActivity.class);
            intent.putExtra(category,3002);
            startActivity(intent);

            // خدمة العملاء
        } else if (checkedItemIndex == 2){
            Intent intent = new Intent(this.getActivity(), QuizSessionActivity.class);
            intent.putExtra(category,3003);
            startActivity(intent);

            // ادارة الوقت
        } else if (checkedItemIndex == 3){
            Intent intent = new Intent(this.getActivity(), QuizSessionActivity.class);
            intent.putExtra(category,3004);
            startActivity(intent);
        }
        // حل المشكلات
        else if (checkedItemIndex == 4){
            Intent intent = new Intent(this.getActivity(), QuizSessionActivity.class);
            intent.putExtra(category,3005);
            startActivity(intent);
        }
        // المهارات الاشرافية
        else if (checkedItemIndex == 5){
            Intent intent = new Intent(this.getActivity(), QuizSessionActivity.class);
            intent.putExtra(category,4006);
            startActivity(intent);
        }
    }
}