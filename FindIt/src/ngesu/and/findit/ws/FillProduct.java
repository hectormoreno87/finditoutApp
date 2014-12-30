package ngesu.and.findit.ws;


import ngesu.and.findit.ItemActivity;
import ngesu.and.findit.Items;
import ngesu.and.findit.MapActivity;
import ngesu.and.findit.R;
import ngesu.and.findit.intefaces.IFillProduct;
import ngesu.and.findit.intefaces.IGetProducto;
import ngesu.and.findit.models.Item;
import ngesu.and.findit.utils.Screen;
import ngesu.and.findit.utils.Variables;


import org.ksoap2.serialization.SoapSerializationEnvelope;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FillProduct extends AsyncTask<String, Void, Void> {
	private IFillProduct fp;	
	private ProgressDialog pd;
	private Item item;
	private static final String NAMESPACE = "http://tempuri.org/";
	//private static final String URL = "http://192.168.1.27/injertos2/AndroidSync.asmx";
	private SoapSerializationEnvelope envelope;
	private static final String URL = "http://192.168.1.110/finditout/wsFindItOut.asmx";
	private LinearLayout itemLayout;
	public  FillProduct(IFillProduct fp, Item item) {
		this.fp=fp;
		this.item=item;
	}
	

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		//Items d;
		super.onPreExecute();
		//pd = new ProgressDialog(((Items)this.fp));
	    //pd.setTitle("Sincronizando...");
	    //pd.setMessage("Por Favor Espere.");
	    //pd.setCancelable(false);
	    //pd.setIndeterminate(true);
	    //pd.show();
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		//pd.setTitle("Finalizado");
		//pd.setMessage(result);
		//pd.setIndeterminate(true);
       // pd.dismiss(); 
		super.onPostExecute(result);
        fp.callback(itemLayout, item);
		
	}



	@Override
	protected Void doInBackground(String... params) {
		
			// TODO Auto-generated method stub
			
		try
		{
			
			itemLayout=(LinearLayout)LayoutInflater.from(((Items)this.fp)).inflate(R.layout.item, null);
			//itemLayout.setId(offset+(++count));
			LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams((int)(Screen.width),(int)(Screen.height*.2));
			//lp.setMargins((int)(Screen.width*.02), (int)(Screen.height*.03), (int)(Screen.width*.02),0);
			itemLayout.setLayoutParams(lp);
			fillItem(itemLayout,item);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
			
	        
	            
				return null;
	        
			
		
	}
	
	public void fillItem(final LinearLayout itemLayout,final Item item)
	{
		
		TextView txtfio=(TextView)itemLayout.findViewById(R.id.finditoutname);
		TextView itemName=(TextView)itemLayout.findViewById(R.id.itemName);
		TextView distanceItem=(TextView)itemLayout.findViewById(R.id.distanceItem);
		TextView priceItem=(TextView)itemLayout.findViewById(R.id.priceItem);
		txtfio.setText(item.getFinditoutName());
		itemName.setText(item.getName());
		distanceItem.setText(item.getDistance()+"");
		priceItem.setText(item.getCost()+"");
		//GetImage getImagews=new GetImage((Items)FillProduct.this.fp,itemLayout,item);
		//getImagews.execute(item.getImage());
		
		
		
		
		
	}

}
