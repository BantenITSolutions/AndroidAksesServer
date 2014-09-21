package dlmbg.pckg.akses.server;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AksesServerActivity extends ListActivity {

	private static String link_url = "http://10.0.2.2/json-parsing/artikel-json.php";
	
	private static final String AR_ID = "id";
	private static final String AR_JUDUL = "judul";
	private static final String AR_CONTENT = "content";

	JSONArray artikel = null;
	ArrayList<HashMap<String, String>> daftar_artikel = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		JSONParser jParser = new JSONParser();

		JSONObject json = jParser.AmbilJson(link_url);

		try {
			artikel = json.getJSONArray("artikel");
			
			for(int i = 0; i < artikel.length(); i++){
				JSONObject ar = artikel.getJSONObject(i);
				
				String id = ar.getString(AR_ID);
				String judul = ar.getString(AR_JUDUL);
				String content = ar.getString(AR_CONTENT).substring(0,100)+"...(baca selengkapnya)";
				
				HashMap<String, String> map = new HashMap<String, String>();

				map.put(AR_ID, id);
				map.put(AR_JUDUL, judul);
				map.put(AR_CONTENT, content);

				daftar_artikel.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.adapter_listview();
	}
	
	public void adapter_listview() {

		ListAdapter adapter = new SimpleAdapter(this, daftar_artikel,
				R.layout.list_item,
				new String[] { AR_JUDUL, AR_CONTENT, AR_ID}, new int[] {
						R.id.judul, R.id.content, R.id.kode});

		setListAdapter(adapter);
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				String kode = ((TextView) view.findViewById(R.id.kode)).getText().toString();
				
				Intent in = new Intent(AksesServerActivity.this, DetailAksesServer.class);
				in.putExtra(AR_ID, kode);
				startActivity(in);

			}
		});


		
	}
}