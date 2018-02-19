package bmtestv4.android.mohalim.bmtestv4.Fragments;




import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

import bmtestv4.android.mohalim.bmtestv4.Adapters.MainRecyclerViewAdapter;
import bmtestv4.android.mohalim.bmtestv4.Link;
import bmtestv4.android.mohalim.bmtestv4.R;


public class TeamFragment extends Fragment implements MainRecyclerViewAdapter.ListItemClickListener{
    RecyclerView mRecyclerView;
    MainRecyclerViewAdapter mAdapter;

    public TeamFragment() {
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
        return inflater.inflate(R.layout.team_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_team);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        ArrayList<Link> mRecyclerArraylist = new ArrayList<>();
        mAdapter = new MainRecyclerViewAdapter(mRecyclerArraylist,this.getContext(),this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerArraylist.add(new Link("محمد شهاب","نائب مدير ادارة . فرع مرسي علم", R.drawable.mohamed_shehab));
        mRecyclerArraylist.add(new Link("بيتر خليفة","اخصائي تمويل المشروعات متناهية الصغر ، فرع القنطرة شرق.", R.drawable.peter));
        mRecyclerArraylist.add(new Link("مصطفى هادي","مسئول خدمات مصرفية - منطقة شمال الصعيد", R.drawable.hadi));
        mRecyclerArraylist.add(new Link("انس احمد الشندويلي","مسئول خدمات مصرفية - منطقة وسط الدلتا (فرع مطوبس)", R.drawable.anas));
        mRecyclerArraylist.add(new Link("علا منصور","فرع طنطا", R.drawable.ola));

        Collections.shuffle(mRecyclerArraylist);

        mAdapter.setLinkData(mRecyclerArraylist);

    }


    @Override
    public void onListItemClick(int checkedItemIndex) {

    }


}