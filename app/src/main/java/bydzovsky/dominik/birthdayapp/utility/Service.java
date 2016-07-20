package bydzovsky.dominik.birthdayapp.utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bydzovsky.dominik.birthdayapp.model.MySQLiteDatabaseHelper;
import bydzovsky.dominik.birthdayapp.model.Person;
import bydzovsky.dominik.birthdayapp.model.SQLiteDataManager;

public class Service {
    MySQLiteDatabaseHelper myDatabaseHelper;
    SQLiteDatabase myDatabase;

    public Service(Context context) {
        myDatabaseHelper = new MySQLiteDatabaseHelper(context);
        myDatabase = myDatabaseHelper.getWritableDatabase();
        myDatabaseHelper.setDatabase(myDatabase);
    }
    public ArrayList<Person> getListOfPeople() {
        return getListOfPeople(366);
    }
    public ArrayList<Person> getListOfPeople(int showDays) {
        //String query = "SELECT * FROM " + SQLiteDataManager.CONTACTS_TABLE_NAME + "order by "+SQLiteDataManager.CONTACTS_COLUMN_BIRTHDAY+";";
        return myDatabaseHelper.getBirthdaysList(showDays);

//        ArrayList<Person> listOfPeople = new ArrayList<>();
//        listOfPeople.add(new Person(1, "David", "Nodopíči", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(2, "Petr", "Kundahunda", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(3, "Honza", "Strčilhomezivrata", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(4, "Kapříček", "Melounek", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(5, "Uršule", "Strčjitamprst", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(6, "Plešoun", "Sinamazalhlavu", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(7, "Hovniválek", "Pršíhovno", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(8, "Anežka", "Malékozymá", "email", "01.01.", new Date()));
//        return listOfPeople;
    }

    public Person get(int index) {
        return myDatabaseHelper.getPerson(index);
    }


}
