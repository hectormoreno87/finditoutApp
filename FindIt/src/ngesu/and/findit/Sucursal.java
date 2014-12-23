package ngesu.and.findit;

import java.util.ArrayList;
import java.util.List;


import com.google.android.gms.internal.es;
import com.google.android.gms.plus.model.people.Person.Image;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.provider.ContactsContract;

import ngesu.and.findit.R;
import ngesu.and.findit.intefaces.IAddNumber;
import ngesu.and.findit.intefaces.CallbackSucursal;
import ngesu.and.findit.intefaces.ICallbackAddNumber;
import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.intefaces.OnClickOpenBrowser;
import ngesu.and.findit.intefaces.OnClickOpenMail;
import ngesu.and.findit.models.ImagenSucursal;
import ngesu.and.findit.models.Item;
import ngesu.and.findit.models.RedSocial;
import ngesu.and.findit.models.SucursalModel;
import ngesu.and.findit.models.Telefono;

import ngesu.and.findit.utils.AdapterVerGaleria;
import ngesu.and.findit.utils.Variables;

import ngesu.and.findit.utils.Screen;
import ngesu.and.findit.ws.GetImage;
import ngesu.and.findit.ws.GetImages;
import ngesu.and.findit.ws.WebServiceManager;


public class Sucursal extends FragmentActivity implements CallbackSucursal, IItems, ICallbackAddNumber {

