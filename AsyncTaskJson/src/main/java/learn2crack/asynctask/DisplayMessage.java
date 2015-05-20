package learn2crack.asynctask;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;




public class DisplayMessage extends Activity {
    Intent intent;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        intent = getIntent();
        textView = new TextView(this);
        acceptIntent();
        setContentView(textView);
    }
    public void acceptIntent(){
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView.setTextSize(40);
        textView.setText(message);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
