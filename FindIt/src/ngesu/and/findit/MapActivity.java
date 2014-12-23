package ngesu.and.findit;

import ngesu.and.findit.models.ItemSerializable;
import ngesu.and.findit.models.SucursalModel;
import ngesu.and.findit.utils.Variables;



import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.MapFragment;


import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;



public class MapActivity extends Activity {
	
	static final LatLng HAMBURG = new LatLng(20.6827248, -103.3466798);
	  static final LatLng KIEL = new LatLng(21.6827248, -103.3466798);
	  private GoogleMap map;
	  double longitude=0;
	  double latitude=0;
	  MarkerOptions myMarker;
	  MarkerOptions itemMarker;
	  Marker mark; 
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		View title = getWindow().findViewById(android.R.id.title);
        View titleBar = (View) title.getParent();
        titleBar.setBackgroundColor(getResources().getColor(R.color.red));
        
        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
		if (savedInstanceState == null) {
		//	getFragmentManager().beginTransaction()
		//			.add(R.id.container, new PlaceholderFragment()).commit();
		}
		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		
		Integer type=(Integer)bundle.getInt("type");
		Bitmap bm=null;
		LatLng itempos=null;
		if(type==Variables.LISTA)
		{
		
		ItemSerializable item=(ItemSerializable)bundle.getSerializable("item");
		bm=(Bitmap)bundle.getParcelable("image");
		itempos= new LatLng(Double.parseDouble(item.getLatitude()),Double.parseDouble(item.getLongitude()));
		
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);			
		 map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_))
			        .getMap();
		 
		 if(bm!=null)
		 itemMarker= new MarkerOptions().position(itempos)
	        .title(item.getName()).icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(bm,60,60)));
		 else
		itemMarker= new MarkerOptions().position(itempos)
		    .title(item.getName());//.icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(bm,60,60)));
		 
		 
		 mark = map.addMarker(itemMarker);
		
		}
		else if(type==Variables.SUCURSAL)
		{
			SucursalModel sucursal=(SucursalModel)bundle.getSerializable("sucursal");
			bm=(Bitmap)bundle.getParcelable("image");
			itempos= new LatLng(Double.parseDouble(sucursal.getvLatitud()),Double.parseDouble(sucursal.getvLongitud()));
			getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);			
			 map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_))
				        .getMap(); 
			 itemMarker= new MarkerOptions().position(itempos)
		        .title(sucursal.getvEmpresa()).icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(bm,60,60)));
				    mark = map.addMarker(itemMarker);
		}
	
			
		
		
		
			    

			    // Move the camera instantly to hamburg with a zoom of 15.
			    map.moveCamera(CameraUpdateFactory.newLatLngZoom(itempos, 15));

			    // Zoom in, animating the camera.
			    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
			    
			    LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
			    
final LocationListener locationListener = new LocationListener() {
					
				    public void onLocationChanged(Location location) {
				        longitude = location.getLongitude();
				        latitude = location.getLatitude();
				        
				        myMarker= new MarkerOptions()
				        .position(new LatLng(latitude, longitude))
				        .title("Me")
				        .snippet("Me is cool")
				        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
				        updateMap();
				        
				        
				       
				        
				    }

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onProviderEnabled(String provider) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onProviderDisabled(String provider) {
						// TODO Auto-generated method stub
						
					}
};

									
				//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000l, 10f, locationListener);
				lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000l, 10f, locationListener);
				
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void  updateMap()
	{
		map.clear();
		if(myMarker!=null)
		{
		map.addMarker(myMarker);
		map.addMarker(itemMarker);
		}
		
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}
		

		/*@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_map, container,
					false);
			return rootView;
		}*/
	}

	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
	    int width = bm.getWidth();
	    int height = bm.getHeight();
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;
	    // CREATE A MATRIX FOR THE MANIPULATION
	    Matrix matrix = new Matrix();
	    // RESIZE THE BIT MAP
	    matrix.postScale(scaleWidth, scaleHeight);

	    // "RECREATE" THE NEW BITMAP
	    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
	    return resizedBitmap;
	}
}
