package bydzovsky.dominik.birthdayapp.utility;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bydzovsky.dominik.birthdayapp.model.Person;
import bydzovsky.dominik.birthdayapp.model.SQLiteDataManager;

/**
 * Created by ee5415 on 15.7.2016.
 */
public class Service {
    SQLiteDatabase myDatabase;

    public Service() {
//        if (null==myDatabase) {
//            myDatabase = new SQLiteDataManager().getMyDatabase();
//        }
    }
    public List<Person> getListOfPeople(){
        //String query = "SELECT * FROM " + SQLiteDataManager.CONTACTS_TABLE_NAME + "order by "+SQLiteDataManager.CONTACTS_COLUMN_BIRTHDAY+";";
        List<Person> listOfPeople = new ArrayList<>();
        listOfPeople.add(new Person(0,"jmeno","prijmeni","email","01.01.", java.sql.Date.valueOf("07/11/1994")));
        listOfPeople.add(new Person(1,"jmeno","prijmeni","email","01.01.", java.sql.Date.valueOf("07/11/1994")));
        return listOfPeople;
    }

}
