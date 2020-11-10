package tk.thblckjkr.aniforum.ui.post;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.Markwon;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.core.MarkwonTheme;
import io.noties.markwon.image.AsyncDrawableLoader;
import io.noties.markwon.image.ImagesPlugin;
import tk.thblckjkr.aniforum.R;
import tk.thblckjkr.aniforum.models.ForumPostComments;


public class ViewPostFragment extends Fragment {
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private ForumPostComments mComments;
    View mView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_view_post, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPostActivity activity = (ViewPostActivity)getActivity();
        mView = view;

        Context context = mView.getContext();
        mComments = ForumPostComments.get(context);
        mComments.loadComments( activity.postId, this, mHandler);
    }

    public void setComments(ForumPostComments comments) {
        mComments = comments;
        updateUI();
    }

    public void updateUI(){
        Context context = mView.getContext();
        final Markwon markwon = Markwon.builder(context)
            .usePlugin(new AbstractMarkwonPlugin() {
                @Override
                public void configureConfiguration(@NonNull MarkwonConfiguration.Builder builder) {
                    builder.asyncDrawableLoader(AsyncDrawableLoader.noOp());
                }
            })
            .usePlugin(ImagesPlugin.create())
            .usePlugin(new AbstractMarkwonPlugin() {
                @Override
                public void configureTheme(@NonNull MarkwonTheme.Builder builder) {
                    builder
                        .linkColor(Color.WHITE)
                        .codeTextColor(Color.BLACK)
                        .codeBackgroundColor(Color.LTGRAY);
                }
            })
            .build();

        TextView post = (TextView)mView.findViewById(R.id.post_body_viewComments);
        markwon.setMarkdown(post, mComments.post.body);

        TextView title = (TextView)mView.findViewById(R.id.post_title_viewComments);
        title.setText(mComments.post.title);

        TextView author = (TextView)mView.findViewById(R.id.post_author_viewComments);
        author.setText(mComments.post.user.name);

        ImageView avatar = (ImageView) mView.findViewById(R.id.user_avatar_viewComments);

        try {
            Ion.with(avatar)
                    .placeholder(R.drawable.hourglass_empty)
                    .error(R.drawable.broken_image)
                    .load(mComments.post.user.avatarSrc);
        }catch (Exception e) {
            // Catching exceptions is hard
        }


    }
}
