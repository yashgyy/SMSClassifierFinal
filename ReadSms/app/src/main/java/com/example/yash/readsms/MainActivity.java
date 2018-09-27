package com.example.yash.readsms;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Permission;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvSms;
    Button btnWriteToFile;

    public static final String TAG = "MAIN ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvSms = (ListView) findViewById(R.id.lvSms);
        btnWriteToFile = (Button) findViewById(R.id.btnWriteToFile);

        PermissionManager.askForPermission(this, new String[]{
                        Manifest.permission.READ_SMS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionManager.OnPermissionResultListener() {
                    @Override
                    public void onGranted(String permission) {
                        if (permission.equals(Manifest.permission.READ_SMS)) {
                            if (fetchInbox()!=null){
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                                        android.R.layout.activity_list_item,
                                        android.R.id.text1,
                                        fetchInbox());
                                lvSms.setAdapter(adapter);
                                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void onDenied(String permission) {
                        Toast.makeText(MainActivity.this, "PERMISSSION DENIED", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        final File sdcard = Environment.getExternalStorageDirectory();

        btnWriteToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile(new File(sdcard,"myfile.txt"),fetchInbox().toString());
            }
        });
    }

    void write (File file, String data) throws IOException {
        Log.d(TAG, "write: " + file.getAbsolutePath());
        File fileToWrite = file;
        FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
        fileOutputStream.write(data.getBytes());
    }

    public void writeToFile(final File file, final String data){
        PermissionManager.askForPermission(this, new String[]
                {Manifest.permission.READ_SMS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                new PermissionManager.OnPermissionResultListener() {
            @Override
            public void onGranted(String permission) {
                if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        try {
                            write(file, data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onDenied(String permission) {
                Toast.makeText(MainActivity.this,"Write Permission Needed",Toast.LENGTH_LONG).show();
            }
        });
    }
    int count=0;
    public ArrayList<String> fetchInbox(){
        ArrayList<String> sms = new ArrayList<String>();
        Uri uri = Uri.parse("content://sms/");
        Cursor cursor = getContentResolver().query(uri,null,null,null,"date DESC");
        //Cursor cursor = getContentResolver().query(uri,new String[]{"body","thread_id","date","date_sent","address"},null,null,"date DESC");
        //Cursor cursor = getContentResolver().query(uri,new String[]{"body","thread_id","date","date_sent","address"},null,null,null);

        //pass null as 2nd Argument to get all the possible details of each message.
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "\n";
                count++;
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) +": "+ cursor.getString(idx)+"$";
                }
                sms.add(msgData);
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
        Log.d(TAG, "fetchInbox: "+count);
        return sms;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
