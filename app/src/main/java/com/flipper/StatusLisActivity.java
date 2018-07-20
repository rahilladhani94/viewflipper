package com.flipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.flipper.Database.Contact;
import com.flipper.Database.DatabaseHandler;
import com.flipper.adapter.DataBaseAdapter;

import java.util.List;


public class StatusLisActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private DataBaseAdapter mAdapter;
    DatabaseHandler db;
    Button btnDelete, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        db = new DatabaseHandler(this);


        init();
        setOnclick();

    }

    private void setOnclick() {



    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        List<Contact> contacts = db.getAllContacts();
        mAdapter = new DataBaseAdapter(StatusLisActivity.this, contacts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        mAdapter.notifyDataSetChanged();


    }


}
