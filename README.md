# AndroidAppUpdater

A small update checker for Android apps. You host one JSON file anywhere (GitHub, your server, S3). The library reads it, compares the version with the installed app, and can show a ready-made update dialog, or just hand you the data.

1. **You host the JSON:** no Play Services, no update SDK.
2. **Built-in dialog:** shows the new version and what's new, and follows your app's theme in light and dark mode.
3. **Forced updates:** set `"cancellable": false` and the dialog has no "Later" button.
4. **Or build your own UI:** get the parsed data and the whole JSON in a callback.
5. **Tiny:** a few small classes, written in Kotlin, works from Java too. Min SDK 21.

## Screenshots

| Light | Dark |
|---|---|
| ![Light](docs/screenshots/light.png) | ![Dark](docs/screenshots/dark.png) |

## Install

1. Add JitPack in `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

2. Add the dependency:

```kotlin
dependencies {
    implementation("com.github.p32929:AndroidAppUpdater:2.0.0")
}
```

## The JSON file

```json
{
  "versionCode": 2,
  "cancellable": true,
  "url": "https://play.google.com/store/apps/details?id=your.app",
  "versionName": "2.0.0",
  "changelog": "1. A fresh new look\n2. Bug fixes"
}
```

1. `versionCode` (required): the version code of the newest release.
2. `cancellable` (required): `false` forces the user to update.
3. `url` (required): where the Update button takes the user, like Google Play or a download page.
4. `versionName` and `changelog` (optional): shown in the built-in dialog.
5. Any other fields you add are passed to you in the callback.

There is a working example in [docs/sample-update.json](docs/sample-update.json).

## Usage

### The easy way

Call this in your activity, for example in `onCreate`. It shows the dialog only when a newer version is out.

Kotlin:

```kotlin
AppUpdater.check(this, "https://example.com/update.json")
```

Java:

```java
AppUpdater.check(this, "https://example.com/update.json");
```

The activity must use an AppCompat or Material theme. With a Material 3 theme the dialog looks like Material 3.

### Your own UI

Kotlin:

```kotlin
AppUpdater(this, jsonUrl, object : UpdateListener {
    override fun onJsonDataReceived(updateModel: UpdateModel, jsonObject: JSONObject) {
        if (updateModel.isNewerThanInstalled(this@MainActivity)) {
            // Show your own UI, or the built-in one:
            AppUpdater.showDialog(this@MainActivity, updateModel)
        }
    }

    override fun onError(error: String) {
        // No internet, bad URL or bad JSON
    }
}).execute()
```

Java:

```java
new AppUpdater(this, jsonUrl, new UpdateListener() {
    @Override
    public void onJsonDataReceived(UpdateModel updateModel, JSONObject jsonObject) {
        if (updateModel.isNewerThanInstalled(MainActivity.this)) {
            AppUpdater.showDialog(MainActivity.this, updateModel);
        }
    }

    @Override
    public void onError(String error) {
    }
}).execute();
```

Callbacks run on the main thread. They are skipped if the activity is already closing.

## Migrating from 1.x

Most code keeps working. `new AppUpdater(context, url, listener).execute()`, `UpdateListener`, `UpdateModel.getVersionCode()`, `isCancellable()`, `getUrl()` and `AppUpdater.getCurrentVersionCode(context)` are all the same.

1. Change the dependency to `com.github.p32929:AndroidAppUpdater:2.0.0` and use the JitPack line above.
2. `AppUpdater` is no longer an `AsyncTask`, so `cancel()` and `executeOnExecutor()` are gone. Just call `execute()`.
3. The library no longer checks the network first, so the "Please check your network connection" error is gone. Network problems now come to `onError` with the real reason.
4. The `ACCESS_NETWORK_STATE` permission is no longer added to your app.
5. Your old JSON files work as they are.

## License

MIT License — Copyright (c) 2019 Fayaz Bin Salam. See [LICENSE](LICENSE) for the full text.

## Contributing

Contributions are warmly welcomed and greatly appreciated! Whether it's a bug fix, new feature, or improvement, your input helps make this project better for everyone.

Before submitting a pull request, please:

1. Create an issue describing the feature or bug fix you'd like to work on
2. Wait for discussion and approval to ensure alignment with project goals
3. Fork the repository and create your feature branch
4. Submit your pull request with a clear description of changes

This approach helps avoid duplicate efforts and ensures smooth collaboration. Thank you for considering contributing!

## Share

Sharing this repository with your friends is just one click away from here

[![facebook](https://user-images.githubusercontent.com/6418354/179013321-ac1d1452-0689-493f-9066-940cf2302b6e.png)](https://www.facebook.com/sharer/sharer.php?u=https://github.com/p32929/AndroidAppUpdater/)
[![twitter](https://user-images.githubusercontent.com/6418354/179013351-7d8d6d1c-4ce2-46ab-bef8-4c4765a1b888.png)](https://twitter.com/intent/tweet?url=https://github.com/p32929/AndroidAppUpdater/)
[![tumblr](https://user-images.githubusercontent.com/6418354/179013343-3111f55a-3b90-40c7-8487-9777348672b0.png)](https://www.tumblr.com/share?v=3&u=https://github.com/p32929/AndroidAppUpdater/)
[![pocket](https://user-images.githubusercontent.com/6418354/179013334-b095c45f-becf-49f4-9ee1-5a731a9b1f85.png)](https://getpocket.com/save?url=https://github.com/p32929/AndroidAppUpdater/)
[![pinterest](https://user-images.githubusercontent.com/6418354/179013331-44cd9206-11b1-4b65-becb-5863b61c828f.png)](https://pinterest.com/pin/create/button/?url=https://github.com/p32929/AndroidAppUpdater/)
[![reddit](https://user-images.githubusercontent.com/6418354/179013338-7416ae3f-73ba-4522-86e1-1374d7082d22.png)](https://www.reddit.com/submit?url=https://github.com/p32929/AndroidAppUpdater/)
[![linkedin](https://user-images.githubusercontent.com/6418354/179013327-ca7b7102-1da8-4b1c-858f-1a6e5f21bd70.png)](https://www.linkedin.com/shareArticle?mini=true&url=https://github.com/p32929/AndroidAppUpdater/)
[![whatsapp](https://user-images.githubusercontent.com/6418354/179013353-f477fa0b-3e6f-4138-a357-c9991b23ff88.png)](https://api.whatsapp.com/send?text=https://github.com/p32929/AndroidAppUpdater/)

---

## Support

If this saved you time, you can buy me a coffee — it keeps these projects maintained and free. Other payment options: https://p32929.github.io/SendMoney2MeV1/

[![Buy Me A Coffee](https://img.shields.io/badge/Buy%20me%20a%20coffee-%E2%98%95-FFDD00?style=for-the-badge&logo=buymeacoffee&logoColor=black)](https://www.buymeacoffee.com/p32929)

<!-- hire-block -->

---

## 💼 Using this at a company?

I do fixed-price delivery work on my own projects. One invoice, one date, no hourly billing:

| | |
|---|---|
| **White-label build** — this project rebranded, extended and deployed as yours | **$6,500** · 3 weeks |
| **Custom app from scratch** on my own stack, signed and auto-updating | **$12,500** · 6 weeks |
| **Production-hardening sprint** — 72 hours on this project, for your load and your security review | **$999** |
| **Ongoing capacity** — one project-week of my time reserved every month | **$9,000 / month** |

Full details → **[p32929.github.io/hire](https://p32929.github.io/hire/)** · Email **[fayazdevinbox@uberip.com](mailto:fayazdevinbox@uberip.com)** — scoping and quotes are free and I answer within one business day.
