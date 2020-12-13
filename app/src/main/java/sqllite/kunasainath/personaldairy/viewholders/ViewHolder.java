package sqllite.kunasainath.personaldairy.viewholders;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import sqllite.kunasainath.personaldairy.R;

public class ViewHolder extends RecyclerView.ViewHolder{

    private TextView slno, date, time, title;
    public ImageButton imgbtnDelete, imgbtnUpdate;
    public EditText edtTitle, edtDesc;

    public ViewHolder(View view){
        super(view);

        slno = view.findViewById(R.id.txt_slno);
        date = view.findViewById(R.id.txt_date);
        time = view.findViewById(R.id.txt_time);
        title = view.findViewById(R.id.txt_title);
        imgbtnDelete = view.findViewById(R.id.imgbtn_delete);
        imgbtnUpdate = view.findViewById(R.id.imgbtn_update);
        edtTitle = view.findViewById(R.id.edt_title);
        edtDesc = view.findViewById(R.id.edt_desc);
    }

    public TextView getSlno() {
        return slno;
    }

    public void setSlno(TextView slno) {
        this.slno = slno;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
}
