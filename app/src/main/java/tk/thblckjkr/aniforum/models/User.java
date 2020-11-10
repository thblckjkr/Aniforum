package tk.thblckjkr.aniforum.models;

import co.anilist.GetForumPostQuery;
import co.anilist.fragment.Thread;

public class User {
    public int id;
    public String name;
    public Thread.Avatar avatar;
    public String avatarSrc;

    User (int id, String name, Thread.Avatar avatar){
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.avatarSrc = avatar.medium();
    }

    User (int id, String name, GetForumPostQuery.Avatar avatar){
        this.id = id;
        this.name = name;
        this.avatarSrc = avatar.medium();
    }

    User (int id, String name, String avatar){
        this.id = id;
        this.name = name;
        this.avatarSrc = avatar;
    }
}
