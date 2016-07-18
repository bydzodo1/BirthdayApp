package bydzovsky.dominik.birthdayapp.utility;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import bydzovsky.dominik.birthdayapp.model.Person;
import bydzovsky.dominik.birthdayapp.model.SQLiteDataManager;

/**
 * Created by ee5415 on 15.7.2016.
 */
public class Service {
    SQLiteDatabase myDatabase;

    public Service() {
        if (null==myDatabase) {
            myDatabase = new SQLiteDataManager().getMyDatabase();
        }
    }
    public List<Person> getListOfPeople(){
        String query = "SELECT * FROM " + SQLiteDataManager.CONTACTS_TABLE_NAME + "order by "+SQLiteDataManager.CONTACTS_COLUMN_BIRTHDAY+";";


        return
    }

}
