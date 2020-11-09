package tk.thblckjkr.aniforum.models;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import co.anilist.GetAllForumPostsQuery;

public class ForumPosts {
    private static ForumPosts sForumPosts;
    private static final List<Post> postsList = new ArrayList<Post>();
    private Context mContext;


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

    public void loadPosts( ){
        ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://graphql.anilist.co/")
            .build();

        apolloClient.query( new GetAllForumPostsQuery() )
        .enqueue(new ApolloCall.Callback<GetAllForumPostsQuery.Data>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NotNull Response<GetAllForumPostsQuery.Data> response) {
                response.getData().recent().threads().forEach( (d)-> {
                    List<Category> categories = new ArrayList<Category>();
                    User user = new User(
                            d.fragments().thread().user().id(),
                            d.fragments().thread().user().name(),
                            d.fragments().thread().user().avatar()
                    );

                    Log.e("ApolloDataItem", "Processing item" + d.fragments().thread().id());

                    postsList.add( new Post(
                            d.fragments().thread().id(),
                            d.fragments().thread().title(),
                            d.fragments().thread().body(),
                            user,
                            2,
                            5,
                            null,
                            categories
                    ));
                });

                Log.e("ApolloData", "Data received");
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e("Apollo", "Error", e);
            }
        });
    }
}
