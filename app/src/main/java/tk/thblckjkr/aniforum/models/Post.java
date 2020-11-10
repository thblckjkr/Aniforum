package tk.thblckjkr.aniforum.models;

import java.util.Date;
import java.util.List;

public class Post {
    public int id;
    public String title;
    public String body;
    public User user;
    public int replyCount;
    public int viewCount;
    public Date createdAt;
    public List<Category> categories;
    public String shareText;

    Post (int id, String title, String body, User user, int replyCount,
          int viewCount, Date createdAt, List<Category> categories){
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
        this.replyCount = replyCount;
        this.viewCount = viewCount;
        this.createdAt = createdAt;

        this.shareText = "Look at this thing! \n " + title + "\n https://anilist.co/forum/thread/" + id;
    }
}
