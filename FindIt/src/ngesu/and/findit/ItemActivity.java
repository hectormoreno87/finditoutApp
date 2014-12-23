package ngesu.and.findit;


import java.util.ArrayList;
import java.util.List;

import com.viewpagerindicator.CirclePageIndicator;

import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.Item;
import ngesu.and.findit.models.ItemSerializable;
import ngesu.and.findit.utils.AdapterVerGaleria;
import ngesu.and.findit.utils.Variables;
import ngesu.and.findit.ws.GetImages;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;

public class ItemActivity extends FragmentActivity implements IItems {

	ItemSerializable item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		View title = getWindow().findViewById(android.R.id.title);
		this.setTitle(R.string.product);
        View titleBar = (View) title.getParent();
        titleBar.setBackgroundColor(getResources().getColor(R.color.red));
		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		item=(ItemSerializable)bundle.getSerializable("item");
		if(item.getAllImages()!=null)
		{
			String[] imgs=item.getAllImages().split("@@");
			new GetImages(this, item).execute(imgs);
		}
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		fillInfo();
		
		
	}

	public void fillInfo()
	{
		if(item!=null)
		{
			TextView des = (TextView)findViewById(R.id.description);
			des.setText(item.getDescription());
			TextView p = (TextView)findViewById(R.id.producto);
			p.setText(item.getName());
			TextView distance = (TextView)findViewById(R.id.distancia);
			distance.setText(item.getDistance()+"");
			ImageView btnInfo = (ImageView)findViewById(R.id.btnInfo);
			btnInfo.setOnClickListener( new OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
				
					Bundle bundle = new Bundle();
					//bundle.putString("search", tvSerach.getText().toString());
					//bundle.putSerializable("item", this.getCopySerializable(selectedItem));
					Intent intent = new Intent(ItemActivity.this,InfoActivity.class);
					//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					bundle.putString("idSucursal", item.getIdSucursal()+"");
					intent.putExtras(bundle);
					
					startActivity(intent);
					
				}
			});
			
			ImageView mapImg= (ImageView)findViewById(R.id.mapImage);
			mapImg.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					
					Bundle bundle = new Bundle();
					//bundle.putString("search", tvSerach.getText().toString());
					bundle.putSerializable("item", item);
					//bundle.putParcelable("image", logo);
					bundle.putInt("type", Variables.LISTA);
					
					Intent intent = new Intent(ItemActivity.this,MapActivity.class);
					//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.putExtras(bundle);
					startActivity(intent);
					
					
				}
			});
			
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item, menu);
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
	public void callback(List<Item> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void callbackImage(Item items, LinearLayout itemLayout, Bitmap image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void callbackImages(List<Bitmap> images) {
		// TODO Auto-generated method stub
		
		//LinearLayout ln= (LinearLayout)findViewById(R.id.idContent);
		
		
		List<Bitmap> items= new ArrayList<Bitmap>();
		//final ImageView i= (ImageView)findViewById(R.id.currentImage);
		//i.setBackgroundDrawable(null);
		//ln.removeAllViews();
		boolean first=true;
		for(Bitmap bm : images)
		{
			Fragment_Image f= new Fragment_Image();
			items.add(bm);
		}
		AdapterVerGaleria adapter= new AdapterVerGaleria(getSupportFragmentManager(), items);
		
		ViewPager pager =(ViewPager)findViewById(R.id.gallery_item);
		pager.setAdapter(adapter);
		CirclePageIndicator  mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
	        mIndicator.setViewPager(pager);
	        ((CirclePageIndicator) mIndicator).setSnap(true);
	
		
	}
	
	
	public  void onBack(View v)
	{
		
		this.finish();
	}

}
