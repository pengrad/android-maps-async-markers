package com.github.pengrad.testtest;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.androidmapsextensions.ClusteringSettings;
import com.androidmapsextensions.GoogleMap;
import com.androidmapsextensions.MapView;
import com.androidmapsextensions.Marker;
import com.androidmapsextensions.MarkerOptions;
import com.androidmapsextensions.OnMapReadyCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {
    private GoogleMap mMap;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
        setTitle("My Map");
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getExtendedMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                setUpMap(googleMap);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    private CameraUpdate loadLastPosition() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        float zoom = preferences.getFloat("zoom", 21);
        float lat = preferences.getFloat("lat", (float) 55.754684);
        float lng = preferences.getFloat("lng", (float) 37.623164);
        return CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom);
    }

    private void setUpMap(GoogleMap map) {
        mMap = map;
        ClusterIconProvider clusterIconProvider = new ClusterIconProvider(getResources());
        ClusteringSettings clusteringSettings = new ClusteringSettings()
                .clusterOptionsProvider(clusterIconProvider)
                .addMarkersDynamically(true);
        mMap.setClustering(clusteringSettings);
        mMap.moveCamera(loadLastPosition());
        addMarkers();
    }

    private void addMarkers() {
        float lat = 55.754584f;
        float lng = 37.623064f;
        for (int i = 0; i < 100; i++) {
            double delta = 0.0001 * i;
            MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(lat + delta, lng + delta));
//            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.default_marker));
            Marker marker = mMap.addMarker(markerOptions);
            loadMarkerIcon(marker);
        }
    }

    private void loadMarkerIcon(final Marker marker) {
        Glide.with(this).load("http://www.myiconfinder.com/uploads/iconsets/256-256-a5485b563efc4511e0cd8bd04ad0fe9e.png")
                .asBitmap().fitCenter().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bitmap);
                marker.setIcon(icon);
            }
        });
    }

}








