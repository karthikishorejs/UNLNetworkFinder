package info.androidhive.googlemapsv2;

import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BatteryInformationActivity extends Activity {

	
	private TextView batterylevelinfo,techinfo,pluggedinfo,statusinfo,tempinfo;
	private ProgressBar progressbar;
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battery_information);
		
		
		//Get the id of corresponding textview
		batterylevelinfo = (TextView)findViewById(R.id.battery_level_info);
		techinfo = (TextView)findViewById(R.id.technology_info);
		pluggedinfo = (TextView)findViewById(R.id.plugged_info);
		statusinfo = (TextView)findViewById(R.id.status_info);
		tempinfo = (TextView)findViewById(R.id.temperature_info);
		
		//Id of progress bar
		progressbar = (ProgressBar)findViewById(R.id.progressBar1);
				
		this.registerReceiver(this.batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    	
	}

				 
	 private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	             
	            
	            int  level= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
	            int  plugged= intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
	            
	            
	            int  status= intent.getIntExtra(BatteryManager.EXTRA_STATUS,0);
	            String  technology= intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
	            double  temperature= intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0)/10;
	            int  voltage= intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);
	            
	            progressbar.setMinimumHeight(10);
	            progressbar.setMinimumWidth(200);
	            progressbar.setProgress(level);
	            
	            
	            batterylevelinfo.setText(String.valueOf(level) + "%");
	            techinfo.setText(technology);
	            pluggedinfo.setText(getPlugTypeString(plugged));
	            tempinfo.setText(String.valueOf(temperature) +""+"°C");
	            statusinfo.setText(getStatusString(status));
	            
	            
	        }
	    };
  

	    
		/*
		 * Battery Plugged Information
		 * */
		private String getPlugTypeString(int plugged) {
			String plugType = "Unknown";

			switch (plugged) {
			case BatteryManager.BATTERY_PLUGGED_AC:
				plugType = "AC";
				break;
			case BatteryManager.BATTERY_PLUGGED_USB:
				plugType = "USB";
				break;
			}

			return plugType;
		}

	    
		/*
		 * Charging status of the Battery
		 * */
		private String getStatusString(int status) {
			String statusString = "Unknown";

			switch (status) {
			case BatteryManager.BATTERY_STATUS_CHARGING:
				statusString = "Charging";
				break;
			case BatteryManager.BATTERY_STATUS_DISCHARGING:
				statusString = "Discharging";
				break;
			case BatteryManager.BATTERY_STATUS_FULL:
				statusString = "Full";
				break;
			case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
				statusString = "Not Charging";
				break;
			}

			return statusString;
		}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battery_information, menu);
		return true;
	}
	

}
