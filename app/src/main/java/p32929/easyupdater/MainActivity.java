package p32929.easyupdater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import p32929.updaterlib.AppUpdater;
import p32929.updaterlib.UpdateListener;
import p32929.updaterlib.UpdateModel;

public class MainActivity extends AppCompatActivity {

    String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AppUpdater(this, "https://raw.githubusercontent.com/p32929/SomeHowTosAndTexts/master/BuySellBD/updater.json", new UpdateListener() {
            @Override
            public void onSuccess(UpdateModel updateModel) {
                Log.d(TAG, "onSuccess: URL: " + updateModel.getUrl());
            }

            @Override
            public void onFailed(String error) {
                Log.d(TAG, "onFailed: " + error);
            }
        }).execute();
    }
}
