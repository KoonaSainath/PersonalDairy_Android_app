package sqllite.kunasainath.personaldairy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sqllite.kunasainath.personaldairy.Entry;
import sqllite.kunasainath.personaldairy.R;
import sqllite.kunasainath.personaldairy.viewholders.ViewHolder;

public class AllEntriesAdapter extends RecyclerView.Adapter<ViewHolder>{

    Context mContext;
    ArrayList<Entry> mEntries;

    public interface AllEntriesInterface{
        public void entryClicked(Entry entry);
    }

    private AllEntriesInterface mInterface;


    public AllEntriesAdapter(Context mContext, ArrayList<Entry> mEntries){
        this.mContext = mContext;
        this.mEntries = mEntries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.each_entry, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mInterface = (AllEntriesInterface) mContext;
        holder.getSlno().setText(Integer.toString(mEntries.get(position).getId()));
        holder.getDate().setText(mEntries.get(position).getDate());
        holder.getTime().setText(mEntries.get(position).getTime());
        holder.getTitle().setText(mEntries.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.entryClicked(mEntries.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEntries.size();
    }


}
