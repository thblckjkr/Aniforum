package tk.thblckjkr.aniforum.models;

import java.util.ArrayList;
import java.util.List;

import co.anilist.GetForumPostQuery;

public class Comment {
    public int id;
    public String comment;
    public int isLiked;
    public int likeCount;
//    Date creationDate;
    public User user;
    public List<Comment> childComments;

    Comment(){

    }

    Comment( int id, String comment, int isLiked, int likeCount, User user, List<Comment> childComments){
        this.id = id;
        this.comment = comment;
        this.isLiked = isLiked;
        this.likeCount = likeCount;
        this.user = user;
        this.childComments = childComments;
    }

    Comment( GetForumPostQuery.ThreadComment comment ){
        List<Comment> childComments = new ArrayList<Comment>();
        Comment holder = new Comment();
        this.id = comment.id();
        this.comment = comment.comment();
        this.user = new User(comment.user().id(), comment.user().name(), comment.user().avatar().large());
//        comment.childComments().
        this.childComments = childComments;
    };
}