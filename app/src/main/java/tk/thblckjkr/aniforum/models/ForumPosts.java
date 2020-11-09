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
import tk.thblckjkr.aniforum.R;
import tk.thblckjkr.aniforum.ui.home.ForumPostRecyclerViewAdapter;

public class ForumPosts {
    private static ForumPosts sForumPosts;
    private static final List<Post> postsList = new ArrayList<Post>();
    private Context mContext;

    OnResult onResult;

    public static ForumPosts get(Context context) {

        if( sForumPosts == null ) {
            sForumPosts = new ForumPosts(context);
        }

        return sForumPosts;
    }

    public ForumPosts(Context context) {
        mContext = context.getApplicationContext();
    }

    public List<Post> posts(){
        return postsList;
    }

    public void setCallback(OnResult onResult) {
        this.onResult = onResult;
    }

    public void loadPosts(ForumPostRecyclerViewAdapter adapter, Handler uiHandler) {
        // clear all loaded posts before loading new ones
        postsList.clear();

        ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://graphql.anilist.co/")
            .build();

        apolloClient.query( new GetAllForumPostsQuery() )
        .enqueue(new ApolloCallback<>(new ApolloCall.Callback<GetAllForumPostsQuery.Data>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NotNull Response<GetAllForumPostsQuery.Data> response) {
                response.data().recent().threads().forEach( (d)-> {
                    List<Category> categories = new ArrayList<Category>();
                    User user = new User(
                            d.fragments().thread().user().id(),
                            d.fragments().thread().user().name(),
                            d.fragments().thread().user().avatar()
                    );

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    Date date = null;
                    try {
                        date = dateFormat.parse( String.valueOf(d.fragments().thread().createdAt()) );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Log.e("ApolloDataItem", "Processing item" + d.fragments().thread().id());

                    postsList.add( new Post(
                            d.fragments().thread().id(),
                            d.fragments().thread().title(),
                            d.fragments().thread().body(),
                            user,
                            d.fragments().thread().replyCount(),
                            d.fragments().thread().viewCount(),
                            date,
                            categories
                    ));
                });

                Log.e("ApolloData", "Data received");
                adapter.setPosts(sForumPosts);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e("Apollo", "Error", e);
            }
        }, uiHandler));
    }
}
