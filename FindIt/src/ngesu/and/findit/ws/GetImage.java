package ngesu.and.findit.ws;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.Item;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.LinearLayout;

public class GetImage extends AsyncTask<String, Void, Void> {

	Bitmap map = null;
	LinearLayout itemLayout=null;
	Item item=null;
	IItems iitems;
	public  GetImage(IItems iitems, LinearLayout ly, Item item) {
		
		this.iitems=iitems;
		this.item=item;
        this.itemLayout=ly;
	}
	
	public  GetImage(IItems iitems, LinearLayout ly) {
		
		this.iitems=iitems;
        this.itemLayout=ly;
	}
	
	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		
        
        map = downloadImage((String)(params[0]));       
			return null;
        
		
	}
	

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		iitems.callbackImage(item, itemLayout, map);
		super.onPostExecute(result);
	}
	
	
	
	private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        try {
        InputStream stream = null;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 4;

        
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
}
