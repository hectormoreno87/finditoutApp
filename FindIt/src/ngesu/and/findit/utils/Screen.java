package ngesu.and.findit.utils;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Screen {
	
	
	
	 public static int widthInDp;
     public static int heightInDp;
     public static int width;
     public static int height;
     public static int defaultsizeY=1317; 
     public static int defaultsizeX=1024;
     public static float porcentaje;
     
     
	public static void initialize(Display display)
	{
		DisplayMetrics dm = new DisplayMetrics();
		display.getMetrics(dm);
		width=display.getWidth();
		height=display.getHeight();
		widthInDp = ConvertPixelsToDp(dm.widthPixels,dm );
		heightInDp = ConvertPixelsToDp(dm.heightPixels,dm);
		porcentaje=(heightInDp/(float)(defaultsizeY));
	}
	
	private static float textSize(float textSize)
	{
		/*if(heightInDp<700)
		{
			return (int)((heightInDp*textSize)/defaultsizeY)-4;
		}
		else
		{*/
			return (int)((heightInDp*textSize)/defaultsizeY);
		//}
	}
	
	private static int ConvertPixelsToDp(float pixelValue,DisplayMetrics dp)
	{
	    int dp_ = (int) ((pixelValue)/dp.density);
	    return dp_;
	}
	
	public static void resizeTextView(LinearLayout v)
	{
		
		for( int i = 0; i < v.getChildCount(); i++ )
		  if( v.getChildAt( i ) instanceof TextView )
		  {
			  float size=((TextView)v.getChildAt( i )).getTextSize();
			 ((TextView)v.getChildAt( i )).setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize(size));
		  }
		  else if( v.getChildAt( i ) instanceof Button )
		  {
			  float size=((Button)v.getChildAt( i )).getTextSize();
			 ((Button)v.getChildAt( i )).setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize(size));
		  }
		  else
		  {
			  reubic(v.getChildAt( i ));
			  
		  }
			  
	}
	
	
	
	
	
	public static void resizeTextView(HorizontalScrollView v)
	{
		
		for( int i = 0; i < v.getChildCount(); i++ )
			 if( v.getChildAt( i ) instanceof TextView )
			  {
				  float size=((TextView)v.getChildAt( i )).getTextSize();
				 ((TextView)v.getChildAt( i )).setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize(size));
			  }
			  else if( v.getChildAt( i ) instanceof Button )
			  {
				  float size=((Button)v.getChildAt( i )).getTextSize();
				 ((Button)v.getChildAt( i )).setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize(size));
			  }
		  else
		  {
			  reubic(v.getChildAt( i ));
		  }
			  
	}
	
	public static void resizeTextView(View v)
	{
		try
		{
		for( int i = 0; i < ((ViewGroup)v).getChildCount(); i++ )
		  if(((ViewGroup)v).getChildAt( i ) instanceof TextView )
		  {
			  float size=((TextView)((ViewGroup)v).getChildAt( i )).getTextSize();
			 ((TextView)((ViewGroup)v).getChildAt( i )).setTextSize(textSize(size));
		  }
		  else
		  {
			  reubic(((ViewGroup)v).getChildAt( i ));
		  }
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
			  
	}
	
	
	
	public static void resizeTextView(ScrollView v)
	{
		
		for( int i = 0; i < v.getChildCount(); i++ )
			if( v.getChildAt( i ) instanceof TextView )
			  {
				  float size=((TextView)v.getChildAt( i )).getTextSize();
				 ((TextView)v.getChildAt( i )).setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize(size));
			  }
			  else if( v.getChildAt( i ) instanceof Button )
			  {
				  float size=((Button)v.getChildAt( i )).getTextSize();
				 ((Button)v.getChildAt( i )).setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize(size));
			  }
		  else
		  {
			  reubic(v.getChildAt( i ));
		  }
			  
	}


	

	
	public static void reubic(View v)
	{
		if(v instanceof LinearLayout)
		  {
			  resizeTextView((LinearLayout)v);
		  }
		  else if( v instanceof ScrollView)
		  {
			  resizeTextView((ScrollView)v);
		  }
		  else if( v instanceof ScrollView)
		  {
			  resizeTextView((HorizontalScrollView)v);
		  }
		  else if( v instanceof View)
		  {
			  resizeTextView((View)v);
		  }
	}
	

}
