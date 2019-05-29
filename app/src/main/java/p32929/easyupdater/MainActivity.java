package p32929.easyupdater;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

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
            public void onUpdateAvailable(final UpdateModel updateModel) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Update available")
                        .setCancelable(updateModel.isForceUpdate())
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateModel.getUrl()));
                                startActivity(browserIntent);
                            }
                        })
                        .show();
            }

            @Override
            public void onError(String error) {

            }
        }).execute();
    }
}
