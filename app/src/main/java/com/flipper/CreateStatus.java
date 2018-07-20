package com.flipper;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.flipper.Database.Contact;
import com.flipper.Database.DatabaseHandler;
import com.flipper.demo.ViewpagerActivityDemo;

public class CreateStatus extends AppCompatActivity {


    TextView bold,ita,sizesmall,sizelarge;
    EditText edtText;
    DatabaseHandler db;
    TextView txtSend;

    String strfontstyle = "n";
    String strsontsize = "12";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstatus);
        getSupportActionBar().hide();
        db = new DatabaseHandler(this);

        idMapping();
        setonclick();
    }
    private void idMapping() {

        bold= (TextView)findViewById(R.id.bold);
        ita= (TextView)findViewById(R.id.ita);
        txtSend= (TextView)findViewById(R.id.txtSend);
        sizesmall= (TextView)findViewById(R.id.sizesmall);

        sizelarge= (TextView)findViewById(R.id.sizelarge);

        edtText = (EditText) findViewById(R.id.edtText);
    }
    private void setonclick() {
        ita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtText.setTypeface(null, Typeface.ITALIC);
                strfontstyle= "I";// for Italic
// for Italic
            }
        });
        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtText.setTypeface(null, Typeface.BOLD);
                strfontstyle= "B";// for Italic
            }
        });

        sizesmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                strsontsize ="15";
            }
        });
        sizelarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 45);// for Italic
                strsontsize ="45";
            }
        });

        txtSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.addContact(new Contact("" + edtText.getText().toString(), ""+strfontstyle ,""+strsontsize,"#C1E7DA"));

                Intent ii =new Intent(CreateStatus.this,ViewpagerActivityDemo.class);
                startActivity(ii);
            }
        });
    }
}
