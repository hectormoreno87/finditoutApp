package ngesu.and.findit.intefaces;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickOpenBrowser implements OnClickListener {

	String url;
	Activity activity;
	public OnClickOpenBrowser(Activity activity,String url)
	{
		this.url=url;
		this.activity=activity;
	}
	@Override
	public void onClick(View v) {
		
		if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://" + url;
		
		Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		this.activity.startActivityForResult(openUrlIntent,1);
		
		
		// TODO Auto-generated method stub

	}

}
