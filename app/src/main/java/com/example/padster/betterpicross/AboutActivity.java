package com.example.padster.betterpicross;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

//Using android-about-page from https://github.com/medyo/android-about-page
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element versionElement = new Element();
        versionElement.setTitle("Version 0.1");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Better Picross combines the best features of my favourite Picross apps all into one. ")
                .setImage(R.mipmap.ic_launcher)
                .addItem(versionElement)
                .addGroup("Connect with us")
                .addEmail("imaier8500@conestogac.on.ca")
                .addWebsite("https://therealpadster.github.io/")
//                .addFacebook("the.medy")
//                .addTwitter("medyo80")
//                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addGitHub("therealpadster")
                .addInstagram("mrmacroman")
                .create();

        setContentView(aboutPage);
    }
}
