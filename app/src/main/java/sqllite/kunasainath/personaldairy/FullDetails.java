package sqllite.kunasainath.personaldairy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FullDetails extends AppCompatActivity {

    private TextView txtDate, txtTime, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_details);

        txtDate = findViewById(R.id.txt_date);
        txtTime = findViewById(R.id.txt_time);
        txtDescription = findViewById(R.id.txt_description);

        Entry entry = (Entry) getIntent().getSerializableExtra("Entry");

        txtDate.setText("Date: " + entry.getDate());
        txtTime.setText("Time: " + entry.getTime());
        txtDescription.setText("Description:\n\n" + entry.getDescription());
    }
}