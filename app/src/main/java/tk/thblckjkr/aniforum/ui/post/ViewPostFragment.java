package tk.thblckjkr.aniforum.ui.post;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

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

//        mComments.loadComments( activity.postId, null, mHandler );

//        view.findViewById(R.id.).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(ViewPostFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }

    public void setComments(ForumPostComments comments) {
        mComments = comments;
        updateUI();
    }

    public void updateUI(){
        TextView post = (TextView)mView.findViewById(R.id.post_body_viewComments);
        post.setText(mComments.post.body);

        TextView title = (TextView)mView.findViewById(R.id.post_title_viewComments);
        title.setText(mComments.post.title);

        TextView author = (TextView)mView.findViewById(R.id.post_author_viewComments);
        author.setText(mComments.post.user.name);

        ImageView avatar = (ImageView) mView.findViewById(R.id.user_avatar_viewComments);

        try {
            Ion.with(avatar)
                    .placeholder(R.drawable.hourglass_empty)
                    .error(R.drawable.broken_image)
//                .animateLoad(  )
//                .animateIn(fadeInAnimation)
                    .load(mComments.post.user.avatarSrc);
        }catch (Exception e) {
            // Catching exceptions is hard
        }


    }
}
