package kr.co.homein.homeinproject.Company;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.IOException;
import java.util.List;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.CompanyItemData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyInfoItemFragment extends Fragment implements
    GoogleApiClient.OnConnectionFailedListener,
    GoogleApiClient.ConnectionCallbacks,
    OnMapReadyCallback {


    RecyclerView recyclerView;
    CompanyItemListAdapter mAdatper;
    final static String OF_NUMBER = "office_number";
    String officeNumber;
    GoogleApiClient mClient;
    double x_current, y_current;

    String CH_number;
    final static String CH_NUMBER = "CH_number";

    public CompanyInfoItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        officeNumber = ((CompanyInfoActivity)getActivity()).getOfficeNumber();

        mAdatper = new CompanyItemListAdapter();
        mAdatper.setOnItemClickListener(new CompanyItemViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, CompanyItemData companyItemData) {
                Intent intent = new Intent(getContext(), CompanyDetailItemActivity.class); //사용자 아이템 상세 페이지 이동
                intent.putExtra(CH_NUMBER , companyItemData.getCH_number());
                startActivity(intent);
            }
        });

        mClient = new GoogleApiClient.Builder(getContext())
                .addApi(LocationServices.API)
                .enableAutoManage((FragmentActivity)getActivity(), this)
                .addConnectionCallbacks(this)
                .build();


//        setData();
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        recyclerView.scrollToPosition(0);
//        mAdatper.clear();
//        setData();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_company_item, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list2);
        recyclerView.setAdapter(mAdatper);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        return view;
    }


    private void setData() {
        NetworkManager.getInstance().getCompanyItemList(getContext(),x_current, y_current, new NetworkManager.OnResultListener<List<CompanyItemData>>() {
            @Override
            public void onSuccess(Request request, List<CompanyItemData> result) {
//                mAdapter.clear();
                mAdatper.addAll(result);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

    @Override
    public void onStop() {
        super.onStop();
        mClient.stopAutoManage((FragmentActivity)getActivity());
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

LocationListener mListener = new LocationListener() {
    @Override
    public void onLocationChanged(Location location) {
        x_current = location.getLatitude();
        y_current = location.getLongitude();
        setData();
    }
};

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

}
