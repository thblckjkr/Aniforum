package tk.thblckjkr.aniforum.models;

import co.anilist.fragment.Thread;

public class User {
    public int id;
    public String name;
    public Thread.Avatar avatar;

    User (int id, String name, Thread.Avatar avatar){
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }
}
