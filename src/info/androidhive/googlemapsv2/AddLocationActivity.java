package info.androidhive.googlemapsv2;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddLocationActivity extends Activity implements LocationListener {
	

	private Button btnShowLocation,btnAddLocation;
    private TextView latinfo,longinfo,conninfo,areainfo;
    private EditText locationareainfo;
    private LocationManager locationManager;
    private String provider;
    private Location location;
    private double lat;
    private double lng;
	private int linkspeed;
	String log;
	
	//GPSDatabase
	GPSDatabase db;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_location);
		
		db = new GPSDatabase(getApplicationContext());		
		//Get the Text coordinates
		btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
		btnAddLocation = (Button)findViewById(R.id.btnAddLocation);
		
		locationareainfo = (EditText)findViewById(R.id.locationname);
		
        latinfo = (TextView)findViewById(R.id.latitudeinfo);
        longinfo = (TextView)findViewById(R.id.longitudeinfo);
        conninfo = (TextView)findViewById(R.id.signalstrengthinfo);
        areainfo = (TextView)findViewById(R.id.locationname);
       
        //WifiManager Class
        WifiManager wifimanager = (WifiManager)getSystemService(WIFI_SERVICE);
        linkspeed = wifimanager.getConnectionInfo().getLinkSpeed();
        
        
        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        location = locationManager.getLastKnownLocation(provider);
        
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
         
            @Override
            public void onClick(View arg0) { 

            	conninfo.setText(String.valueOf(linkspeed));
            	// Initialize the location fields
                if (location != null) {
                  System.out.println("Provider " + provider + " has been selected.");
                  onLocationChanged(location);
                } else {
                  latinfo.setText("Location not available");
                  longinfo.setText("Location not available");
                }
            	
             }	
        });
        
        btnAddLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				System.out.println(location.getLatitude());
				System.out.println(location.getLongitude());
				System.out.println(linkspeed);
				System.out.println(locationareainfo.getEditableText().toString());
				//updatedb();
				db.open();
				System.out.println("Data base open");
				db.insertRows(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()),locationareainfo.getEditableText().toString(),String.valueOf(linkspeed));
				System.out.println("Succesfully inserted");
				/*
				Cursor cursor=db.getAllRows();
				cursor.moveToFirst();
				for (int i=0; i<cursor.getCount(); i++) {
						 log = "Lat=" +cursor.getString(1) +"  "+"Log "+ cursor.getString(2);
				}
				Log.d("log", log);
				*/
				
				db.close();
			}
		});
        
     }
	
	
	/* Request updates at startup */
	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }

	/* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	  }

	  @Override
	  public void onLocationChanged(Location location) {
	    lat = location.getLatitude();
	    lng = location.getLongitude();
	    latinfo.setText(String.valueOf(lat));
	    longinfo.setText(String.valueOf(lng));
	  }

	  @Override
	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	  }

	  @Override
	  public void onProviderEnabled(String provider) {
	    Toast.makeText(this, "Enabled new provider " + provider,
	        Toast.LENGTH_SHORT).show();

	  }

	  @Override
	  public void onProviderDisabled(String provider) {
	    Toast.makeText(this, "Disabled provider " + provider,
	        Toast.LENGTH_SHORT).show();
	  }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_location, menu);
		return true;
	}

}
