package ngesu.and.findit;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import ngesu.and.findit.adapters.ItemAdapter;
import ngesu.and.findit.intefaces.CallbackSucursal;
import ngesu.and.findit.intefaces.ICatalogo;
import ngesu.and.findit.intefaces.IGetImageProducto;
import ngesu.and.findit.intefaces.IGetLogo;
import ngesu.and.findit.models.Categoria;
import ngesu.and.findit.models.Empresa;
import ngesu.and.findit.models.Producto;
import ngesu.and.findit.utils.AdapterItem;
import ngesu.and.findit.utils.Screen;
import ngesu.and.findit.utils.Variables;
import ngesu.and.findit.ws.GetImage;
import ngesu.and.findit.ws.GetImageProducto;
import ngesu.and.findit.ws.GetLogo;
import ngesu.and.findit.ws.WebServiceManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class CatalogoActivity extends Activity implements ICatalogo, IGetLogo, IGetImageProducto{

	Empresa empresa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalogo);
		
		View title = getWindow().findViewById(android.R.id.title);
        View titleBar = (View) title.getParent();
        
        titleBar.setBackgroundColor(getResources().getColor(R.color.red));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		WebServiceManager ws= new WebServiceManager((ICatalogo)this);
	    String idSucursal= this.getIntent().getExtras().getString("idSucursal");
	    try {
	    	ws.execute("getInfoBySucursal",idSucursal);
	    	
			} catch (Exception e) {
		
				e.printStackTrace();
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.catalogo, menu);
		return true;
	}

	
	public void fillCatalogo()
	{
		TextView findOutName= (TextView)findViewById(R.id.findItOutName);
		TextView sucursal= (TextView)findViewById(R.id.sucursal);
		TextView domicilio= (TextView)findViewById(R.id.domicilio);
		
		findOutName.setText(empresa.getvEmpresa());
		sucursal.setText(empresa.getSucursal().getvNombreSuc());
		domicilio.setText(empresa.getSucursal().getvDirecc());
		
		Spinner sp= (Spinner)findViewById(R.id.spCategoria);
		List<AdapterItem> items= new ArrayList<AdapterItem>();
		AdapterItem sel= new AdapterItem();
		sel.setItem("--Selecione--");
		sel.setValue("0");
		items.add(sel);
		for(Categoria c : empresa.getCategorias())
		{
			AdapterItem i= new AdapterItem();
			i.setItem(c.getNombre());
			i.setValue(c.getIdCategoria()+"");
			items.add(i);
			
		}
		final ItemAdapter adapter= new ItemAdapter(this, items);
		sp.setAdapter(adapter);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				AdapterItem it=adapter.getItem(position);
				LinearLayout lly= (LinearLayout)findViewById(R.id.itemsContent);
				lly.removeAllViews();
				fillProductos(it.getValue());
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
				
			}
		});
		
		//logo
		new GetLogo(this).execute(getString(R.string.urlimages)+empresa.getIdEmpresa()+"/logoCh.png");
		
		ImageView btnInfo = (ImageView)findViewById(R.id.btnInfo);
		btnInfo.setOnClickListener( new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
			
				Bundle bundle = new Bundle();
				//bundle.putString("search", tvSerach.getText().toString());
				//bundle.putSerializable("item", this.getCopySerializable(selectedItem));
				Intent intent = new Intent(CatalogoActivity.this,InfoActivity.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				bundle.putString("idSucursal", empresa.getSucursal().getIdSucursal()+"");
				intent.putExtras(bundle);
				
				startActivity(intent);
				
			}
		});
		
		ImageView mapImg= (ImageView)findViewById(R.id.mapImage);
		mapImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				Bundle bundle = new Bundle();
				//bundle.putString("search", tvSerach.getText().toString());
				bundle.putSerializable("empresa", empresa);
				//bundle.putParcelable("image", logo);
				bundle.putInt("type", Variables.Catalogo);
				
				Intent intent = new Intent(CatalogoActivity.this,MapActivity.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtras(bundle);
				startActivity(intent);
				
				
			}
		});
		
		
	}
	
	public void fillProductos(String idCategoria)
	{
		
		for(Categoria c : empresa.getCategorias())
		{
			if(c.getIdCategoria().toString().equals(idCategoria))
			{
				
				for(Producto p : c.getProductos())
				{
					if(p.getImagenesProducto().size()>0)
					new GetImageProducto(this, p).execute(getString(R.string.urlimages)+p.getImagenesProducto().get(0).getUrl());
				}
				
			}
		}
		
		
	}
	
	@Override
	public void callback(Empresa s) {
		this.empresa=s;
		fillCatalogo();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void callbackImage(Bitmap image) {
		
		//logo=image;
		ImageView img= (ImageView)findViewById(R.id.logo);
		BitmapDrawable ob = new BitmapDrawable(image);
		/*LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT,.25f);
		lp.setMargins((int)(Screen.width*.02), (int)(Screen.height*.02), (int)(Screen.width*.02), (int)(Screen.height*.02));
		img.setLayoutParams(lp);*/
		img.setBackgroundDrawable(ob);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void callbackImageProducto(Bitmap image, final Producto p) {
		
		LinearLayout lly= (LinearLayout)findViewById(R.id.itemsContent);
		View v= LayoutInflater.from(this).inflate(R.layout.item_catalog, null);
		TextView txtN= (TextView)v.findViewById(R.id.txtProducto);
		TextView txtD= (TextView)v.findViewById(R.id.txtDescription);
		TextView txtCosto= (TextView)v.findViewById(R.id.txtCosto);
		txtCosto.setText("$ "+p.getPrecio());
		txtN.setText(p.getNombre());
		
		if(p.getDescripcion().length()>100)
		txtD.setText(p.getDescripcion().substring(0, 100)+"...");
		else
		txtD.setText(p.getDescripcion());
			
		ImageView img= (ImageView)v.findViewById(R.id.imgCatalog);
		BitmapDrawable ob = new BitmapDrawable(image);
		
		//LinearLayout.LayoutParams lp1= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int)(Screen.height*.2));
		
		
		
		LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int)(Screen.height*.18));
		//lp.setMargins((int)(Screen.width*.01), (int)(Screen.height*.01), (int)(Screen.width*.01), (int)(Screen.height*.01));
		//lp.setMargins((int)(Screen.width*.3), (int)(Screen.height*.2), (int)(Screen.width*.02), (int)(Screen.height*.02));
		//img.setLayoutParams(lp);
		img.setBackgroundDrawable(ob);
		v.setLayoutParams(lp);
		
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				//bundle.putString("search", tvSerach.getText().toString());
				//bundle.putString("distance", distance.getText().toString().replace(" m","").replace(" k","000").replace("m",""));
				Intent intent = new Intent(CatalogoActivity.this,ItemActivity.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//bundle.putSerializable("item",getCopySerializable(item));
				bundle.putString("idProducto",p.getIdProducto()+"");
				
				intent.putExtras(bundle);
				
				startActivity(intent);
				
			}
		});
		
		lly.addView(v);
	}

	public  void onBack(View v)
	{
		
		this.finish();
	}

	@Override
	public void callbackImage(Bitmap image, ImageView v) {
		// TODO Auto-generated method stub
		
	}


}
