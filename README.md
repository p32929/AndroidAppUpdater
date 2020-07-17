# AndroidAppUpdater
A simple, Lightweight App Updater Library for Android

[![](https://badgen.net/github/release/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/release/p32929/AndroidAppUpdater/stable)]() [![](https://badgen.net/github/tag/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/watchers/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/checks/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/status/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/stars/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/forks/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/issues/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/open-issues/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/closed-issues/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/label-issues/p32929/AndroidAppUpdater/help-wanted/open)]() [![](https://badgen.net/github/prs/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/open-prs/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/closed-prs/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/merged-prs/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/commits/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/last-commit/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/branches/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/releases/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/tags/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/license/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/contributors/p32929/AndroidAppUpdater)]() [![](https://badgen.net/github/dependents-pkg/p32929/AndroidAppUpdater)]() 

## Share
Sharing with your friends is just one click away from here

[![facebook](https://image.flaticon.com/icons/png/32/124/124010.png)](https://www.facebook.com/sharer/sharer.php?u=https://github.com/p32929/AndroidAppUpdater)
[![twitter](https://image.flaticon.com/icons/png/32/124/124021.png)](https://twitter.com/intent/tweet?source=https://github.com/p32929/AndroidAppUpdater)
[![tumblr](https://image.flaticon.com/icons/png/32/124/124012.png)](https://www.tumblr.com/share?v=3&u=https://github.com/p32929/AndroidAppUpdater)
[![pocket](https://image.flaticon.com/icons/png/32/732/732238.png)](https://getpocket.com/save?url=https://github.com/p32929/AndroidAppUpdater)
[![pinterest](https://image.flaticon.com/icons/png/32/124/124039.png)](https://pinterest.com/pin/create/button/?url=https://github.com/p32929/AndroidAppUpdater)
[![reddit](https://image.flaticon.com/icons/png/32/2111/2111589.png)](https://www.reddit.com/submit?url=https://github.com/p32929/AndroidAppUpdater)
[![linkedin](https://image.flaticon.com/icons/png/32/1409/1409945.png)](https://www.linkedin.com/shareArticle?mini=true&url=https://github.com/p32929/AndroidAppUpdater)
[![whatsapp](https://image.flaticon.com/icons/png/32/733/733585.png)](https://api.whatsapp.com/send?text=https://github.com/p32929/AndroidAppUpdater)

## Support
If you like my works and want to support me/my works, feel free to support or donate. My payment details can be found here: https://p32929.github.io/SendMoney2Me/

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
new AppUpdater(this, "https://raw.githubusercontent.com/p32929/SomeHowTosAndTexts/master/Updater/updater.json", new UpdateListener() {
            @Override
            public void onJsonDataReceived(final UpdateModel updateModel, JSONObject jsonObject) {
                if (AppUpdater.getCurrentVersionCode(MainActivity.this) < updateModel.getVersionCode()) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Update available")
                            .setCancelable(updateModel.isCancellable())
                            .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateModel.getUrl()));
                                    startActivity(browserIntent);
                                    finish();
                                }
                            })
                            .show();
                }
            }

            @Override
            public void onError(String error) {
                // Do something
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

If you add more data in the JSON file, you can get them from the `jsonObject` variable.

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
