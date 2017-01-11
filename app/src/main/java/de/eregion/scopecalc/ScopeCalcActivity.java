package de.eregion.scopecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ScopeCalcActivity extends AppCompatActivity {

    private EditText aperture_edit, focallength_edit;

    private int mmin, mmax, epmin, epmax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scope_calc);
    }
}
