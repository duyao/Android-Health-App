package example.dy.com.homework;

import android.app.Fragment;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import example.dy.com.homework.myUtil.GooglePlacesReadTask;

/**
 * Created by dy on 2016/4/26.
 */
public class MapFragment extends Fragment {
    private MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_map, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();


        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setRotateGesturesEnabled(true);
        uiSettings.setTiltGesturesEnabled(true);
        // latitude and longitude
        double latitude = 31.2681406;
        double longitude = 120.746597;

        LatLng cur = new LatLng(latitude, longitude);

        // create marker
        MarkerOptions marker = new MarkerOptions().position(cur).title("My Location");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        googleMap.addMarker(marker);

        String APIKEY = "AIzaSyBK7tSGQnwGfHS3R8Mq525JVpokkVsinGo";
        String type = "school";
        String radius = "5000";

                StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                googlePlacesUrl.append("location=" + latitude + "," + longitude);
                googlePlacesUrl.append("&radius=" + radius);
                googlePlacesUrl.append("&types=" + type);
                googlePlacesUrl.append("&sensor=true");
                googlePlacesUrl.append("&key=" + APIKEY);

                GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
                Object[] toPass = new Object[2];
                toPass[0] = googleMap;
                toPass[1] = googlePlacesUrl.toString();
                googlePlacesReadTask.execute(toPass);





//        //add nearby park
//        MarkerOptions park1 = new MarkerOptions().position(new LatLng(31.270173, 120.741783)).title("Sports Park");
//        park1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        googleMap.addMarker(park1);
//        MarkerOptions park2 = new MarkerOptions().position(new LatLng(31.277297, 120.734259)).title("Renmin University of China National University Science Park");
//        park2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        googleMap.addMarker(park2);
//        MarkerOptions park3 = new MarkerOptions().position(new LatLng(31.276452, 120.725988)).title("Suzhou Dushuhu Higher Education Area");
//        park3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        googleMap.addMarker(park3);
//        MarkerOptions park4 = new MarkerOptions().position(new LatLng(31.260925, 120.753972)).title("Sipivt");
//        park4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        googleMap.addMarker(park4);
//        MarkerOptions park5 = new MarkerOptions().position(new LatLng(31.255617, 120.750759)).title("Suzhou Industrial Park Jincheng Clothing And Accessories");
//        park5.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        googleMap.addMarker(park5);
//        MarkerOptions park6 = new MarkerOptions().position(new LatLng(31.273581, 120.749408)).title("Layde Park");
//        park6.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        googleMap.addMarker(park6);
//        MarkerOptions park7 = new MarkerOptions().position(new LatLng(31.264173, 120.725798)).title("Suzhou Dushu Lake Higher Education Town Sports Development Center Gym");
//        park7.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        googleMap.addMarker(park7);






        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(cur).zoom(13).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        // Perform any camera updates here
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}
