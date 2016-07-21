package bydzovsky.dominik.birthdayapp.utility;

import android.content.Context;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import bydzovsky.dominik.birthdayapp.model.MySQLiteDatabaseHelper;
import bydzovsky.dominik.birthdayapp.model.Person;

public class Service {
    MySQLiteDatabaseHelper myDatabaseHelper;

    public Service(Context context) {
        myDatabaseHelper = new MySQLiteDatabaseHelper(context);
    }

    public ArrayList<Person> getOrderedCelebrationList(int showDays){

        ArrayList<Person> birthdayList = myDatabaseHelper.getBirthdaysList(showDays);
        ArrayList<Person> namedayList = myDatabaseHelper.getNameDayList(showDays);
        ArrayList<Person> merged = new ArrayList<>();
        merged.addAll(birthdayList);
        merged.addAll(namedayList);
        Collections.sort(merged);

        return merged;
    }

    public Person get(int index) {
        return myDatabaseHelper.getPerson(index);
    }

    public static String formatDate(Date date){
        Format formatter = new SimpleDateFormat("dd. MM. yyyy");
        return formatter.format(date);
    }

}
