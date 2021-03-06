package kr.co.homein.homeinproject.Maps;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import kr.co.homein.homeinproject.Company.CompanyInfoActivity;
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
    final static String OF_NUMBER = "office_number";
    HomeInMapData homeInMapData;
    SearchMapData searchTagData;

    Intent intent;
    String office_number;

    EditText editText;
    TextView companyName;
    TextView companySubName;
    ImageView comapanyLogo;
    RelativeLayout relativeLayout;

    ImageButton backKey;
    ImageButton changeLoc;

//    double x_current = 37.466051;
//    double y_current = 126.941138;
      double x_current;
      double y_current;
      int isPushed;
      String search_name;

    ArrayAdapter<AroundOffice> mAdapter;
    Map<Marker, AroundOffice> poiResolver = new HashMap<>();
    Map<AroundOffice, Marker> markerResolver = new HashMap<>();

    Circle outer, inner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_map);

        mAdapter = new ArrayAdapter<AroundOffice>(this, android.R.layout.simple_list_item_1);
        intent = getIntent();
        office_number = intent.getStringExtra(OF_NUMBER);

        companyName = (TextView) findViewById(R.id.company_name);
        companySubName = (TextView) findViewById(R.id.company_sub_name);
        comapanyLogo = (ImageView) findViewById(R.id.company_logo);
        relativeLayout = (RelativeLayout) findViewById(R.id.company_info);


        changeLoc = (ImageButton) findViewById(R.id.change_loc);
        backKey = (ImageButton) findViewById(R.id.btn_back);
        backKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        isPushed = 1;
        //내 위치 활성화 하기
        changeLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPushed == 0){ //버튼이 눌렸으면
                    removeCircle();

                    isPushed= 1;
                    changeLoc.setImageResource(R.drawable.place_bt_on);
                    outer = mMap.addCircle(new CircleOptions().center(new LatLng(x_current, y_current))
                            .radius(500)
                            .strokeWidth(10)
                            .strokeColor(ContextCompat.getColor(HomeInMapActivity.this, R.color.homeinColor))
                            .fillColor(Color.argb(0x40, 0, 0, 0xff))); // ff면 불투명 , 00은 투명

                    inner = mMap.addCircle(new CircleOptions().center( new LatLng(x_current,y_current))
                            .radius(30)
                            .strokeWidth(2)
                            .strokeColor(ContextCompat.getColor(HomeInMapActivity.this, R.color.homeinColor))
                            .fillColor(R.color.homeinColor));

                    moveMap(x_current, y_current, 15f);
                }else if(isPushed == 1){
                        isPushed =0;
                    changeLoc.setImageResource(R.drawable.place_bt_off);
                    removeCircle();
                    }
                }

        });

        editText = (EditText) findViewById(R.id.company_name_tag);

        editText.setCursorVisible(false);
        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText.setCursorVisible(true);
                //키보드 보이게 하는 부분
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeInMapActivity.this, CompanyInfoActivity.class);
                intent.putExtra(OF_NUMBER , office_number );
                startActivity(intent);
                finish();
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
//                        Toast.makeText(getApplicationContext(), "검색", Toast.LENGTH_LONG).show();
                        search_name = editText.getText().toString();
                        setSearchData();
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        //여기서 검색 키워드 서버에 보내주기
                        break;
                    default:
//                        Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_LONG).show();
                        return false;
                }
                return true;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.search_icon_white);


//        infoView = (TextView)findViewById(R.id.text_info);
        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .build();
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapAsync(this);

        //setData();

    }

    GoogleMap mMap;


    public void removeCircle(){
        if (outer!= null) {
            outer.remove();
            outer = null;
        }
        if (inner != null) {
            inner.remove();
            inner = null;
        }
    }


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
//        Toast.makeText(this, "marker : " + marker.getTitle(), Toast.LENGTH_SHORT).show();
        marker.hideInfoWindow();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
//        infoView.setText(marker.getTitle() + "\n\r" + marker.getSnippet());
        marker.showInfoWindow();

        AroundOffice aO = poiResolver.get(marker);
        CompanyInfoDialogFragment companyInfoDialogFragment = new CompanyInfoDialogFragment();
        Bundle b = new Bundle();
        b.putSerializable("arg2", aO);
//        companyInfoDialogFragment.setData(cO);
        companyInfoDialogFragment.setArguments(b);
        companyInfoDialogFragment.show(getSupportFragmentManager(), "dialog");
        return true;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
