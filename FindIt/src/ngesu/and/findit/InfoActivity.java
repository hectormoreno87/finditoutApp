package ngesu.and.findit;

import ngesu.and.findit.intefaces.CallbackSucursal;
import ngesu.and.findit.intefaces.IAddNumber;
import ngesu.and.findit.intefaces.ICallbackAddNumber;
import ngesu.and.findit.intefaces.OnClickOpenBrowser;
import ngesu.and.findit.intefaces.OnClickOpenMail;
import ngesu.and.findit.models.RedSocial;
import ngesu.and.findit.models.SucursalModel;
import ngesu.and.findit.models.Telefono;
import ngesu.and.findit.utils.Screen;
import ngesu.and.findit.utils.Variables;
import ngesu.and.findit.ws.WebServiceManager;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;

public class InfoActivity extends FragmentActivity implements CallbackSucursal,ICallbackAddNumber {
	SucursalModel _sucursalModel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
        titleBar.setBackgroundColor(getResources().getColor(R.color.red));
        this.setTitle(R.string.informacioncontacto);
		if (savedInstanceState == null) {
			/*getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();*/
		}
		
		 WebServiceManager ws= new WebServiceManager((CallbackSucursal)this,true);
	        String idSucursal= this.getIntent().getExtras().getString("idSucursal");
	        try {
	        	
				ws.execute("GetSucursalByID",idSucursal);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
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
		// TODO Auto-generated method stub
		_sucursalModel=s;
		fillSucursal();
	}
	

	private void fillSucursal()
	{
		
		//se cambia el tamaño de la imagen de whatsapp y telephons ya que están fijos, cambiará su tamaño dependiendo del dispositivo
		ImageView ivw= (ImageView)findViewById(R.id.ivWhatsApp);
		
		LinearLayout.LayoutParams lpW= new LinearLayout.LayoutParams((int)(Screen.width*.1),(int)(Screen.width*.1));
		lpW.setMargins(20, 3, 3, 3);
		ivw.setLayoutParams(lpW);
		ImageView ivT= (ImageView)findViewById(R.id.ivTelephone);
		
		LinearLayout.LayoutParams lpT= new LinearLayout.LayoutParams((int)(Screen.width*.1),(int)(Screen.width*.1));
		lpT.setMargins(20, 3, 3, 3);
		ivT.setLayoutParams(lpT);
		
		
		if(_sucursalModel!=null)
		{
			TextView findOutName=(TextView)findViewById(R.id.findItOutName);
			findOutName.setText(_sucursalModel.getvEmpresa());
			TextView sucursal=(TextView)findViewById(R.id.sucursal);
			sucursal.setText(_sucursalModel.getvNombreSuc());
			TextView domicilio=(TextView)findViewById(R.id.domicilio);
			domicilio.setText(_sucursalModel.getvDirecc());
			//TextView descripcion=(TextView)findViewById(R.id.description);
			//LinearLayout.LayoutParams lpt= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
			//lpt.setMargins(20, 3, 3, 3);
			//descripcion.setLayoutParams(lpt);
			//descripcion.setText(_sucursalModel.getvDescripcion());
			
			LinearLayout whatsApp=(LinearLayout)findViewById(R.id.whatsappContainer);
			//email
			TextView txtEmail= (TextView)findViewById(R.id.txtEmail);
			ImageView imgE= (ImageView)findViewById(R.id.ivEmail);
			LinearLayout.LayoutParams lpEmail= new  LinearLayout.LayoutParams((int)(Screen.width*.1), (int)(Screen.width*.1));
			lpEmail.setMargins(20, 3, 3, 3);
			imgE.setLayoutParams(lpEmail);
			OnClickOpenMail opm= new OnClickOpenMail(this, _sucursalModel.getvMail());
			txtEmail.setOnClickListener(opm);
			txtEmail.setText(_sucursalModel.getvMail());
			
			//web
			ImageView imgW= (ImageView)findViewById(R.id.ivWeb);
			LinearLayout.LayoutParams lpWeb= new LinearLayout.LayoutParams((int)(Screen.width*.1), (int)(Screen.width*.1));
			lpWeb.setMargins(20, 3, 3, 3);
			imgW.setLayoutParams(lpWeb);
			TextView txtWeb= (TextView)findViewById(R.id.txtWeb);
			txtWeb.setText(_sucursalModel.getvWeb());
			OnClickOpenBrowser opb= new OnClickOpenBrowser(this,_sucursalModel.getvWeb());
			txtWeb.setOnClickListener(opb);
			
			
			
			//whatsapp
			whatsApp.removeAllViews();
			
			whatsApp.setGravity(Gravity.CENTER_VERTICAL);
			int counter=0;
			for(final Telefono t:_sucursalModel.getTelefonos())
			{
				if(t.getbWhatsApp())
				{
					TextView tex= new TextView(this);
					tex.setTextColor(Color.parseColor("#000000"));
					tex.setId(counter+1000);
					tex.append(t.getvTelefono());
					LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
					lp.setMargins(0, 3, 20, 3);
					tex.setLayoutParams(lp);
					IAddNumber number= new IAddNumber(this, true, t.getvTelefono(), (ICallbackAddNumber)this);
					tex.setOnClickListener(number);
					whatsApp.addView(tex);
					
					counter++;
				}
			}
			
			//Telefonos
			LinearLayout telephones=(LinearLayout)findViewById(R.id.telephoneContainer);
			telephones.setGravity(Gravity.CENTER_VERTICAL);
			telephones.removeAllViews();
			
			
			for(final Telefono t:_sucursalModel.getTelefonos())
			{
				TextView tex= new TextView(this);
				tex.setTextColor(Color.parseColor("#000000"));
				tex.setId(counter+1000);
				tex.append(t.getvTelefono());
				LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
				lp.setMargins(0, 3, 20, 3);
				tex.setLayoutParams(lp);
				
				//onclick
				tex.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v2) {
						// TODO Auto-generated method stub
						
						final Dialog alert= new Dialog(InfoActivity.this);
						LayoutInflater inflater = InfoActivity.this.getLayoutInflater();
						alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
						alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
						View v =inflater.inflate(R.layout.popup, null);
						TextView txt=(TextView)v.findViewById(R.id.tittlePopUp);
						String message = getString(R.string.call);
						txt.setText(message+ " "+t.getvTelefono() +"?");
						Button no= (Button)v.findViewById(R.id.no);
						no.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								
								alert.dismiss();
								
								// TODO Auto-generated method stub
							}
						});
						
