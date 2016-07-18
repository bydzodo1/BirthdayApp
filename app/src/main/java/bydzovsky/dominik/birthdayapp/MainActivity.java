package bydzovsky.dominik.birthdayapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bydzovsky.dominik.birthdayapp.model.MyArrayAdapter;
import bydzovsky.dominik.birthdayapp.model.Person;
import bydzovsky.dominik.birthdayapp.utility.Service;

public class MainActivity extends AppCompatActivity {
    Service s = new Service();
    ListView lstView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lstView = (ListView)findViewById(R.id.lstview);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });


        TextView celebrates = (TextView) findViewById(R.id.today_celebrates);
        Date date = new Date();
        String name = "_____";
        celebrates.setText("Today's " + date.toString() + " and celebrates " + name);
        setContentOfListView();
    }

    public void setContentOfListView(){
        ArrayList<Person> listOfPeople = s.getListOfPeople();
        ArrayAdapter<Person> adapter = new MyArrayAdapter(getApplicationContext(),
                listOfPeople, this);
        lstView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void intentAddPersonActivity(View view){
        Intent intent = new Intent(this, AddPersonActivity.class);
        startActivity(intent);
    }

    public void search(View view){
        //id: search_edit_text
    }

}
