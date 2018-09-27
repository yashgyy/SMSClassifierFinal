package com.varshney.smscategoriser;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.varshney.smscategoriser.Adapter.FoodAdapter;
import com.varshney.smscategoriser.CategorisationActivities.CabActivity;
import com.varshney.smscategoriser.CategorisationActivities.FashionActivity;
import com.varshney.smscategoriser.CategorisationActivities.FinanceActivity;
import com.varshney.smscategoriser.CategorisationActivities.FoodActivity;
import com.varshney.smscategoriser.CategorisationActivities.HealthActivity;
import com.varshney.smscategoriser.CategorisationActivities.OtherActivity;
import com.varshney.smscategoriser.CategorisationActivities.TelecomActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rvMainList;
    public static final String TAG = "MA";
    ArrayList<Message> sms;
    //ArrayList<String> msgBodyList;
    public static final String URL = "http://a944223d.ngrok.io";
   //public static final String URL = "http://19c71efd.ngrok.io";

    ArrayList<Message> otherMsgArrayList;
    ArrayList<Message> foodMsgArrayList;
    ArrayList<Message> cabMsgArrayList;
    ArrayList<Message> fashionMsgArrayList;
    ArrayList<Message> financeMsgArrayList;
    ArrayList<Message> healthMsgArrayList;
    ArrayList<Message> telecomMsgArrayList;
    ArrayList<Message> personalMsgArrayList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

             startActivity(new Intent(MainActivity.this,SendSmsActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // sssssssssssssssss

        otherMsgArrayList = new ArrayList<Message>();
        foodMsgArrayList = new ArrayList<Message>();
        cabMsgArrayList = new ArrayList<Message>();
        fashionMsgArrayList = new ArrayList<Message>();
        financeMsgArrayList = new ArrayList<Message>();
        healthMsgArrayList = new ArrayList<Message>();
        telecomMsgArrayList = new ArrayList<Message>();


        if (isNetworkStatusAvailable(getApplicationContext())){
            PermissionManager.askForPermission(this, new String[]{
                            Manifest.permission.READ_SMS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionManager.OnPermissionResultListener() {
                        @Override
                        public void onGranted(String permission) {
                            if (permission.equals(Manifest.permission.READ_SMS)) {
                                ArrayList<Message> msgList;
                                msgList = fetchInbox();
                                // foodAdapter = new FoodAdapter(MainActivity.this,foodMsgArrayList);

                              /*  msgBodyList = new ArrayList<String>();
                                for (int i=0;i<msgList.size();i++) {
                                    msgBodyList.add(msgList.get(i).getBody());
                                }*/
                                //lvSms = (ListView) findViewById(R.id.lvSms);

                                if (msgList!=null){
                                    rvMainList = (RecyclerView) findViewById(R.id.rvMainList);
                                    rvMainList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                    MainAdapter mainAdapter = new MainAdapter(MainActivity.this,msgList);
                                    rvMainList.setAdapter(mainAdapter);
                                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                                /*    rvMainList.setOnLongClickListener(new View.OnLongClickListener() {
                                        @Override
                                        public boolean onLongClick(View v) {
                                            return false;
                                        }
                                    });*/
                                }
                            }
                        }

                        @Override
                        public void onDenied(String permission) {
                            Toast.makeText(MainActivity.this, "PERMISSSION DENIED", Toast.LENGTH_SHORT).show();

                        }
                    }
            );
        }
        else{
            //Toast.makeText(this,"Internet not Available",Toast.LENGTH_LONG).show();
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

            alertDialog.setTitle("Info");
            alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
            //alertDialog.setIcon(R.drawable.alerticon);
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alertDialog.show();
        }

    }
    int count=0;
    public ArrayList<Message> fetchInbox(){
        sms = new ArrayList<Message>();
        Uri uri = Uri.parse("content://sms/");
        //Cursor cursor = getContentResolver().query(uri,null,null,null,"date DESC");
        Cursor cursor = getContentResolver().query(uri,new String[]{"body","thread_id","address"},null,null,"date DESC");
        //Cursor cursor = getContentResolver().query(uri,new String[]{"body","thread_id","date","date_sent","address"},null,null,null);

        //pass null as 2nd Argument to get all the possible details of each message.
        RequestQueue queue = Volley.newRequestQueue(this);
        final JSONObject jsonObject = new JSONObject();

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                //Message ms = new Message();
                String msgData = "\n";
                final Message ms = new Message();
                count++;
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                   // Log.d(TAG, "fetchInbox: "+cursor.getColumnName(idx));
                   // msgData += " " + cursor.getColumnName(idx) +" : "+ cursor.getString(idx)+"$";
                    if (cursor.getColumnName(idx).equals("body")){
                        String b = cursor.getString(idx).toString();
                        ms.setBody(b);
                    }
                    if (cursor.getColumnName(idx).equals("thread_id")){
                        String b = cursor.getString(idx).toString();
                        ms.setThread_id(b);
                    }
                    if (cursor.getColumnName(idx).equals("address")){
                        String b = cursor.getString(idx);
                        ms.setAddress(b);
                    }

                    Log.d(TAG, "fetchInbox: body"+ms.getBody());
                    Log.d(TAG, "fetchInbox: address" +ms.getAddress());
                    Log.d(TAG, "fetchInbox: thread_id"+ms.getThread_id());
                }
                Log.d(TAG, "fetchInbox: "+sms.size());
                try {
                    jsonObject.put("msg",ms.getBody().toLowerCase());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                       /* Log.d(TAG, "onResponse: " + response.toString());*/
                       /* Log.d(TAG, "onResponse: "+ms.getBody());*/
                        try {
                            String msgCat = response.getString("result");
                            Log.d(TAG, "onResponse: MEssage" + msgCat);
                            ms.setCategory(msgCat);

                            if (msgCat.equals("food")){
                               foodMsgArrayList.add(ms);
                                //FoodAdapter adapter = new FoodAdapter(MainActivity.this, foodMsgArrayList);
                                //foodAdapter.updateFoodList(foodMsgArrayList);
                            }
                            else if (msgCat.equals("education")){
                                cabMsgArrayList.add(ms);
                            }else if (msgCat.equals("fashion")){
                                fashionMsgArrayList.add(ms);
                            }else if(msgCat.equals("health")){
                                healthMsgArrayList.add(ms);
                            }else if (msgCat.equals("telecom")){
                                telecomMsgArrayList.add(ms);
                            }else if (msgCat.equals("finance")){
                                financeMsgArrayList.add(ms);
                            }
                            else{
                                otherMsgArrayList.add(ms);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: "+error.toString());
                        Log.d(TAG, "onErrorResponse: "+ms.getBody());

                    }
                });
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(100000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                queue.add(jsonObjectRequest);

                sms.add(ms);
                //sms.add(msgData);
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
        Log.d(TAG, "fetchInbox: "+count);
        return sms;
    }

    public static boolean isNetworkStatusAvailable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
            {
                return netInfos.isConnected();
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.foodActivity) {
            Intent i = new Intent(MainActivity.this,FoodActivity.class);
            i.putExtra("list",(Serializable) foodMsgArrayList);
            startActivity(i);
        }
        else if (id == R.id.cabActivity)
        {
            Intent i = new Intent(MainActivity.this,CabActivity.class);
            i.putExtra("list",(Serializable) cabMsgArrayList);
            startActivity(i);
        }
         else if(id == R.id.healthActivity){
            Intent i = new Intent(MainActivity.this,HealthActivity.class);
            i.putExtra("list",(Serializable) healthMsgArrayList);
            startActivity(i);
        }
        else if(id == R.id.financeActivity){
            Intent i = new Intent(MainActivity.this,FinanceActivity.class);
            i.putExtra("list",(Serializable) financeMsgArrayList);
            startActivity(i);
        }
        else if(id == R.id.fashionActivity){
            Intent i = new Intent(MainActivity.this,FashionActivity.class);
            i.putExtra("list",(Serializable) fashionMsgArrayList);
            startActivity(i);
        }
        else if(id == R.id.telecomActivity){
            Intent i = new Intent(MainActivity.this, TelecomActivity.class);
            i.putExtra("list",(Serializable) telecomMsgArrayList);
            startActivity(i);
        }
        else if(id == R.id.otherActivity){
            Intent i = new Intent(MainActivity.this,OtherActivity.class);
            i.putExtra("list",(Serializable) otherMsgArrayList);
            startActivity(i);
        }
       /* else if (id == R.id.shareLocation)
        {
            startActivity(new Intent(MainActivity.this,ShareUrLocation.class));
        }*/
        else if (id == R.id.aboutUs) {
            startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
        } else if(id == R.id.contactUs){
            startActivity(new Intent(MainActivity.this, ContactUsActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
