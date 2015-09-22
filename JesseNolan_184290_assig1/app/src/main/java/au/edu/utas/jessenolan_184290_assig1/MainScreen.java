package au.edu.utas.jessenolan_184290_assig1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);   //load the main screen layout

        initialiseMainScreenGUI();
    }

    public void initialiseMainScreenGUI() { //initialise the buttons
        Button dataEntryButton=(Button)findViewById(R.id.data_entry_button);
        Button weeklySummaryButton=(Button)findViewById(R.id.weekly_summary_button);

        dataEntryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("", "data entry button clicked");
                Intent i = new Intent();
                //load the data entry screen if the data entry button is pressed
                i.setClass(MainScreen.this, DataEntryScreen.class);
                startActivity(i);
            }
        });

        weeklySummaryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("", "weekly summary button clicked");
                Intent i = new Intent();
                //load the weekly summary screen if the weekly summary button is pressed
                i.setClass(MainScreen.this, WeeklySummaryScreen.class);
                startActivity(i);
            }
        });

    }
}
