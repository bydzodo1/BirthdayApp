package bydzovsky.dominik.birthdayapp.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import bydzovsky.dominik.birthdayapp.MainActivity;
import bydzovsky.dominik.birthdayapp.PersonDetailActivity;
import bydzovsky.dominik.birthdayapp.R;
import bydzovsky.dominik.birthdayapp.utility.Service;

public class MyArrayAdapter extends ArrayAdapter<Person> {
    private final Context context;
    private final ArrayList<Person> values;
    private final MainActivity mainActivity;
    private final Service service;

    public MyArrayAdapter(Context context, ArrayList<Person> values, MainActivity mainActivity) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.mainActivity = mainActivity;
        this.service = mainActivity.getService();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_people_main, parent, false);
        Person person = (Person) getItem(position);
        final int person_id = person.getId();

        RelativeLayout relativeLayout = (RelativeLayout) rowView.findViewById(R.id.to_repeat);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, PersonDetailActivity.class);
                intent.putExtra("EXTRA_MESSAGE", new Integer(person_id));
                mainActivity.startActivity(intent);
            }
        });

        TextView whatCelebratesTextView = (TextView) rowView.findViewById(R.id.whatCelebrates);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.smile);
        int resid = mainActivity.getResources().getIdentifier("face" + person_id, "drawable", mainActivity.getPackageName());
        imageView.setImageResource(resid);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.name);
        TextView surnameTextView = (TextView) rowView.findViewById(R.id.surname);
        TextView dateTextView = (TextView) rowView.findViewById(R.id.birthday);
        TextView yearOldTextView = (TextView) rowView.findViewById(R.id.yearOld);
        TextView labelForYearOld = (TextView) rowView.findViewById(R.id.detail);
        TextView labelCelebrates = (TextView) rowView.findViewById(R.id.celebrates);
        //imageView.setImageDrawable();
        nameTextView.setText(person.getName());
        surnameTextView.setText(person.getSurname());


// -------------------setBackgroundColorOfLayoutAccordingToCelebration(relativeLayout, person);

        if (person.getCelebratesWhat() == Person.BIRTHDAY) {
            relativeLayout.setBackgroundColor(getContext().getResources().getColor(R.color.colorBirthday));
            whatCelebratesTextView.setText("Birthday");
            dateTextView.setText(Service.formatDate(person.getBirthday()));
            yearOldTextView.setText(person.getAge() + "");
            labelCelebrates.setText("has BIRTHDAY on ");

        } else if (person.getCelebratesWhat() == Person.NAMEDAY) {
            relativeLayout.setBackgroundColor(getContext().getResources().getColor(R.color.colorNameday));
            whatCelebratesTextView.setText("Nameday");
            dateTextView.setText(service.getStringDateAccortingToDayOfYear(person.getNameday()));
            yearOldTextView.setText("");
            labelForYearOld.setText("");
            labelCelebrates.setText("has nameday on ");
        }
        //----------------------------
        return rowView;
    }

    @Override
    public Person getItem(int position) {
        return super.getItem(position);
    }
}
