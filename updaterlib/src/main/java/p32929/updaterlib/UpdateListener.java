package p32929.updaterlib;

public interface UpdateListener {
    void onSuccess(UpdateModel updateModel);
    void onFailed(String error);
}
