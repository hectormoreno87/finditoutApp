package ngesu.and.findit.intefaces;

import java.util.List;

import android.graphics.Bitmap;
import android.widget.LinearLayout;

import ngesu.and.findit.models.Item;


public interface IItems {
	
public void callback(List<Item> items);
public void callbackImage(Item items, LinearLayout itemLayout,Bitmap image);
public void callbackImages(List<Bitmap> image);

	
}
