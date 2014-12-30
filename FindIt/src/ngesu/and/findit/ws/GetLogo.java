package ngesu.and.findit.ws;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import ngesu.and.findit.intefaces.IGetLogo;
import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.Item;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GetLogo extends AsyncTask<String, Void, Void> {

	Bitmap map = null;
	LinearLayout itemLayout=null;
	Item item=null;
	IGetLogo caller;
	ImageView img;
	Integer type;
	public  GetLogo(IGetLogo caller) {
		type=0;
		this.caller=caller;
		
	}
	public  GetLogo(IGetLogo caller, ImageView img) {
		type=1;
		this.caller=caller;
		this.img=img;
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
		if(type==0)
		caller.callbackImage(map);
		else
		caller.callbackImage(map, img);
			
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
}
