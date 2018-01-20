package bmtestv4.android.mohalim.bmtestv4.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bmtestv4.android.mohalim.bmtestv4.Link;
import bmtestv4.android.mohalim.bmtestv4.R;
import bmtestv4.android.mohalim.bmtestv4.Result.CorrectAnswer;

/**
 * Created by Mohamed ALi on 12/28/2017.
 */

public class CorrectAnswerAdapter extends RecyclerView.Adapter<CorrectAnswerAdapter.MainAdapterViewHolder> {

    private ArrayList<CorrectAnswer> mLinkData;
    Context mCtx;




    public CorrectAnswerAdapter(ArrayList<CorrectAnswer> arrayList, Context context){
        mLinkData = new ArrayList<>();
        mLinkData.addAll(arrayList);
        mCtx = context;

    }


    @Override
    public MainAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.row_recycler_correct_answer;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MainAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapterViewHolder holder, int position) {
        CorrectAnswer correctAnswer = mLinkData.get(position);
        holder.title.setText(correctAnswer.getQuestion());
        holder.description.setText(correctAnswer.getCorrectAnswer());
        holder.choices.setText(correctAnswer.getChoices());
    }

    @Override
    public int getItemCount() {
        if(null == mLinkData) return 0;
        return mLinkData.size();
    }

    class MainAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView title,description,choices;
        public MainAdapterViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_recyclerview_title);
            description = itemView.findViewById(R.id.tv_recyclerview_description);
            choices = itemView.findViewById(R.id.tv_choices);
        }

    }

    public void setCorrectAnswer(ArrayList<CorrectAnswer> linkData) {
        mLinkData = linkData;
        notifyDataSetChanged();
    }

}
