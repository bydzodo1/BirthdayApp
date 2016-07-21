package bydzovsky.dominik.birthdayapp.utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import bydzovsky.dominik.birthdayapp.model.MySQLiteDatabaseHelper;
import bydzovsky.dominik.birthdayapp.model.Person;
import bydzovsky.dominik.birthdayapp.model.SQLiteDataManager;

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


}
