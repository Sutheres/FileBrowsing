package com.example.sutheres.filebrowsing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 1;

    private Uri photoUri;
    private Bitmap thumbnail;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView)findViewById(R.id.imageview);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {


        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            thumbnail = resultData.getParcelableExtra("resultData");
            photoUri = resultData.getData();
            Toast.makeText(this, "Photo URI: " + photoUri.toString(), Toast.LENGTH_SHORT).show();
            image.setImageURI(photoUri);
        } else {
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();
        }
    }

    public void open(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

}
