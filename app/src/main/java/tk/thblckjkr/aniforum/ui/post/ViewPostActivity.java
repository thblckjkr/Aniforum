package tk.thblckjkr.aniforum.ui.post;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import tk.thblckjkr.aniforum.MainActivity;
import tk.thblckjkr.aniforum.R;

public class ViewPostActivity extends AppCompatActivity {
    public int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        postId = b.getInt("postId");

        setContentView(R.layout.activity_view_post);
        Toolbar toolbar = findViewById(R.id.viewpost_toolbar);
        setSupportActionBar(toolbar);

        Log.e("Intent received", "IUD = " + postId);

        toolbar.setNavigationIcon(R.drawable.abc_vector_test);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Look at this post on the Anilist Forum!");
                String shareBody = "Look at this thing! \n " + "\n https://anilist.co/forum/thread/" + postId;
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody );

                view.getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }
}