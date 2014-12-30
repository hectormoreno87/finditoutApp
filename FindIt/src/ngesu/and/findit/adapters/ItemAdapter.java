package ngesu.and.findit.adapters;

import java.util.List;

import ngesu.and.findit.R;
import ngesu.and.findit.utils.AdapterItem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter implements SpinnerAdapter{
    private Activity activity;
    private List<AdapterItem> list_bsl; 

    public ItemAdapter(Activity activity, List<AdapterItem> list_bsl){
        this.activity = activity;
        this.list_bsl = list_bsl;
    }

    public int getCount() {
        return list_bsl.size();
    }

    public AdapterItem getItem(int position) {
        return list_bsl.get(position);
    }

    public long getItemId(int position) {
    	
        return Long.parseLong(list_bsl.get(position).getValue());
    
    }
    
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {

    View spinView;
    if( convertView == null ){
        LayoutInflater inflater = activity.getLayoutInflater();
        spinView = inflater.inflate(R.layout.spin_layout, null);
    } else {
         spinView = convertView;
    }
    TextView t1 = (TextView) spinView.findViewById(R.id.field1);
    //TextView t2 = (TextView) spinView.findViewById(R.id.field2);
    t1.setText(String.valueOf(list_bsl.get(position).getItem()));
    //t2.setText(list_bsl.get(position).getName());
    return spinView;
    }



}