						Button si= (Button)v.findViewById(R.id.si);
						si.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								
								
								Intent intent = new Intent(Intent.ACTION_CALL);

								intent.setData(Uri.parse("tel:" + t.getvTelefono()));
								InfoActivity.this.startActivity(intent);
								alert.dismiss();
							}
						});
					    alert.setContentView(v);
						alert.show();
						
					}
				});
				//end onclick
				
				//tex.setOnClickListener(number);
				telephones.addView(tex);
				counter++;
			}
			
			
			//Redes sociales
			LinearLayout redessociales=(LinearLayout)findViewById(R.id.idRedesSociales);
			redessociales.removeAllViews();
			redessociales.setGravity(Gravity.CENTER_VERTICAL);
			int count=0;
			for(RedSocial rs:_sucursalModel.getRedesSociales())
			{
				LinearLayout l= new LinearLayout(this);
				l.setGravity(Gravity.CENTER_VERTICAL);
				LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams((int)(Screen.width*.1), (int)(Screen.width*.1));
				lp.setMargins(20, 3, 3, 3);
				ImageView img= new ImageView(this);
				TextView txt= new TextView(this);
				txt.setTextColor(Color.parseColor("#000000"));
				Drawable image=null;
			//	https://twitter.com/@hecmol87
				
				if(rs.getIdRedSocial()==1)
				{
					//tipo 1: extraemos imagen de Facebook
				  image = getResources().getDrawable(R.drawable.facebook);
				  img.setLayoutParams(lp);
				  img.setBackgroundDrawable(image);
				  OnClickOpenBrowser OCOB= new OnClickOpenBrowser(this, rs.getvRedSocial());
				  
				  //Agregamos textview para agregar la dirección de la red social
				  
				  txt.setText(rs.getvRedSocial());
				  txt.setOnClickListener(OCOB);
				  l.addView(img);
				  l.addView(txt);
				  
				}
				else if(rs.getIdRedSocial()==2)
				{
					//tipo 2: extraemos imagen de Twitter
					image = getResources().getDrawable(R.drawable.twitter);
					img.setLayoutParams(lp);
					img.setBackgroundDrawable(image);
					OnClickOpenBrowser OCOB= new OnClickOpenBrowser(this, "https://twitter.com/"+rs.getvRedSocial());
					
					//Agregamos textview para agregar la dirección de la red social
					txt.setText(rs.getvRedSocial());
					txt.setOnClickListener(OCOB);
					l.addView(img);
					l.addView(txt);
					
				}else if(rs.getIdRedSocial()==3)
				{
					//tipo 3: extraemos imagen de Instagram
					image = getResources().getDrawable(R.drawable.instagram);
					img.setLayoutParams(lp);
					img.setBackgroundDrawable(image);
					OnClickOpenBrowser OCOB= new OnClickOpenBrowser(this, rs.getvRedSocial());
					//Agregamos textview para agregar la dirección de la red social
					txt.setText(rs.getvRedSocial());  
					txt.setOnClickListener(OCOB);
					l.addView(img);
					l.addView(txt);
				}
				
				//count++;
				redessociales.addView(l);
			}
			
			
			
			
			
			
			
		}
	}

	@Override
	public void addNumberCallback(int estatus, String number) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_info, container,
					false);
			return rootView;
		}
	}*/

}
