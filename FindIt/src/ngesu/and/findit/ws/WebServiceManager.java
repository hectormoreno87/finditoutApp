package ngesu.and.findit.ws;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ngesu.and.findit.InfoActivity;
import ngesu.and.findit.Items;
import ngesu.and.findit.Sucursal;
import ngesu.and.findit.intefaces.CallbackSucursal;
import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.Item;
import ngesu.and.findit.models.SucursalModel;

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
	private static final String URL = "http://192.168.6.110/finditout/wsFindItOut.asmx";
	IItems itemsInterface;
	CallbackSucursal sucursal;
	List<Item> items;
	SucursalModel _sucursalModel;
	String type;
	public WebServiceManager(IItems itemsInterface)
	{
		try
		{		
			type="GetInfo";
			items= new ArrayList<Item>();
			this.itemsInterface=itemsInterface;
			envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public WebServiceManager(CallbackSucursal sucursalInterface)
	{
		try
		{	
			type="GetSucursalByID";
			items= new ArrayList<Item>();
			this.sucursal=sucursalInterface;
			envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public WebServiceManager(CallbackSucursal sucursalInterface, Boolean fromInfo)
	{
		try
		{	
			type="GetSucursalByIDForInfo";
			items= new ArrayList<Item>();
			this.sucursal=sucursalInterface;
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
	
	
	private String callWebServiceGetSucursal(String method, String idSucursal) throws IOException, XmlPullParserException
	{
		String res="";
	
		try
		{
			String SOAP_ACTION = NAMESPACE+method;
			SoapObject request = new SoapObject(NAMESPACE, method);
			request.addProperty("idSucursal", idSucursal);
			
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
			else if(params[0]=="GetSucursalByID")
			{
				sResult=callWebServiceGetSucursal(params[0],params[1]);
			    JSONArray array = new JSONArray(sResult);
			    Gson gson = new Gson();
			    
			    for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					_sucursalModel=gson.fromJson(obj.toString(), SucursalModel.class);
					
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
    	
    	if(type.compareTo("GetInfo")==0)
		{
			
    		pd = new ProgressDialog((Items)(WebServiceManager.this.itemsInterface));
			
		}
		else if(type.compareTo("GetSucursalByID")==0)
		{
			
			pd = new ProgressDialog((Sucursal)(WebServiceManager.this.sucursal));
		}
		else if(type.compareTo("GetSucursalByIDForInfo")==0)
		{
			
			pd = new ProgressDialog((InfoActivity)(WebServiceManager.this.sucursal));
		}
    	
    	
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
		if(type.compareTo("GetInfo")==0)
		{
			
			itemsInterface.callback(items);
			
		}
		else if(type.compareTo("GetSucursalByID")==0)
		{
			
			sucursal.callback(_sucursalModel);
		}
		else if(type.compareTo("GetSucursalByIDForInfo")==0)
		{
			
			sucursal.callback(_sucursalModel);
		}
		
		
		
    }
}

