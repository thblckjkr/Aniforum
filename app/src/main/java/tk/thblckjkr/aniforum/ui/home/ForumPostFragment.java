package tk.thblckjkr.aniforum.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import tk.thblckjkr.aniforum.MainActivity;
import tk.thblckjkr.aniforum.R;
import tk.thblckjkr.aniforum.models.ForumPosts;
import tk.thblckjkr.aniforum.models.OnResult;
import tk.thblckjkr.aniforum.models.Post;

/**
 * A fragment representing a list of Items.
 */
public class ForumPostFragment extends Fragment  {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private ForumPostRecyclerViewAdapter mAdapter;
    private RecyclerView mView;
    private ForumPosts mPosts;

    private final Handler mhandler = new Handler(Looper.getMainLooper());

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ForumPostFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ForumPostFragment newInstance(int columnCount) {
        ForumPostFragment fragment = new ForumPostFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {

            Context context = view.getContext();
            mView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            mPosts = ForumPosts.get(context);
            updateUI();

            mPosts.loadPosts( mAdapter, mhandler );
        }

        return view;
    }

    public void updateUI() {
        Context context = mView.getContext();
        mPosts = ForumPosts.get(context);

        if (mAdapter == null) {
            mAdapter = new ForumPostRecyclerViewAdapter(mPosts);
            mView.setAdapter(mAdapter);
        } else {
            mAdapter.setPosts(mPosts);
            mAdapter.notifyDataSetChanged();
        }
    }

}