package com.example.android_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_room.database.MainData;
import com.example.android_room.database.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Initialize variable

    EditText editText;
    Button btn_add, btn_reset;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;

    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        editText = findViewById(R.id.et_editText);
        btn_add = findViewById(R.id.btn_add);
        btn_reset = findViewById(R.id.btn_reset);
        recyclerView = findViewById(R.id.rev_recyclerView);

        // Initialize database
        database = RoomDB.getInstance(this);

        // Store database value in data list

         dataList = database.mainDAO().getAllData();

         // Initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);

        // Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);

        // Initialize adapter
        adapter = new MainAdapter(dataList, MainActivity.this);

        // Set adapter
        recyclerView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get string from edit text
                String stext = editText.getText().toString().trim();

                // Check condition
                if(!stext.equals("")){

                    // When text is not empty
                    // Initialize main data
                    MainData mainData = new MainData();

                    // Set text on main data
                    mainData.setText(stext);

                    // Insert text in database
                    database.mainDAO().insert(mainData);

                    // Clear edit text
                    editText.setText("");

                    // Notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDAO().getAllData());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Delete all data from database
                database.mainDAO().reset(dataList);

                // Notify when all data deleted
                dataList.clear();
                dataList.addAll(database.mainDAO().getAllData());
                adapter.notifyDataSetChanged();
            }
        });

    }
}