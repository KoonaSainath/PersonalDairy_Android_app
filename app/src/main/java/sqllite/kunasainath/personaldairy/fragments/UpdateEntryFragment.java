package sqllite.kunasainath.personaldairy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import sqllite.kunasainath.personaldairy.Database;
import sqllite.kunasainath.personaldairy.R;
import sqllite.kunasainath.personaldairy.adapters.UpdateEntryAdapter;

public class UpdateEntryFragment extends Fragment {
    private RecyclerView mRecyclerview;
    private UpdateEntryAdapter mUpdateEntryAdapter;
    public UpdateEntryFragment() {
        // Required empty public constructor
    }
    public static UpdateEntryFragment newInstance() {
        UpdateEntryFragment fragment = new UpdateEntryFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Update an entry");

        Database database = new Database(getActivity(), Database.DATABASE_NAME, null, Database.VERSION);

        mUpdateEntryAdapter = new UpdateEntryAdapter(getActivity(), database.getAllEntries());

        mRecyclerview.setAdapter(mUpdateEntryAdapter);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_entry, container, false);
        mRecyclerview = view.findViewById(R.id.recycler_update_entry);
        return view;
    }
}