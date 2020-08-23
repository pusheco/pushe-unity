package co.pushe.plus.ext;

import android.text.TextUtils;
import android.util.Log;
import android.content.Context;
import co.pushe.plus.Pushe;
import co.pushe.plus.analytics.PusheAnalytics;
import co.pushe.plus.analytics.event.Ecommerce;
import co.pushe.plus.inappmessaging.InAppMessage;
import co.pushe.plus.notification.NotificationButtonData;
import co.pushe.plus.notification.NotificationData;

import com.unity3d.player.UnityPlayer;
import java.util.Arrays;
import java.util.List;
import java.lang.Exception;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * The extended module is built on top of `Plus:Base` module and adds extra functionalities to the library
 *  The features include Listener to Unity bridge sender, API simpler, MultidexApplication class, etc.
 */
public class PusheExt {

    // Core

    /**
     * Get the tags that user has added as a string JSON
     */
    public static String getSubscribedTagsJson() {
        return new JSONObject(Pushe.getSubscribedTags()).toString();
    }

    public static void removeTags(String csvTags) {
        try {
            String[] arrayOfTags = csvTags.split(",");
            List<String> tags = Arrays.asList(arrayOfTags);
            Pushe.removeTags(tags);
        } catch (Exception e) {
            Log.e("Pushe [Unity]","Failed to remove tags.", e);
        }
    }

    /**
     * Get the topics subscribed by user in a comma separated value format.
     */
    public static String getSubscribedTopicsCsv() {
        return TextUtils.join(",", Pushe.getSubscribedTopics());
    }


    // InAppMessaging

    public static String inAppToJson(InAppMessage inAppMessage) {
        JSONObject object = new JSONObject();
        try {
            object.put("title", inAppMessage.getTitle());
            object.put("content", inAppMessage.getContent());
            if (inAppMessage.getButtons() != null) {
                JSONArray array = new JSONArray();
                for (int i = 0; i < inAppMessage.getButtons().size(); i++) {
                    JSONObject btn = new JSONObject();
                    btn.put("text", inAppMessage.getButtons().get(i).getText());
                    array.put(btn);
                }
                object.put("buttons", array);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    // Analytics

    public static void sendEcommerce(String name, double price, String category, long quantity) {
        PusheAnalytics analytics = Pushe.getPusheService(PusheAnalytics.class);
        if (analytics != null) {
            analytics.sendEcommerceData(new Ecommerce.Builder(name, price).setCategory(category).setQuantity(quantity).build());
        }
    }

    // Notification

    private static String notificationToJson(NotificationData data) {
        JSONObject j = new JSONObject();
        try {
            j.put("messageId", data.getMessageId());
            if (data.getTitle() != null)
                j.put("title", data.getTitle());
            j.put("content", data.getContent());
            if (data.getBigTitle() != null)
                j.put("bigTitle", data.getBigTitle());
            if (data.getBigContent() != null)
                j.put("bigContent", data.getBigContent());
            if (data.getSummary() != null)
                j.put("summary", data.getSummary());
            if (data.getImageUrl() != null)
                j.put("imageUrl", data.getImageUrl());
            if (data.getIconUrl() != null)
                j.put("iconUrl", data.getIconUrl());
            if (data.getBigIconUrl() != null)
                j.put("bigIconUrl", data.getBigIconUrl());
            if (data.getCustomContent() != null)
                j.put("customContent", new JSONObject(data.getCustomContent()).toString());
        } catch (JSONException e) {
            reportError("Failed to parse notification", e);
        }

        return j.toString();
    }

    private static String notificationButtonToJson(NotificationButtonData data) {
        JSONObject j = new JSONObject();
        try {
            j.put("id", data.getId());
            if (data.getText() != null)
                j.put("text", data.getText());
            if (data.getIcon() != null)
                j.put("icon", data.getIcon());
        } catch (JSONException e) {
            reportError("Failed to parse button.", e);
        }
        return j.toString();
    }

    public static String mapToString(Map<String, Object> map) {
        return new JSONObject(map).toString();
    }

    public static void reportError(String message, Exception e) {
        Log.e("Pushe [Unity]", message, e);
    }

    private static void report(String message) {
        Log.i("Pushe [Unity]", message);
    }

}
