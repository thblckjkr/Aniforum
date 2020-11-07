package tk.thblckjkr.aniforum.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import co.anilist.GetForumPostQuery;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        Input<Integer> input;
        input = Input.fromNullable(11738);

        ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://anilist.co/graphql")
            .build();

        apolloClient.query(new GetForumPostQuery( input ) )
        .enqueue(new ApolloCall.Callback<GetForumPostQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetForumPostQuery.Data> response) {
//                Log.e("Apollo", "data" + response.getData().toString());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
//                Log.e("Apollo", "Error", e);
            }
        });


        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}