//        addMarker(latLng.latitude, latLng.longitude);
    }




    public void addMarker(AroundOffice aroundOffice) {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(aroundOffice.getOffice_address_number().getOffice_latitude(), aroundOffice.getOffice_address_number().getOffice_logitude()));
        options.icon(BitmapDescriptorFactory.defaultMarker());
        options.anchor(0.5f, 1);
        options.title(aroundOffice.getOffice_name());
        options.snippet(aroundOffice.getOffice_sub_name());
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
//        Location location = LocationServices.FusedLocationApi.getLastLocation(mClient);
//        displayMessage(location);
        LocationRequest request = new LocationRequest();

//        if(location != null) {
//            x_current = location.getLatitude();
//            y_current = location.getLongitude();
//        }


//        request.setInterval(10000);
//        request.setFastestInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        request.setNumUpdates(1);
        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, request, mListener);

    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            displayMessage(location);
            x_current = location.getLatitude();
            y_current = location.getLongitude();

            setData();

            outer = mMap.addCircle(new CircleOptions().center(new LatLng(x_current, y_current))
                    .radius(500)
                    .strokeWidth(10)
                    .strokeColor(ContextCompat.getColor(HomeInMapActivity.this, R.color.homeinColor))
                    .fillColor(Color.argb(0x40, 0, 0, 0xff))); // ff면 불투명 , 00은 투명

            inner = mMap.addCircle(new CircleOptions().center(new LatLng(x_current, y_current))
                    .radius(30)
                    .strokeWidth(2)
                    .strokeColor(ContextCompat.getColor(HomeInMapActivity.this, R.color.homeinColor))
                    .fillColor(Color.BLUE));

//            mMap.addCircle(new CircleOptions().center(new LatLng(location.getLatitude(), location.getLongitude()))
//                    .radius(500)
//                    .strokeWidth(10)
//                    .strokeColor(Color.BLUE)
//                    .fillColor(Color.argb(0x40, 0, 0, 0xff))); // ff면 불투명 , 00은 투명
//
//            mMap.addCircle(new CircleOptions().center( new LatLng(location.getLatitude(),location.getLongitude()))
//                    .radius(30)
//                    .strokeWidth(2)
//                    .strokeColor(Color.BLUE)
//                    .fillColor(Color.BLUE));
        }
    };

    private void sendLocation(Location location) {
        if (location != null) {

        }
    }

    private void displayMessage(Location location) {
        if (location != null) {
//            messageView.setText("lat : " + location.getLatitude() + ", lng : " + location.getLongitude());
            NetworkManager.getInstance().getTMapReverseGeocoding(this, location.getLatitude(), location.getLongitude(), new NetworkManager.OnResultListener<AddressInfo>() {
                @Override
                public void onSuccess(Request request, AddressInfo result) {
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
//                Toast.makeText(HomeInMapActivity.this, "result : " + homeInMapData.getAround_office().size(),Toast.LENGTH_SHORT).show();
                for (int i = 0; i < homeInMapData.getAround_office().size(); i++) {
//                    Toast.makeText(HomeInMapActivity.this, "size :" + i, Toast.LENGTH_SHORT).show();
                    addMarker(homeInMapData.getAround_office().get(i));
                }

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(HomeInMapActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void setSearchData(){
        NetworkManager.getInstance().getSearchMapTag(this, x_current, y_current, search_name, new NetworkManager.OnResultListener<SearchMapData>() {
            @Override
            public void onSuccess(Request request, SearchMapData result) {
//                mAdapter.set(result);
                searchTagData = result;
//                Toast.makeText(HomeInMapActivity.this, "result : " + searchTagData.getAround_office().size(),Toast.LENGTH_SHORT).show();

                if(searchTagData.getAround_office().get(0).getOffice_name() != null) {
                    for (int i = 0; i < searchTagData.getAround_office().size(); i++) {
//                        Toast.makeText(HomeInMapActivity.this, "size :" + i, Toast.LENGTH_SHORT).show();
                        addMarker(searchTagData.getAround_office().get(i));
                    }

                    moveMap(searchTagData.getAround_office().get(0).getOffice_address_number().getOffice_latitude(),
                            searchTagData.getAround_office().get(0).getOffice_address_number().getOffice_logitude(), 15f);

                }
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(HomeInMapActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
