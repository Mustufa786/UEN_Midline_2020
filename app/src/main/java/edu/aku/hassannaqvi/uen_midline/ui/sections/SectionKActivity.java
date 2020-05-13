package edu.aku.hassannaqvi.uen_midline.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.uen_midline.R;
import edu.aku.hassannaqvi.uen_midline.contracts.KishMWRAContract;
import edu.aku.hassannaqvi.uen_midline.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_midline.core.MainApp;
import edu.aku.hassannaqvi.uen_midline.databinding.ActivitySectionKBinding;
import edu.aku.hassannaqvi.uen_midline.utils.Util;

public class SectionKActivity extends AppCompatActivity {

    ActivitySectionKBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_k);
        bi.setCallback(this);

        setlistener();

    }

    private void setlistener() {

        bi.k101.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.k101b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVk101aa);
            }
        }));

        bi.k102.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.k102b.getId()) {
                Clear.clearAllFields(bi.fldGrpk1022);
            }

        }));


        bi.k105aac.setOnCheckedChangeListener((compoundButton, b) -> {

            if (b) {
                bi.fldGrpCVk106.setVisibility(View.VISIBLE);
                Clear.clearAllFields(bi.fldGrpCVk106);
                bi.k105aab.setText(null);
                bi.k105aaa.setText(null);
                bi.k105aab.setEnabled(false);
                bi.k105aaa.setEnabled(false);
            } else {
                bi.fldGrpCVk106.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVk106);
                bi.k105aab.setText(null);
                bi.k105aaa.setText(null);
                bi.k105aab.setEnabled(true);
                bi.k105aaa.setEnabled(true);
            }
        });

        /*bi.k105.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.k105aac.getId()) {
                Clear.clearAllFields(bi.fldGrpCVk106);
            }
        }));*/

        bi.k1071.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.k1071b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVk1072);
            }
        }));


        bi.k1072c.setOnCheckedChangeListener((compoundButton, b) -> {

            if (b) {
                Clear.clearAllFields(bi.k107check1, true);
                bi.k107check1.setTag("0");
            } else {
                Clear.clearAllFields(bi.k107check1, false);
                bi.k107check1.setTag("-1");
            }
        });

        bi.k1081.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.k1081b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVk1082);
            }
        }));

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
                startActivity(new Intent(this, SectionLActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnEnd() {

        Util.openEndActivity(this);
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesKishMWRAColumn(KishMWRAContract.SingleKishMWRA.COLUMN_SK, MainApp.kish.getsK());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("k101", bi.k101a.isChecked() ? "1" :
                bi.k101b.isChecked() ? "2" : "0");

        json.put("k101aaa", bi.k101aaa.isChecked() ? "1" : "0");
        json.put("k101aab", bi.k101aab.isChecked() ? "2" : "0");
        json.put("k101aac", bi.k101aac.isChecked() ? "3" : "0");
        json.put("k101aad", bi.k101aad.isChecked() ? "4" : "0");
        json.put("k101aae", bi.k101aae.isChecked() ? "5" : "0");
        json.put("k101aaf", bi.k101aaf.isChecked() ? "6" : "0");
        json.put("k101aag", bi.k101aag.isChecked() ? "7" : "0");
        json.put("k101aah", bi.k101aah.isChecked() ? "8" : "0");
        json.put("k101aai", bi.k101aai.isChecked() ? "9" : "0");
        json.put("k101aaj", bi.k101aaj.isChecked() ? "10" : "0");
        json.put("k101aak", bi.k101aak.isChecked() ? "11" : "0");
        json.put("k101aal", bi.k101aal.isChecked() ? "12" : "0");
        json.put("k101aam", bi.k101aam.isChecked() ? "13" : "0");

        json.put("k102", bi.k102a.isChecked() ? "1" :
                bi.k102b.isChecked() ? "2" : "0");

        json.put("k103", bi.k103a.isChecked() ? "1" :
                bi.k103b.isChecked() ? "2" : "0");

        json.put("k104a", bi.k104a.isChecked() ? "1" : "0");
        json.put("k104b", bi.k104b.isChecked() ? "2" : "0");
        json.put("k104c", bi.k104c.isChecked() ? "3" : "0");
        json.put("k104d", bi.k104d.isChecked() ? "4" : "0");
        json.put("k104e", bi.k104e.isChecked() ? "5" : "0");
        json.put("k104f", bi.k104f.isChecked() ? "6" : "0");
        json.put("k104g", bi.k104g.isChecked() ? "7" : "0");
        json.put("k104h", bi.k104h.isChecked() ? "8" : "0");
        json.put("k104i", bi.k104i.isChecked() ? "9" : "0");
        json.put("k104j", bi.k104j.isChecked() ? "10" : "0");
        json.put("k104k", bi.k104k.isChecked() ? "11" : "0");
        json.put("k104l", bi.k104l.isChecked() ? "12" : "0");
        json.put("k104m", bi.k104m.isChecked() ? "13" : "0");

        json.put("k105",
                bi.k105a.isChecked() ? "1" :
                        bi.k105b.isChecked() ? "2" :
                                bi.k105c.isChecked() ? "3" :
                                        bi.k105d.isChecked() ? "4" :
                                                bi.k105e.isChecked() ? "5" :
                                                        bi.k105f.isChecked() ? "6" :
                                                                bi.k105g.isChecked() ? "7" :
                                                                        bi.k105h.isChecked() ? "8" :
                                                                                bi.k105i.isChecked() ? "9" :
                                                                                        "0");


        json.put("k105aaa", bi.k105aaa.getText().toString());
        json.put("k105aab", bi.k105aab.getText().toString());
        json.put("k105aac", bi.k105aac.isChecked() ? "444" : "0");

        json.put("k106a", bi.k106a.isChecked() ? "1" : "0");
        json.put("k106b", bi.k106b.isChecked() ? "2" : "0");
        json.put("k106c", bi.k106c.isChecked() ? "3" : "0");
        json.put("k106d", bi.k106d.isChecked() ? "4" : "0");
        json.put("k106e", bi.k106e.isChecked() ? "5" : "0");
        json.put("k106f", bi.k106f.isChecked() ? "6" : "0");
        json.put("k106g", bi.k106g.isChecked() ? "7" : "0");
        json.put("k106h", bi.k106h.isChecked() ? "8" : "0");
        json.put("k10696", bi.k10696.isChecked() ? "96" : "0");
        json.put("k10696x", bi.k10696x.getText().toString());

        json.put("k107", bi.k1071a.isChecked() ? "1"
                : bi.k1071b.isChecked() ? "2"
                : "0");

        json.put("k1072a", bi.k1072a.isChecked() ? "1" : "0");
        json.put("k1072b", bi.k1072b.isChecked() ? "2" : "0");
        json.put("k1072c", bi.k1072c.isChecked() ? "3" : "0");
        json.put("k1072d", bi.k1072d.isChecked() ? "4" : "0");
        json.put("k1072e", bi.k1072e.isChecked() ? "5" : "0");
        json.put("k1072f", bi.k1072f.isChecked() ? "6" : "0");
        json.put("k1072g", bi.k1072g.isChecked() ? "7" : "0");

        json.put("k108", bi.k1081a.isChecked() ? "1"
                : bi.k1081b.isChecked() ? "2"
                : bi.k1081c.isChecked() ? "3"
                : "0");

        json.put("k108a", bi.k1082a.isChecked() ? "1"
                : bi.k1082b.isChecked() ? "2"
                : bi.k1082c.isChecked() ? "3"
                : bi.k1082d.isChecked() ? "4"
                : bi.k1082e.isChecked() ? "5"
                : bi.k1082f.isChecked() ? "6"
                : bi.k1082g.isChecked() ? "7"
                : "0");

        json.put("k109", bi.k109a.isChecked() ? "1" :
                bi.k109ab.isChecked() ? "2" : "0");

        MainApp.kish.setsK(String.valueOf(json));

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionk01);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
