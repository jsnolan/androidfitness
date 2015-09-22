package au.edu.utas.jessenolan_184290_assig1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;


public class DataEntryScreen extends Activity implements OnItemSelectedListener {

    public static final String EXTRA_STRING="au.edu.utas.jessenolan_184290_assig1.selectedActivityType";

    //Used for the activity spinner
    String[] activityTypeItems = {"Walking", "Brisk Walking", "Cleaning", "Sweeping", "Carpentry",
            "Stacking Wood", "Mowing Lawn", "Badminton", "Basketball", "Bicycling", "Dancing",
            "Fishing","Golf","Sailing","Swimming","Table Tennis","Tennis","Volleyball","Hiking",
            "Jogging","Shoveling","Carrying Heavy Load","Heavy Farming","Basketball","Skiing",
            "Soccer"};
    //Used for the intensity spinner
    String[] activityIntensityItems = {"Moderate", "Vigorous"};
    //Used for the day spinner
    String[] activityDayItems = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
            "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    //Used for the month spinner
    String[] activityMonthItems = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec"};
    //Used for the year spinner
    String[] activityYearItems = {"2014","2015","2016"};
    int num = 1;    //Tracks how many entries have been entered


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_entry_screen); //use the data entry screen layout when loaded

        initialiseDataEntryScreenGUI();
    }

    public void initialiseDataEntryScreenGUI() {

        //Below code initialises the spinners and populates them with the string array values
        Spinner activityTypeSpinner=(Spinner)findViewById(R.id.activityType);

        activityTypeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> activityTypeAA =new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, activityTypeItems);
        activityTypeAA.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        activityTypeSpinner.setAdapter(activityTypeAA);

        Spinner activityIntensitySpinner=(Spinner)findViewById(R.id.activityIntensity);
        activityIntensitySpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> activityIntensityAA =new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, activityIntensityItems);
        activityIntensityAA.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        activityIntensitySpinner.setAdapter(activityIntensityAA);

        Spinner activityDaySpinner=(Spinner)findViewById(R.id.activityDay);
        activityDaySpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> activityDayAA =new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, activityDayItems);
        activityDayAA.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        activityDaySpinner.setAdapter(activityDayAA);

        Spinner activityMonthSpinner=(Spinner)findViewById(R.id.activityMonth);
        activityMonthSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> activityMonthAA =new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, activityMonthItems);
        activityMonthAA.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        activityMonthSpinner.setAdapter(activityMonthAA);

        Spinner activityYearSpinner=(Spinner)findViewById(R.id.activityYear);
        activityYearSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> activityYearAA =new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, activityYearItems);
        activityYearAA.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        activityYearSpinner.setAdapter(activityYearAA);


        Button updateButton=(Button)findViewById(R.id.updateButton);

        //Intent used for the weekly summary screen
        final Intent i = new Intent();

        updateButton.setOnClickListener(new View.OnClickListener() {    //Run when update button is clicked
            public void onClick(View v) {
                //Below code saves the values of the spinners and text boxes into variables
                Spinner activityTypeSpinner=(Spinner)findViewById(R.id.activityType);
                String selectedActivityType = activityTypeSpinner.getSelectedItem().toString();
                Log.d("", selectedActivityType + " selected");

                EditText activityDurationEditText=(EditText)findViewById(R.id.activityDuration);
                String enteredActivityDuration = activityDurationEditText.getText().toString();
                Log.d("", enteredActivityDuration + " minutes entered");

                Spinner activityIntensitySpinner=(Spinner)findViewById(R.id.activityIntensity);
                String selectedActivityIntensity = activityIntensitySpinner.getSelectedItem().toString();
                Log.d("", selectedActivityIntensity + " selected");

                Spinner activityDaySpinner=(Spinner)findViewById(R.id.activityDay);
                String selectedActivityDay = activityDaySpinner.getSelectedItem().toString();
                Log.d("", selectedActivityDay + " day selected");

                Spinner activityMonthSpinner=(Spinner)findViewById(R.id.activityMonth);
                String selectedActivityMonth = activityMonthSpinner.getSelectedItem().toString();
                Log.d("", selectedActivityMonth + " month selected");

                Spinner activityYearSpinner=(Spinner)findViewById(R.id.activityYear);
                String selectedActivityYear = activityYearSpinner.getSelectedItem().toString();
                Log.d("", selectedActivityYear + " year selected");

                //values are appended onto weekly summary intent as extras
                i.setClass(DataEntryScreen.this, WeeklySummaryScreen.class);
                Bundle extras = new Bundle();
                extras.putString("selectedActivityType"+String.valueOf(num), selectedActivityType);
                extras.putString("enteredActivityDuration"+String.valueOf(num), enteredActivityDuration);
                extras.putString("selectedActivityIntensity"+String.valueOf(num), selectedActivityIntensity);
                extras.putString("selectedActivityDay"+String.valueOf(num), selectedActivityDay);
                extras.putString("selectedActivityMonth"+String.valueOf(num), selectedActivityMonth);
                extras.putString("selectedActivityYear"+String.valueOf(num), selectedActivityYear);
                extras.putString("num",String.valueOf(num));

                i.putExtras(extras);

                //Notifies the user that their entry was successful
                TextView updateText = (TextView)findViewById(R.id.updated_text);
                updateText.setText(num+" entries entered");

                num++;  //tracks how many entries have been entered
            }
        });

        Button weeklySummaryButton=(Button)findViewById(R.id.weekly_summary_button_2);

        weeklySummaryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //enter when weekly summary button is pressed

                if (num <= 1)    //only go to weekly summary screen if there is an entry
                {
                    //load weekly summary screen into intent
                    i.setClass(DataEntryScreen.this, WeeklySummaryScreen.class);
                }
                startActivity(i);   //run weekly summary intent
            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {
    } //onNothingSelected

    /**
     * onDestroy. Called when the activity is destroyed.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("", "onDestroy");
    } // onDestroy
}
