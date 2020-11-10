package tk.thblckjkr.aniforum.models;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloCallback;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.anilist.GetAllForumPostsQuery;
import co.anilist.GetForumPostQuery;
import tk.thblckjkr.aniforum.R;
import tk.thblckjkr.aniforum.ui.home.ForumPostRecyclerViewAdapter;
import tk.thblckjkr.aniforum.ui.post.CommentsRecyclerViewAdapter;
import tk.thblckjkr.aniforum.ui.post.ViewPostFragment;

public class ForumPostComments {
    private static ForumPostComments sForumComments;
    public Post post;
    private static final List<Comment> commentsList = new ArrayList<Comment>();
    private Context mContext;

    OnResult onResult;

    public static ForumPostComments get(Context context) {

        if( sForumComments == null ) {
            sForumComments = new ForumPostComments(context);
        }

        return sForumComments;
    }

    public ForumPostComments(Context context) {
        mContext = context.getApplicationContext();
    }

    public List<Comment> comments(){
        return commentsList;
    }

    public void setCallback(OnResult onResult) {
        this.onResult = onResult;
    }

    public void loadComments(int postId, CommentsRecyclerViewAdapter adapter, Handler uiHandler) {
        // clear all loaded posts before loading new ones
        commentsList.clear();

        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl("https://graphql.anilist.co/")
                .build();

        apolloClient.query( new GetForumPostQuery( postId,1 ) )
            .enqueue(new ApolloCallback<>(new ApolloCall.Callback<GetForumPostQuery.Data>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(@NotNull Response<GetForumPostQuery.Data> response) {
                    User user = new User(
                            response.data().Thread().user().id(),
                            response.data().Thread().user().name(),
                            response.data().Thread().user().avatar()
                    );

                    List<Category> categories = new ArrayList<Category>();

                    post = new Post(
                            response.data().Thread().id(),
                            response.data().Thread().title(),
                            response.data().Thread().body(),
                            user,
                            response.data().Thread().replyCount(),
                            response.data().Thread().viewCount(),
                            null,
                            categories
                    );

                    response.data().Page().threadComments().forEach( (d)-> {
                        Log.i("ApolloDataItem", "Comment received" + d.comment());
                        commentsList.add( new Comment(d) );
                    });

                    Log.e("ApolloData", "Data received");

                    adapter.setComments(sForumComments);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                    Log.e("Apollo", "Error", e);
                }
            }, uiHandler));
    }

    public void loadComments(int postId, ViewPostFragment fragment, Handler uiHandler) {
        // clear all loaded posts before loading new ones
        commentsList.clear();

        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl("https://graphql.anilist.co/")
                .build();

        apolloClient.query( new GetForumPostQuery( postId,1 ) )
                .enqueue(new ApolloCallback<>(new ApolloCall.Callback<GetForumPostQuery.Data>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(@NotNull Response<GetForumPostQuery.Data> response) {
                        User user = new User(
                                response.data().Thread().user().id(),
                                response.data().Thread().user().name(),
                                response.data().Thread().user().avatar()
                        );

                        List<Category> categories = new ArrayList<Category>();

                        post = new Post(
                                response.data().Thread().id(),
                                response.data().Thread().title(),
                                response.data().Thread().body(),
                                user,
                                response.data().Thread().replyCount(),
                                response.data().Thread().viewCount(),
                                null,
                                categories
                        );

                        response.data().Page().threadComments().forEach( (d)-> {
                            Log.i("ApolloDataItem", "Comment received" + d.comment());
                            commentsList.add( new Comment(d) );
                        });

                        Log.e("ApolloData", "Data received");

                        fragment.setComments(sForumComments);
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.e("Apollo", "Error", e);
                    }
                }, uiHandler));
    }



}
