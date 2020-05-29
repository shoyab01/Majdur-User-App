package com.example.majdurapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import adapter.grid_product_view_adapter;
import adapter.slider_adapter;
import model.horizontal_product_scroller;
import model.slider_model;

import static android.content.Context.LOCATION_SERVICE;

public class HomeFragment extends Fragment {

    /*MarkerOptions markerOptionsFix = new MarkerOptions();
    MarkerOptions markerOptionsMov = new MarkerOptions();

    ImageButton curLoc;

    MapView mMapView;*/
    View mView;
    public double latitude, longitude;
    public String address = "";

    Geocoder geocoder;
    List<Address> addresses;

    TextView customerAddress;

    ProgressDialog dialog;

    public HomeFragment() {
    }

////////////////grid


    //////////


    ///////////////// BannerSlider
    private ViewPager bannerslider;
    private slider_adapter sliderAdaper;
    private List<slider_model> slider_modelList;
    private int currentpage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;

    //////////////////BannerSlider


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);


        ////////////////////
        bannerslider = mView.findViewById(R.id.view_pager_banner);

        slider_modelList = new ArrayList<slider_model>();

        slider_modelList.add(new slider_model(R.drawable.carpenter_offer, "#eca610"));
        slider_modelList.add(new slider_model(R.drawable.plumber, "#eca610"));
        slider_modelList.add(new slider_model(R.drawable.offer_1, "#eca610"));


        slider_modelList.add(new slider_model(R.drawable.painter_offer, "#eca610"));
        slider_modelList.add(new slider_model(R.drawable.offer_1, "#eca610"));
        slider_modelList.add(new slider_model(R.drawable.plumber, "#eca610"));
        slider_modelList.add(new slider_model(R.drawable.offer_2, "#eca610"));
        slider_modelList.add(new slider_model(R.drawable.offer_1, "#eca610"));


        slider_modelList.add(new slider_model(R.drawable.carpenter_offer, "#eca610"));
        slider_modelList.add(new slider_model(R.drawable.plumber, "#eca610"));
        slider_modelList.add(new slider_model(R.drawable.offer_1, "#eca610"));


        sliderAdaper = new slider_adapter(slider_modelList);
        bannerslider.setClipToPadding(false);
        bannerslider.setPageMargin(20);

        bannerslider.setCurrentItem(currentpage);

        bannerslider.setAdapter(sliderAdaper);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentpage = position;
                pageLooper();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {

                }
            }
        };
        bannerslider.addOnPageChangeListener(onPageChangeListener);
        startbannerSlideshow();
        bannerslider.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                StopBanner();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startbannerSlideshow();
                }

                return false;
            }
        });


/////////////// grid_product       /////////////
        /////////////////






        List<horizontal_product_scroller> horizontalProductScrollerList = new ArrayList<>();
        horizontalProductScrollerList.add(new horizontal_product_scroller(R.drawable.elec, "Electrician", "",""));
        horizontalProductScrollerList.add(new horizontal_product_scroller(R.drawable.carpenter, "carpenter", " ", ""));
        horizontalProductScrollerList.add(new horizontal_product_scroller(R.drawable.painter, "Painter"," ", "" ));
        horizontalProductScrollerList.add(new horizontal_product_scroller(R.drawable.engineer, "engineer", " ", ""));
        horizontalProductScrollerList.add(new horizontal_product_scroller(R.drawable.cleaner, "cleaner", " ", ""));
        horizontalProductScrollerList.add(new horizontal_product_scroller(R.drawable.farmer, "farmer", " ", ""));
        horizontalProductScrollerList.add(new horizontal_product_scroller(R.drawable.plumber, "plumber", " ", ""));
        horizontalProductScrollerList.add(new horizontal_product_scroller(R.drawable.mistri, "mistri", " ", ""));
        horizontalProductScrollerList.add(new horizontal_product_scroller(R.drawable.mistri, "mistri", " ", ""));





        GridView gridView= mView.findViewById(R.id.grid_view_layout);

        gridView.setAdapter(new grid_product_view_adapter(horizontalProductScrollerList));
