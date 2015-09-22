package au.edu.utas.jessenolan_184290_assig1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


public class WeeklySummaryScreen extends Activity {

    int durationsArray[] = new int[999];    //used to store entry duration data
    int i = 1;  //used to loop for calculating entry total

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_summary_screen); //load the weekly summary layout

        //pass in the extras from the data entry activity
        Bundle extras = getIntent().getExtras();
        String retrievedString = extras != null ? extras.getString(DataEntryScreen.EXTRA_STRING) : null;    //prevents crashing if extras are null
        String selectedActivityType;
        String enteredActivityDuration;
        String selectedActivityIntensity;
        String selectedActivityDay;
        String selectedActivityMonth;
        String selectedActivityYear;
        String numString = getIntent().getStringExtra("num");   //retrieve the number of entries
        int num = 0;
        int totalModerateDuration = 0;
        int totalVigorousDuration = 0;
        int totalDuration = 0;
        double totalMETMinutes = 0;
        double met = 0;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");    //used to format the data info

        int enteredMonth = 0;
        int enteredDay = 0;
        int enteredYear = 0;

        String enteredDateString;

        Date currentDate = new Date(); //gets current date and time
        Date weekAgo = new Date(currentDate.getTime() - (8*1000*60*60*24)); //gets the date one week ago
        Date enteredDate = new Date();  //used to store the entry date data

        if (extras != null) //only displays info if successful entries
        {
            num = Integer.parseInt(numString);
            for (i = 1; i <= num; i++)   //loops through all the passed extras from DataEntryScreen activity
            {
                selectedActivityType = getIntent().getStringExtra("selectedActivityType" + i);
                enteredActivityDuration = getIntent().getStringExtra("enteredActivityDuration" + i);
                selectedActivityIntensity = getIntent().getStringExtra("selectedActivityIntensity" + i);
                selectedActivityDay = getIntent().getStringExtra("selectedActivityDay" + i);
                selectedActivityMonth = getIntent().getStringExtra("selectedActivityMonth" + i);
                selectedActivityYear = getIntent().getStringExtra("selectedActivityYear" + i);


                Log.d("", selectedActivityType + " obtained successfully");
                Log.d("", enteredActivityDuration + " obtained successfully");
                Log.d("", selectedActivityIntensity + " obtained successfully");
                Log.d("", selectedActivityDay + " obtained successfully");
                Log.d("", selectedActivityMonth + " obtained successfully");
                Log.d("", selectedActivityYear + " obtained successfully");

                enteredDay = Integer.parseInt(selectedActivityDay);
                enteredYear = Integer.parseInt(selectedActivityYear);

                switch(selectedActivityMonth) {
                    case "Jan": enteredMonth = 1;
                        break;
                    case "Feb": enteredMonth = 2;
                        break;
                    case "Mar": enteredMonth = 3;
                        break;
                    case "Apr": enteredMonth = 4;
                        break;
                    case "May": enteredMonth = 5;
                        break;
                    case "Jun": enteredMonth = 6;
                        break;
                    case "Jul": enteredMonth = 7;
                        break;
                    case "Aug": enteredMonth = 8;
                        break;
                    case "Sep": enteredMonth = 9;
                        break;
                    case "Oct": enteredMonth = 10;
                        break;
                    case "Nov": enteredMonth = 11;
                        break;
                    case "Dec": enteredMonth = 12;
                        break;
                }

                enteredDateString = enteredYear+"-"+enteredMonth+"-"+enteredDay;
                //formats entered date

                try {
                    enteredDate = formatter.parse(enteredDateString);
                }
                catch (ParseException e){
                }

                //Checks if the entered date is within the last seven days
                if (enteredDate.before(currentDate) && enteredDate.after(weekAgo))
                {
                    if (selectedActivityIntensity.equals("Moderate"))       //different MET values for moderate and vigorous
                    {
                        switch (selectedActivityType) { //determines the MET values for moderate activities
                            case "Walking": met = 3.3;
                                break;
                            case "Brisk Walking": met = 5;
                                break;
                            case "Cleaning": met = 3;
                                break;
                            case "Sweeping": met = 3.5;
                                break;
                            case "Carpentry": met = 3.6;
                                break;
                            case "Stacking Wood": met = 5.5;
                                break;
                            case "Mowing Lawn": met = 5.5;
                                break;
                            case "Badminton": met = 4.5;
                                break;
                            case "Basketball": met = 4.5;
                                break;
                            case "Bicycling": met = 6;
                                break;
                            case "Dancing": met = 3;
                                break;
                            case "Fishing": met = 4;
                                break;
                            case "Golf": met = 4.3;
                                break;
                            case "Sailing": met = 3;
                                break;
                            case "Swimming": met = 6;
                                break;
                            case "Table Tennis": met = 4;
                                break;
                            case "Tennis": met = 5;
                                break;
                            case "Volleyball": met = 3.5;
                                break;
                            case "Hiking": met = 8.5;
                                break;
                            case "Jogging": met = 10;
                                break;
                            case "Shoveling": met = 8;
                                break;
                            case "Carrying Heavy Load": met = 7.5;
                                break;
                            case "Heavy Farming": met = 8;
                                break;
                            case "Skiing": met = 8;
                                break;
                            case "Soccer": met = 8.5;
                                break;
                        }
                        totalModerateDuration += Integer.parseInt(enteredActivityDuration); //calculates total time exercised
                        totalMETMinutes += met*totalModerateDuration;   //adds to total MET minutes
                    }
                    else if (selectedActivityIntensity.equals("Vigorous")) //determines the MET values for vigorous activities
                    {
                        switch (selectedActivityType) {
                            case "Walking": met = 5;
                                break;
                            case "Brisk Walking": met = 7;
                                break;
                            case "Cleaning": met = 3;
                                break;
                            case "Sweeping": met = 3.5;
                                break;
                            case "Carpentry": met = 3.6;
                                break;
                            case "Stacking Wood": met = 5.5;
                                break;
                            case "Mowing Lawn": met = 5.5;
                                break;
                            case "Badminton": met = 4.5;
                                break;
                            case "Basketball": met = 8;
                                break;
                            case "Bicycling": met = 9;
                                break;
                            case "Dancing": met = 3;
                                break;
                            case "Fishing": met = 4;
                                break;
                            case "Golf": met = 4.3;
                                break;
                            case "Sailing": met = 3;
                                break;
                            case "Swimming": met = 10;
                                break;
                            case "Table Tennis": met = 4;
                                break;
                            case "Tennis": met = 8;
                                break;
                            case "Volleyball": met = 8;
                                break;
                            case "Hiking": met = 8.5;
                                break;
                            case "Jogging": met = 10;
                                break;
                            case "Shoveling":
                                met = 8;
                                break;
                            case "Carrying Heavy Load": met = 7.5;
                                break;
                            case "Heavy Farming": met = 8;
                                break;
                            case "Skiing": met = 8;
                                break;
                            case "Soccer": met = 8.5;
                                break;
                        }
                        totalVigorousDuration += Integer.parseInt(enteredActivityDuration); //calculates total time exercised
                        totalMETMinutes += met*totalVigorousDuration;   //adds to total MET minutes
                    }
                    //tracks the total number of minutes
                    totalDuration += Integer.parseInt(enteredActivityDuration);
                }

            }
            Log.d("total mod dur last7", String.valueOf(totalModerateDuration));
            Log.d("total vig duration", String.valueOf(totalVigorousDuration));
            Log.d("total dur last 7", String.valueOf(totalDuration));
            Log.d("total MET mins last 7", String.valueOf(totalMETMinutes));

            //Hides the text shown when no entries have been entered on the weekly summary screen
            TextView emptySummaryScreen = (TextView)findViewById(R.id.empty_weekly_summary);
            emptySummaryScreen.setVisibility(View.INVISIBLE);

            //Used to display duration info
            TextView totalModerateDurationText = (TextView)findViewById(R.id.total_moderate_duration);
            TextView totalVigorousDurationText = (TextView)findViewById(R.id.total_vigorous_duration);
            TextView totalDurationText = (TextView)findViewById(R.id.total_duration);
            TextView totalMETText = (TextView)findViewById(R.id.total_met_minutes);
            TextView recommendedMETText = (TextView)findViewById(R.id.recommended_met_minutes);

            //Prints the duration info on the screen
            totalModerateDurationText.setText("Moderate exercise in last 7 days: " + totalModerateDuration + " minutes.");
            totalVigorousDurationText.setText("Vigorous exercise in last 7 days: " + totalVigorousDuration + " minutes.");
            totalMETText.setText("Total exercise in last 7 days: " + (int)Math.round(totalMETMinutes) + " MET minutes.");

            //Customises the message if the user is above or below the recommended 450 MET minutes
            if (totalMETMinutes >= 450)
            {
                recommendedMETText.setText("You are above the recommended 450 MET minutes per week. Congratulations!");
            }
            else
            {
                recommendedMETText.setText("You are currently below the minimum 450 MET minutes in the last 7 days.");
            }

        }
    }
}
