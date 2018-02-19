package bmtestv4.android.mohalim.bmtestv4;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import bmtestv4.android.mohalim.bmtestv4.Fragments.DocumentationFragment;
import bmtestv4.android.mohalim.bmtestv4.Fragments.ProductFragments;
import bmtestv4.android.mohalim.bmtestv4.Fragments.QuizFragments;
import bmtestv4.android.mohalim.bmtestv4.Fragments.RulesFragments;
import bmtestv4.android.mohalim.bmtestv4.Fragments.SkillsFragment;
import bmtestv4.android.mohalim.bmtestv4.Fragments.TeamFragment;

public class MainActivity extends AppCompatActivity {

    //For Result Activity
    public static int circlCorners, circleStroke;


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleStroke = convertDpToPixel(30);
        circlCorners = convertDpToPixel(250);



        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(5).setIcon(R.drawable.ethics);
        tabLayout.getTabAt(4).setIcon(R.drawable.products_icon);
        tabLayout.getTabAt(3).setIcon(R.drawable.documentation_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.skills_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.quiz_icon);
        tabLayout.getTabAt(0).setIcon(R.drawable.team_icon);

        /////// Temp
        //startActivity(new Intent(MainActivity.this,QuizSessionActivity.class));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TeamFragment(), "فريق العمل");
        adapter.addFragment(new QuizFragments(), "الامتحانات");
        adapter.addFragment(new SkillsFragment(), "المهارات");
        adapter.addFragment( new DocumentationFragment(), "التعليمات");
        adapter.addFragment( new ProductFragments(), "المنتجات");
        adapter.addFragment( new RulesFragments(), "الميثاق والمخاطر");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(5);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int DEVICE_DENSITY_DPI = metrics.densityDpi;

        return (int) (dp * (DEVICE_DENSITY_DPI / 160f));
    }
}

