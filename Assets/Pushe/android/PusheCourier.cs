namespace Pushe.android
{
    public class PusheCourier
    {
        /**
         * Returns token of FCM courier
         * Empty if not the selected service
         */
        public static string GetFcmToken()
        {
            return PusheAndroidUtils.PusheFcmService().Call<string>("getToken");
        }
        
        /**
         * Returns token of HMS courier
         * Empty if not the selected service
         */
        public static string GetHmsToken()
        {
            return PusheAndroidUtils.PusheHmsService().Call<string>("getToken");
        }

        /**
         * Return the active service (fcm, hms)
         * Null if no service is registered
         */
        public static string GetActiveService()
        {
            return PusheAndroidUtils.Native().CallStatic<string>("getActiveCourier");
        }
    }
}