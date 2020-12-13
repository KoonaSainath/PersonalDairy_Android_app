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
import sqllite.kunasainath.personaldairy.adapters.DeleteEntryAdapter;

public class DeleteEntryFragment extends Fragment {
    public RecyclerView mRecyclerView;
    private DeleteEntryAdapter mAdapter;
    public DeleteEntryFragment() {
    }

    public static DeleteEntryFragment newInstance() {
        DeleteEntryFragment fragment = new DeleteEntryFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Delete an entry");

        Database database = new Database(getActivity(), Database.DATABASE_NAME, null, Database.VERSION);

        mAdapter = new DeleteEntryAdapter(getActivity(), database.getAllEntries());

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
        View view = inflater.inflate(R.layout.fragment_delete_entry, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_delete_entry);
        return view;
    }
}