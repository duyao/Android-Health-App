package example.dy.com.homework.myUtil;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by dy on 2016/4/27.
 */
public class GooglePlacesReadTask extends AsyncTask<Object, Integer, String> {
    String googlePlacesData = null;
    GoogleMap googleMap;

    @Override
    protected String doInBackground(Object... inputObj) {
        try {
            googleMap = (GoogleMap) inputObj[0];
            String googlePlacesUrl = (String) inputObj[1];
            new ConnectionUtils(googlePlacesUrl, new ConnectionUtils.ConnectionCallback() {
                @Override
                public void onSuccess(Object result) {
                    System.out.println("comsume->" + result);
                    googlePlacesData = result.toString();
                }

                @Override
                public void onFail() {
                    System.out.println("cannot find consume and burn of server");

                }
            }, new String[]{});

        } catch (Exception e) {
            Log.d("Google Place Read Task", e.toString());
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String result) {
        PlacesDisplayTask placesDisplayTask = new PlacesDisplayTask();
        Object[] toPass = new Object[2];
        toPass[0] = googleMap;
        toPass[1] = result;
        placesDisplayTask.execute(toPass);
    }
}
