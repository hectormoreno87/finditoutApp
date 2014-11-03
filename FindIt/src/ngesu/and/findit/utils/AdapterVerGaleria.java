package ngesu.and.findit.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.internal.ft;



import ngesu.and.findit.Fragment_Image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AdapterVerGaleria extends FragmentPagerAdapter {
FragmentManager fm;
	public AdapterVerGaleria(FragmentManager fm,List<Bitmap> dataList) {
		super(fm);
		this.fm=fm;
		this.dataList=dataList;
		this.ctx=fm;
		// TODO Auto-generated constructor stub
	}


	
	private Fragment fragment;
	private FragmentManager ctx;
	private List<Bitmap> dataList;

	

	@Override
	public int getCount() {
	    return dataList.size();
	}
	@Override
	public Fragment getItem(int pos) {
		
			 
		fragment = new Fragment_Image();
		
	
			Bundle args = new Bundle();
			((Fragment_Image) fragment).setImgBit(dataList.get(pos));
			
		 fragment.setArguments(args);
		 
	    return fragment;
	}

	
	@Override
	public void destroyItem(View collection, int position, Object view)
	{
	    ((ViewPager)collection).removeView((ImageView)view);

	}
	@Override
	public long getItemId(int pos) {
	    return pos;
	}



	/*@Override
	public boolean isViewFromObject(View view, Object object)
	{
	    // TODO Auto-generated method stub
	    return view == ((Fragment_Image)object);
	}
*/
}
