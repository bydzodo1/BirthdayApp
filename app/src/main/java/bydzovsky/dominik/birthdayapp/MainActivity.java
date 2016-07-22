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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bydzovsky.dominik.birthdayapp.model.MyArrayAdapter;
import bydzovsky.dominik.birthdayapp.model.MySQLiteDatabaseHelper;
import bydzovsky.dominik.birthdayapp.model.Person;
import bydzovsky.dominik.birthdayapp.utility.Service;
// URL address to upload my project bit.ly/2a0TZRM
public class MainActivity extends AppCompatActivity {
    Service s = new Service(this);
    ListView lstView;

    public Service getService(){
        return s;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        lstView = (ListView) findViewById(R.id.lstview);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });
//
        CheckBox showAllCheckBox = (CheckBox) findViewById(R.id.show_all_checkbox);
        CheckBox showBirthdayCheckBox = (CheckBox) findViewById(R.id.show_birthday_checkbox);
        showBirthdayCheckBox.setChecked(true);
        CheckBox showNamedayCheckBox = (CheckBox) findViewById(R.id.show_nameday_checkbox);
        showNamedayCheckBox.setChecked(true);

        TextView celebrates = (TextView) findViewById(R.id.today_celebrates);
        Date date = new Date();
        String name = s.whoCelebrates();
        celebrates.setText("Today's " + Service.formatDate(date) + " and celebrates " + name + ".");
        setContentOfListView(null);
    }

    public void setContentOfListView(View view) {
        CheckBox showAllCheckBox = (CheckBox) findViewById(R.id.show_all_checkbox);
        CheckBox showBirthdayCheckBox = (CheckBox) findViewById(R.id.show_birthday_checkbox);
        CheckBox showNamedayCheckBox = (CheckBox) findViewById(R.id.show_nameday_checkbox);
        TextView showDaysTextview = (TextView) findViewById(R.id.showsDays);

        int showDays;
        if (showAllCheckBox.isChecked()){
            showDays = 366;

        } else {
            showDays = MySQLiteDatabaseHelper.DEFAULT_SHOW_DAYS;
        }

        showDaysTextview.setText(showDays + " days");
        ArrayList<Person> listOfPeople;
        if (showBirthdayCheckBox.isChecked() && showNamedayCheckBox.isChecked()){
            listOfPeople = s.getOrderedCelebrationList(showDays);
        } else if (showBirthdayCheckBox.isChecked()){
            listOfPeople = s.getOrderedBirthdayList(showDays);
        } else if (showNamedayCheckBox.isChecked()){
            listOfPeople = s.getOrderedNamedayList(showDays);
        } else {
            listOfPeople = new ArrayList<>();
        }
        ArrayAdapter<Person> adapter = new MyArrayAdapter(getApplicationContext(),
                listOfPeople, this);
        lstView.setAdapter(adapter);

    }

    public void refreshAll(){

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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

        }
        if (id == R.id.action_about) {

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        if (id==android.R.id.home) {
            finish();
        }
        if (id == R.id.action_allContacts) {
            Intent intent = new Intent(this, AllContactsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void intentAddPersonActivity(View view) {
        Intent intent = new Intent(this, AddPersonActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        //id: search_edit_text
    }


}
