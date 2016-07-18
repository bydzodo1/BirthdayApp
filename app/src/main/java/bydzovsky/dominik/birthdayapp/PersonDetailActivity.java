package bydzovsky.dominik.birthdayapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import bydzovsky.dominik.birthdayapp.model.Person;
import bydzovsky.dominik.birthdayapp.utility.Service;

public class PersonDetailActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE  = "bydzovsky.dominik.birthdayapp.message";
    Service s = new Service();
    int person_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Intent intent = getIntent();
        person_id = (int) intent.getIntExtra("EXTRA_MESSAGE");
        TextView person_id_textview = (TextView) findViewById(R.id.person_id);
        person_id_textview.setText(person_id);
        fillPersonInView();
    }
    public void fillPersonInView(){
        Person person = s.getListOfPeople().get(person_id);
        ImageView image_imageView = (ImageView) findViewById(R.id.image);
        TextView name_textview = (TextView) findViewById(R.id.name);
        TextView surname_textview = (TextView) findViewById(R.id.surname);
        TextView email_textview = (TextView) findViewById(R.id.email);
        TextView nameday_textview = (TextView) findViewById(R.id.nameday);
        TextView birthday_textview = (TextView) findViewById(R.id.birthday);

        name_textview.setText(person.getName());
        surname_textview.setText(person.getSurname());
        email_textview.setText(person.getEmail());
        nameday_textview.setText(person.getNameday());
        birthday_textview.setText(person.getBirthday().toString());
    }

    public void sendEmailToHonoree(View view){
        //ToDo
    }

}
