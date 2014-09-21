package dlmbg.pckg.akses.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailAksesServer  extends Activity {
	
	private static final String AR_ID = "id";
	JSONArray artikel = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        Intent in = getIntent();
        String kode = in.getStringExtra(AR_ID);
    	String link_url = "http://10.0.2.2/json-parsing/detail-artikel.php?kode="+kode;
        
        JSONParser jParser = new JSONParser();

		JSONObject json = jParser.AmbilJson(link_url);

		try {
			artikel = json.getJSONArray("artikel");
			
			for(int i = 0; i < artikel.length(); i++){
				JSONObject ar = artikel.getJSONObject(i);
				

		        TextView judul = (TextView) findViewById(R.id.judul);
		        TextView detail = (TextView) findViewById(R.id.detail);
		        TextView isi = (TextView) findViewById(R.id.isi);

				String judul_d = ar.getString("judul");
				String detail_d = ar.getString("tanggal")+" - "+ar.getString("waktu")+" - "+ar.getString("penulis");
				String isi_d = ar.getString("content");

		        judul.setText(judul_d);
		        detail.setText(detail_d);
		        isi.setText(isi_d);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
    }
}
