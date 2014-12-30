package ngesu.and.findit;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



import ngesu.and.findit.MainActivity.PlaceholderFragment;
import ngesu.and.findit.intefaces.IFillProduct;
import ngesu.and.findit.intefaces.IGetLogo;
import ngesu.and.findit.intefaces.IGetLogoView;
import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.Item;
import ngesu.and.findit.models.ItemSerializable;
import ngesu.and.findit.utils.ResizeAnimation;
import ngesu.and.findit.utils.Screen;
import ngesu.and.findit.utils.Variables;
import ngesu.and.findit.ws.FillProduct;
import ngesu.and.findit.ws.GetImage;
import ngesu.and.findit.ws.GetLogo;
import ngesu.and.findit.ws.WebServiceManager;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class Items extends Activity implements IItems, OnMarkerClickListener, IFillProduct, IGetLogo{

	LinearLayout items;
	public final int offset=1000;
	private int count=0;
	private Hashtable<Integer,View> hash;
	private GoogleMap map;
	private int currentMapID;
	private Item selectedItem;
	LinearLayout lnMap;
	double longitude = 0;
	double latitude = 0;
	MarkerOptions myMarker;
	MarkerOptions itemMArker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentMapID=0;
		setContentView(R.layout.activity_items);
		
		hash= new Hashtable<Integer,View>();
		items=(LinearLayout)this.findViewById(R.id.idLayoutList);
		items.removeAllViews();
		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		lnMap=(LinearLayout)LayoutInflater.from(Items.this).inflate(R.layout.mapview, null);;
		count=0;
		
		Screen.initialize(getWindowManager().getDefaultDisplay());
		if (this.getIntent().getExtras() != null) {
			String search= this.getIntent().getExtras().getString("search");
			String distance= this.getIntent().getExtras().getString("distance");
			
			WebServiceManager ws= new WebServiceManager(this);
			//String jSonResult="";
			try {
				ws.execute("GetInfo",search,"","","GetInfo",distance);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}


	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.items, menu);
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
	public void callback(List<Item> items_) {
		// TODO Auto-generated method stub
		for (Item i : items_) {
			FillProduct fp=new FillProduct(this,i);
			fp.execute();
			
		}	
		Log.d("finjhgjhgjhgjgjhghjghgkjgjgkj","terminoghjgjgkhgjhgjhgjhgjgjkgjgj ");
		
		
	}

	
	
	/*public void fillItem(final LinearLayout itemLayout,final Item item)
	{
		
		TextView txtfio=(TextView)itemLayout.findViewById(R.id.finditoutname);
		TextView itemName=(TextView)itemLayout.findViewById(R.id.itemName);
		TextView distanceItem=(TextView)itemLayout.findViewById(R.id.distanceItem);
		TextView priceItem=(TextView)itemLayout.findViewById(R.id.priceItem);
		txtfio.setText(item.getFinditoutName());
		itemName.setText(item.getName());
		distanceItem.setText(item.getDistance()+"");
		priceItem.setText(item.getCost()+"");
		GetImage getImagews=new GetImage(this,itemLayout,item);
		getImagews.execute(item.getImage());
		
		
		
		
		
		final ImageView img=(ImageView)itemLayout.findViewById(R.id.goToMap);
		
		img.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				selectedItem=item;
				Bundle bundle = new Bundle();
				//bundle.putString("search", tvSerach.getText().toString());
				bundle.putSerializable("item", getCopySerializable(selectedItem));
				bundle.putParcelable("image", item.getBitMam());
				bundle.putInt("type", Variables.LISTA);
				
				Intent intent = new Intent(Items.this,MapActivity.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
	}
	*/
	
	public void  updateMap()
	{
		map.clear();
		if(myMarker!=null)
		map.addMarker(myMarker);
		map.addMarker(itemMArker);
		
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
			View rootView = inflater.inflate(R.layout.fragment_items,
					container, false);
			
			
			return rootView;
		}
	}*/

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


	@Override
	public void callbackImage(final Item item, LinearLayout itemLayout, Bitmap image) {
		// TODO Auto-generated method stub
		
		
   	    BitmapDrawable ob = new BitmapDrawable(image);
   	    ImageView imageView = (ImageView)itemLayout.findViewById(R.id.imgItem);
		imageView.setBackgroundDrawable(ob);
		//item.setBitMam(image);
		
	}

	public ItemSerializable getCopySerializable(Item i)
	{
		ItemSerializable is=new ItemSerializable();
		
		is.setActive(i.getActive());
		is.setAllImages(i.getAllImages());
		is.setCost(i.getCost());
		is.setDistance(i.getDistance());
		is.setFinditoutName(i.getFinditoutName());
		is.setHasCost(i.getHasCost());
		is.setIdcategory(i.getIdcategory());
		is.setIdCategory(i.getIdCategory());
		is.setIdItem(i.getIdItem());
		is.setImage(i.getImage());
		is.setLatitude(i.getLatitude());
		is.setLogo(i.getLogo());
		is.setLongitude(i.getLongitude());
		is.setName(i.getName());
		is.setDescription(i.getDescription());
		is.setIdSucursal(i.getIdSucursal());
		return is;
		
	}



	@Override
	public boolean onMarkerClick(Marker m) {
		// TODO Auto-generated method stub
		if(selectedItem!=null)
		{
		Bundle bundle = new Bundle();
		//bundle.putString("search", tvSerach.getText().toString());
		bundle.putSerializable("item", this.getCopySerializable(selectedItem));
		Intent intent = new Intent(this,ItemActivity.class);
		//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtras(bundle);
		startActivity(intent);
		}
		return false;
	}




	@Override
	public void callbackImages(List<Bitmap> image) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void callback(View v, final Item item) {
		// TODO Auto-generated method stub
		items.addView(v);
		items.setGravity(Gravity.CENTER_HORIZONTAL);
		LinearLayout lm=new LinearLayout(this);
		lm.setLayoutParams(new LinearLayout.LayoutParams(0,0));
		//LinearLayout mapLayout=(LinearLayout)LayoutInflater.from(this).inflate(R.layout.mapview, null);
		//hash.put(v.getId(), lm);
		items.addView(lm);
		LinearLayout ly=(LinearLayout)v.findViewById(R.id.idCompania);
		ly.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Bundle bundle = new Bundle();
				//bundle.putString("search", tvSerach.getText().toString());
				//bundle.putSerializable("item", this.getCopySerializable(selectedItem));
				Intent intent = new Intent(Items.this,Sucursal.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				bundle.putString("idSucursal", item.getIdSucursal()+"");
				intent.putExtras(bundle);
				
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		ImageView imageView = (ImageView)v.findViewById(R.id.imgItem);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				//bundle.putString("search", tvSerach.getText().toString());
				//bundle.putString("distance", distance.getText().toString().replace(" m","").replace(" k","000").replace("m",""));
				Intent intent = new Intent(Items.this,ItemActivity.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//bundle.putSerializable("item",getCopySerializable(item));
				bundle.putString("idProducto",item.getIdItem()+"");
				
				intent.putExtras(bundle);
				
				startActivity(intent);

			}
		});
		
final ImageView img=(ImageView)v.findViewById(R.id.goToMap);
		
		img.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				//selectedItem=item;
				Bundle bundle = new Bundle();
				//bundle.putString("search", tvSerach.getText().toString());
				bundle.putSerializable("item", item);
				//bundle.putParcelable("image", item.getBitMam());
				bundle.putInt("type", Variables.LISTA);
				
				Intent intent = new Intent((Items.this),MapActivity.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
		GetImage getImagews=new GetImage(this,(LinearLayout)v,item);
		getImagews.execute(item.getImage());
		
		ImageView imgv=(ImageView)v.findViewById(R.id.logo);
		new GetLogo(this,imgv).execute(item.getLogo());
	}




	




	@Override
	public void callbackImage(Bitmap image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void callbackImage(Bitmap image, ImageView v) {
		// TODO Auto-generated method stub
		 BitmapDrawable ob = new BitmapDrawable(image);
	   	    //ImageView imageView = (ImageView)itemLayout.findViewById(R.id.imgItem);
			v.setBackgroundDrawable(ob);
			//item.setBitMam(image);
	}





	

}
