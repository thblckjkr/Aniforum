package tk.thblckjkr.aniforum.ui.home;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tk.thblckjkr.aniforum.R;
import tk.thblckjkr.aniforum.models.ForumPosts;

public class ForumPostRecyclerViewAdapter extends RecyclerView.Adapter<ForumPostRecyclerViewAdapter.ViewHolder> {

    private final ForumPosts mPosts;

    public ForumPostRecyclerViewAdapter(ForumPosts items) {
        mPosts = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mPosts.get(0).;
        holder.mPostTitleView.setText(mPosts.posts().get(position).title);
        holder.mPostBodyView.setText(mPosts.posts().get(position).body);
        holder.mPostUserView.setText(mPosts.posts().get(position).user.name);
    }

    @Override
    public int getItemCount() {
        return mPosts.posts().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mPostTitleView;
        public final TextView mPostBodyView;
        public final TextView mPostUserView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mPostTitleView = (TextView) view.findViewById(R.id.post_title);
            mPostBodyView = (TextView) view.findViewById(R.id.post_body);
            mPostUserView = (TextView) view.findViewById(R.id.post_author);
        }

        @Override
        public String toString() {
            return mPostBodyView.getText().toString();
        }
    }
}