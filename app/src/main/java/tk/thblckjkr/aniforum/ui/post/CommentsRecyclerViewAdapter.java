package tk.thblckjkr.aniforum.ui.post;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import tk.thblckjkr.aniforum.models.ForumPostComments;
import tk.thblckjkr.aniforum.models.ForumPosts;
import tk.thblckjkr.aniforum.models.OnResult;
import tk.thblckjkr.aniforum.ui.post.ViewPostActivity;

public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.ViewHolder> {
    private AdapterView.OnClickListener onClickListener;

    private ForumPostComments mComments;

    public CommentsRecyclerViewAdapter(ForumPostComments items) {
        mComments = items;
        CommentsRecyclerViewAdapter adapter = this;
    }

    public void setComments(ForumPostComments comments) {
        mComments = comments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post_comment, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        String body = mComments.comments().get(position).body;
//        body = body.length() > 256 ? body.substring(0, 256) + "..." : body;
//
        holder.mPostComment.setText(mComments.comments().get(position).comment);
        holder.mPostUserAuthor.setText(mComments.comments().get(position).user.name);
//        holder.mPostBodyView.setText(body);
//        holder.mPostUserView.setText("u/" + mComments.comments().get(position).user.name);
//
//        holder.mPostImage.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mComments.comments().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView mPostComment;
        public final TextView mPostUserAuthor;


        private final Context context;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();

            mPostComment = (TextView) view.findViewById(R.id.textComment);
            mPostUserAuthor = (TextView) view.findViewById(R.id.textCommentAuthor);

//            mButtonView = (Button) view.findViewById(R.id.post_view_button);
//            mButtonShare = (Button) view.findViewById(R.id.post_share_button);

        }

        @Override
        public String toString() {
            return mPostComment.getText().toString();
        }

    }
}