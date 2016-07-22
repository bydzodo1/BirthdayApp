package bydzovsky.dominik.birthdayapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import bydzovsky.dominik.birthdayapp.utility.Service;

public class AddPersonActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
    }
    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

}

    public void addPerson(View view) {
        Button submitButton = (Button) findViewById(R.id.submitButton);
        TextView nameTextView = (TextView) findViewById(R.id.name);
        TextView surnameTextView = (TextView) findViewById(R.id.surname);
        TextView emailTextView = (TextView) findViewById(R.id.email);
        TextView namedayTextView = (TextView) findViewById(R.id.nameday);
        TextView birthdayTextView = (TextView) findViewById(R.id.birthday);
        TextView phoneTextView = (TextView) findViewById(R.id.phone);
        TextView addressTextView = (TextView) findViewById(R.id.address);
        TextView otherTextView = (TextView) findViewById(R.id.other);
        Service s = new Service(getApplicationContext());
        String name = namedayTextView.getText() + "";
        String surname = surnameTextView.getText() + "";
        String email = emailTextView.getText() + "";
        String nameday = namedayTextView.getText() + "";
        int n_day = Integer.parseInt(nameday.substring(0,1));
        int n_month = Integer.parseInt(nameday.substring(3,4));
        int dayOfYearNameday = s.getDayOfYear(n_day, n_month);
        String birthday = birthdayTextView.getText()+"";
        int b_day = Integer.parseInt(birthday.substring(5,6));
        int b_month = Integer.parseInt(birthday.substring(8, 9));
        int dayOfYearBirthday = s.getDayOfYear(b_day, b_month);
        String phone = phoneTextView.getText()+"";
        String address = addressTextView.getText()+"";
        String other = otherTextView.getText()+"";

        String sql = "INSERT INTO contacts (`id`, `name`, `surname`, `nameday`, `birthday`, `day_of_year_birthday`, `email`, `phone`, `address`, `other`) VALUES\n" +
                "(null, '"+name+"', '"+surname+"', "+dayOfYearNameday+", '"+birthday+"', "+dayOfYearBirthday+", '"+email+"', '"+phone+"', NULL, '"+other+"')";

        boolean succedded = s.execSql(sql);
        if (succedded) submitButton.setBackgroundColor(Color.parseColor("#22ff22"));
    }

}
