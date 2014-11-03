package ngesu.and.findit;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import ngesu.and.findit.R;
import ngesu.and.findit.intefaces.CallbackSucursal;
import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.SucursalModel;
import ngesu.and.findit.ws.WebServiceManager;


public class Sucursal extends Activity implements CallbackSucursal {

	SucursalModel _sucursalModel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sucursal);
		
		View title = getWindow().findViewById(android.R.id.title);
        View titleBar = (View) title.getParent();
        titleBar.setBackgroundColor(getResources().getColor(R.color.red));
        WebServiceManager ws= new WebServiceManager(this);
        String idSucursal= this.getIntent().getExtras().getString("idSucursal");
        try {
        	
			ws.execute("GetSucursalByID",idSucursal);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		if (savedInstanceState == null) {
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sucursal, menu);
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

	@Override
	public void callback(SucursalModel s) {
		
		_sucursalModel=s;
		// TODO Auto-generated method stub
		
	}

	

}
