package stemma.labs.housemanager;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<RowType> {
	
	private ArrayList<RowType> objects;
	private static DBClass db ;
	
	public CustomArrayAdapter(Context context, int a, ArrayList<RowType> objects){
		super(context,a,objects);
		this.objects = objects;
		db = new DBClass(context);
	}

	public View getView(final int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item, null);
			//Log.d("tag","i m here");
			
		//Log.e("tag1",position +" :Hello:"+objects.get(position).getType());
		RowType rT = objects.get(position);

		if(rT !=null)
		{
			TextView tt1 = (TextView) v.findViewById(R.id.SNO);
			TextView tt2 = (TextView) v.findViewById(R.id.NAME);
			TextView tt3 = (TextView) v.findViewById(R.id.TYPE);
			TextView tt4 = (TextView) v.findViewById(R.id.TIMEOUT);
			Switch tt5 = (Switch) v.findViewById(R.id.Toggle_switch);
			
			
			
			if(tt1 !=null)
			{
				tt1.setText(Integer.toString(rT.getsno()));				
			}
			
			if(tt2 !=null)
			{
				tt2.setText(rT.getName());
				
			}

			if(tt3 !=null)
			{
				tt3.setText(rT.getType());
				
			}

			if(tt4 !=null)
			{
				tt4.setText(Long.toString(rT.getTimeout()));
				
			}

			if(tt5 !=null)
			{
				tt5.setChecked(rT.getSwitch());
				
			}
			tt5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					
					// TODO Auto-generated method stub
					Log.i("tag",Boolean.toString(isChecked) + "  SWITCH  " + position);	
					db.updateTable_switch(isChecked, position);
					//Log.d("tag1","Switch is toggled");
					
				}
			});


		}
		return v;
		
	}

}