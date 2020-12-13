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

public class DeleteEntryAdapter extends RecyclerView.Adapter<ViewHolder>{

    Context mContext;
    ArrayList<Entry> entries;
    public DeleteEntryAdapter(Context context, ArrayList<Entry> entries){
        mContext = context;
        this.entries = entries;
    }

    public interface DeleteEntryInterface{
        public void entryDeleted(Entry entry, int position);
    }

    private DeleteEntryInterface mInterface;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.each_entry_for_delete, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        mInterface = (DeleteEntryInterface) mContext;

        holder.getSlno().setText(Integer.toString(entries.get(position).getId()));
        holder.getDate().setText(entries.get(position).getDate());
        holder.getTime().setText(entries.get(position).getTime());
        holder.getTitle().setText(entries.get(position).getTitle());

        holder.imgbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.entryDeleted(entries.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }
}
