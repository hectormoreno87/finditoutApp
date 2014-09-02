package ngesu.and.findit;


import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	private static Integer position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
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
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
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
		
		
		
		
	}
	
	

}
