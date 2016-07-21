package bydzovsky.dominik.birthdayapp.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import bydzovsky.dominik.birthdayapp.MainActivity;
import bydzovsky.dominik.birthdayapp.PersonDetailActivity;
import bydzovsky.dominik.birthdayapp.R;

public class MyArrayAdapter extends ArrayAdapter<Person> {
    private final Context context;
    private final ArrayList<Person> values;
    private final MainActivity mainActivity;

    public MyArrayAdapter(Context context, ArrayList<Person> values, MainActivity mainActivity) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.mainActivity = mainActivity;
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

        setBackgroundColorOfLayoutAccordingToCelebration(relativeLayout, person);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.smile);
        TextView nameTextView = (TextView) rowView.findViewById(R.id.name);
        TextView surnameTextView = (TextView) rowView.findViewById(R.id.surname);
        TextView dateTextView = (TextView) rowView.findViewById(R.id.birthday);
        TextView yearOldTextView = (TextView) rowView.findViewById(R.id.yearOld);

        //imageView.setImageDrawable();
        nameTextView.setText(person.getName());
        surnameTextView.setText(person.getSurname());
        dateTextView.setText(person.getBirthday().toString());
        yearOldTextView.setText(person.getAge()+"");

        return rowView;
    }

    public void setBackgroundColorOfLayoutAccordingToCelebration(RelativeLayout layout, Person person){
        if (person.getCelebratesWhat() == Person.BIRTHDAY){
            layout.setBackgroundColor(Color.parseColor("#FAC9A0"));

        } else if (person.getCelebratesWhat() == Person.NAMEDAY){
            layout.setBackgroundColor(Color.parseColor("#C9645E"));

        }
    }

    @Override
    public Person getItem(int position) {
        return super.getItem(position);
    }
}
