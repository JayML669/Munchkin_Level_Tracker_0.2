package com.example.jay.localsavetest;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.annotation.ColorInt;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.larswerkman.lobsterpicker.LobsterPicker;
import com.larswerkman.lobsterpicker.OnColorListener;
import com.larswerkman.lobsterpicker.sliders.LobsterOpacitySlider;
import com.larswerkman.lobsterpicker.sliders.LobsterShadeSlider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Settings extends BaseActivity {
    LobsterPicker lobsterPicker;
    LobsterShadeSlider shadeSlider;
    LobsterOpacitySlider opacitySlider;
    FrameLayout pickerFrame;
    FrameLayout shadeFrame;
    FrameLayout opacityFrame;
    ImageButton colorButton;
    TextView colorText;
    Button doneButton;


    private String preferencesFileName = "com.example.jay.localsavetest.preferences";
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
/*            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);*/
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };



    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);



        lobsterPicker = (LobsterPicker) findViewById(R.id.lobsterpicker);
        shadeSlider = (LobsterShadeSlider) findViewById(R.id.shadeslider);
        opacitySlider = (LobsterOpacitySlider) findViewById(R.id.opacityslider);
        lobsterPicker.getLayoutParams();
        pickerFrame = (FrameLayout) findViewById(R.id.colorFrame1);
        shadeFrame = (FrameLayout) findViewById(R.id.colorFrame2);
        opacityFrame = (FrameLayout) findViewById(R.id.colorFrame3);
        colorText = (TextView) findViewById(R.id.textView8);
        doneButton = (Button) findViewById(R.id.button7);
        shadeFrame.getLayoutParams().width=lobsterPicker.getLayoutParams().width;
        opacityFrame.getLayoutParams().width=lobsterPicker.getLayoutParams().width;


        colorButton= (ImageButton)this.findViewById(R.id.imageButton);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opacitySlider.setVisibility(View.VISIBLE);
                lobsterPicker.setVisibility(View.VISIBLE);
                shadeSlider.setVisibility(View.VISIBLE);
                pickerFrame.setBackgroundColor(Integer.decode("0x64FFFFFF").intValue());
                pickerFrame.invalidate();
                shadeFrame.setBackgroundColor(Integer.decode("0x64FFFFFF").intValue());
                shadeFrame.invalidate();
                opacityFrame.setBackgroundColor(Integer.decode("0x64FFFFFF").intValue());
                opacityFrame.invalidate();
                colorButton.setVisibility(View.GONE);
                colorText.setVisibility(View.GONE);
                doneButton.setVisibility(View.VISIBLE);


            }});
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opacitySlider.setVisibility(View.GONE);
                lobsterPicker.setVisibility(View.GONE);
                shadeSlider.setVisibility(View.GONE);
                colorButton.setVisibility(View.VISIBLE);
                colorText.setVisibility(View.VISIBLE);
                doneButton.setVisibility(View.GONE);
            }});
//To connect them
        lobsterPicker.addDecorator(shadeSlider);
        lobsterPicker.addDecorator(opacitySlider);


//To retrieve the selected color use
        lobsterPicker.getColor();

//You'r also able to add a listener
        lobsterPicker.addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {


            }

            @Override
            public void onColorSelected(@ColorInt int color) {

                SharedPreferences backgroundColor=getApplicationContext().getSharedPreferences(preferencesFileName, Settings.MODE_PRIVATE);
                SharedPreferences.Editor edit=backgroundColor.edit();
                edit.putString("backgroundColor", Integer.toHexString(color));
                edit.commit();
                getWindow().getDecorView().setBackgroundColor(Long.decode("0x" + backgroundColor.getString("backgroundColor", "0x00000000")).intValue());





            }
        });




        // Set up the user interaction to manually show or hide the system UI.



        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

    }
@Override
protected void onStart(){
    super.onStart();


    opacitySlider.setVisibility(View.GONE);
    lobsterPicker.setVisibility(View.GONE);
    shadeSlider.setVisibility(View.GONE);
    doneButton.setVisibility(View.GONE);
}

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
       // mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