	SucursalModel _sucursalModel;
	Bitmap logo=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sucursal);
	
		View title = getWindow().findViewById(android.R.id.title);
        View titleBar = (View) title.getParent();
        
        titleBar.setBackgroundColor(getResources().getColor(R.color.red));
        if (savedInstanceState == null) {
			
		}
        WebServiceManager ws= new WebServiceManager((CallbackSucursal)this);
        String idSucursal= this.getIntent().getExtras().getString("idSucursal");
        try {
        	
			ws.execute("GetSucursalByID",idSucursal);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		
		
		 WebServiceManager ws= new WebServiceManager((CallbackSucursal)this);
	        String idSucursal= this.getIntent().getExtras().getString("idSucursal");
	        try 
	        {	
				ws.execute("GetSucursalByID",idSucursal);	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
	        
			
		
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
		fillSucursal();
		
		// TODO Auto-generated method stub
		
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
			TextView descripcion=(TextView)findViewById(R.id.description);
			LinearLayout.LayoutParams lpt= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
			lpt.setMargins(20, 3, 3, 3);
			descripcion.setLayoutParams(lpt);
			descripcion.setText(_sucursalModel.getvDescripcion());
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
						
						final Dialog alert= new Dialog(Sucursal.this);
						LayoutInflater inflater = Sucursal.this.getLayoutInflater();
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
								Sucursal.this.startActivity(intent);
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
			
			
			
			
			
			
			
			//imagenes
			String[] imagenes= new String[_sucursalModel.getImagenes().size()];
			count=0;
			for(ImagenSucursal imgs:_sucursalModel.getImagenes())
			{
				
					imagenes[count]=getString(R.string.urlimages)+imgs.getUrl()+".png";
							count++;
			}
			
			new GetImages(this, null).execute(imagenes);
			
			LinearLayout lnly= (LinearLayout)this.findViewById(R.id.content);
			new GetImage(this, lnly).execute(getString(R.string.urlimages)+_sucursalModel.getIdEmpresa()+"/logo.png");
			
			
			//onclickOpenMAoActivity
			ImageView mapImg= (ImageView)findViewById(R.id.ivmap);
			mapImg.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					
					Bundle bundle = new Bundle();
					//bundle.putString("search", tvSerach.getText().toString());
					bundle.putSerializable("sucursal", _sucursalModel);
					bundle.putParcelable("image", logo);
					bundle.putInt("type", Variables.SUCURSAL);
					
					Intent intent = new Intent(Sucursal.this,MapActivity.class);
					//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.putExtras(bundle);
					startActivity(intent);
					
					
				}
			});
			
			
			
			
		}
	}

	@Override
	public void callback(List<Item> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void callbackImage(Item items, LinearLayout itemLayout, Bitmap image) {
		// TODO Auto-generated method stub
		logo=image;
		ImageView img= (ImageView)itemLayout.findViewById(R.id.logo);
		BitmapDrawable ob = new BitmapDrawable(image);
		LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT,.25f);
		lp.setMargins((int)(Screen.width*.02), (int)(Screen.height*.02), (int)(Screen.width*.02), (int)(Screen.height*.02));
		img.setLayoutParams(lp);
		img.setBackgroundDrawable(ob);
		
				
		
	}

	@Override
	public void callbackImages(List<Bitmap> image) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		
				//LinearLayout ln= (LinearLayout)findViewById(R.id.idContent);
				
				
				
				//final ImageView i= (ImageView)findViewById(R.id.currentImage);
				//i.setBackgroundDrawable(null);
				//ln.removeAllViews();
				
				
				AdapterVerGaleria adapter= new AdapterVerGaleria(getSupportFragmentManager(), image);
				
				ViewPager pager =(ViewPager)findViewById(R.id.gallery_item);
				pager.removeAllViews();
				pager.setAdapter(adapter);
				CirclePageIndicator  mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
				mIndicator.refreshDrawableState();
			        mIndicator.setViewPager(pager);
			        ((CirclePageIndicator) mIndicator).setSnap(true);
			        mIndicator.refreshDrawableState();
		
	}

	@Override
	public void addNumberCallback(int estatus, final String number) {
		
		//openWhatsappContact(number);
		if(estatus==0)
		{
			final Dialog alert= new Dialog(this);
			LayoutInflater inflater = this.getLayoutInflater();
			alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
			alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			View v =inflater.inflate(R.layout.popup, null);
			TextView txt=(TextView)v.findViewById(R.id.tittlePopUp);
			txt.setText(R.string.addcontact);
			Button no= (Button)v.findViewById(R.id.no);
			no.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					openWhatsappContact(number);
					alert.dismiss();
					
					// TODO Auto-generated method stub
				}
			});
			
			Button si= (Button)v.findViewById(R.id.si);
			si.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					addContact(Sucursal.this, _sucursalModel.getvEmpresa(), null, number, null, null, null, "", "");
					openWhatsappContact(number);
					alert.dismiss();
				}
			});
		    alert.setContentView(v);
			alert.show();
			
		}
		else
		{
			openWhatsappContact(number);
		}
		
		
	}

	void openWhatsappContact(String number) {
	    Uri uri = Uri.parse("smsto:" + number);
	    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
	    i.setPackage("com.whatsapp");  
	    startActivityForResult(Intent.createChooser(i, ""),1);
	}
	
	void addContact(Context ctx, String displayname, String homenumber,
			  String mobilenumber, String worknumber, String homeemail,
			  String workemail, String companyname,String jobtitle) {
			 String DisplayName = displayname;
			 String MobileNumber = homenumber;
			 String HomeNumber = mobilenumber;
			 String WorkNumber = worknumber;
			 String homeemailID = homeemail;
			 String workemailID = workemail;
			 String company = companyname;
			 String jobTitle = jobtitle;
			 ArrayList<ContentProviderOperation> contentProviderOperation = new
			ArrayList<ContentProviderOperation>();

			 contentProviderOperation.add(ContentProviderOperation
			   .newInsert(ContactsContract.RawContacts.CONTENT_URI)
			   .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
			   .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
			   .build());

			 // ------------------------------------------------------ Names
			 if (DisplayName != null) {
			  contentProviderOperation.add(ContentProviderOperation
			    .newInsert(ContactsContract.Data.CONTENT_URI)
			    .withValueBackReference(
			      ContactsContract.Data.RAW_CONTACT_ID, 0)
			    .withValue(
			      ContactsContract.Data.MIMETYPE,
			      ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
			    .withValue(
			      ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
			      DisplayName).build());
			 }

			 // ------------------------------------------------------ Mobile Number
			 if (MobileNumber != null) {
			  contentProviderOperation.add(ContentProviderOperation
			    .newInsert(ContactsContract.Data.CONTENT_URI)
			    .withValueBackReference(
			      ContactsContract.Data.RAW_CONTACT_ID, 0)
			    .withValue(
			      ContactsContract.Data.MIMETYPE,
			      ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
			    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
			      MobileNumber)
			    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
			      ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
			    .build());
			 }

			 // ------------------------------------------------------ Home Numbers
			 if (HomeNumber != null) {
			  contentProviderOperation.add(ContentProviderOperation
			    .newInsert(ContactsContract.Data.CONTENT_URI)
			    .withValueBackReference(
			      ContactsContract.Data.RAW_CONTACT_ID, 0)
			    .withValue(
			      ContactsContract.Data.MIMETYPE,
			      ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
			    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
			      HomeNumber)
			    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
			      ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
			    .build());
			 }

			 // ------------------------------------------------------ Work Numbers
			 if (WorkNumber != null) {
			  contentProviderOperation.add(ContentProviderOperation
			    .newInsert(ContactsContract.Data.CONTENT_URI)
			    .withValueBackReference(
			      ContactsContract.Data.RAW_CONTACT_ID, 0)
			    .withValue(
			      ContactsContract.Data.MIMETYPE,
			      ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
			    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
			      WorkNumber)
			    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
			      ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
			    .build());
			 }

			 // ------------------------------------------------------ workEmail
			 if (workemailID != null) {
			  contentProviderOperation.add(ContentProviderOperation
			    .newInsert(ContactsContract.Data.CONTENT_URI)
			    .withValueBackReference(
			      ContactsContract.Data.RAW_CONTACT_ID, 0)
			    .withValue(
			      ContactsContract.Data.MIMETYPE,
			      ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
			    .withValue(ContactsContract.CommonDataKinds.Email.DATA,
			      workemailID)
			    .withValue(ContactsContract.CommonDataKinds.Email.TYPE,
			      ContactsContract.CommonDataKinds.Email.TYPE_WORK)
			    .build());
			 }
			 // ------------------------------------------------------ homeEmail
			 if (homeemailID != null) {
			  contentProviderOperation.add(ContentProviderOperation
			    .newInsert(ContactsContract.Data.CONTENT_URI)
			    .withValueBackReference(
			      ContactsContract.Data.RAW_CONTACT_ID, 0)
			    .withValue(
			      ContactsContract.Data.MIMETYPE,
			      ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
			    .withValue(ContactsContract.CommonDataKinds.Email.DATA,
			      homeemailID)
			    .withValue(ContactsContract.CommonDataKinds.Email.TYPE,
			      ContactsContract.CommonDataKinds.Email.TYPE_HOME)
			    .build());
			 }
			 // ------------------------------------------------------ Organization
			 if (!company.equals("") && !jobTitle.equals("")) {
			  contentProviderOperation.add(ContentProviderOperation
			    .newInsert(ContactsContract.Data.CONTENT_URI)
			    .withValueBackReference(
			      ContactsContract.Data.RAW_CONTACT_ID, 0)
			    .withValue(
			      ContactsContract.Data.MIMETYPE,
			      ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
			    .withValue(
			      ContactsContract.CommonDataKinds.Organization.COMPANY,
			      company)
			    .withValue(
			      ContactsContract.CommonDataKinds.Organization.TYPE,
			      ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
			    .withValue(
			      ContactsContract.CommonDataKinds.Organization.TITLE,
			      jobTitle)
			    .withValue(
			      ContactsContract.CommonDataKinds.Organization.TYPE,
			      ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
			    .build());
			 }
			 // Asking the Contact provider to create a new contact
			 try {
			  ctx.getContentResolver()
			    .applyBatch(ContactsContract.AUTHORITY, contentProviderOperation);
			  
			 } catch (Exception e) {
			  e.printStackTrace();
			  //show exception in toast
			  Toast.makeText(ctx, "Exception: " + e.getMessage(),
			    Toast.LENGTH_SHORT).show();
			 }
			}
	

}
