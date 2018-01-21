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
        import android.widget.Toast;

        import java.util.ArrayList;

        import bmtestv4.android.mohalim.bmtestv4.Adapters.MainRecyclerViewAdapter;
        import bmtestv4.android.mohalim.bmtestv4.Link;
        import bmtestv4.android.mohalim.bmtestv4.QuizSessionActivity;
        import bmtestv4.android.mohalim.bmtestv4.R;


public class DocumentationFragment extends Fragment implements MainRecyclerViewAdapter.ListItemClickListener{
    RecyclerView mRecyclerView;
    MainRecyclerViewAdapter mAdapter;

    public DocumentationFragment() {
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
        return inflater.inflate(R.layout.documentation_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_documentation);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        ArrayList<Link> mRecyclerArraylist = new ArrayList<>();
        mAdapter = new MainRecyclerViewAdapter(mRecyclerArraylist,this.getContext(),this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerArraylist.add(new Link("المشروعات المتناهية الصغر","هذا القسم مخصص لتعليمات المشروعات المتناهية الصغر ومنشوراتها", 0));
        mRecyclerArraylist.add(new Link("ائتمان التجزئة المصرفية والبطاقات الائتمانية","هذا القسم مخصص لتعليمات ائتمان التجزئة المصرفية والبطاقات الائتمانية ", 0));
        mRecyclerArraylist.add(new Link("التوفير والاحوال الشخصية","هذا القسم مخصص لتعليمات التوفير والاحوال الشخصية ", 0));
        mAdapter.setLinkData(mRecyclerArraylist);

    }

    @Override
    public void onListItemClick(int checkedItemIndex) {
        String category = "category";

        // المشروعات المتناهية الصغر
        if (checkedItemIndex == 0){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2001);
            startActivity(SME);
        }

        // ائتمان التجزئة المصرفية والبطاقات الائتمانية
        else if (checkedItemIndex == 1){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2002);
            startActivity(SME);
        }

        // التوفير والاحوال الشخصية
        else if (checkedItemIndex == 2){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2003);
            startActivity(SME);
        }

    }
}