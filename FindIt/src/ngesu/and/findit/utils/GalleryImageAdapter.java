package ngesu.and.findit.utils;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryImageAdapter extends BaseAdapter {

	 private Context mContext;

	  List<Bitmap> list;

	    public GalleryImageAdapter(Context context, List<Bitmap> list) 
	    {
	        mContext = context;
	        this.list=list;
	    }

	    public int getCount() {
	        return list.size();
	    }

	    public Bitmap getItem(int position) {
	        return list.get(position);
	    }

	    public long getItemId(int position) {
	        return position;
	    }


	    // Override this method according to your need
	    public View getView(int index, View view, ViewGroup viewGroup) 
	    {
	        // TODO Auto-generated method stub
	        ImageView i = new ImageView(mContext);
	        BitmapDrawable bitmapDrawable = new BitmapDrawable(list.get(index));
	        Drawable drawable = (Drawable)bitmapDrawable;
	   
	      
	        i.setLayoutParams(new Gallery.LayoutParams(200, 200));
	    
	        i.setScaleType(ImageView.ScaleType.FIT_XY);

	        return i;
	    }
}
