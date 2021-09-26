package com.example.myapplicationone;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_credits);
        TextView noteView3 = (TextView) findViewById(R.id.textView3);
        TextView noteView5 = (TextView) findViewById(R.id.textView5);
        TextView noteView9 = (TextView) findViewById(R.id.textView9);

        noteView3.setClickable(true);
        noteView5.setClickable(true);
        noteView9.setClickable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            noteView3.setText(Html.fromHtml("<a href=" + getResources().getString(R.string.My_Name_URL) + ">" + getResources().getString(R.string.My_Name_Text) + "</a>", Html.FROM_HTML_MODE_LEGACY));
            noteView5.setText(Html.fromHtml("<a href=" + getResources().getString(R.string.Guidance_by_URL) + ">" + getResources().getString(R.string.Guidance_by_Text) + "</a>", Html.FROM_HTML_MODE_LEGACY));
            noteView9.setText(Html.fromHtml("<a href=" + getResources().getString(R.string.Github_link_URL) + ">" + getResources().getString(R.string.Github_link_Text) + "</a>", Html.FROM_HTML_MODE_LEGACY));
        } else {
            noteView3.setText(Html.fromHtml("<a href=" + getResources().getString(R.string.My_Name_URL) + ">" + getResources().getString(R.string.My_Name_Text) + "</a>"));
            noteView5.setText(Html.fromHtml("<a href=" + getResources().getString(R.string.Guidance_by_URL) + ">" + getResources().getString(R.string.Guidance_by_Text) + "</a>"));
            noteView9.setText(Html.fromHtml("<a href=" + getResources().getString(R.string.Github_link_URL) + ">" + getResources().getString(R.string.Github_link_Text) + "</a>"));
        }
        noteView3.setMovementMethod(LinkMovementMethod.getInstance());
        noteView5.setMovementMethod(LinkMovementMethod.getInstance());
        noteView9.setMovementMethod(LinkMovementMethod.getInstance());

    }
}