package sqllite.kunasainath.personaldairy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sqllite.kunasainath.personaldairy.adapters.AllEntriesAdapter;
import sqllite.kunasainath.personaldairy.Database;
import sqllite.kunasainath.personaldairy.Entry;
import sqllite.kunasainath.personaldairy.R;
public class AllEntriesFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private AllEntriesAdapter mAdapter;

    public AllEntriesFragment() {
    }

    public static AllEntriesFragment newInstance() {
        AllEntriesFragment fragment = new AllEntriesFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(getString(R.string.app_name));

        Database database = new Database(getActivity(), Database.DATABASE_NAME, null, Database.VERSION);
        ArrayList<Entry> entries = database.getAllEntries();

        mAdapter = new AllEntriesAdapter(getActivity(), entries);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_entries, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_all_entries);
        return view;
    }

}