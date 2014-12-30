package ngesu.and.findit.intefaces;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class IAddNumber implements OnClickListener {

	Boolean sendWhatsApp;
	String number;
	FragmentActivity  root;
	ICallbackAddNumber parent;
	
	public IAddNumber(FragmentActivity root,Boolean sendWhatsApp, String number,ICallbackAddNumber parent)
	{
		this.sendWhatsApp=sendWhatsApp;
		this.number=number;
		this.root=root;
		this.parent=parent;
	}
	@Override
	public void onClick(View v) {

		Cursor cur=null;
		Cursor pCur=null;
		Boolean go=true;;
		Integer estatus=0; 
		try
		{
			ContentResolver cr = root.getContentResolver();
			cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
			if (cur.getCount() > 0) {
				
				while (cur.moveToNext()&&go) {
					
					String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
					String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
						//Query phone here.
						if(pCur!=null)
						{
							pCur.close();
						}
						pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,	null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",	new String[]{id}, null);

						while (pCur.moveToNext()&&go) {

							if(pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).compareTo(number)==0)
							{
								estatus=1;
								go=false;
							
								
							}
						
						} 
						


					}

				}
			}
			
			this.parent.addNumberCallback(estatus,number);
		}catch(Exception ex)
		{
			ex.printStackTrace();

		}
		finally
		{
			if(pCur!=null)
			{
				pCur.close();
			}
			if(cur!=null)
			{
				cur.close();
			}
			
		}
		// TODO Auto-generated method stub

	}

}
