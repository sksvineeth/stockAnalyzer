package com.example.stevenabraham.stocksassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.UnsupportedEncodingException;



public class MainActivity extends AppCompatActivity {


    public String type;
    public String similar;
    public String Fifwh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


//
                AnalysisResult analysisResult = dataSnapshot.getValue(AnalysisResult.class);
////
//                Log.i("AppleClose",analysisResult.getChange().toString());
////
                EditText AppleChange = (EditText)findViewById(R.id.editText24);
                EditText AppleFifwl = (EditText)findViewById(R.id.editText25);
                EditText AppleHigh = (EditText)findViewById(R.id.editText26);
                EditText AppleLow = (EditText)findViewById(R.id.editText27);
                EditText AppleOpen = (EditText)findViewById(R.id.editText28);

                AppleChange.setText(analysisResult.getChange().toString());
                AppleFifwl.setText(analysisResult.getFifwl().toString());
                AppleHigh.setText(analysisResult.getHigh().toString());
                AppleLow.setText(analysisResult.getLow().toString());
                AppleOpen.setText(analysisResult.getOpen().toString());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });







    }

    void sendToServer(View v) throws JSONException, IOException {

        EditText tickerField = (EditText) findViewById(R.id.editText);
        String ticker = String.valueOf(tickerField.getText());

        EditText changeEditText = (EditText) findViewById(R.id.editText16);
        EditText weeklowEditText = (EditText) findViewById(R.id.editText17);
        EditText highEditText = (EditText) findViewById(R.id.editText18);
        EditText lowEditText = (EditText) findViewById(R.id.editText19);
        EditText openEditText = (EditText) findViewById(R.id.editText20);
        EditText ipaddressEditText = (EditText) findViewById(R.id.ipaddressEditText);
        TextView  nameTextView  =(TextView)findViewById(R.id.textView14);
        //EditText weekhighEditText = (EditText) findViewById(R.id.editText21);

        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        // 2. make POST request to the given URL
        HttpPost httpPost = new HttpPost(ipaddressEditText.getText().toString());

        String json = "";

        // 3. build jsonObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("Ticker", ticker);


        // 4. convert JSONObject to JSON to String
        json = jsonObject.toString();

        // ** Alternative way to convert Person object to JSON string usin Jackson Lib
        // ObjectMapper mapper = new ObjectMapper();
        // json = mapper.writeValueAsString(person);

        // 5. set json to StringEntity
        StringEntity se = new StringEntity(json);

        // 6. set httpPost Entity
        httpPost.setEntity(se);

        // 7. Set some headers to inform server about the type of the content
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        // 8. Execute POST request to the given URL
        HttpResponse httpResponse = httpclient.execute(httpPost);

        // 9. receive response as inputStream
        InputStream inputStream = httpResponse.getEntity().getContent();


        for(int i=0;i<1000000000;i++);
        for(int i=0;i<1000000000;i++);
        for(int i=0;i<1000000000;i++);
        for(int i=0;i<1000000000;i++);
        for(int i=0;i<1000000000;i++);
        for(int i=0;i<10000000;i++);



        HttpClient httpclient1 = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("https://stock-analysis-14079.firebaseio.com/.json");

        HttpResponse response = httpclient.execute(httpget);

        if (response.getStatusLine().getStatusCode() == 200) {


            String server_response = EntityUtils.toString(response.getEntity());
            Log.i("Server response", server_response);

            server_response = server_response.replace("\"", "'");
            server_response = server_response.replace(" ", "");

            Log.i("Replaced String", server_response);

            JSONObject obj = new JSONObject(server_response);


            //get the text for all fields
            //tickerField.setText(String.valueOf(obj.getJSONObject(ticker).getDouble("Positive")));
            changeEditText.setText(String.valueOf(obj.getJSONObject(ticker).getString("Change")));
            weeklowEditText.setText(String.valueOf(obj.getJSONObject(ticker).getDouble("Fifwl")));
            highEditText.setText(String.valueOf(obj.getJSONObject(ticker).getDouble("High")));
            lowEditText.setText(String.valueOf(obj.getJSONObject(ticker).getDouble("Low")));
            openEditText.setText(String.valueOf(obj.getJSONObject(ticker).getDouble("Open")));
            //weekhighEditText.setText(String.valueOf(obj.getJSONObject(ticker).getDouble("Fifwh")));
            nameTextView.setText(String.valueOf(obj.getJSONObject(ticker).getString("Name")));
            type=String.valueOf(obj.getJSONObject(ticker).getString("Type"));
            similar=String.valueOf(obj.getJSONObject(ticker).getString("Similar"));
            Fifwh=String.valueOf(obj.getJSONObject(ticker).getString("Fifwh"));


        }

    }


    void moreInfo(View v)
    {
        Intent intent = new Intent(MainActivity.this, Details.class);
        intent.putExtra("Type",type);
        intent.putExtra("Similar",similar);
        intent.putExtra("Fifwh",Fifwh);
        startActivity(intent);

    }

}
