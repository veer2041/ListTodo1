package com.example.lovishsoftware.listtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    EditText  editText;
    Button button;
    Dbhandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText=(EditText)findViewById(R.id.ettext);
        final String s=editText.getText().toString();


        db=new Dbhandler(this);

        button=(Button)findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i=new Intent();
                    i.putExtra("TEXT",editText.getText().toString());
                    Log.d("Second Activity",s);

                    setResult(RESULT_OK,i);
                    finish();
                }
        });



    }
}
