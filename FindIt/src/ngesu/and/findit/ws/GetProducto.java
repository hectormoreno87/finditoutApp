package ngesu.and.findit.ws;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.google.gson.Gson;

import ngesu.and.findit.CatalogoActivity;
import ngesu.and.findit.ItemActivity;
import ngesu.and.findit.intefaces.IGetProducto;
import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.Empresa;
import ngesu.and.findit.models.Item;
import ngesu.and.findit.models.ItemSerializable;
import ngesu.and.findit.models.SucursalModel;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class GetProducto extends AsyncTask<String, Void, Void> {

	ItemSerializable item=null;
	IGetProducto iGetP;
	String id;
	private ProgressDialog pd;
	private static final String NAMESPACE = "http://tempuri.org/";
	//private static final String URL = "http://192.168.1.27/injertos2/AndroidSync.asmx";
	private SoapSerializationEnvelope envelope;
	private static final String URL = "http://192.168.1.110/finditout/wsFindItOut.asmx";
	
	public  GetProducto(IGetProducto iGetP,  String  id) {
		
		this.id=id;
        this.iGetP=iGetP;
	}
	

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	pd = new ProgressDialog((ItemActivity)(GetProducto.this.iGetP));
	
	
    pd.setTitle("Sincronizando...");
    pd.setMessage("Por Favor Espere.");
    pd.setCancelable(false);
    pd.setIndeterminate(true);
    pd.show();
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		pd.setTitle("Finalizado");
		//pd.setMessage(result);
		pd.setIndeterminate(true);
        pd.dismiss(); 
		iGetP.callbackProducto(item);
		super.onPostExecute(result);
	}



	@Override
	protected Void doInBackground(String... params) {
		
			// TODO Auto-generated method stub
			
				String sResult="";
				try {
						sResult=callWebServiceGetProducto();
					    JSONArray array = new JSONArray(sResult);
					    Gson gson = new Gson();
					    
					    for (int i = 0; i < array.length(); i++) {
							JSONObject obj = array.getJSONObject(i);
							item=gson.fromJson(obj.toString(), ItemSerializable.class);
							
						}
						
					}
				catch(Exception ex){
					ex.printStackTrace();
					
				}
			
	        
	            
				return null;
	        
			
		
	}
	
	private String callWebServiceGetProducto() throws IOException, XmlPullParserException
	{
		String res="";
	
		try
		{
			String SOAP_ACTION = NAMESPACE+"GetProductoByID";
			SoapObject request = new SoapObject(NAMESPACE, "GetProductoByID");
			request.addProperty("id", id);
			envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE transporte = new HttpTransportSE(URL);
			transporte.call(SOAP_ACTION, envelope);
			SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
			res = resultado_xml.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return res;
	}
}
