package stemma.labs.housemanager;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class arrayAdapterDialog extends ArrayAdapter<MenuType> {
	
	private Context context;
	private ArrayList<MenuType> objects;
	private static ImageView iv;
	private static TextView tv;

	public arrayAdapterDialog(Context context, int resource,
			ArrayList<MenuType> objects) {
		super(context, resource, objects);
		this.context = context;
		this.objects = objects;
	}
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.reminder_category, null);
		
		iv = (ImageView) v.findViewById(R.id.imageView);
		tv = (TextView) v.findViewById(R.id.textView);
		
		MenuType menuType = objects.get(position);
		
		if(menuType != null) {
			tv.setText(menuType.Category);
			iv.setImageResource(context.getResources().getIdentifier(menuType.Image, "drawable", context.getPackageName()));
		}
		
		
		
		
		
		return v;
	}
	
	
}
