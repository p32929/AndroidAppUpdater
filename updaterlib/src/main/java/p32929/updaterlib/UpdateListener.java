package p32929.updaterlib;

public interface UpdateListener {
    void onUpdateAvailable(UpdateModel updateModel);
    void onError(String error);
}
