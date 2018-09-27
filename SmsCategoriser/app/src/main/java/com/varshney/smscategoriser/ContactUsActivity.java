package com.varshney.smscategoriser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUsActivity extends AppCompatActivity {


    EditText etName,etMessage,etSubject,etEmail;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        etName = (EditText) findViewById(R.id.etName);
        etMessage = (EditText) findViewById(R.id.etMessage);
        etSubject = (EditText) findViewById(R.id.etSubject);
        etEmail = (EditText) findViewById(R.id.etEmail);

        btnSend = (Button) findViewById(R.id.btnSendSuggestion);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String subject = etSubject.getText().toString();
                String message = etMessage.getText().toString();

                Toast.makeText(ContactUsActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etEmail.setText("");
                etSubject.setText("");
                etMessage.setText("");
            }

        });



    }
}
