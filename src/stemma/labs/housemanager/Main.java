package stemma.labs.housemanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Main extends Activity  {
	
    private static RelativeLayout rl = null;
    
    private static ArrayList<RowType> rt ;
    private static CustomArrayAdapter caa;
    private static ListView lv = null;
    private static AlertDialog alertDialog = null;
    private static ArrayList<MenuType> mt;
    private static arrayAdapterDialog aad;
    private static ListView lv2 = null;
    
	private static DBClass db ;
	
	private static int rowID ;
	private static RowType rowType ;
	private static TextView tv_default;
	private static AlertDialog.Builder alertBuilder;

    
    
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		rowID =  0;
		
		rt = new ArrayList<RowType>();
		mt = new ArrayList<MenuType>();
		
		rl = (RelativeLayout) this.findViewById(R.id.relativeView1);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		if ( alertDialog!=null && alertDialog.isShowing() )
	        alertDialog.dismiss();
		
		//TODO: Add condition for first time usage or if no item is there.
		/********INSIDE THE IF CONDITION****/
		tv_default = new TextView(this);
		tv_default.setText("No item in the list. Click on Add to start clicking");
		tv_default.setTextColor(Color.GRAY);
		tv_default.setTextSize(22);
		ViewGroup.LayoutParams lp_tv = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
		tv_default.setLayoutParams(lp_tv);
		tv_default.setGravity(Gravity.CENTER);
		rl.addView(tv_default);
		
		lv = (ListView) this.findViewById(R.id.lv1);
		
		caa = new CustomArrayAdapter(this,R.layout.list_item, rt);
		lv.setAdapter(caa);
		/*FileInputStream fis = null;
		ObjectInputStream in;
		//ArrayList<RowType> one = null;		
		try {
			fis = mContext.openFileInput("arpit");
			in = null;
			try {
				in = new ObjectInputStream(fis);
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			try {
				rt = (ArrayList<RowType>)in.readObject();
			} catch (OptionalDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	Log.e("tag1:reading", rt.get(0).getName());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		if(fis!=null){
			caa = new CustomArrayAdapter(this,R.layout.list_item, rt);
			lv.setAdapter(caa);
		} */
		
		
		
		db = new DBClass(this);
		//Log.i("tag1","hello");
		
		while(db.readFromDB(rowID) != null)
			
		{
			//Log.d("tag","Helaas***o" );
			
			

			rl.removeView(tv_default);
			rt.add(db.readFromDB(rowID)) ;
			//Log.d("tag:main","rt read" + rt.get(rowID).getsno());
			caa = new CustomArrayAdapter(this,R.layout.list_item, rt);
			lv.setAdapter(caa);
			rowID ++;
		}
		
		
		Log.d("tag","Hell*******o" );
		alertBuilder = new AlertDialog.Builder(this);
		lv2  = new ListView(Main.this);
		addToAlert();
		lv2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		alertBuilder.setView(lv2);
		alertBuilder.setTitle("Choose a Category").setCancelable(false);
		alertBuilder.setPositiveButton("Save", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				rowType = new RowType(rowID, "Arpit", "Hello", 344, true);
				
				rt.add(rowType);
				//Log.v("tag1",rt.get(i1).getType());
				
				
				Log.d("tag1","***" + db.writeToDB(rowType));
				rowID ++ ;
				
				caa = new CustomArrayAdapter(getApplicationContext(),R.layout.list_item, rt);
			    lv.setAdapter(caa);
				dialog.dismiss();

				
			}
		});
		
		alertBuilder.setNegativeButton("Cancel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(rowID == 0)
					rl.addView(tv_default);
				
				
				
				dialog.dismiss();
				
			}
		});
		
		
		
		
		
	/*	Button b1 = (Button) findViewById(R.id.button1);
		
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(rowID ==0)
					rl.removeView(tv_default);

				lv2  = new ListView(Main.this);
				
				
				addToAlert();
				lv2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Main.this);
				alertBuilder.setView(lv2);
				alertBuilder.setTitle("Choose a Category").setCancelable(false);
				alertBuilder.setPositiveButton("Save", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						rowType = new RowType(rowID, "Arpit", "Hello", 344, true);
						
						rt.add(rowType);
						//Log.v("tag1",rt.get(i1).getType());
						
						
						Log.d("tag1","***" + db.writeToDB(rowType));
						rowID ++ ;
						
						caa = new CustomArrayAdapter(getApplicationContext(),R.layout.list_item, rt);
					    lv.setAdapter(caa);
						dialog.dismiss();

						
					}
				});
				
				alertBuilder.setNegativeButton("Cancel", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if(rowID == 0)
							rl.addView(tv_default);
						
						
						
						dialog.dismiss();
						
					}
				});
				
				AlertDialog alertDialog = alertBuilder.create();
				alertDialog.show();
				
				
				
				
				/*FileOutputStream fos = null;
		        ObjectOutputStream oos = null;*/
		        
		     /*   tt5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						
						// TODO Auto-generated method stub
						rt.get(i1).setSwitch(isChecked);
						
					}
				});*/
		        
		        
		        
				/*
				 * Using SAVING IN FILE
				 * 
				 * try {
					fos = mContext.openFileOutput ( "arpit", Context.MODE_PRIVATE );
					oos = new ObjectOutputStream ( fos );
					oos.writeObject (rt);
					oos.close();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
		       				
		        /*TableRow row= new TableRow(mContext);
		        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
		        lp.setMargins(10, 10, 10, 10);
		        row.setLayoutParams(lp);
		        
		        TableRow row1= new TableRow(mContext);
		        row1.setLayoutParams(lp);
		        tv_sno = new TextView(mContext);
		        tv_name = new TextView(mContext);
		        tv_timeout = new TextView(mContext);
		        tv_type = new TextView(mContext);
		        tv1 = new TextView(mContext);

		        if(i==0)
				{
					tv_sno.setText("S. No.");
					tv_name.setText("Name");
					tv_type.setText("Type");
					tv_timeout.setText("Timeout");
					tv1.setText("on/off");
					
					row1.addView(tv_sno);
					row1.addView(tv_name);
					row1.addView(tv_type);
					row1.addView(tv_timeout);
					row1.addView(tv1);
					tl.addView(row1,i);
					i = i+1;
					
				}
		        tv_sno = new TextView(mContext);
		        tv_name = new TextView(mContext);
		        tv_timeout = new TextView(mContext);
		        tv_type = new TextView(mContext);
		        sw = new Switch(mContext);
				
				// TODO Auto-generated method stub
				
		        tv_sno.setText(Integer.toString(i/2 + 1));
		        tv_name.setText("Name"); //TODO
		        tv_type.setText("type"); // TODO
		        tv_timeout.setText("timout"); //TODO
		        sw.setTextOff("Off");
		        sw.setTextOn("on");
		        
				tv_sno.setMinimumWidth(125);
				tv_name.setMinimumWidth(200);
				tv_type.setMinimumWidth(200);
				tv_timeout.setMinimumWidth(200);
				
		        
		
		        
				row.addView(tv_sno);
				row.addView(tv_name);
				row.addView(tv_type);
				row.addView(tv_timeout);
				row.addView(sw);
		        row.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
					}
				});
		        row.setLongClickable(true);
		        row.setOnLongClickListener(new View.OnLongClickListener() {
					
					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub
						TableRow SelectedRow;
						
			             
			             SelectedRow = (TableRow)v;

			             SelectedRow.setBackgroundColor(Color.GREEN);
			             //AlreadySelectedRow = v.getId();
			           // showPopupMenu(v);
			            //return false;
						return false;
					}
				});
				
		        tl.addView(row,i);
		        
		        i = i+1;
		        /*View ruler = new View(mContext); ruler.setBackgroundColor(0xFF00FF00);
		        tl.addView(ruler,
		         new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, 2));*/
		     /*   View v1= new View(mContext);
		        TableLayout.LayoutParams lp2 = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 1);
		        //lp2.setMargins(0,0,0,0);
		        
		        v1.setLayoutParams(lp2);
		        v1.setBackgroundColor(Color.GRAY);
		        tl.addView(v1,i);
		        i=i+1;*/
				
			/*	
				
			}
		});
		
	*/	
	}
	
	private void addToAlert ()
	{
		
		if(mt != null)
			mt.clear();
		
		mt.add(new MenuType("Lock your Door","ic_launcher"));
		mt.add(new MenuType("Off your Geyser","ic_launcher"));
		mt.add(new MenuType("Call somebody","ic_launcher"));
		mt.add(new MenuType("wish birthday","ic_launcher"));
		mt.add(new MenuType("Take the pills","ic_launcher"));
		mt.add(new MenuType("Miscellaneous","ic_launcher"));
		
		aad = new arrayAdapterDialog(getApplicationContext(), R.layout.reminder_category, mt);
		lv2.setAdapter(aad);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem Item){
		switch (Item.getItemId()) {
		case R.id.action_add:
			if(rowID ==0)
				rl.removeView(tv_default);
			 	
				AlertDialog alertDialog = alertBuilder.create();
				alertDialog.show();
				

			
			
			break;

		default:
			break;
		}
		return true;
		
	}
	
	@Override
	public void onDestroy(){
	    super.onDestroy();
	    if ( alertDialog!=null && alertDialog.isShowing() ){
	        alertDialog.dismiss();
	    }
	}
	
	

}
