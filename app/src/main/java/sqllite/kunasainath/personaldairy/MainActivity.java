package sqllite.kunasainath.personaldairy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Stack;

import sqllite.kunasainath.personaldairy.adapters.AllEntriesAdapter;
import sqllite.kunasainath.personaldairy.adapters.DeleteEntryAdapter;
import sqllite.kunasainath.personaldairy.adapters.UpdateEntryAdapter;
import sqllite.kunasainath.personaldairy.fragments.AddEntryFragment;
import sqllite.kunasainath.personaldairy.fragments.AllEntriesFragment;
import sqllite.kunasainath.personaldairy.fragments.DeleteEntryFragment;
import sqllite.kunasainath.personaldairy.fragments.UpdateEntryFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AllEntriesAdapter.AllEntriesInterface, DeleteEntryAdapter.DeleteEntryInterface, UpdateEntryAdapter.UpdateFragmentInterface {

    public static final String ALL_ENTRIES_KEY = "all entries";
    public static final String ADD_ENTRY_KEY = "add entry";
    public static final String DELETE_ENTRY_KEY = "delete entry";
    public static final String UPDATE_ENTRY_KEY = "update entry";
    public static final String DELETE_FRAGMENT_TAG = "delete tag";
    public static final String UPDATE_FRAGMENT_TAG = "update tag";

    private  boolean firstTimeBackPressed = true;

    private Stack<String> stackForFragments;


    private FloatingActionButton fabShowOptions, fabAddEntry, fabDeleteEntry, fabEditEntry;
    private boolean areFabOptionsVisible;

    private AllEntriesFragment mAllEntriesFragment;
    private AddEntryFragment mAddEntryFragment;
    private DeleteEntryFragment mDeleteEntryFragment;
    private UpdateEntryFragment mUpdateEntryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        areFabOptionsVisible = false;

        stackForFragments = new Stack<>();

        firstTimeBackPressed = true;

        fabShowOptions = findViewById(R.id.fab_options);
        fabAddEntry = findViewById(R.id.add_entry);
        fabDeleteEntry = findViewById(R.id.delete_entry);
        fabEditEntry = findViewById(R.id.update_entry);

        fabShowOptions.setOnClickListener(this);
        fabAddEntry.setOnClickListener(this);
        fabDeleteEntry.setOnClickListener(this);
        fabEditEntry.setOnClickListener(this);

        mAllEntriesFragment = AllEntriesFragment.newInstance();
        mAddEntryFragment = AddEntryFragment.newInstance();
        mDeleteEntryFragment = DeleteEntryFragment.newInstance();
        mUpdateEntryFragment = UpdateEntryFragment.newInstance();


        showAllEntriesFragment();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_new_entry:
                showAddEntryFragment();
                break;
            case R.id.menu_item_delete_an_entry:
                showDeleteEntryFragment();
                break;
            case R.id.menu_item_update_an_entry:
                showUpdateEntryFragment();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fab_options:
                if (!areFabOptionsVisible) {
                    openFab();
                } else {
                    closeFab();
                }
                break;
            case R.id.add_entry:
                if (!stackForFragments.peek().equals(ADD_ENTRY_KEY)){
                    showAddEntryFragment();
                    closeFab();
                }
                break;
            case R.id.delete_entry:
                if(!stackForFragments.peek().equals(DELETE_ENTRY_KEY)) {
                    showDeleteEntryFragment();
                    closeFab();
                }
                break;
            case R.id.update_entry:
                if(!stackForFragments.peek().equals(UPDATE_ENTRY_KEY)) {
                    showUpdateEntryFragment();
                    closeFab();
                }
                break;
        }
    }

    private void openFab(){
        int duration = 200;
        areFabOptionsVisible = true;
        fabAddEntry.animate().translationY(-200).setDuration(duration).start();
        fabDeleteEntry.animate().translationY(-400).setDuration(duration).start();
        fabEditEntry.animate().translationY(-600).setDuration(duration).start();
        fabShowOptions.animate().rotation(45).start();
    }

    private void closeFab(){
        int duration = 200;
        areFabOptionsVisible = false;
        fabAddEntry.animate().translationY(0).setDuration(duration).start();
        fabDeleteEntry.animate().translationY(0).setDuration(duration).start();
        fabEditEntry.animate().translationY(0).setDuration(duration).start();
        fabShowOptions.animate().rotation(0).start();
    }

    private void showAllEntriesFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mAllEntriesFragment)
                .commit();

        stackForFragments.push(ALL_ENTRIES_KEY);

        Log.i("STACK STATUS", stackForFragments.toString());

    }
    private void showAddEntryFragment(){
        if(stackForFragments.peek().equals(ALL_ENTRIES_KEY)) {
            stackForFragments.push(ADD_ENTRY_KEY);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mAddEntryFragment)
                    .addToBackStack(null)
                    .commit();
        }else{
            getSupportFragmentManager().popBackStack();
            stackForFragments.pop();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mAddEntryFragment)
                    .addToBackStack(null)
                    .commit();

            stackForFragments.push(ADD_ENTRY_KEY);
        }

        Log.i("STACK STATUS", stackForFragments.toString());
    }
    private void showDeleteEntryFragment(){
        if(stackForFragments.peek().equals(ALL_ENTRIES_KEY)) {
            stackForFragments.push(DELETE_ENTRY_KEY);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mDeleteEntryFragment, DELETE_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();
        }else{
            getSupportFragmentManager().popBackStack();
            stackForFragments.pop();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mDeleteEntryFragment, DELETE_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();

            stackForFragments.push(DELETE_ENTRY_KEY);
        }

        Log.i("STACK STATUS", stackForFragments.toString());

    }
    private void showUpdateEntryFragment(){
        if(stackForFragments.peek().equals(ALL_ENTRIES_KEY)) {
            stackForFragments.push(UPDATE_ENTRY_KEY);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mUpdateEntryFragment, UPDATE_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();
        }else{
            getSupportFragmentManager().popBackStack();
            stackForFragments.pop();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mUpdateEntryFragment, UPDATE_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();

            stackForFragments.push(UPDATE_ENTRY_KEY);
        }

        Log.i("STACK STATUS", stackForFragments.toString());

    }

    @Override
    public void onBackPressed() {
        if(!stackForFragments.peek().equals(ALL_ENTRIES_KEY)){
            stackForFragments.pop();
        }

        super.onBackPressed();

        Log.i("STACK STATUS", stackForFragments.toString());

    }

    @Override
    public void entryClicked(Entry entry) {
        Intent intent = new Intent(this , FullDetails.class);
        intent.putExtra("Entry", entry);
        startActivity(intent);
    }

    @Override
    public void entryDeleted(Entry entry, int position) {
        Database database = new Database(this, Database.DATABASE_NAME, null, Database.VERSION);
        database.deleteEntry(entry.getId());
        Snackbar.make(findViewById(android.R.id.content), "Entry deleted.", Snackbar.LENGTH_LONG).show();

        //RELOAD FRAGMENT

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(DELETE_FRAGMENT_TAG);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.detach(fragment);

        transaction.attach(fragment);

        transaction.commit();

    }

    @Override
    public void updateEntry(Entry entry, String title, String description) {
        Database database = new Database(this, Database.DATABASE_NAME, null, Database.VERSION);
        database.updateEntry(entry);

        //RELOAD FRAGMENT

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(UPDATE_FRAGMENT_TAG);

        getSupportFragmentManager().beginTransaction()
                .detach(fragment)
                .attach(fragment)
                .commit();

        Snackbar.make(findViewById(android.R.id.content), "Entry updated.", Snackbar.LENGTH_LONG).show();

    }
}