package ngesu.and.findit;





import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public  class Fragment_Image extends Fragment {

	ImageView img;
	Bitmap bm;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_image,
				container, false);
		
		img=(ImageView)rootView.findViewById(R.id.image1);
		BitmapDrawable ob = new BitmapDrawable(bm);
		img.setBackgroundDrawable(ob);
		
		return rootView;
	}
	public ImageView getImg() {
		return img;
	}
	public void setImg(ImageView img) {
		this.img = img;
	}

	public void setImgBit(Bitmap bm) {
		this.bm=bm;
		
		
	}
	
	
	
	
	

}
