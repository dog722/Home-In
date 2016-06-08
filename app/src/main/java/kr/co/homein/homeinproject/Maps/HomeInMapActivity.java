package kr.co.homein.homeinproject.Maps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class HomeInMapActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnCameraChangeListener,
        GoogleMap.OnMapLongClickListener {

    GoogleApiClient mClient;
    TextView messageView;
    TextView infoView;
    final static String OF_NUMBER = "office_number";
    HomeInMapData homeInMapData;

    Intent intent;
    String office_number;

    double x_current = 37.466051;
    double y_current = 126.941138;


    ArrayAdapter<AroundOffice> mAdapter;
    Map<Marker, AroundOffice> poiResolver = new HashMap<>();
    Map<AroundOffice, Marker> markerResolver = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_map);


        mAdapter = new ArrayAdapter<AroundOffice>(this, android.R.layout.simple_list_item_1);

        intent = getIntent();
        office_number = intent.getStringExtra(OF_NUMBER);

        messageView = (TextView) findViewById(R.id.text_message);
        infoView = (TextView)findViewById(R.id.text_info);
        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .build();
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapAsync(this);

        setData();

    }

    GoogleMap mMap;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnCameraChangeListener(this);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        VisibleRegion region = mMap.getProjection().getVisibleRegion();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "marker : " + marker.getTitle(), Toast.LENGTH_SHORT).show();
        marker.hideInfoWindow();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        infoView.setText(marker.getTitle() + "\n\r" + marker.getSnippet());
        marker.showInfoWindow();

        AroundOffice aO = poiResolver.get(marker);
//      Glide.with(imageMain.getContext()).load(p.getPackage_mainpicture()).into(imageMain);
        return true;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
//        addMarker(latLng.latitude, latLng.longitude);
    }



    public void addMarker(AroundOffice aroundOffice) {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(aroundOffice.getOffice_address_number().getOffice_latitude(), aroundOffice.getOffice_address_number().getOffice_longitude()));
        options.icon(BitmapDescriptorFactory.defaultMarker());
        options.anchor(0.5f, 1);
        options.title(aroundOffice.getOffice_address1());
//        options.snippet(aroundOffice.getOffice_sub_name());
        Marker marker = mMap.addMarker(options);
        mAdapter.add(aroundOffice);
        markerResolver.put(aroundOffice, marker);
        poiResolver.put(marker, aroundOffice);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mClient);
        displayMessage(location);
        LocationRequest request = new LocationRequest();

        if(location != null) {
//        x_current = location.getLatitude();
//        y_current = location.getLongitude();
        }

//        request.setInterval(10000);
//        request.setFastestInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, request, mListener);
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            displayMessage(location);
        }
    };

    private void displayMessage(Location location) {
        if (location != null) {
//            messageView.setText("lat : " + location.getLatitude() + ", lng : " + location.getLongitude());
            NetworkManager.getInstance().getTMapReverseGeocoding(this, location.getLatitude(), location.getLongitude(), new NetworkManager.OnResultListener<AddressInfo>() {
                @Override
                public void onSuccess(Request request, AddressInfo result) {
                    messageView.setText(result.fullAddress);
                }

                @Override
                public void onFail(Request request, IOException exception) {

                }
            });
            moveMap(location.getLatitude(), location.getLongitude(), 15f);
        }
    }

    private void moveMap(double lat, double lng, float zoom) {
        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(lat, lng))
                .zoom(zoom)
//                .bearing(30)
//                .tilt(30)
                .build();

//        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom);
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);

        if (mMap != null) {
            mMap.moveCamera(update);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void setData(){


        NetworkManager.getInstance().getPeopleMapData(this, x_current, y_current, new NetworkManager.OnResultListener<HomeInMapData>() {
            @Override
            public void onSuccess(Request request, HomeInMapData result) {
//                mAdapter.set(result);
                homeInMapData = result;
                Toast.makeText(HomeInMapActivity.this, "result : " + homeInMapData.getAround_office().size(),Toast.LENGTH_SHORT).show();
                for (int i = 0; i < homeInMapData.getAround_office().size(); i++) {
                    Toast.makeText(HomeInMapActivity.this, "size :" + i, Toast.LENGTH_SHORT).show();
                    addMarker(homeInMapData.getAround_office().get(i));
                }

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(HomeInMapActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
