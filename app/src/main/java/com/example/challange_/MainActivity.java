package com.example.challange_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText edtName, edtEmail, edtPassword, edtRePassword;
    private CheckBox checkBoxAgree;
    private RadioGroup rgGender;
    private RadioButton radioMale,radioFemale,radioOther;
    private Spinner spinnerCountry;
    private Button btnRegister;
    private ConstraintLayout parent;
    private TextView txtName,txtEmail,txtPassword,txtRePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();
            }
        });
    }
    private void initRegister(){
        Log.d(TAG, "initRegister: Started");

        if (validateData()){
            if (checkBoxAgree.isChecked()){
                showSnackBar();
            }else {
                Toast.makeText(this,"you need to agree to license", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void showSnackBar(){
        Log.d(TAG, "showSnackBar: Started");
        txtName.setVisibility(View.GONE);
        txtEmail.setVisibility(View.GONE);
        txtPassword.setVisibility(View.GONE);
        txtRePassword.setVisibility(View.GONE);

        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String country = spinnerCountry.getSelectedItem().toString();
        String gender = "";
        if (rgGender.getCheckedRadioButtonId() == findViewById(R.id.radioMale).getId()){
            gender = "male";
        }
        if (rgGender.getCheckedRadioButtonId() == findViewById(R.id.radioFemale).getId()){
            gender = "female";
        }
        if (rgGender.getCheckedRadioButtonId() == findViewById(R.id.radioOther).getId()){
            gender = "other";
        }
        String snackText = "Name: "+name+"\nEmail:"+email+"\nGender: "+gender+"\nCountry: " + country;

        Log.d(TAG, "showSnackBar: Snack bar test");
        Snackbar.make(parent,snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtEmail.setText("");
                        edtName.setText("");
                        edtPassword.setText("");
                        edtRePassword.setText("");
                    }
                }).show();
    }
    private boolean validateData(){
        Log.d(TAG, "validateData: started");
        if (edtName.getText().toString().equals("")){
            txtName.setVisibility(View.VISIBLE);
            return false;
        }
        if (edtPassword.getText().toString().equals("")){
            txtPassword.setVisibility(View.VISIBLE);
            return false;
        }
        if (edtEmail.getText().toString().equals("")){
            txtEmail.setVisibility(View.VISIBLE);
            return false;
        }
        if (edtRePassword.getText().toString().equals("")){
            txtRePassword.setVisibility(View.VISIBLE);
            return false;
        }
        if (!edtPassword.getText().toString().equals(edtRePassword.getText().toString())){
            txtRePassword.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }
    private void initViews(){
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassword = findViewById(R.id.edtRePassword);
        checkBoxAgree = findViewById(R.id.checkBoxAgree);
        rgGender = findViewById(R.id.radioGroup);
        spinnerCountry = findViewById(R.id.spinner);
        btnRegister = findViewById(R.id.btnRegister);
        parent = findViewById(R.id.parent);
        txtEmail = findViewById(R.id.txtEmail);
        txtName = findViewById(R.id.txtName);
        txtPassword = findViewById(R.id.txtPassword);
        txtRePassword = findViewById(R.id.txtRePassword);

    }
}