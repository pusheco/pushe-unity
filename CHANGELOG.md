# ChangeLog

## 2.5.0 (9 May, 2021)
- **Breaking**: Plugin version will not match the native release version

### Plugin
- Added `PusheNotification` api `EnableNotificationForceForegroundAware`
- Added `PusheNotification` api `DisableNotificationForceForegroundAware`
- Added `PusheNotification` api `IsForceForegroundAware`
- Added `PusheCourier` api `GetFcmToken`
- Added `PusheCourier` api `GetHmsToken`
- Added `PusheCourier` api `GetActiveService`

### Android
- Update native dependency to `2.5.0`
  - **New**: Adds ability to ignore showing notification if app is open (either all notifications or individually using `show_foreground`)
  - **New**: Location and Geofencing features are applied to `hms` module. Huawei devices can have location-related features
  - Bug fixes and improvements
- **Feat:** New APIs for notification foreground awareness
  - `enableNotificationForceForegroundAware`: Force enable foreground awareness for all notifications
  - `disableNotificationForceForegroundAware`: Disable what was enabled by above function
  - `isForceForegroundAware`: Is enabled or not
- **Feat** New APIs to retrieve FCM and HMS token (and the service that is activated)


## 0.7.2 (18 Jan, 2021)
### Plugin
- Change `WithAndroidId` to `WithDeviceId` in PusheNotification

### Android
- Fix error when calling `PusheNotification` APIs on Android 8 or lower
- Fix Device to device (D2D) notification


## 0.7.0 (3 Jan, 2021)
### Plugin
- Move all APIs to `PusheUnity.cs`. Classes like `PusheAnalytics` are android only. But `PusheUnity` adds iOS as well.

### Android
- Fix some bugs in `hms` module
- Fix bugs in registration and initialization state
- Deprecate `PusheUnity.GetGoogleAdvertisingId()`. Use `PusheUnity.GetAdvertisingId()` instead. New API will return `OAID` for `hms` module if activated.
- [**Breaking**]: New changes to plugin and APIs to support iOS functionality (Move all APIs to `PusheUnity`)

### iOS
- Add support for iOS in this version
- Add basic functionality

## 0.6.0 (28 Nov, 2020)
> **Breaking**
> Native module `unity-extended` is removed and the native SDK itself
> will provide the util methods.

#### Plugin    
- **EDM4U** is used instead of Gradle. Thus, support of Unity engine comes back to `4.x`
- Remove of `unity-extended` native library
- Add support for .Net 3.5 for Unity 2017
    Unity 2017 uses `.Net3.5` and supports `.Net 4.5` as an experimental feature. Since .net4 is only required for
    "String interpolation", it's better to remove that feature out of scripts and support earlier as well.
- Rename `Pushe` to `PusheUnity` to respect Pushe namespace
- Added `Template` folder for Manifest and gradle template

#### Android
- Update to `2.4.1-beta05`
- New feature: support of `hms` (Huawei mobile services) along with `fcm` (Firebase cloud messaging)    
    **From now on your app users can receive push notifications even if they don't have Google play services and instead use HMS-Core**

#### iOS
- iOS is not yet supported

## 0.5.0 (26 Aug, 2020)
### Native plugin
- Update native dependency to 2.2.0 (Featearing InAppMessaging)
- Notification callbacks are re-written using `AndroidJavaProxy`. No more `sendMessage` using unity and GameObjects are required. Passing a listener is enough.
- [**Breaking**]: Remove `PusheMultiDexApplication` and `PusheUnityApplication` and way of adding notification callbacks.
- Code optimizations

### Unity plugin
- Add related APIs for supporing **InAppMessaging**:
    - `TriggerEvent` to trigger local events.
    - `EnableInAppMessaging`, `DisableInAppMessaging` and `IsInAppMessagingEnabled`
    - `SetInAppMessagingListener` to enable receiving following events of an InAppMessage: `recieve`, `trigger`, `click`, `dismiss` and `buttonClick`
    - `testInAppMessage` test function to enable testing the PIAM (Pushe InAppMessaging) via script code
- Change `SetNotificationListener` implementation (No GameObject is needed anymore)
- [**Breaking**]: Most of scripts are changed. You should consider removing `Pushe` folder and importing it again.
- Logging improvements

### Known issues
- In Androids lower than 8.0 which `android.app.NotificationChannel` is not meaningful, some notification related APIs will not work and cause internal crash. **These crashes does not affect the app nor code**, but the functionality will not work.


## 0.4.7
### Native plugin
- Updated Native dependency to latest (2.1.1)
- `GetPusheId` function is removed

### Unity plugin
- Added support for GDPR compliance. Methods `Initialize` and `setUserConsentGiven` for the feature.
- Deprecated `GetAndroidId`. `GetDeviceId` should be used instead
- `GetPusheId` is no longer supported and is removed

## 0.4.6
### Native plugin
- Update the native dependency to latest
- Attempt to add `com.unity3d` to dex excluded
- Fix bug with `CreateNotificationChannel`

## 0.4.5 (26 Jan, 2020)
### Native plugin
- Update the native dependency to latest

## 0.4.4 (01 Dec, 2019)
### Native plugin
- Updated native plugin to use non-modified RxJava
- Added custom callback channel API to change the GameObject at runtime

## 0.4.3
### Native plugin
- Added some helper functions to ease the interaction between SDK and Unity scripts
- Added Listener support
- Added MultiDex helper
- Added Debug mode