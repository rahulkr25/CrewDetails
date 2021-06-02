package com.example.crewdetails;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
     String WikiUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detial_activity);
        TextView nameView=(TextView)findViewById(R.id.name);
        TextView status=(TextView)findViewById(R.id.status);
        TextView agency=(TextView)findViewById(R.id.agency);
        TextView wikiUrl=(TextView)findViewById(R.id.wikiurl);
        ImageView imageView=(ImageView)findViewById(R.id.imageview);
        Intent intent=getIntent();



            Bundle bundle=intent.getExtras();
        String name=bundle.getString(String.valueOf(R.string.CREW_NAME));

            nameView.setText(name);
            status.setText(bundle.getString(String.valueOf(R.string.CREW_STATUS)).toUpperCase());
            agency.setText(bundle.getString(String.valueOf(R.string.CREW_AGENCY)));

            WikiUrl=bundle.getString(String.valueOf(R.string.CREW_WIKI_URL));
            Picasso.get().load(bundle.getString(String.valueOf(R.string.CREW_IMAGE_URL))).into(imageView);




    }

    public void gotoWiki(View view) {
        String url =  WikiUrl;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
