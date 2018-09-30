package com.pals.isitrainingthere;

import android.content.Intent;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pals.isitrainingthere.model.Location;

public class AskQuestion extends AppCompatActivity {

    EditText etLocation;
    Button btSend;
    String loc;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_question);

        etLocation = findViewById(R.id.et_location);
        btSend = findViewById(R.id.bt_send);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Locations");

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loc = etLocation.getText().toString();

                Location location = new Location(loc);
                databaseReference.push().setValue(location);

                Intent sendIntent = new Intent(getApplicationContext(), Answer.class);
                sendIntent.putExtra("loc", loc);
                startActivity(sendIntent);
            }
        });
    }
}
