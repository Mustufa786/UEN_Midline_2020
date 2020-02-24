package edu.aku.hassannaqvi.uen_midline.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.uen_midline.CONSTANTS;
import edu.aku.hassannaqvi.uen_midline.R;
import edu.aku.hassannaqvi.uen_midline.contracts.ChildContract;
import edu.aku.hassannaqvi.uen_midline.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_midline.core.MainApp;
import edu.aku.hassannaqvi.uen_midline.databinding.ActivitySectionJ02Binding;
import edu.aku.hassannaqvi.uen_midline.utils.JSONUtils;
import edu.aku.hassannaqvi.uen_midline.utils.Util;

public class SectionJ02Activity extends AppCompatActivity {


    ActivitySectionJ02Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_j02);
        bi.setCallback(this);

        setUIComponent();

    }

    private void setUIComponent() {

        bi.j10409y.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.j10409y.setMinvalue(CONSTANTS.MINYEAR_IM);

        bi.j10410y.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.j10410y.setMinvalue(CONSTANTS.MINYEAR_IM);

        bi.j10411y.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.j10411y.setMinvalue(CONSTANTS.MINYEAR_IM);

        bi.j10412y.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.j10412y.setMinvalue(CONSTANTS.MINYEAR_IM);

        bi.j10413y.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.j10413y.setMinvalue(CONSTANTS.MINYEAR_IM);

        bi.j10414y.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.j10414y.setMinvalue(CONSTANTS.MINYEAR_IM);

        bi.j10415y.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.j10415y.setMinvalue(CONSTANTS.MINYEAR_IM);

        bi.j10416y.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.j10416y.setMinvalue(CONSTANTS.MINYEAR_IM);

        //Immunization visibility
        int totalMonth = Integer.valueOf(MainApp.indexKishMWRAChild.getAge()) + Integer.valueOf(MainApp.indexKishMWRAChild.getMonthfm());

        if (totalMonth > 2) {
            bi.fldGrpCVj10409.setVisibility(View.VISIBLE);
            bi.fldGrpCVj10410.setVisibility(View.VISIBLE);
        }

        if (totalMonth > 3) {
            bi.fldGrpCVj10411.setVisibility(View.VISIBLE);
            bi.fldGrpCVj10412.setVisibility(View.VISIBLE);
            bi.fldGrpCVj10413.setVisibility(View.VISIBLE);
            bi.fldGrpCVj10414.setVisibility(View.VISIBLE);
        }

        if (totalMonth > 9) {
            bi.fldGrpCVj10415.setVisibility(View.VISIBLE);
        }

        if (totalMonth > 15) {
            bi.fldGrpCVj10416.setVisibility(View.VISIBLE);
        }

        if (totalMonth > 23) {
            bi.fldGrpCVj10417.setVisibility(View.VISIBLE);
        }

    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionJ03Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesChildColumn(ChildContract.SingleChild.COLUMN_SJ, MainApp.child.getsJ());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject j2 = new JSONObject();

        j2.put("j10409d", bi.j10409d.getText().toString());
        j2.put("j10409m", bi.j10409m.getText().toString());
        j2.put("j10409y", bi.j10409y.getText().toString());

        j2.put("j10410d", bi.j10410d.getText().toString());
        j2.put("j10410m", bi.j10410m.getText().toString());
        j2.put("j10410y", bi.j10410y.getText().toString());

        j2.put("j10411d", bi.j10411d.getText().toString());
        j2.put("j10411m", bi.j10411m.getText().toString());
        j2.put("j10411y", bi.j10411y.getText().toString());

        j2.put("j10412d", bi.j10412d.getText().toString());
        j2.put("j10412m", bi.j10412m.getText().toString());
        j2.put("j10412y", bi.j10412y.getText().toString());

        j2.put("j10413d", bi.j10413d.getText().toString());
        j2.put("j10413m", bi.j10413m.getText().toString());
        j2.put("j10413y", bi.j10413y.getText().toString());

        j2.put("j10414d", bi.j10414d.getText().toString());
        j2.put("j10414m", bi.j10414m.getText().toString());
        j2.put("j10414y", bi.j10414y.getText().toString());

        j2.put("j10415d", bi.j10415d.getText().toString());
        j2.put("j10415m", bi.j10415m.getText().toString());
        j2.put("j10415y", bi.j10415y.getText().toString());

        j2.put("j10416d", bi.j10416d.getText().toString());
        j2.put("j10416m", bi.j10416m.getText().toString());
        j2.put("j10416y", bi.j10416y.getText().toString());

        j2.put("j10417d", bi.j10417d.getText().toString());
        j2.put("j10417m", bi.j10417m.getText().toString());
        j2.put("j10417y", bi.j10417y.getText().toString());

        try {
            JSONObject s4_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.child.getsJ()), j2);

            MainApp.child.setsJ(String.valueOf(s4_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionJ02);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

    public void BtnEnd() {

        Util.openEndActivity(this);
    }
}
