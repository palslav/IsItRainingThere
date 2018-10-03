package com.pals.isitrainingthere.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pals.isitrainingthere.R;
import com.pals.isitrainingthere.model.Users;

public class UserSignUp extends AppCompatActivity implements View.OnClickListener  {

    private EditText etName, etEmailId, etPassword, etConfPassword;
    private ImageButton ibNext;
    private RadioGroup rgGender;
    String name, emailId, password, confPassword, gender;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_up);

        etName = (EditText) findViewById(R.id.et_name);
        etEmailId = (EditText) findViewById(R.id.et_email_id);
        etPassword = (EditText) findViewById(R.id.et_password);
        etConfPassword = (EditText) findViewById(R.id.et_conf_password);
        rgGender = (RadioGroup) findViewById(R.id.radio_gender);
        ibNext = (ImageButton) findViewById(R.id.ib_next);

        mAuth = FirebaseAuth.getInstance();

        ibNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.ib_next){
            if(isValidate()) {
                onNextButtonClicked();
                //startActivity(new Intent(UserSignUp.this, UserSignIn.class));
            }
            return;
        }
    }

    public boolean isValidate(){

        name = etName.getText().toString();
        emailId = etEmailId.getText().toString();
        password = etPassword.getText().toString();
        confPassword = etConfPassword.getText().toString();
        int selectedGender = rgGender.getCheckedRadioButtonId();
        RadioButton rb =  (findViewById(selectedGender));
        gender = "Male";
        if (rb != null)  {
            gender = rb.getText().toString();
        }
        if(password.equals(confPassword))
            return true;
        else {
            Toast.makeText(this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            etName.setText("");
            etEmailId.setText("");
            etPassword.setText("");
            etConfPassword.setText("");
        }
    }

    private void onNextButtonClicked() {

        /*if (name.isEmpty())  {
            etName.setHint("Please Enter Valid name");
            return;
        }*/

        mAuth.createUserWithEmailAndPassword(emailId, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    final FirebaseUser firebaseUser = task.getResult().getUser();
                    if(firebaseUser != null){

                        mAuth.signInWithEmailAndPassword(emailId, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                                    databaseReference = databaseReference.child(firebaseUser.getUid());

                                    Users user = new Users(name, emailId, password, gender);

                                    databaseReference.setValue(user);

                                }
                            }
                        });
                    }
                }
            }
        });

        //Intent nextIntent = new Intent(this, ProfileSetupComplete.class);
        //startActivityForResult(nextIntent, 1001);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.show_db_menu, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.item_show_db){
            Intent dbMenuIntent = new Intent(getApplicationContext(), Database.class);
            startActivity(dbMenuIntent);
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }*/
}
