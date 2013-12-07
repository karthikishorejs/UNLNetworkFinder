package info.androidhive.googlemapsv2;



import info.androidhive.googlemapsv2.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener{

	private Button map,newlocation,speedtest,networktest,batterytest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Map button event
		map = (Button)findViewById(R.id.map);
		map.setOnClickListener(this);
		
		//New location button event
		newlocation = (Button)findViewById(R.id.location);
		newlocation.setOnClickListener(this);
		
		//Speed test button event
		speedtest = (Button)findViewById(R.id.speed);
		speedtest.setOnClickListener(this);
		
		//Network information button event
		networktest = (Button)findViewById(R.id.network);
		networktest.setOnClickListener(this);
		
		//Battery information button event
		batterytest = (Button)findViewById(R.id.battery);
		batterytest.setOnClickListener(this);
		
	}

	public void onClick(View v){
		if(v.getId() ==R.id.map){
			Intent mapintent  = new Intent(MainActivity.this,MapActivity.class);
			startActivity(mapintent);
		}else if(v.getId()==R.id.location){
			Intent locationintent  = new Intent(MainActivity.this,AddLocationActivity.class);
			startActivity(locationintent);	
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		}else if(v.getId()==R.id.speed){
			Intent speedintent  = new Intent(MainActivity.this,SpeedActivity.class);
			startActivity(speedintent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		}else if(v.getId()==R.id.network){
			Intent networkintent  = new Intent(MainActivity.this,NetworkInformationActivity.class);
			startActivity(networkintent);
			overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}else{
			Intent batteryintent = new Intent(MainActivity.this, BatteryInformationActivity.class);
			startActivity(batteryintent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			
		}	
		
	}
	
	
	
	

}
