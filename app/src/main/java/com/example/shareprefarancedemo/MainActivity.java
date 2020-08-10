package com.example.shareprefarancedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView detailsTextView;
    private EditText userEditText,passwordEditText;
    private Button saveButton, loadButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailsTextView = findViewById(R.id.detalstextViewID);
        userEditText = findViewById(R.id.usernameEditTextID);
        passwordEditText = findViewById(R.id.passwordEditTextID);
        saveButton = findViewById(R.id.saveButtonID);
        loadButton = findViewById(R.id.LoadButtonID);


        saveButton.setOnClickListener(this);
        loadButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.saveButtonID){



            String username = userEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (userEditText.equals("") && passwordEditText.equals("")){

                Toast.makeText(getApplicationContext(),"Please enter some data",Toast.LENGTH_LONG).show();
            }
            else {
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor  editor =  sharedPreferences.edit();
                editor.putString("usernameKey", username);
                editor.putString("paswordKey",password);
                editor.commit();
                userEditText.setText("");
                passwordEditText.setText("");
                Toast.makeText(getApplicationContext(),"Data is Stored successfuly",Toast.LENGTH_LONG).show();
            }




        }

        else if (v.getId()==R.id.LoadButtonID){

            // to read data
            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);

            if (sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")){
                String username = sharedPreferences.getString("usernameKey","Data not Found");
                String password = sharedPreferences.getString("passwordKey","Data not Found");
                detailsTextView.setText( username+"\n"+password);
            }
        }
    }
}
