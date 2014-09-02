package ngesu.and.findit.ws;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.Item;
import ngesu.and.findit.models.ItemSerializable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.LinearLayout;

public class GetImages extends AsyncTask<String, Void, Void> {

	List<Bitmap> lbm = null;
	
	ItemSerializable item=null;
	IItems iitems;
	
	
	public  GetImages(IItems iitems,  ItemSerializable item2) {
		
		lbm=new ArrayList<Bitmap>();
		this.iitems=iitems;
		this.item=item2;
        
	}
	
	
	

	@Override
	protected void onPreExecute() {
		lbm=new ArrayList<Bitmap>();
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		iitems.callbackImages(lbm);
		super.onPostExecute(result);
	}
	
	
	
	private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        try {
        InputStream stream = null;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
       // bmOptions.inSampleSize = 4;

        
            stream = getHttpConnection(url);
            bitmap = BitmapFactory.
                    decodeStream(stream, null, bmOptions);
            //bitmap=Bitmap.createScaledBitmap(bitmap, 50, 50, false);
            
            stream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;
    }

	
	private InputStream getHttpConnection(String urlString)
            throws IOException {
        InputStream stream = null;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        try {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stream;
    }




	@Override
	protected Void doInBackground(String... params) {
		
			// TODO Auto-generated method stub
			for(String s : params)
			{
				  lbm.add(downloadImage(s));  
			}
	        
	            
				return null;
	        
			
		
	}
}
