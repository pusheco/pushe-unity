# Pushe SDK for Unity game engine

## Installation

 * Build tool: **Gradle**
 * Engine version: Prefer LTS versions  
 * Android MinSDK=17  
 * Pushe relies on **Firebase** (and **PushKit** on Huawei devices which don't have GPlay services).
   * So you need to provide FCM credentials to create an app in [pushe console](https://console.pushe.co)
   * and provide **Huawei appId and appSecret** for Huawei users  (Need to connect to support to enable it for you)
 
## Import the package

1. Download the latest package from [releases of this repo](https://github.com/pusheco/pushe-unity/releases) or [official documents](https://docs.pushe.co/docs/unity/intro)
2. Import the `.unitypackage` file as "Custom package" into your project
3. Let the `Google play resolver` (EDM4U package resolver) sync the project dependencies
    You can sync it manually from `Assets -> External Dependency Manager -> Android (and iOS) Resolver -> Resolve (or Force resolve)`

After EDM4U synced the packages, you are good to go to next steps

## Setup application token
### Android
Get the **Android manifest token** (`pushe_token` meta-data) from Console and add it into your `Assets/Plugins/Android/AndroidManifest.xml` (create using engine or template if it does not exist)

```xml
<manifest>
    <application>

        <!-- other stuff... -->

        <!-- Pushe TOKEN - Mandatory -->
        <meta-data
            android:name="pushe_token"
            android:value="${PUSHE_TOKEN}" />

        <!-- HMS - Attention: Don't add it if you don't have it -->

        <meta-data
            android:name="com.huawei.hms.client.appid"
            android:value="appid=${app_id}" />
    </application>
</manifest>
```

> Again, for Huawei pushkit you need to contact support and apply to get a huawei appId. Also the app must be signed at the first time.

After setting up the token and running the app, SDK will register by itself and no further actions are required to get it working.

### iOS
...

## Use provided scripts

Write a script and import it in your scene hirarchy to be able to use scripts provided to work with Pushe SDK.
### Registration Utils

#### Check for registration

Use this code:

```cs
var isPusheRegistered = PusheUnity.IsRegistered();
```
Or get a callback instead:

```cs
void Start()
{
    PusheUnity.OnPusheRegistered(OnPusheRegisteredSuccessfully);
}

private void OnPusheRegisteredSuccessfully()
{
    // Registration is done.
    Debug.Log("Pushe is registered!");
}
```
#### User IDs and Unification

##### CustomId, phone and email of the user
Used for Segmentation, User journey and analytics

```cs
var phoneNumber = "";
PusheUnity.SetUserPhoneNumber(phoneNumber);

const customId = "aCustomIdYouSetForUser";

PusheUnity.SetCustomId(customId);

var userEmail = "";
PusheUnity.SetUserEmail(userEmail);
```

There are also `getters` for all above

##### Pre-created IDs

```cs
// For device unique id for this app (ANdroid and ios)
var deviceId = PusheUnity.GetDeviceId();

// for android it comes from Google AdvertisingId of Google play and AOID of Huawei
var adId = PusheUnity.GetAdvertisingId();
```

#### Topic

```cs
PusheUnity.Subscribe("sport");

// or
PusheUnity.Unsubscribe("sport");
```

#### User Tag

```cs
var tags = new Dictionary<string, string> {
    {"name","Mohammad"},
    {"age", "25"},
    {"birthday","1435187386"}
};
PusheUnity.AddTags(tags);

// Remove the keys 'name', 'age' and 'birthday' from tags
PusheUnity.RemoveTags("name", "age", "birthday");

// And get them
Dictionary<string, string> tags = PusheUnity.GetSubscribedTags(); // tags: {"name":"Ali"}
```

#### Device to device notification (Android)

```cs
using Pushe;

UserNotification notification =
    UserNotification.WithDeviceId(androidId)
            .SetTitle("title")
            .SetContent("content of push");

PusheUnity.SendNotificationToUser(notification);
```

other props for UserNotification

```cs
public UserNotification SetTitle(string title);
public UserNotification SetContent(string content);
public UserNotification SetBigTitle(string bigTitle);
public UserNotification SetBigContent(string bigContent);
public UserNotification SetImageUrl(string imageUrl);
public UserNotification SetIconUrl(string iconUrl);
public UserNotification SetNotifIcon(string notifIcon);
public UserNotification SetCustomContent(string customContent);

// or use this for complete cusomizability
UserNotification.SetAdvancedNotification(jsonOfNotification)
```

#### Notification callbacks
Get the events happenning to a received notification

```cs
public class PusheNotifListener : IPusheNotificationListener
{
    public void OnNotification(NotificationData notificationData)
    {
        // Pushe.Log("Notification received: " + notificationData);
    }

    public void OnCustomContentReceived(string customJson)
    {
        // Pushe.Log("Notification custom content received: " + customJson);
    }

    public void OnNotificationClick(NotificationData notificationData)
    {
        // Pushe.Log("Notification clicked: " + notificationData);
    }

    public void OnNotificationDismiss(NotificationData notificationData)
    {
        // Pushe.Log("Notification dismissed: " + notificationData);
    }

    public void OnButtonClick(NotificationButtonData notificationButtonData,
        NotificationData notificationData)
    {
        // Pushe.Log("Notification button clicked\n Data: " + notificationData +
                  // "\n ButtonData: " + notificationButtonData);
    }
}
```

And set a new instance of it for pushe

```cs
PusheUnity.SetNotificationListener(new PusheNotifListener());
```

#### Analytics Event

```cs
PusheAnalytics.SendEvent(eventName);
```