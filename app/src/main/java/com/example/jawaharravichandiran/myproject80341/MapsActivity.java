package com.example.jawaharravichandiran.myproject80341;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
Button Bsearch;
EditText TFaddress;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Bsearch=(Button)findViewById(R.id.Bsearch);
        TFaddress=(EditText)findViewById(R.id.TFadderss);

        Bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch(v);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    public void onSearch(View view){
        EditText location_tf=(EditText) findViewById(R.id.TFadderss);
        String location = location_tf.getText().toString();
        Toast.makeText(this,location,Toast.LENGTH_LONG).show();
        List<Address> addressList = null;
        Geocoder geocoder = new Geocoder(this);
        try {
            addressList = geocoder.getFromLocationName(location, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address address = addressList.get(0);
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title(location)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))).setSnippet("Your Result");
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }
         public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("My Location"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);


        LatLng SMVEC = new LatLng(11.918905, 79.632490);
        LatLng Home = new LatLng(11.9285507, 79.7914086);
        LatLng DhamoHome = new LatLng(11.950995, 79.818449);
        LatLng DhillipHome = new LatLng(11.928813,  79.786730);

        mMap.addMarker(new MarkerOptions().position(SMVEC).title("SMVEC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SMVEC));
        mMap.addMarker(new MarkerOptions().position(Home).title("MY HOME"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Home));
        mMap.addMarker(new MarkerOptions().position(DhamoHome).title("Dhamo HOME"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(DhamoHome));
        mMap.addMarker(new MarkerOptions().position(DhillipHome).title("Dhillip HOME"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(DhillipHome));

    }
}
