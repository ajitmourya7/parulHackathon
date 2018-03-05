package co.iam_infinity.www.hackathon;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import co.iam_infinity.www.hackathon.model.Dusbindata;
import de.hdodenhof.circleimageview.CircleImageView;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPoiClickListener {

    private GoogleMap mMap, pl;
    FloatingActionButton myLocation;
    LocationManager locationManager;
    LocationListener n,p;
    Button button;
    CardView dustbin,toilet;
    List <Dusbindata> dusbinList,toiletList;
    CircleImageView notibut,inbulid;
    CircleImageView blacklist,taxmap;
    int count;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        myLocation = (FloatingActionButton)findViewById(R.id.fab);
        button = (Button) findViewById(R.id.submit);
        dustbin = (CardView) findViewById(R.id.dustbin);
        toilet = (CardView) findViewById(R.id.toilet);
        notibut = (CircleImageView)findViewById(R.id.notibut);
        blacklist = (CircleImageView) findViewById(R.id.blacklist);
        inbulid = (CircleImageView)findViewById(R.id.inbulid);
        taxmap = (CircleImageView)findViewById(R.id.Tax);

        progress = new ProgressDialog(MapsActivity.this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        dustbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference().child("development").child("dustBin");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        fetchdusbin(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        toilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference().child("development").child("toilet");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        fetchtoilet(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        notibut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this,NotificationActivity.class));
            }
        });

        blacklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this,BlacklistedActivity.class));
            }
        });

        inbulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this,SubmitFormActivity.class));
            }
        });

        taxmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this,AadharActivity.class));
            }
        });


        myLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                yourLocation();
            }
        });

        PlaceAutocompleteFragment places= (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                String name = (String) place.getName();
                pl.clear();
                pl.addMarker(new MarkerOptions()
                        .title(name)
                        .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                        .position(new LatLng(place.getLatLng().latitude, place.getLatLng().longitude)));
                pl.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(place.getLatLng().latitude, place.getLatLng().longitude), 16));

            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        // get the bottom sheet view
        LinearLayout llBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);

        // init the bottom sheet behavior
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

        // change the state of the bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        // set hideable or not
        bottomSheetBehavior.setHideable(true);

        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }

    private void yourLocation() {
        count = 0;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //check the network provider  is enabled
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            n = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double lal = location.getLatitude();
                    double log = location.getLongitude();
                    LatLng latLng = new LatLng(lal,log);
                    mMap.clear();
                    //instantiate the class, Geocoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());

                    if (count<1){
                        mMap.addMarker(new MarkerOptions().position(latLng).title("Your Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_loc)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
                        count++;
                    }
                    progress.dismiss();
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,n);

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            progress.dismiss();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, n);
        }
    }

    private void fetchdusbin(DataSnapshot dataSnapshot) {
        dusbinList = new ArrayList<>();
        dusbinList.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Dusbindata te = ds.getValue(Dusbindata.class);
            dusbinList.add(te);
        }
        pl.clear();
        mMap.clear();
        for (int i=0;i<dusbinList.size();i++){
            pl.addMarker(new MarkerOptions()
                    .title("Dusbin")
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(Double.valueOf(dusbinList.get(i).getLat()),Double.valueOf(dusbinList.get(i).getLng())))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon)));
        }
        yourdusbinLocation();
    }

    private void fetchtoilet(DataSnapshot dataSnapshot) {
        toiletList = new ArrayList<>();
        toiletList.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Dusbindata te = ds.getValue(Dusbindata.class);
            toiletList.add(te);
        }
        pl.clear();
        mMap.clear();
        for (int i=0;i<toiletList.size();i++){
            pl.addMarker(new MarkerOptions()
                    .title("Public Washroom")
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(Double.valueOf(toiletList.get(i).getLat()),Double.valueOf(toiletList.get(i).getLng())))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon)));
        }
        yourdusbinLocation();
    }

    private void yourdusbinLocation() {
        count = 0;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //check the network provider  is enabled
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            progress.dismiss();
            n = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double lal = location.getLatitude();
                    double log = location.getLongitude();
                    LatLng latLng = new LatLng(lal,log);
                    //instantiate the class, Geocoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    if (count<1){
                        mMap.addMarker(new MarkerOptions().position(latLng).title("Your Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_loc)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
                        count++;
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,n);

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            progress.dismiss();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, n);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapbluestyle));

            if (!success) {
            }
        } catch (Resources.NotFoundException e) {
        }

        mMap = googleMap;

        pl = googleMap;

        LatLng latLng = new LatLng(22.3071588,73.18121870000004);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,13));

        mMap.setOnPoiClickListener(this);

    }

    @Override
    public void onPoiClick(PointOfInterest poi) {

        Toast.makeText(getApplicationContext(), "Clicked: " +
                        poi.name + "\nPlace ID:" + poi.placeId +
                        "\nLatitude:" + poi.latLng.latitude +
                        " Longitude:" + poi.latLng.longitude,
                Toast.LENGTH_SHORT).show();
    }

}
