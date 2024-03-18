package com.example.tp04;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    Integer index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nomInput = findViewById(R.id.name);
        EditText prenomInput = findViewById(R.id.prenom);
        Button getRowButton = findViewById(R.id.btn);
        Button insert = findViewById(R.id.insert_btn);
        TextView idView = findViewById(R.id.id);
        TextView sizeView = findViewById(R.id.sizeofdb);
        Button getSize = findViewById(R.id.database_size);
        Button deleteAll = findViewById(R.id.delete);



        dbHelper = new DatabaseHelper(this);
        dbHelper.insertData("Mouaadh",  "stuv1617");
        dbHelper.insertData("Hisoka", "abcd1234");
        dbHelper.insertData("Fethi", "cdsf4578");
        dbHelper.insertData("Sid Ahmed", "ghij91011");
        dbHelper.insertData("Khalil",  "klmn1213");
        dbHelper.insertData("Zakarya",  "opqr1415");

        dbHelper.insertData("Youssef",  "wxyz1819");
        Cursor cursor = dbHelper.getData();

        getRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cursor.moveToNext() && dbHelper.dbSize() != 0) {
                    String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                    String nom = cursor.getString(cursor.getColumnIndexOrThrow("v_username"));
                    String prenom = cursor.getString(cursor.getColumnIndexOrThrow("v_password"));
                    index+=1;
                    idView.setText(Integer.toString(index));
                    nomInput.setText(nom);
                    prenomInput.setText(prenom);

                } else if (dbHelper.dbSize() != 0) {
                    cursor.moveToFirst();
                    index=1;
                    idView.setText(Integer.toString(index));
                    String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                    String nom = cursor.getString(cursor.getColumnIndexOrThrow("v_username"));
                    String prenom = cursor.getString(cursor.getColumnIndexOrThrow("v_password"));

                    nomInput.setText(nom);
                    prenomInput.setText(prenom);
                    Toast.makeText(MainActivity.this, "We're back to the beginning", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MainActivity.this, "Your Database is empty", Toast.LENGTH_SHORT).show();
                }

            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNom = nomInput.getText().toString();
                String newPrenom = prenomInput.getText().toString();
                if(newNom.isEmpty() || newPrenom.isEmpty()){
                    Toast.makeText(MainActivity.this, "Invalid Data\nnom or prenom is empty", Toast.LENGTH_SHORT).show();
                }else {
                    dbHelper.insertData(newNom,newPrenom);
                    nomInput.setText("");
                    prenomInput.setText("");

                    Toast.makeText(MainActivity.this, "Insert Data successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        getSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s =dbHelper.dbSize();
                String ss = Integer.toString(s);
                sizeView.setText(ss);
            }
        });
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAllData();
                nomInput.setText("");
                prenomInput.setText("");
                index=0;
                idView.setText("");
                Toast.makeText(MainActivity.this, "Database is empty Now", Toast.LENGTH_SHORT).show();
            }
        });
    }
}







