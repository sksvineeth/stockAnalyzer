package com.example.stevenabraham.stocksassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView typeTextView = (TextView)findViewById(R.id.typeTextView);
        TextView similarTextView = (TextView)findViewById(R.id.similarTextView);
        TextView fifwhTextView = (TextView)findViewById(R.id.FifwhTextView);


        typeTextView.setText(getIntent().getExtras().getString("Type"));
        similarTextView.setText(getIntent().getExtras().getString("Similar"));
        fifwhTextView.setText(getIntent().getExtras().getString("Fifwh"));


    }
}
