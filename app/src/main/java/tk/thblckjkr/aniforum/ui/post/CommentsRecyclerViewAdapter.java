package tk.thblckjkr.aniforum.ui.post;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.Markwon;
import io.noties.markwon.core.MarkwonTheme;
import io.noties.markwon.image.ImagesPlugin;
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
        holder.mPostUserAuthor.setText("@" + mComments.comments().get(position).user.name);

        Context context = holder.itemView.getContext();

        final Markwon markwon = Markwon.builder(context)
        .usePlugin(ImagesPlugin.create())
        .usePlugin(new AbstractMarkwonPlugin() {
            @Override
            public void configureTheme(@NonNull MarkwonTheme.Builder builder) {
                builder
                    .linkColor(Color.BLACK)
                    .codeTextColor(Color.BLACK)
                    .codeBackgroundColor(Color.LTGRAY);
            }
        })
        .build();

        markwon.setMarkdown(holder.mPostComment, mComments.comments().get(position).comment);
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
        }

        @Override
        public String toString() {
            return mPostComment.getText().toString();
        }

    }
}