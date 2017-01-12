package de.eregion.scopecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ScopeCalcActivity extends AppCompatActivity {

    private EditText aperture_edit, focallength_edit;

    private TextView fratio_text, mmin_text, mmax_text, epmin_text, epmax_text;

    private int mmin, mmax, epmin, epmax;
    private float fratio;

    public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    protected void calc_and_update() {
/*
formulas used:
f-ratio = (float)(focallength/aperture)
mmin    = (int)(aperture/7) based on the idea that the human pupil can't dilate to more than 7mm
mmax    = (int)(aperture*2) based on the idea that the exit pupil should not be smaller than 0.5mm
epmin   = (int)(focallength/mmax)
epmax   = (int)(focallength/mmin)
*/
        fratio = (float) (Float.valueOf(focallength_edit.getText().toString()) / Float.valueOf(aperture_edit.getText().toString()));
        fratio_text.setText(String.format("F-Ratio: %3.2f", round(fratio,2)));

        mmin = (int) (Float.valueOf(aperture_edit.getText().toString())/7.0);
        mmin_text.setText(String.format("lowest magnification: %3.0fx", round(mmin,0)));

        mmax = (int) (Float.valueOf(aperture_edit.getText().toString())*2);
        mmax_text.setText(String.format("highest magnification: %3.0fx", round(mmax,0)));

        epmin = (int) (Float.valueOf(focallength_edit.getText().toString())/mmin);
        epmin_text.setText(String.format("(%3.0f mm)", round(epmin,0)));

        epmax = (int) (Float.valueOf(focallength_edit.getText().toString())/mmax);
        epmax_text.setText(String.format("(%3.0f mm)", round(epmax,0)));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scope_calc);

        // find all the screen elements that we need to access
        //mmin_text =
        fratio_text = (TextView) findViewById(R.id.fRatio_textView);
        mmin_text   = (TextView) findViewById(R.id.minMag_textView);
        mmax_text   = (TextView) findViewById(R.id.maxMag_textView);
        epmin_text  = (TextView) findViewById(R.id.minEP_textView);
        epmax_text  = (TextView) findViewById(R.id.maxEP_textView);

        aperture_edit = (EditText) findViewById(R.id.editText_aperture);
        focallength_edit = (EditText) findViewById(R.id.editText_focalLength);

        //attach listeners to the two text fields
        aperture_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean changed) {
                calc_and_update();
            }
        });
        focallength_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean changed) {
                calc_and_update();
            }
        });

        calc_and_update();
    }
}
