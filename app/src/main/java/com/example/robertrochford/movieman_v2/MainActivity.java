package com.example.robertrochford.movieman_v2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
{
    public final static String EXTRA_MESSAGE = "com.example.rochford.MovieMan_v2.MESSAGE";
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;

    /* Called when the user clicks out search button. View will be the view returned when clicked*/
    public void sendSearch(View view){
        //do something to search our Movie DB in the future. Had to import the View class.
        //an intent can carry data types as key-value pairs called extras
        Intent intent = new Intent (this, DisplaySearchActivity.class);
        EditText editText = (EditText) findViewById(R.id.autocomplete_actor);
        //is what we will change
        String search = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, search);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_actor);
        String[] actors = getResources().getStringArray(R.array.actors_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, actors);
        textView.setAdapter(adapter);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
    radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(MainActivity.this,
                        radioSexButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioActors:
                if (checked)
                    // Display actors
                    break;
            case R.id.radioDirectors:
                if (checked)
                    // Display directors
                    break;
            case R.id.radioTitle:
                if (checked)
                    // Display directors
                    break;
        }
    }

    // Was told to remove it but maybe we can use it...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_display_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
