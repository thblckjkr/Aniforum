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

import tk.thblckjkr.aniforum.R;
import tk.thblckjkr.aniforum.models.ForumPostComments;
import tk.thblckjkr.aniforum.models.ForumPosts;
import tk.thblckjkr.aniforum.ui.home.ForumPostRecyclerViewAdapter;


public class CommentsFragment extends Fragment {
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private CommentsRecyclerViewAdapter mAdapter;
    private RecyclerView mView;
    private ForumPostComments mComments;
    int mColumnCount;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        ViewPostActivity activity = (ViewPostActivity)getActivity();
        mColumnCount = 1;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_post_comments, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {

            Context context = view.getContext();
            mView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            updateUI();

            Log.e("IDINTENT", "ID " + activity.postId);
            mComments.loadComments( activity.postId, mAdapter, mHandler );
        }

        return view;
    }

    public void updateUI() {
        Context context = mView.getContext();
        mComments = ForumPostComments.get(context);

        if (mAdapter == null) {
            mAdapter = new CommentsRecyclerViewAdapter(mComments);
            mView.setAdapter(mAdapter);
        } else {
            mAdapter.setComments(mComments);
            mAdapter.notifyDataSetChanged();
        }
    }
}
