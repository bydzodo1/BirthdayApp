package bydzovsky.dominik.birthdayapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import bydzovsky.dominik.birthdayapp.model.AllContactsArrayAdapter;
import bydzovsky.dominik.birthdayapp.model.MyArrayAdapter;
import bydzovsky.dominik.birthdayapp.model.MySQLiteDatabaseHelper;
import bydzovsky.dominik.birthdayapp.model.Person;
import bydzovsky.dominik.birthdayapp.utility.Service;

public class AllContactsActivity extends AppCompatActivity {
    Service service;
    ListView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);
        service = new Service(getBaseContext());
        lstView = (ListView) findViewById(R.id.all_contacts_list_view);
        setContentOfListView(null);
    }


    public void setContentOfListView(View view) {
        ArrayAdapter<Person> adapter = new AllContactsArrayAdapter(getApplicationContext(),
                service.getAllContacts(), service, this);
        lstView.setAdapter(adapter);
    }

}
