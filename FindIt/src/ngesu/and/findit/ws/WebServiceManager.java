package ngesu.and.findit.ws;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ngesu.and.findit.Items;
import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;


import com.google.gson.Gson;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class WebServiceManager extends AsyncTask<String, Integer, String> {

	
	
	private static final String NAMESPACE = "http://tempuri.org/";
	//private static final String URL = "http://192.168.1.27/injertos2/AndroidSync.asmx";
	private SoapSerializationEnvelope envelope;
	private static final String URL = "http://192.168.1.110/finditout/wsFindItOut.asmx";
	IItems itemsInterface;
	List<Item> items;
	public WebServiceManager(IItems itemsInterface)
	{
		try
		{		
			items= new ArrayList<>();
			this.itemsInterface=itemsInterface;
			envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	private String callWebServiceGetItems(String search, String latitude, String longitude, String methodName, String distance) throws IOException, XmlPullParserException
	{
		String res="";
	
		try
		{
			String SOAP_ACTION = NAMESPACE+methodName;
			SoapObject request = new SoapObject(NAMESPACE, methodName);
			request.addProperty("search", search);
			request.addProperty("latitud", latitude);
			request.addProperty("longitude", longitude);
			request.addProperty("distance", distance);
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
	
	


	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String sResult="";
		try {
			
			if(params[0]=="GetInfo")
			{
				
				sResult=callWebServiceGetItems(params[1],params[2], params[3], params[4], params[5]);
			    JSONArray array = new JSONArray(sResult);
			    Gson gson = new Gson();
			    
			    for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					Item item=gson.fromJson(obj.toString(), Item.class);
					items.add(item);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sResult;
	}
	
	
	private ProgressDialog pd;

    @Override
    protected void onPreExecute() {
    	pd = new ProgressDialog((Items)(WebServiceManager.this.itemsInterface));
        pd.setTitle("Sincronizando...");
        pd.setMessage("Por Favor Espere.");
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        
     
        
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        pd.show();
    }
	
	@Override
	protected void onPostExecute(String result) {
		pd.setTitle("Finalizado");
		//pd.setMessage(result);
		pd.setIndeterminate(true);
        pd.dismiss(); 
		int duration = Toast.LENGTH_SHORT;
		//Toast toast = Toast.makeText((Items)(WebServiceManager.this.itemsInterface), result, duration);
		//toast.show();
		itemsInterface.callback(items);
		
    }
}

