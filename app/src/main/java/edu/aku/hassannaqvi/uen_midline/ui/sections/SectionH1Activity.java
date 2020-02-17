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

import edu.aku.hassannaqvi.uen_midline.R;
import edu.aku.hassannaqvi.uen_midline.contracts.KishMWRAContract;
import edu.aku.hassannaqvi.uen_midline.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_midline.core.MainApp;
import edu.aku.hassannaqvi.uen_midline.databinding.ActivitySectionH1Binding;
import edu.aku.hassannaqvi.uen_midline.utils.Util;
import edu.aku.hassannaqvi.uen_midline.validator.ClearClass;

public class SectionH1Activity extends AppCompatActivity {

    ActivitySectionH1Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_h1);
        bi.setCallback(this);
        setupSkips();

    }


    private void setupSkips() {

        //h102
        bi.h102.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId != bi.h102a.getId()) {
                bi.fldGrpCVh103.setVisibility(View.VISIBLE);
                bi.fldGrpCVh104.setVisibility(View.VISIBLE);
            } else {
                ClearClass.ClearAllFields(bi.fldGrpCVh103, null);
                ClearClass.ClearAllFields(bi.fldGrpCVh104, null);
                bi.fldGrpCVh103.setVisibility(View.GONE);
                bi.fldGrpCVh104.setVisibility(View.GONE);
            }
        });


        //h103
        bi.h103.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h103a.getId()) {
                bi.fldGrpCVh104.setVisibility(View.VISIBLE);
            } else {
                ClearClass.ClearAllFields(bi.fldGrpCVh104, null);
                bi.fldGrpCVh104.setVisibility(View.GONE);
            }
        });


        //h105
        bi.h105.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h105a.getId()) {
                bi.fldGrpCVh106.setVisibility(View.VISIBLE);
                bi.fldGrpCVh107.setVisibility(View.VISIBLE);
            } else {
                ClearClass.ClearAllFields(bi.fldGrpCVh106, null);
                ClearClass.ClearAllFields(bi.fldGrpCVh107, null);
                bi.fldGrpCVh106.setVisibility(View.GONE);
                bi.fldGrpCVh107.setVisibility(View.GONE);
            }
        });


        //h110
        bi.h110.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h110a.getId()) {
                bi.fldGrpCVh111.setVisibility(View.VISIBLE);
            } else {
                ClearClass.ClearAllFields(bi.fldGrpCVh111, null);
                bi.fldGrpCVh111.setVisibility(View.GONE);
            }
        });


        //h113
        bi.h113.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h113a.getId()) {
                bi.fldGrpCVh114.setVisibility(View.VISIBLE);
            } else {
                ClearClass.ClearAllFields(bi.fldGrpCVh114, null);
                bi.fldGrpCVh114.setVisibility(View.GONE);
            }
        });


        //h116
        bi.h116.setOnCheckedChangeListener((group, checkedId) -> {

            ClearClass.ClearAllFields(bi.fldGrpCVh117, null);
            ClearClass.ClearAllFields(bi.fldGrpCVh118, null);
            ClearClass.ClearAllFields(bi.fldGrpCVh119, null);
            ClearClass.ClearAllFields(bi.fldGrpCVh120, null);
            bi.fldGrpCVh117.setVisibility(View.GONE);
            bi.fldGrpCVh118.setVisibility(View.GONE);
            bi.fldGrpCVh119.setVisibility(View.GONE);
            bi.fldGrpCVh120.setVisibility(View.GONE);

            if (checkedId == bi.h116a.getId()) {
                bi.fldGrpCVh117.setVisibility(View.VISIBLE);
            } else if (checkedId == bi.h116b.getId()) {
                bi.fldGrpCVh118.setVisibility(View.VISIBLE);
                bi.fldGrpCVh119.setVisibility(View.VISIBLE);
            } else
                bi.fldGrpCVh120.setVisibility(View.VISIBLE);
        });


        //h118
        bi.h118.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h118a.getId()) {
                bi.fldGrpCVh119.setVisibility(View.VISIBLE);
            } else {
                ClearClass.ClearAllFields(bi.fldGrpCVh119, null);
                bi.fldGrpCVh119.setVisibility(View.GONE);
            }
        });


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
                startActivity(new Intent(this, SectionH102Activity.class));
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
        int updcount = db.updatesKishMWRAColumn(KishMWRAContract.SingleKishMWRA.COLUMN_SH1, MainApp.kish.getsH1());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        /*json.put("h101", bi.h101a.isChecked() ? "1" :
                bi.h101b.isChecked() ? "2" :
                        bi.h10198.isChecked() ? "98" : "0");*/
        json.put("h101aw", bi.h101aw.getText().toString());
        //json.put("h101bm", bi.h101bm.getText().toString());

        json.put("h102", bi.h102a.isChecked() ? "1" :
                bi.h102b.isChecked() ? "2" :
                        bi.h10298.isChecked() ? "98" : "0");

        json.put("h103", bi.h103a.isChecked() ? "1" :
                bi.h103b.isChecked() ? "2" :
                        bi.h10398.isChecked() ? "98" : "0");

        json.put("h104a", bi.h104a.isChecked() ? "1" : "0");
        json.put("h104b", bi.h104b.isChecked() ? "2" : "0");
        json.put("h104c", bi.h104c.isChecked() ? "3" : "0");
        json.put("h10496", bi.h10496.isChecked() ? "96" : "0");

        json.put("h10496x", bi.h10496x.getText().toString());

        json.put("h105", bi.h105a.isChecked() ? "1" :
                bi.h105b.isChecked() ? "2" :
                        bi.h10598.isChecked() ? "98" : "0");

        json.put("h106", bi.h106.getText().toString());

        json.put("h107", bi.h107a.isChecked() ? "1" :
                bi.h107b.isChecked() ? "2" :
                        bi.h107c.isChecked() ? "3" :
                                bi.h107d.isChecked() ? "4" :
                                        bi.h10798.isChecked() ? "98" : "0");

        json.put("h108", bi.h108a.isChecked() ? "1" :
                bi.h108b.isChecked() ? "2" :
                        bi.h108c.isChecked() ? "3" :
                                bi.h108d.isChecked() ? "4" :
                                        bi.h10898.isChecked() ? "98" : "0");

        json.put("h109", bi.h109a.isChecked() ? "1" :
                bi.h109b.isChecked() ? "2" :
                        bi.h109c.isChecked() ? "3" :
                                bi.h109d.isChecked() ? "4" :
                                        bi.h109e.isChecked() ? "5" :
                                                bi.h109f.isChecked() ? "6" :
                                                        bi.h109g.isChecked() ? "7" :
                                                                bi.h109h.isChecked() ? "8" : "0");

        json.put("h110", bi.h110a.isChecked() ? "1" :
                bi.h110b.isChecked() ? "2" :
                        bi.h11098.isChecked() ? "98" : "0");

        json.put("h111", bi.h111.getText().toString());
        json.put("h112", bi.h112a.isChecked() ? "1" : bi.h112b.isChecked() ? "2" : bi.h112c.isChecked() ? "3" : "0");

        json.put("h113", bi.h113a.isChecked() ? "1" :
                bi.h113b.isChecked() ? "2" : "0");

        json.put("h114", bi.h114a.isChecked() ? "1" :
                bi.h114b.isChecked() ? "2" :
                        bi.h114c.isChecked() ? "3" :
                                bi.h114d.isChecked() ? "4" :
                                        bi.h114e.isChecked() ? "5" :
                                                bi.h114f.isChecked() ? "6" :
                                                        bi.h114g.isChecked() ? "7" :
                                                                bi.h114h.isChecked() ? "8" : "0");

        json.put("h115a", bi.h115a.isChecked() ? "1" : "0");
        json.put("h115b", bi.h115b.isChecked() ? "2" : "0");
        json.put("h115c", bi.h115c.isChecked() ? "3" : "0");
        json.put("h115d", bi.h115d.isChecked() ? "4" : "0");
        json.put("h115e", bi.h115e.isChecked() ? "5" : "0");
        json.put("h115f", bi.h115f.isChecked() ? "6" : "0");
        json.put("h115g", bi.h115g.isChecked() ? "7" : "0");
        json.put("h115h", bi.h115h.isChecked() ? "8" : "0");
        json.put("h115i", bi.h115i.isChecked() ? "9" : "0");

        json.put("h116", bi.h116a.isChecked() ? "1" :
                bi.h116b.isChecked() ? "2" :
                        bi.h116c.isChecked() ? "3" : "0");

        json.put("h117", bi.h117.getText().toString());

        json.put("h118", bi.h118a.isChecked() ? "1" :
                bi.h118b.isChecked() ? "2" : "0");

        json.put("h119", bi.h119.getText().toString());

        json.put("h120", bi.h120a.isChecked() ? "1" :
                bi.h120b.isChecked() ? "2" :
                        bi.h120c.isChecked() ? "3" :
                                bi.h120d.isChecked() ? "4" :
                                        bi.h120e.isChecked() ? "5" :
                                                bi.h12098.isChecked() ? "98" : "0");

        MainApp.kish.setsH1(String.valueOf(json));
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
