package com.pals.isitrainingthere.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pals.isitrainingthere.R;
import com.pals.isitrainingthere.model.Users;

public class UserSignUp extends AppCompatActivity implements View.OnClickListener  {

    EditText etName, etEmailId, etPassword, etConfPassword;
    ImageButton ibNext;
    RadioGroup rgGender;

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

        ibNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.ib_next){
            onNextButtonClicked();
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

        String name = etName.getText().toString();
        String emailId = etEmailId.getText().toString();
        String password = etPassword.getText().toString();
        String confPassword = etConfPassword.getText().toString();
        int selectedGender = rgGender.getCheckedRadioButtonId();
        RadioButton rb =  (findViewById(selectedGender));
        String gender = "Male";
        if (rb != null)  {
            gender = rb.getText().toString();
        }
        /*if (name.isEmpty())  {
            etName.setHint("Please Enter Valid name");
            return;
        }
        if (address.isEmpty())  {
            etAddress.setHint("Please Enter Valid Address");
            return;
        }
        if (lisence.isEmpty())  {
            etLicense.setHint("Please Enter Valid Lisence");
            return;
        }*/
        Users user = new Users(name, emailId, password, confPassword, gender);

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
