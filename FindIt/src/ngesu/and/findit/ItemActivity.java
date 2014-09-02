package ngesu.and.findit;


import java.util.List;

import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.Item;
import ngesu.and.findit.models.ItemSerializable;
import ngesu.and.findit.ws.GetImages;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

public class ItemActivity extends Activity implements IItems {

	ItemSerializable item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		View title = getWindow().findViewById(android.R.id.title);
        View titleBar = (View) title.getParent();
        titleBar.setBackgroundColor(getResources().getColor(R.color.red));
        
        
		
		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		item=(ItemSerializable)bundle.getSerializable("item");
		String[] imgs=item.getAllImages().split("@@");
		new GetImages(this, item).execute(imgs);
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
		
		LinearLayout ln= (LinearLayout)findViewById(R.id.idContent);
		final ImageView i= (ImageView)findViewById(R.id.currentImage);
		i.setBackgroundDrawable(null);
		ln.removeAllViews();
		boolean first=true;
		for(Bitmap bm : images)
		{
			
			final ImageView iv= new ImageView(this);
			iv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					i.setBackgroundDrawable(iv.getBackground());
				}
			});
			iv.setBackgroundDrawable(new BitmapDrawable(getResources(), bm));
			if(first)
			{
				first=false;
				i.setBackgroundDrawable(new BitmapDrawable(getResources(), bm));
			}
			ln.addView(iv);
		}
		
	}

}
