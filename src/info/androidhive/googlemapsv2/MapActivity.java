package info.androidhive.googlemapsv2;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MapActivity extends Activity {

	
	private GoogleMap googleMap;
	private GPSDatabase db;
	String log;
	MarkerOptions marker;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		db = new GPSDatabase(getApplicationContext());
		
		try{
			
			initilizeMap();
			fetchdb();
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}

	private void fetchdb() {
		// TODO Auto-generated method stub
		double lat = 0;
		double lng = 0;
		int signal=0;
		db.open();
		
		Cursor cursor=db.getAllRows();
		cursor.moveToFirst();
		System.out.println(cursor.getCount());
		for (int i=0; i<cursor.getCount(); i++) {
			
			log = "Lat=" +cursor.getString(1) +"  "+"Log "+ cursor.getString(2);
			Log.d("log", log);
			lat = cursor.getDouble(cursor.getColumnIndex(GPSDatabase.COLUMN2));
			lng = cursor.getDouble(cursor.getColumnIndex(GPSDatabase.COLUMN3));
			marker = new MarkerOptions().position(new LatLng(lat, lng));
			
			signal = cursor.getInt(cursor.getColumnIndex(GPSDatabase.COLUMN5));
			if(signal<=50){
				marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
			}else{
				marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				
			}
			googleMap.addMarker(marker);
			
			cursor.moveToNext();
		}
		
		db.close();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
	
	private void initilizeMap() {
		// TODO Auto-generated method stub
		if (googleMap == null) {
            googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap(); 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }

	}

	
	@Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

}
