package ngesu.and.findit;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import ngesu.and.findit.intefaces.IItems;
import ngesu.and.findit.models.Item;
import ngesu.and.findit.ws.GetImage;
import ngesu.and.findit.ws.GetProfileImage;
import ngesu.and.findit.ws.WebServiceManager;



import com.facebook.AppEventsLogger;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.OpenRequest;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import android.app.Activity;
import android.app.ActionBar;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends FragmentActivity {
	
	
	
	//////////////

	private Fragment mainFragment;
	
	/////////////

    
	private static Integer position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}else
		{
		
		mainFragment = (Fragment) getSupportFragmentManager()
		        .findFragmentById(android.R.id.content);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */



  
  
    
    
	public static class PlaceholderFragment extends Fragment implements IItems{

		
		private static final String TAG = "MainFragment";
		private UiLifecycleHelper uiHelper;
		View rootView;
		Fragment f;
		private Session.StatusCallback callback = new Session.StatusCallback() {
		    @Override
		    public void call(Session session, SessionState state, Exception exception) {
		        onSessionStateChange(session, state, exception);
		        
		    }
		    
		};
		
		
		public PlaceholderFragment() {
		}

		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			    uiHelper = new UiLifecycleHelper(getActivity(), callback);
			    uiHelper.onCreate(savedInstanceState);
			    f=this;
		}

		
	
		
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			
			rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			LoginButton authButton = (LoginButton) rootView.findViewById(R.id.login_button);
			authButton.setFragment(f);
			//authButton.setPublishPermissions("publish_actions","publish_stream");
			authButton.setReadPermissions(Arrays.asList("user_likes", "user_status","public_profile"));
			
			
			
			final String[] a={"100 m",
						"200 m",
						"500 m",
						"700 m",
						"1 km",
						"2 km",
						"5 km",
						"10 km",
						"20 km",
						"50 km"
						};
			position=4;
			
			final TextView distance = (TextView)rootView.findViewById(R.id.tvDistance);
			distance.setText(a[4]);
			ImageView less=(ImageView)rootView.findViewById(R.id.less);
			ImageView more=(ImageView)rootView.findViewById(R.id.more);
			less.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(position>0)
					{
						position--;
						distance.setText(a[position]);
					}
					// TODO Auto-generated method stub
				}
			});
			
			more.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(position<a.length-1)
					{
						position++;
						distance.setText(a[position]);
					}
					// TODO Auto-generated method stub
				}
			});
			
			ImageView search=(ImageView)rootView.findViewById(R.id.searchImage);
			final EditText tvSerach= (EditText)rootView.findViewById(R.id.tvSearch);
			search.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					// TODO Auto-generated method stub
					
						Bundle bundle = new Bundle();
						bundle.putString("search", tvSerach.getText().toString());
						bundle.putString("distance", distance.getText().toString().replace(" m","").replace(" k","000").replace("m",""));
						Intent intent = new Intent(PlaceholderFragment.this.getActivity(),Items.class);
						//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtras(bundle);
						startActivity(intent);

					
				}
			});
			
			
			
			/*NumberPicker numberPicker=(NumberPicker)rootView.findViewById(R.id.noDistance);
			numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
			numberPicker.setDisplayedValues(a);
			numberPicker.setMaxValue(14);
			numberPicker.setMinValue(0);
			numberPicker.setScaleX(.7f);
			numberPicker.setScaleY(.5f);*/
			
			
			return rootView;
		}
		
		
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			  Session session = Session.getActiveSession();
			  
			  /*OpenRequest open = new OpenRequest(this);
		        open.setLoginBehavior(SessionLoginBehavior.SUPPRESS_SSO);
		        open.setPermissions(Arrays.asList(new String[]{"email", "publish_actions", "user_birthday", "user_hometown"}));
		        open.setCallback(callback);
		        session.openForPublish(open);
*/			  
			  
			    if (session != null &&
			           (session.isOpened() || session.isClosed()) ) { 
			    	 if(session.isOpened())
						{
							 Request.newMeRequest(session, new Request.GraphUserCallback() {

						          

									@Override
									public void onCompleted(GraphUser user, Response response) {
										 if (user != null) {
								                TextView welcome = (TextView) rootView.findViewById(R.id.txtName);
								                welcome.setText(user.getName());
								                LinearLayout lly=(LinearLayout)rootView.findViewById(R.id.userdata);
								                GetProfileImage ws= new GetProfileImage(PlaceholderFragment.this,lly,null);   
								                ws.execute("https://graph.facebook.com/"+user.getId()+"/picture?type=large");
								              }
										
									}
						          }).executeAsync();
						}
			        onSessionStateChange(session, session.getState(), null);
			       
			    }

			    uiHelper.onResume();
			    
			   // Session session = Session.getActiveSession();
			    LinearLayout viewToAnimate=((LinearLayout)rootView.findViewById(R.id.userdata));
			    if((!session.isClosed()&&!session.isOpened())||session.isClosed())
			    {
			             Animation out = AnimationUtils.makeOutAnimation(this.getActivity(), true);
			             out.setDuration(2000);
			             viewToAnimate.startAnimation(out);
			             
			             
			             viewToAnimate.setVisibility(View.INVISIBLE);
			      
			      
			      
			    
			    }
			    /*else 
			    {
			        Animation in = AnimationUtils.loadAnimation(this.getActivity(), android.R.anim.fade_in);
		             viewToAnimate.startAnimation(in);
		             viewToAnimate.setVisibility(View.VISIBLE);
			    }*/
		}
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
		    super.onActivityResult(requestCode, resultCode, data);
		    uiHelper.onActivityResult(requestCode, resultCode, data);
		   
		   // Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
		}

		
		@Override
		public void onPause() {
		    super.onPause();
		    uiHelper.onPause();
		}

		@Override
		public void onDestroy() {
		    super.onDestroy();
		    uiHelper.onDestroy();
		}
		
		@Override
		public void onSaveInstanceState(Bundle outState) {
		    super.onSaveInstanceState(outState);
		    uiHelper.onSaveInstanceState(outState);
		}
		
		private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		    if (state.isOpened()) {
		        Log.i(TAG, "Logged in...");
		    } else if (state.isClosed()) {
		        Log.i(TAG, "Logged out...");
		        LinearLayout viewToAnimate =((LinearLayout)rootView.findViewById(R.id.userdata));
				
				
			            Animation out = AnimationUtils.makeOutAnimation(this.getActivity(), true);
			            viewToAnimate.startAnimation(out);
			            viewToAnimate.setVisibility(View.INVISIBLE);
		       
		    }
		    
		    
		    if((!session.isClosed()&&!session.isOpened())||session.isClosed())
		    {
		    	LinearLayout ln=((LinearLayout)rootView.findViewById(R.id.idLinearFace));
		    	LinearLayout lnuser=((LinearLayout)rootView.findViewById(R.id.lyFace));
		    	LoginButton b= (LoginButton)rootView.findViewById(R.id.login_button);
		    	lnuser.removeAllViews();
		    	if(ln.getChildCount()==0)
		    	ln.addView(b);
		    	
		    	
		    	
		    	
		    	
		    }
		    else 
		    {
		    	//((LinearLayout)rootView.findViewById(R.id.userdata)).setVisibility(View.VISIBLE);
		    	LinearLayout ln=((LinearLayout)rootView.findViewById(R.id.idLinearFace));
		    	LinearLayout lnuser=((LinearLayout)rootView.findViewById(R.id.lyFace));
		    	LoginButton b= (LoginButton)rootView.findViewById(R.id.login_button);
		    	ln.removeAllViews();
		    	if(lnuser.getChildCount()==0)
		    		lnuser.addView(b);
		    	
		    	
		    }
		}


		@Override
		public void callback(List<Item> items) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void callbackImage(Item items, LinearLayout itemLayout,
				Bitmap image) {
			image=getRoundedCornerBitmap(image,20);
			  BitmapDrawable ob = new BitmapDrawable(image);
				
			ImageView user_picture=(ImageView)itemLayout.findViewById(R.id.profileimage);
			user_picture.setImageBitmap(image);
			
			LinearLayout viewToAnimate =((LinearLayout)rootView.findViewById(R.id.userdata));
			
			
		            Animation in = AnimationUtils.loadAnimation(this.getActivity(), android.R.anim.fade_in);
		            viewToAnimate.startAnimation(in);
		            viewToAnimate.setVisibility(View.VISIBLE);
		        
		         /*   
		             make the API call 
		            Session session = Session.getActiveSession();
		            session.getPermissions().clear();
		            
		            Bundle params = new Bundle();
		            params.putString("message", "This is a test message from android SDK");
		             make the API call 
		            new Request(
		                session,
		                "/me/feed",
		                params,
		                HttpMethod.POST,
		                new Request.Callback() {
		                    public void onCompleted(Response response) {
		                    	int i=-0;
		                    	i++;
		                         handle the result 
		                    }
		                }
		            ).executeAsync();*/
			//user_picture.setBackgroundDrawable(ob);
			//item.setBitMam(image);
			//user_picture.setImageBitmap(image);
			// TODO Auto-generated method stub
			
		}

		 public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
		        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
		                .getHeight(), Config.ARGB_8888);
		        Canvas canvas = new Canvas(output);

		        final int color = 0xff424242;
		        final Paint paint = new Paint();
		        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		        final RectF rectF = new RectF(rect);
		        final float roundPx = pixels;

		        paint.setAntiAlias(true);
		        canvas.drawARGB(0, 0, 0, 0);
		        paint.setColor(color);
		        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		        canvas.drawBitmap(bitmap, rect, rect, paint);

		        return output;
		    }
		
		@Override
		public void callbackImages(List<Bitmap> image) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	

}
