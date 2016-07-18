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

import java.util.List;

import bydzovsky.dominik.birthdayapp.model.MyArrayAdapter;
import bydzovsky.dominik.birthdayapp.model.Person;
import bydzovsky.dominik.birthdayapp.utility.Service;

public class MainActivity extends AppCompatActivity {
    Service s = new Service();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });
        setContentOfListView();
    }

    public void setContentOfListView(){
        ImageView imageView = (ImageView) findViewById(R.id.smile);
        TextView nameTextView = (TextView) findViewById(R.id.name);
        TextView dateTextView = (TextView) findViewById(R.id.birthday);
        //TextView yearOldTextView = (TextView) findViewById(R.id.yearOld);

        List<Person> listOfPeople = s.getListOfPeople();

        // use your custom layout
        ArrayAdapter<RelativeLayout> adapter = new MyArrayAdapter(this,
                R.layout.content_main, R.id.to_repeat, listOfPeople);
        //setListAdapter(adapter);
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
    public void showPersonDetail(View view){
        Intent intent = new Intent(this, PersonDetailActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void intentAddPersonActivity(View view){
        Intent intent = new Intent(this, AddPersonActivity.class);
        startActivity(intent);
    }

    public void search(View view){
        //id: search_edit_text
    }

}
