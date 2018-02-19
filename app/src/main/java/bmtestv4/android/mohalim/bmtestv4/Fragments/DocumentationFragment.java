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
        mRecyclerArraylist.add(new Link("القروض الشخصية والتمويل العقاري","هذا القسم مخصص لتعليمات القروض الشخصية والتمويل العقاري ", 0));
        mRecyclerArraylist.add(new Link("الحسابات العامة","هذا القسم مخصص لتعليمات الحسابات العامة ", 0));
        mRecyclerArraylist.add(new Link("الخزينة والتللر","هذا القسم مخصص لتعليمات الخزينة والتللر ", 0));
        mRecyclerArraylist.add(new Link("الحسابات الجارية والشيكات","هذا القسم مخصص لتعليمات الحسابات الجارية والشيكات ", 0));
        mRecyclerArraylist.add(new Link("المشروعات الصغيرة والمتوسطة","هذا القسم مخصص لتعليمات المشروعات الصغيرة والمتوسطة ", 0));
        mRecyclerArraylist.add(new Link("التأمين البنكي","هذا القسم مخصص لتعليمات التأمين البنكي ", 0));
        mRecyclerArraylist.add(new Link("التحاويل الداخلية","هذا القسم مخصص لتعليمات التحاويل الداخلية ", 0));
        mRecyclerArraylist.add(new Link("الاوراق التجارية","هذا القسم مخصص لتعليمات الاوراق التجارية ", 0));
        mRecyclerArraylist.add(new Link("الالتزام المصرفي","هذا القسم مخصص لتعليمات الالتزام المصرفي ", 0));

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

        //القروض الشخصية والتمويل العقاري
        else if (checkedItemIndex == 3){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2004);
            startActivity(SME);
        }

        // الحسابات العامة
        else if (checkedItemIndex == 4){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2010);
            startActivity(SME);
        }
        // الحسابات العامة
        else if (checkedItemIndex == 5){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2011);
            startActivity(SME);
        }
        // الحسابات الجارية والشيكات
        else if (checkedItemIndex == 6){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2012);
            startActivity(SME);
        }

        // المشروعات الصغيرة والمتوسطة
        else if (checkedItemIndex == 7){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2020);
            startActivity(SME);
        }

        // التأمين البنكي
        else if (checkedItemIndex == 8){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2013);
            startActivity(SME);
        }

        // التحاويل الداخلية
        else if (checkedItemIndex == 9){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2014);
            startActivity(SME);
        }

        // الاوراق التجارية
        else if (checkedItemIndex == 10){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2015);
            startActivity(SME);
        }

        // الالتزام المصرفي
        else if (checkedItemIndex == 11){
            Intent SME = new Intent(this.getActivity(), QuizSessionActivity.class);
            SME.putExtra(category,2016);
            startActivity(SME);
        }
    }
}