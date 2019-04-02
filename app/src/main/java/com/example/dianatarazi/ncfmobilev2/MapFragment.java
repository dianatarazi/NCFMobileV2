package com.example.dianatarazi.ncfmobilev2;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;
import static com.example.dianatarazi.ncfmobilev2.Constants.ERROR_DIALOG_REQUEST;
import static com.example.dianatarazi.ncfmobilev2.Constants.MAPVIEW_BUNDLE_KEY;
import static com.example.dianatarazi.ncfmobilev2.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    //private boolean mLocationPermissionGranted = false;
    private static final String TAG = "MapFragment";
    private MapViewModel mapViewModel;

    private MapView mapView;
    private GoogleMap googleMap;
    private Building pin;
    private Marker currentMarker;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_map_tab, container, false);
        mapView = mView.findViewById(R.id.map);

        initGoogleMap(savedInstanceState);

        mapViewModel = ViewModelProviders.of(getActivity()).get(MapViewModel.class);
        mapViewModel.getSelected().observe(this, item -> {
            this.pin = item;

            if (pin != null && googleMap != null) {
                if (currentMarker != null) {
                    currentMarker.remove();

                }
                LatLng pinLatLng = new LatLng(pin.getLatitude(), pin.getLongitude());
                currentMarker = googleMap.addMarker(new MarkerOptions().position(new LatLng(pin.getLatitude(), pin.getLongitude()))
                        .title(pin.getName()).snippet(pin.getFunction()));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pinLatLng, 15));
            }

        });

        return mView;

    }

    private void initGoogleMap(Bundle savedInstanceState) {
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        Log.d(TAG, "mapview on resume");

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        MapsInitializer.initialize(getContext());

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);

        CameraPosition ncf = CameraPosition.builder().target(new LatLng(27.385047, -82.558714)).zoom(16).bearing(0).tilt(45).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(ncf));

        this.googleMap = map;
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
        Log.d(TAG, "mapview on pause");
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void showLocation(GoogleMap map) {
        //map.add
    }
}
