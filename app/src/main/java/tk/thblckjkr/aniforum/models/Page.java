package tk.thblckjkr.aniforum.models;

public class Page {
    int total;
    int perPage;
    int currentPage;
    int lastPage;
    int hasNextPage;

    Page (int total, int perPage, int currentPage, int lastPage, int hasNextPage){
        this.total = total;
        this.perPage = perPage;
        this.currentPage = currentPage;
        this.lastPage = lastPage;
        this.hasNextPage = hasNextPage;
    }
}