package com.pals.isitrainingthere;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Answer extends AppCompatActivity implements View.OnClickListener {

    Button btYes, btNo, btPart;
    TextView tvLocation;
    boolean didVote = false;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);

        btYes = findViewById(R.id.bt_yes);
        btNo = findViewById(R.id.bt_no);
        btPart = findViewById(R.id.bt_part);

        tvLocation = findViewById(R.id.tv_location);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Locations");

        Intent recIntent = getIntent();
        String loc = recIntent.getStringExtra("loc");
        tvLocation.setText(loc);

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String loc = dataSnapshot.getValue(String.class);
//                tvLocation.setText(loc);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
//            }
//        });

        btYes.setOnClickListener(this);
        btNo.setOnClickListener(this);
        btPart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent sendIntent = new Intent(getApplicationContext(), Result.class);
        if (!didVote) {
            if (v == btYes) {
                sendIntent.putExtra("answer", "Yes");
            } else if (v == btNo) {
                sendIntent.putExtra("answer", "No");
            } else if (v == btPart) {
                sendIntent.putExtra("answer", "Partially");
            }
            startActivity(sendIntent);
            didVote = true;
        } else {
            Toast.makeText(this, "You have already casted the vote", Toast.LENGTH_LONG).show();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        Toast.makeText(getApplicationContext(), "Result is reset", Toast.LENGTH_LONG).show();
                        didVote = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
