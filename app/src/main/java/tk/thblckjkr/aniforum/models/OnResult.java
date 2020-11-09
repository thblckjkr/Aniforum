package tk.thblckjkr.aniforum.models;

public interface OnResult {
    void onSuccess();
    void onError(Exception e);
}