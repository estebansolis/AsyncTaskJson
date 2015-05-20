package learn2crack.asynctask;

import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import learn2crack.asynctask.library.JSONParser;


public class MainActivity extends Activity {
    public static String EXTRA_MESSAGE = "HOlA";
    public TextView uid;
	public Button getDataButton;
    public Button sendButton;


	//URL to get JSON Array
	private static String URL = "http://api.icndb.com/jokes/random";

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessage.class);
        EditText editText= (EditText) findViewById(R.id.edittext);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_main);
        getDataButton = (Button)findViewById(R.id.getdata);
        getDataButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new JSONParse().execute();

            }
        });
        sendButton = (Button)findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sendMessage(view);
            }
        });
        
        
    }

    
    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            uid = (TextView) findViewById(R.id.uid);
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting Data ....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(URL);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            String x = json.toString();
            uid.setText(x);

        }
    }
    }

