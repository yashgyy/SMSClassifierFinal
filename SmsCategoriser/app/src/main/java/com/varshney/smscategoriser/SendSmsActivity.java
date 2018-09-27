package com.varshney.smscategoriser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSmsActivity extends AppCompatActivity {

    EditText etNumber,etMessage;
    Button btnSend;
    String number,msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        btnSend = (Button) findViewById(R.id.btnSend);
        etNumber = (EditText) findViewById(R.id.etNumber);
        etMessage = (EditText) findViewById(R.id.etMessage);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = etMessage.getText().toString();
                number = etNumber.getText().toString();
                int flag=1;
                if (number.length()!=10){
                    etNumber.setError("Invalid Number");
                    flag=0;
                }
                if (msg.length()==0 || msg.equals(" "))
                {
                    etMessage.setError("Empty Field");
                    flag=0;
                }
                if (flag==1){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null, msg , null, null);
                }
                Toast.makeText(SendSmsActivity.this, "Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
