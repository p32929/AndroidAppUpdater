# AndroidAppUpdater
A simple, Lightweight App Updater Library for Android

## Installation
Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency
```
dependencies {
     implementation 'com.github.p32929:AndroidAppUpdater:1.0.2'
}
```

## Basic Usage
Steps to follow:
* Create new `new AppUpdater(context, jsonUrl, listener)`
* Execute the task
* Do something if update is available

## Code example
In your Activity or Fragment, you can add the code below:
```
new AppUpdater(this, "https://raw.githubusercontent.com/p32929/SomeHowTosAndTexts/master/BuySellBD/updater.json", new UpdateListener() {
            @Override
            public void onJsonDataReceived(final UpdateModel updateModel) {
                // Do something here
            }

            @Override
            public void onError(String error) {
                // Check if there's any error
            }
        }).execute();
```

If you want to show a dialog, when an update is available, you can use the code below:
```
new AppUpdater(this, "https://raw.githubusercontent.com/p32929/SomeHowTosAndTexts/master/BuySellBD/updater.json", new UpdateListener() {
            @Override
            public void onJsonDataReceived(final UpdateModel updateModel) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Update available")
                        .setCancelable(!updateModel.isForceUpdate())
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
                // Check if there's any error
            }
        }).execute();
```

the JSON data should be like below:
```
{
	"versionCode": 1, // Updated version code
	"cancellable": false, // If you want to force users to update the app
	"url": "http://tiny.cc/BuySellBD" // If you want users to update the app from an External URL or from the Google Play URL
}
```

Hope you will enjoy using the library :)
Thanks :)

## License:
```
MIT License

Copyright (c) 2019 Fayaz Bin Salam

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```