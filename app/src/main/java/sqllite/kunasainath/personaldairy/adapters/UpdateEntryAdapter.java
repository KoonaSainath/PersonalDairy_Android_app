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

public class UpdateEntryAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context mContext;
    ArrayList<Entry> entries;
    public UpdateEntryAdapter(Context context, ArrayList<Entry> entries){
        this.mContext = context;
        this.entries = entries;
    }

    public interface UpdateFragmentInterface{
        public void updateEntry(Entry entry, String title, String description);
    }
    private UpdateFragmentInterface mInterface;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.each_entry_for_update, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        mInterface = (UpdateFragmentInterface) mContext;

        holder.getSlno().setText(Integer.toString(entries.get(position).getId()));
        holder.getDate().setText(entries.get(position).getDate());
        holder.getTime().setText(entries.get(position).getTime());

        holder.edtTitle.setText(entries.get(position).getTitle());
        holder.edtDesc.setText(entries.get(position).getDescription());

        holder.imgbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title, description;
                title = holder.edtTitle.getText().toString();
                description = holder.edtDesc.getText().toString();
                if(title.length()==0){
                    holder.edtTitle.setError("Title can't be empty");
                    return;
                }
                if(description.length()==0){
                    holder.edtDesc.setError("Description can't be empty");
                    return;
                }

                entries.get(position).setTitle(title);
                entries.get(position).setDescription(description);
                mInterface.updateEntry(entries.get(position), title, description);
            }
        });
    }


    @Override
    public int getItemCount() {
        return entries.size();
    }

}
