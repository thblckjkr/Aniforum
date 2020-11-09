package tk.thblckjkr.aniforum.models;

public class Categories {
    static Category categories[] = new Category[15];
    Categories() {
        categories[0] = new Category(7, "General");
        categories[1] = new Category(1, "Anime");
        categories[2] = new Category(2, "Manga");
        categories[3] = new Category(5, "Release Discussion");
        categories[4] = new Category(8, "News");
        categories[5] = new Category(9, "Music");
        categories[6] = new Category(10, "Gaming");
        categories[7] = new Category(4, "Visual Novels");
        categories[8] = new Category(3, "Light Novels");
        categories[9] = new Category(16, "Forum Games");
        categories[10] = new Category(15, "Recommendations");
        categories[11] = new Category(11, "Site Feedback");
        categories[12] = new Category(12, "Bug Reports");
        categories[13] = new Category(18, "AniList Apps");
        categories[14] = new Category(17, "Misc");
    }

    Category[] getCategories() {
        return categories;
    }

}
