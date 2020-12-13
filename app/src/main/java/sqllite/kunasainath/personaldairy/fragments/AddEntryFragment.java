package sqllite.kunasainath.personaldairy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import sqllite.kunasainath.personaldairy.Database;
import sqllite.kunasainath.personaldairy.DateAndTime;
import sqllite.kunasainath.personaldairy.Entry;
import sqllite.kunasainath.personaldairy.R;
public class AddEntryFragment extends Fragment {

    private EditText edtTitle, edtDescription;
    private Button btnSave;

    public AddEntryFragment() {
    }
    public static AddEntryFragment newInstance() {
        AddEntryFragment fragment = new AddEntryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add an entry");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String description = edtDescription.getText().toString();
                String[] dateAndTime = DateAndTime.getDateAndTime();
                String date = dateAndTime[0], time = dateAndTime[1];

                if(title.length() == 0){
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Title cannot be empty", Snackbar.LENGTH_LONG).show();
                    edtTitle.setError("Title cannot be empty");
                    return ;
                }

                if(description.length() == 0){
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Description cannot be empty", Snackbar.LENGTH_LONG).show();
                    edtDescription.setError("Description cannot be empty");
                    return ;
                }

                Entry entry = new Entry(0, date, time, title, description);
                Database database = new Database(getActivity(), Database.DATABASE_NAME, null, Database.VERSION);

                database.saveEntry(entry);

                Snackbar.make(getActivity().findViewById(android.R.id.content), "Entry is saved.", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_entry, container, false);
        edtTitle = view.findViewById(R.id.edt_title);
        edtDescription = view.findViewById(R.id.edt_description);
        btnSave = view.findViewById(R.id.btn_save);
        return view;
    }
}