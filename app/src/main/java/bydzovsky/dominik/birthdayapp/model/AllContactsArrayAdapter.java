package bydzovsky.dominik.birthdayapp.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class AllContactsArrayAdapter extends ArrayAdapter<Person> {
    private final Context context;
    private final ArrayList<Person> values;
    private Activity activity;
    private final Service service;

    public AllContactsArrayAdapter(Context context, ArrayList<Person> values, Service service, Activity activity) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.service = service;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.all_contacts_listview, parent, false);
        Person person = (Person) getItem(position);
        final int person_id = person.getId();

        RelativeLayout relativeLayout = (RelativeLayout) rowView.findViewById(R.id.click_to_detail);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PersonDetailActivity.class);
                intent.putExtra("EXTRA_MESSAGE", new Integer(person_id));
                activity.startActivity(intent);
            }
        });

        TextView whatCelebratesTextView = (TextView) rowView.findViewById(R.id.whatCelebrates);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.smile);
        int resid = getContext().getResources().getIdentifier("face" + person_id, "drawable", getContext().getPackageName());
        imageView.setImageResource(resid);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.name);
        TextView surnameTextView = (TextView) rowView.findViewById(R.id.surname);
        TextView dateTextView = (TextView) rowView.findViewById(R.id.birthday);
        TextView yearOldTextView = (TextView) rowView.findViewById(R.id.yearOld);
        TextView labelForYearOld = (TextView) rowView.findViewById(R.id.detail);
        TextView labelCelebrates = (TextView) rowView.findViewById(R.id.celebrates);
        Button deleteButton = (Button) rowView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.deletePerson(person_id);
                activity.recreate();
            }
        });

        yearOldTextView.setText(person.getAge()+"");
        labelCelebrates.setText("Birthday: "+Service.formatDate(person.getBirthday())+" Nameday: "+ service.getStringDateAccortingToDayOfYear(person.getNameday()));
        //imageView.setImageDrawable();
        nameTextView.setText(person.getName());
        surnameTextView.setText(person.getSurname());

        return rowView;
    }

    @Override
    public Person getItem(int position) {
        return super.getItem(position);
    }
}
