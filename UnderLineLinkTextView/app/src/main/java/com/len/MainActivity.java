package com.len;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.len.library.AutoLinkStyleTextView;

public class MainActivity extends AppCompatActivity {
    private AutoLinkStyleTextView autoLinkStyleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoLinkStyleTextView = (AutoLinkStyleTextView) findViewById(R.id.tv);
        autoLinkStyleTextView.setOnClickCallBack(new AutoLinkStyleTextView.ClickCallBack() {
            @Override
            public void onClick(int position, String clickText) {
                Toast.makeText(MainActivity.this, clickText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
