package com.example.padster.betterpicross;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class TutorialActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();
//        setContentView(R.layout.activity_tutorial);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.tutorial_slide_1_title),
                getResources().getString(R.string.tutorial_slide_1_text),
                R.drawable.ic_slide1,
                getResources().getColor(R.color.lightBlue)));
        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.tutorial_slide_2_title),
                getResources().getString(R.string.tutorial_slide_2_text),
                R.drawable.ic_slide2,
                getResources().getColor(R.color.pink)));
        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.tutorial_slide_3_title),
                getResources().getString(R.string.tutorial_slide_3_text),
                R.drawable.ic_slide3,
                getResources().getColor(R.color.about_play_store_color)));
        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.tutorial_slide_4_title),
                getResources().getString(R.string.tutorial_slide_4_text),
                R.drawable.ic_slide4,
                getResources().getColor(R.color.about_instagram_color)));
        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.tutorial_slide_5_title),
                getResources().getString(R.string.tutorial_slide_5_text),
                R.drawable.ic_slide5,
                getResources().getColor(R.color.about_youtube_color)));
        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.tutorial_slide_6_title),
                getResources().getString(R.string.tutorial_slide_6_text),
                R.drawable.ic_slide6,
                getResources().getColor(R.color.green)));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
//        showSkipButton(false);
//        setProgressButtonEnabled(false);

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        loadMenu();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        loadMenu();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    private void loadMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
