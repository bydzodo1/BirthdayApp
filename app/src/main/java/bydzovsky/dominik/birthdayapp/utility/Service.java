package bydzovsky.dominik.birthdayapp.utility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import bydzovsky.dominik.birthdayapp.model.MySQLiteDatabaseHelper;
import bydzovsky.dominik.birthdayapp.model.Person;

public class Service {
    MySQLiteDatabaseHelper myDatabaseHelper;

    public Service(Context context) {
        myDatabaseHelper = new MySQLiteDatabaseHelper(context);
    }

    public String whoCelebrates(){
        String result = "";
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        SQLiteDatabase db = myDatabaseHelper.getDb();
        Cursor c1 = db.rawQuery("SELECT svatek1,svatek2,svatek3 FROM days WHERE day = "+day+" AND n_month = "+month + " LIMIT 0,1", null);

        c1.moveToFirst();
        if (c1.getCount() == 1) {
            String sv1 = c1.getString(0);
            String sv2 = c1.getString(1);
            String sv3 = c1.getString(2);
            result = sv1 + ", " + sv2 + ", " + sv3;
        }
        return result;
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
    public ArrayList<Person> getOrderedBirthdayList(int showDays){
        ArrayList<Person> birthdayList = myDatabaseHelper.getBirthdaysList(showDays);
        Collections.sort(birthdayList);
        return birthdayList;
    }
    public ArrayList<Person> getOrderedNamedayList(int showDays){
        ArrayList<Person> namedayList = myDatabaseHelper.getNameDayList(showDays);
        Collections.sort(namedayList);
        return namedayList;
    }

    public String getStringDateAccortingToDayOfYear(String dayOfYear){
        String result = "";

        SQLiteDatabase db = myDatabaseHelper.getDb();
        Cursor c1 = db.rawQuery("SELECT day,month FROM days WHERE id = " + dayOfYear + " LIMIT 0,1", null);

        c1.moveToFirst();
        if (c1.getCount() == 1) {
            String sv1 = c1.getString(0);
            String sv2 = c1.getString(1);
            result = sv1 + ". " + sv2;
        }
        return result;
    }
    public Person get(int index) {
        return myDatabaseHelper.getPerson(index);
    }

    public static String formatDate(Date date){
        Format formatter = new SimpleDateFormat("dd. MM. yyyy");
        return formatter.format(date);
    }

}
