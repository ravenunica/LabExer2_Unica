package com.unica.labexer_unica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.lineSeparator;

public class MainActivity extends AppCompatActivity {

    EditText et_username;
    EditText et_password;
    Button save_sharedPref;
    Button save_internal;
    Button next;
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.etUser);
        et_password = (EditText) findViewById(R.id.etPassword);
        save_sharedPref = (Button) findViewById(R.id.btn_SharedPref);
        save_internal = (Button) findViewById(R.id.btn_IntStorage);
        next = (Button) findViewById(R.id.btn_Next);
    }

    public void saveSharedPref (View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", et_username.getText().toString());
        editor.putString("password", et_password.getText().toString());
        editor.commit();
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();

    }
    public void saveInternal (View view) {
        String username = et_username.getText().toString();
        String space = ("\r\n");
        String password = et_password.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(username.getBytes());
            fos.write(space.getBytes());
            fos.write(password.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Message saved!", Toast.LENGTH_SHORT).show();
    }

    public void nextActivity (View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}