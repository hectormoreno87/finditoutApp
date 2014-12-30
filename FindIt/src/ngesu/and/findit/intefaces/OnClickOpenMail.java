package ngesu.and.findit.intefaces;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickOpenMail implements OnClickListener {
	String subject;
	Activity activity;
	public OnClickOpenMail(Activity activity ,String subject)
	{
		this.subject=subject;
		this.activity=activity;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri data = Uri.parse("mailto:?subject=" + this.subject);
		intent.setData(data);
		activity.startActivity(intent);
		

	}

}
