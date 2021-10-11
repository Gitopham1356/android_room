package com.example.android_room;



import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_room.database.MainData;
import com.example.android_room.database.RoomDB;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    // Initialize variable

    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    public MainAdapter(List<MainData> dataList, Activity context) {
        this.dataList = dataList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_man, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Initialize main data
        MainData data = dataList.get(position);

        // Initialize database
        database = RoomDB.getInstance(context);

        // Set text on text view
        holder.textView.setText(data.getName());
        holder.textView1.setText(data.getName_class());


        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize main data
                MainData mainData = dataList.get(holder.getAdapterPosition());

                // Get id
                int sID = mainData.getId();

                // Get text
                String sText = mainData.getName();
                String sText2 = mainData.getName_class();

                // Create dialog
                Dialog dialog = new Dialog(context);
                // Set content view
                dialog.setContentView(R.layout.dialog_update);

                // Initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;

                // Initialize height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                // Set layout
                dialog.getWindow().setLayout(width,height);

                // Show dialog
                dialog.show();

                // Initialize and assign variable
                EditText editText = dialog.findViewById(R.id.et_editText);
                EditText editText1 = dialog.findViewById(R.id.et_editText1);
                Button btn_update = dialog.findViewById(R.id.btn_update);

                // Set text on edit text
                editText.setText(sText);
                editText1.setText(sText2);

                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Dismiss dialog
                        dialog.dismiss();

                        // Get updated text from edit text
                        String uText = editText.getText().toString().trim();
                        String uText2 = editText1.getText().toString().trim();

                        //Update text in database
                        database.mainDAO().update(sID,uText,uText2);

                        // Notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDAO().getAllData());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.iv_delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                // Initialize main data
                MainData mainData = dataList.get(holder.getAdapterPosition());

                // Delete text from database
                database.mainDAO().delete(mainData);

                // Notify when data is deleted
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        TextView textView, textView1;
        ImageView iv_edit, iv_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Assign variable
            textView = itemView.findViewById(R.id.tv_textView);
            textView1 = itemView.findViewById(R.id.tv_textView1);
            iv_edit = itemView.findViewById(R.id.iv_edit);
            iv_delete = itemView.findViewById(R.id.iv_delete);


        }
    }
}
