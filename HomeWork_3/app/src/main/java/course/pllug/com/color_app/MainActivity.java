package course.pllug.com.color_app;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements
        SeekBar.OnSeekBarChangeListener, View.OnClickListener{


    private ImageView image;
    private SeekBar barRed, barGreen, barBlue;
    private Button buttonRed, buttonOrange, buttonYellow, buttonGreen, buttonBlue, buttonIndigo, buttonViolet;
    private TextView textColor;
    private int red, green, blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        barRed.setOnSeekBarChangeListener(this);
        barGreen.setOnSeekBarChangeListener(this);
        barBlue.setOnSeekBarChangeListener(this);

        buttonRed.setOnClickListener(this);
        buttonOrange.setOnClickListener(this);
        buttonYellow.setOnClickListener(this);
        buttonGreen.setOnClickListener(this);
        buttonBlue.setOnClickListener(this);
        buttonIndigo.setOnClickListener(this);
        buttonViolet.setOnClickListener(this);

        setupImageColor();
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.main_image);

        barRed = (SeekBar) findViewById(R.id.seekbar_red);
        barGreen = (SeekBar) findViewById(R.id.seekbar_green);
        barBlue = (SeekBar) findViewById(R.id.seekbar_blue);

        buttonRed = (Button) findViewById(R.id.main_button_red);
        buttonOrange = (Button) findViewById(R.id.main_button_orange);
        buttonYellow = (Button) findViewById(R.id.main_button_yellow);
        buttonGreen = (Button) findViewById(R.id.main_button_green);
        buttonBlue = (Button) findViewById(R.id.main_button_blue);
        buttonIndigo = (Button) findViewById(R.id.main_button_indigo);
        buttonViolet = (Button) findViewById(R.id.main_button_violet);

        textColor = (TextView) findViewById(R.id.main_current_color);

        red = 0;
        green = 0;
        blue = 0;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.seekbar_red:
                red = i;
                break;

            case R.id.seekbar_green:
                green = i;
                break;

            case R.id.seekbar_blue:
                blue = i;
                break;
        }

        setupImageColor();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void setupImageColor() {
        int color = Color.argb(255, red, green, blue);
        image.setBackgroundColor(color);

        String hex = String.format("#%02x%02x%02x", red, green, blue);
        textColor.setText(red + "/" + green + "/" + blue + " == " + hex);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_button_red:
                red = 255;
                green = 0;
                blue = 0;
                break;

            case R.id.main_button_orange:
                red = 255;
                green = 127;
                blue = 0;
                break;

            case R.id.main_button_yellow:
                red = 255;
                green = 255;
                blue = 0;
                break;

            case R.id.main_button_green:
                red = 0;
                green = 255;
                blue = 0;
                break;

            case R.id.main_button_blue:
                red = 0;
                green = 0;
                blue = 255;
                break;

            case R.id.main_button_indigo:
                red = 75;
                green = 0;
                blue = 130;
                break;

            case R.id.main_button_violet:
                red = 143;
                green = 0;
                blue = 255;
                break;
        }

        barRed.setProgress(red);
        barGreen.setProgress(green);
        barBlue.setProgress(blue);
    }
}