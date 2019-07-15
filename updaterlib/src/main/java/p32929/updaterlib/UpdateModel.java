package p32929.updaterlib;

public class UpdateModel {
    int versionCode;
    boolean cancellable;
    String url;

    public UpdateModel(int versionCode, boolean cancellable, String url) {
        this.versionCode = versionCode;
        this.cancellable = cancellable;
        this.url = url;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
