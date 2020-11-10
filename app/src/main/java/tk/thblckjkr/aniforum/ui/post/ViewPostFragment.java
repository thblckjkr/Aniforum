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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tk.thblckjkr.aniforum.R;
import tk.thblckjkr.aniforum.models.ForumPostComments;


public class ViewPostFragment extends Fragment {
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private ForumPostComments mComments;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_view_post, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        int fragment = getArguments().getInt("postId", 1);
        ViewPostActivity activity = (ViewPostActivity)getActivity();

        super.onViewCreated(view, savedInstanceState);


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
    }

    public void updateUI(){

    }
}
