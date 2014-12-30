package ngesu.and.findit.intefaces;



import android.graphics.Bitmap;
import android.widget.ImageView;

public interface IGetLogo {
	
	public void callbackImage(Bitmap image);
	public void callbackImage(Bitmap image,ImageView v);
	

}
