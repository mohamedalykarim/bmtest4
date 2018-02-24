package bmtestv4.android.mohalim.bmtestv4.Adapters;

import android.content.Context;
import android.os.AsyncTask;
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

/**
 * Created by Mohamed ALi on 12/28/2017.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainAdapterViewHolder> {

    private ArrayList<Link> mLinkData;
    Context mCtx;
    final private ListItemClickListener mListItemClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int checkedItemIndex);
    }


    public MainRecyclerViewAdapter(ArrayList<Link> arrayList, Context context, ListItemClickListener listener) {
        mLinkData = new ArrayList<>();
        mLinkData.addAll(arrayList);
        mCtx = context;
        mListItemClickListener = listener;

    }


    @Override
    public MainAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.row_recycler_main;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MainAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapterViewHolder holder, int position) {
        Link link = mLinkData.get(position);
        holder.title.setText(link.get_title());
        holder.description.setText(link.get_description());

        if (link.get_resource() != 0) {
            Picasso.with(mCtx)
                    .load(link.get_resource())
                    .resize(150, 150)
                    .into(holder.iconView);


        }
    }

    @Override
    public int getItemCount() {
        if (null == mLinkData) return 0;
        return mLinkData.size();
    }

    class MainAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, description;
        ImageView iconView;

        public MainAdapterViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_recyclerview_title);
            description = itemView.findViewById(R.id.tv_recyclerview_description);
            iconView = itemView.findViewById(R.id.icon_recycleview);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clickedPostion = getAdapterPosition();
            mListItemClickListener.onListItemClick(clickedPostion);
        }
    }

    public void setLinkData(ArrayList<Link> linkData) {
        mLinkData = linkData;
        notifyDataSetChanged();
    }



}
