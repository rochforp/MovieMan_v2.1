package com.example.robertrochford.movieman_v2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


public class DisplaySearchActivity extends ActionBarActivity {
    TextView results;
    String search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search);


        //get the search from the intent in MainActivity.sendSearch
        Intent intent = getIntent();
        search = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        new LongOperation().execute(search);
        //create the text view
    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            results = (TextView) findViewById(R.id.result);
            String response = "";
            String url = "jdbc:mysql://98.130.0.90:3306/pggarla_movies";
            String user = "pggarla_preader";
            String pass = "Csc4610mysql";
            System.out.println("searchMoviesByWord just went dawg");
            try {

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection conn = DriverManager.getConnection(url, user, pass);
                Statement stmt = conn.createStatement();
                System.out.println("Connected to database");

                ResultSet rs = stmt.executeQuery("select distinct title.title from cast.info inner join title on cast_info.movie_id=title.id inner join name on cast_info.person.id=name.id where title.kind_id=1 and cast_info.role=1 and name.name like " + search);
                String qres = "";
                ResultSetMetaData rsmd = rs.getMetaData();
                while (rs.next()) {
                    qres += rsmd.getColumnName(1) + ": " + rs.getString(1) + "\n";

                }
                response = qres;
            }
            catch(java.sql.SQLException e1){
                e1.printStackTrace();
            }
            catch(ClassNotFoundException | IllegalAccessException | InstantiationException e){
                e.printStackTrace();
            }
            catch(Exception e){
                System.out.println("Failed to connect to the database." + e.getMessage());
            }
            results.setText(response);
            return response;
        }
        @Override
        protected void onPostExecute(String result){
            results.setText(result);
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
           //put more actions into bar if we want...
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
