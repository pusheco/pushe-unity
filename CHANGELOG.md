# ChangeLog

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