///////////////////////////testing

       return mView;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //mMapView = (MapView) mView.findViewById(R.id.map);

        customerAddress = (TextView) view.findViewById(R.id.cust_address);
        getLocation();
        getAddress(latitude, longitude);
        customerAddress.setText(address);

        /*curLoc = (ImageButton) view.findViewById(R.id.cur_loc);

        if(mMapView != null)
        {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }*/


    }



    /*@Override
    public void onMapReady(final GoogleMap googleMap) {

        getLocation();
        getAddress(latitude, longitude);
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(markerOptionsFix.draggable(true).position(new LatLng(latitude, longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.fin_per_loc)).title("Iam Here").snippet("I love this place"));
        final CameraPosition currentPos = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(18).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(currentPos));

        googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override            public void onCameraChange(CameraPosition cameraPosition) {
                googleMap.clear();

                googleMap.addMarker(markerOptionsFix.draggable(true).position(new LatLng(latitude, longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.fin_per_loc)).title("Iam Here").snippet("I love this place"));
                final CameraPosition currentPosFix = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(18).bearing(0).tilt(45).build();


                googleMap.addMarker(markerOptionsMov.position(new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.fin_pin_loc)).title("Iam Here").snippet("I love this place"));
                final CameraPosition currentPos = CameraPosition.builder().target(new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude)).bearing(0).tilt(45).build();
                getAddress(cameraPosition.target.latitude,cameraPosition.target.longitude);
            }
        });*/

       /* curLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(getContext());
                dialog.setMessage("Loading...");
                dialog.show();

                getLocation();
                dialog.dismiss();
                googleMap.clear();

                googleMap.addMarker(markerOptionsFix.draggable(true).position(new LatLng(latitude, longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.fin_per_loc)).title("Iam Here").snippet("I love this place"));
                final CameraPosition currentPosFix = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(18).bearing(0).tilt(45).build();


                googleMap.addMarker(markerOptionsMov.position(new LatLng(latitude, longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.fin_pin_loc)).title("Iam Here").snippet("I love this place"));
                final CameraPosition currentPos = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(18).bearing(0).tilt(45).build();
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(currentPos));
            }
        });
    }
*/


    public boolean isInternetConnected() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else
            connected = false;
        return connected;
    }

    public boolean isGPSEnabled() {
        LocationManager service = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return enabled;
    }

    public void getLocation() {
        GpsTracker gpsTracker = new GpsTracker(getContext());
        if (gpsTracker.canGetLocation() && isGPSEnabled() && isInternetConnected()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
        } else if (!isGPSEnabled()) {
            gpsTracker.showGPSSettingsAlert();
        } else if (!isInternetConnected()) {
            gpsTracker.showInternetSettingsAlert();
        }
    }

    public void getAddress(double latitude, double longitude) {
        GpsTracker gpsTracker = new GpsTracker(getContext());
        geocoder = new Geocoder(getContext(), Locale.getDefault());

        if (gpsTracker.canGetLocation() && isGPSEnabled() && isInternetConnected()) {
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                getAddress(latitude, longitude);
            }
            address = addresses.get(0).getAddressLine(0);
            customerAddress.setText(address);

        } else if (!isGPSEnabled()) {
            gpsTracker.showGPSSettingsAlert();
        } else if (!isInternetConnected()) {
            gpsTracker.showInternetSettingsAlert();
        }
    }


    public String getCustAddress() {
        return address;
    }


    private void pageLooper() {
        if (currentpage == slider_modelList.size() - 2) {
            currentpage = 2;
            bannerslider.setCurrentItem(currentpage, false);
        }
        if (currentpage == 1) {
            currentpage = slider_modelList.size() - 3;
            bannerslider.setCurrentItem(currentpage, false);
        }
    }

    private void StopBanner() {
        timer.cancel();
    }

    private void startbannerSlideshow() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentpage >= slider_modelList.size())
                    currentpage = 1;
                bannerslider.setCurrentItem(currentpage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }
}








