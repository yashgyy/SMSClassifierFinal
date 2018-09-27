package com.varshney.smscategoriser;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ShareUrLocation extends AppCompatActivity {

    public static final int MY_PERMISSION_REQUEST_LOCATION = 1;
    public static final String TAG = "SHARELOCATION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_ur_location);

        PermissionManager.askForPermission(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionManager.OnPermissionResultListener() {
            @Override
            public void onGranted(String permission) {
                if (ActivityCompat.checkSelfPermission(ShareUrLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ShareUrLocation.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    if (ActivityCompat.shouldShowRequestPermissionRationale(ShareUrLocation.this,Manifest.permission.ACCESS_COARSE_LOCATION)){
                        ActivityCompat.requestPermissions(ShareUrLocation.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},MY_PERMISSION_REQUEST_LOCATION);

                    }else{
                        ActivityCompat.requestPermissions(ShareUrLocation.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},MY_PERMISSION_REQUEST_LOCATION);
                    }
                    return;
                }
                else{
                    LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try{
                        Log.d(TAG, "onGranted: "+ hereLocation(location.getLatitude(),location.getLongitude()));
                    }
                    catch (Exception e){
                        Toast.makeText(ShareUrLocation.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onDenied(String permission) {
                Toast.makeText(ShareUrLocation.this, "Permission Needed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode){
            case MY_PERMISSION_REQUEST_LOCATION:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(ShareUrLocation.this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        try{

                            //Log.d(TAG, "onGranted: "+ hereLocation(location.getLatitude(),location.getLongitude()));

                            Toast.makeText(ShareUrLocation.this, hereLocation(location.getLatitude(),location.getLongitude()), Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){
                            Toast.makeText(ShareUrLocation.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }


    public  String hereLocation(double lat,double lon){
        String curCity = "";
        Geocoder geocoder = new Geocoder(ShareUrLocation.this, Locale.getDefault());
        List<Address> addressList;
        try{
            addressList = geocoder.getFromLocation(lat,lon,1);
            if (addressList.size()>0)
            {
                curCity = addressList.get(0).getLocality();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return curCity;
    }


}
