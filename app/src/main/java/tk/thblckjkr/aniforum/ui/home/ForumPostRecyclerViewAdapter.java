package tk.thblckjkr.aniforum.ui.home;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
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
import tk.thblckjkr.aniforum.ui.post.ViewPostActivity;

public class ForumPostRecyclerViewAdapter extends RecyclerView.Adapter<ForumPostRecyclerViewAdapter.ViewHolder> {
    private AdapterView.OnClickListener onClickListener;

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
        holder.mPostUserView.setText("@" + mPosts.posts().get(position).user.name);

        holder.mPostImage.setVisibility(View.GONE);

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

        holder.mButtonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent intent;

                intent =  new Intent(v.getContext(), ViewPostActivity.class);
                intent.putExtra("postId", mPosts.posts().get(position).id);

                v.getContext().startActivity(intent);
            }
        });

        holder.mButtonShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Look at this post on the Anilist Forum!");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mPosts.posts().get(position).shareText);

                v.getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPosts.posts().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        public final View mView;
        public final TextView mPostTitleView;
        public final TextView mPostBodyView;
        public final TextView mPostUserView;
        public final ImageView mPostUserAvatar;
        public final ImageView mPostImage;
        public final Button mButtonView;
        public final Button mButtonShare;

        private final Context context;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();

            mPostTitleView = (TextView) view.findViewById(R.id.post_title);
            mPostBodyView = (TextView) view.findViewById(R.id.post_body);
            mPostUserView = (TextView) view.findViewById(R.id.post_author_viewComments);
            mPostImage = (ImageView) view.findViewById(R.id.post_image);
            mPostUserAvatar = (ImageView) view.findViewById(R.id.user_avatar);
            mButtonView = (Button) view.findViewById(R.id.post_view_button);
            mButtonShare = (Button) view.findViewById(R.id.post_share_button);

        }

        @Override
        public void onClick(View v) {
            Log.e("Click", "On click on 1");
            final Intent intent;
            if (getAdapterPosition() == 0){
                intent =  new Intent(context, ViewPostActivity.class);
//            } else if (getPosition() == sth2){
//                intent =  new Intent(context, SecondActivity.class);
            } else {
                intent =  new Intent(context, ViewPostActivity.class);
            }
            context.startActivity(intent);
        }

        @Override
        public String toString() {
            return mPostBodyView.getText().toString();
        }

    }
}