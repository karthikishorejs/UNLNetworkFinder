package info.androidhive.googlemapsv2;

import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.format.Formatter;
import android.view.Menu;
import android.widget.TextView;

public class NetworkInformationActivity extends Activity {

	private TextView macaddress,ipaddress,ssid,bssid,networkconnected;
	private TextView subnetmask,defaultgateway,dnsserver1,dnsserver2;
	
	String mac_address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network_information);
		
		
		//WIFI Information
		macaddress = (TextView)findViewById(R.id.mac_address_info);
		ipaddress = (TextView)findViewById(R.id.ip_address_info);
		ssid = (TextView)findViewById(R.id.SS_ID_info);
		bssid = (TextView)findViewById(R.id.BSS_ID_info);
		networkconnected = (TextView)findViewById(R.id.Network_Connected_info);
		
		//Connection Information
		subnetmask = (TextView)findViewById(R.id.subnet_mask_info);
		defaultgateway = (TextView)findViewById(R.id.default_gateway_info);
		dnsserver1 = (TextView)findViewById(R.id.dns_server1_info);
		dnsserver2 = (TextView)findViewById(R.id.dns_server2_info);
	
		
		
		ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		//If wifi connection available
		if (mWifi.isConnected()) {
		    
			WifiManager wifimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
			WifiInfo winfo = wifimanager.getConnectionInfo();
			
			//Get mac address
			mac_address = winfo.getMacAddress();
			macaddress.setText(mac_address);
			
			//Get IP Address
			String ip_address = Formatter.formatIpAddress(winfo.getIpAddress());
			ipaddress.setText(ip_address);
			
			//Get SSID
			String ss_id = winfo.getSSID();
			ssid.setText(ss_id);
			
			//GET BSSID
			String bss_id = winfo.getBSSID();
			bssid.setText(bss_id);
			
			//NetworkConnected
			networkconnected.setText("Yes");
			
			//Subnetmask
			DhcpInfo d = wifimanager.getDhcpInfo();
			String netmask = Formatter.formatIpAddress(d.netmask);
			subnetmask.setText(netmask);
			
			//Default Gateway
			String dg = Formatter.formatIpAddress(d.gateway);
			defaultgateway.setText(dg);
			
			//DNS Server 1
			String dns1 = Formatter.formatIpAddress(d.dns1);
			dnsserver1.setText(dns1);
			
			
			//DNS Server 2
			String dns2 = Formatter.formatIpAddress(d.dns2);
			dnsserver2.setText(dns2);
			
			
		} else{
			
			//No wifi connection
			macaddress.setText("N/A");
			ipaddress.setText("N/A");
			ssid.setText("N/A");
			bssid.setText("N/A");
			networkconnected.setText("N/A");
			subnetmask.setText("N/A");
			defaultgateway.setText("N/A");
			dnsserver1.setText("N/A");
			dnsserver2.setText("N/A");
			
			
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.network_information, menu);
		return true;
	}

}
