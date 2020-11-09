package tk.thblckjkr.aniforum.ui.home;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import tk.thblckjkr.aniforum.R;
import tk.thblckjkr.aniforum.models.ForumPosts;
import tk.thblckjkr.aniforum.models.OnResult;

public class ForumPostRecyclerViewAdapter extends RecyclerView.Adapter<ForumPostRecyclerViewAdapter.ViewHolder> {
    private AdapterView.OnItemClickListener onItemClickListener;

    private ForumPosts mPosts;

    public ForumPostRecyclerViewAdapter(ForumPosts items) {
        mPosts = items;
        ForumPostRecyclerViewAdapter adapter = this;
    }

    public void setPosts(ForumPosts posts) {
        mPosts = posts;
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
        String body = mPosts.posts().get(position).body;
        body = body.length() > 256 ? body.substring(0, 256) + "..." : body;

        holder.mPostTitleView.setText(mPosts.posts().get(position).title);
        holder.mPostBodyView.setText(body);
        holder.mPostUserView.setText("u/" + mPosts.posts().get(position).user.name);

        holder.mPostImage.setVisibility(View.GONE);
        holder.mButtonView.setOnClickListener(v -> {
            Log.e("A clicked button button", "Clicked");
        });

        try {
            Ion.with(holder.mPostUserAvatar)
                    .placeholder(R.drawable.hourglass_empty)
                    .error(R.drawable.broken_image)
//                .animateLoad(  )
//                .animateIn(fadeInAnimation)
                    .load(mPosts.posts().get(position).user.avatar.medium());
        }catch (Exception e) {
            // Catching exceptions is hard
        }

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
        public final ImageView mPostUserAvatar;
        public final ImageView mPostImage;
        public final Button mButtonView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mPostTitleView = (TextView) view.findViewById(R.id.post_title);
            mPostBodyView = (TextView) view.findViewById(R.id.post_body);
            mPostUserView = (TextView) view.findViewById(R.id.post_author);
            mPostImage = (ImageView) view.findViewById(R.id.post_image);
            mPostUserAvatar = (ImageView) view.findViewById(R.id.user_avatar);
            mButtonView = (Button) view.findViewById(R.id.post_view_button);
        }

        @Override
        public String toString() {
            return mPostBodyView.getText().toString();
        }

    }
}