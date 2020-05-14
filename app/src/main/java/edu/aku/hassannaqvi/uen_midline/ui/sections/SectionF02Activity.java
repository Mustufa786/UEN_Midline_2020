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
import edu.aku.hassannaqvi.uen_midline.databinding.ActivitySectionF02Binding;
import edu.aku.hassannaqvi.uen_midline.utils.JSONUtils;
import edu.aku.hassannaqvi.uen_midline.utils.Util;

public class SectionF02Activity extends AppCompatActivity {

    ActivitySectionF02Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f02);
        bi.setCallback(this);

        setUIComponent();
    }

    private void setUIComponent() {


        bi.f121.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i != bi.f121a.getId()) {
                Clear.clearAllFields(bi.fldGrp2223);
            }

        }));


        bi.f1211.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.f1211.getId()) {
                Clear.clearAllFields(bi.fldGrpCVf1212);
            }

        }));

        bi.f124.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i != bi.f124a.getId()) {
                Clear.clearAllFields(bi.fldGrp2531);
            }
        }));

        //f128g
        bi.f128g.setOnCheckedChangeListener((compoundButton, b) -> {

            if (b) {
                Clear.clearAllFields(bi.fldGrp2532);
                bi.fldGrp2532.setVisibility(View.GONE);
            } else
                bi.fldGrp2532.setVisibility(View.VISIBLE);

        });

        bi.f121.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i != bi.f121a.getId()) {
                Clear.clearAllFields(bi.fldGrp2223);
            }

        }));

        bi.f1212c.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                bi.f1212check03.setVisibility(View.VISIBLE);
            else {
                Clear.clearAllFields(bi.f1212check03);
                bi.f1212check03.setVisibility(View.GONE);
            }
        });

        bi.f129.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.f129a.getId()) {
                Clear.clearAllFields(bi.fldGrpCVf130);
            }
        }));

        bi.f129.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.f129b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVf131);
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
                startActivity(new Intent(SectionF02Activity.this, SectionGActivity.class));
            }

        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesKishMWRAColumn(KishMWRAContract.SingleKishMWRA.COLUMN_SF, MainApp.kish.getsF());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject f1 = new JSONObject();

        f1.put("f121", bi.f121a.isChecked() ? "1" :
                bi.f121b.isChecked() ? "2" : "0");
        f1.put("f121a", bi.f1211a.isChecked() ? "1" :
                bi.f1211b.isChecked() ? "2" : "0");
        f1.put("f121ba", bi.f1212a.isChecked() ? "1" : "0");
        f1.put("f121bb", bi.f1212b.isChecked() ? "2" : "0");
        f1.put("f121bc", bi.f1212c.isChecked() ? "3" : "0");
        f1.put("f121bd", bi.f1212d.isChecked() ? "3a" : "0");
        f1.put("f121be", bi.f1212e.isChecked() ? "3b" : "0");
        f1.put("f121bf", bi.f1212f.isChecked() ? "3c" : "0");
        f1.put("f121bg", bi.f1212g.isChecked() ? "3d" : "0");

        f1.put("f122", bi.f122.getText().toString());

        f1.put("f123a", bi.f123a.isChecked() ? "1" : "0");
        f1.put("f123b", bi.f123b.isChecked() ? "2" : "0");
        f1.put("f123c", bi.f123c.isChecked() ? "3" : "0");
        f1.put("f123d", bi.f123d.isChecked() ? "4" : "0");
        f1.put("f123e", bi.f123e.isChecked() ? "5" : "0");
        f1.put("f123f", bi.f123f.isChecked() ? "6" : "0");
        f1.put("f123g", bi.f123g.isChecked() ? "7" : "0");
        f1.put("f123h", bi.f123h.isChecked() ? "8" : "0");
        f1.put("f12396", bi.f12396.isChecked() ? "96" : "0");
        f1.put("f12396x", bi.f12396x.getText().toString());

        f1.put("f124", bi.f124a.isChecked() ? "1" :
                bi.f124b.isChecked() ? "2" : "0");

        f1.put("f125a", bi.f125a.isChecked() ? "1" : "0");
        f1.put("f125b", bi.f125b.isChecked() ? "2" : "0");
        f1.put("f125c", bi.f125c.isChecked() ? "3" : "0");
        f1.put("f125d", bi.f125d.isChecked() ? "4" : "0");
        f1.put("f125e", bi.f125e.isChecked() ? "5" : "0");
        f1.put("f125f", bi.f125f.isChecked() ? "6" : "0");

        f1.put("f126a", bi.f126a.isChecked() ? "1" : "0");
        f1.put("f126b", bi.f126b.isChecked() ? "2" : "0");
        f1.put("f126c", bi.f126c.isChecked() ? "3" : "0");
        f1.put("f126d", bi.f126d.isChecked() ? "4" : "0");
        f1.put("f126e", bi.f126e.isChecked() ? "5" : "0");
        f1.put("f126f", bi.f126f.isChecked() ? "6" : "0");
        f1.put("f126g", bi.f126g.isChecked() ? "7" : "0");

        f1.put("f127", bi.f127a.isChecked() ? "1" :
                bi.f127b.isChecked() ? "2" :
                        bi.f127c.isChecked() ? "3" : "0");

        f1.put("f128a", bi.f128a.isChecked() ? "1" : "0");
        f1.put("f128b", bi.f128b.isChecked() ? "2" : "0");
        f1.put("f128c", bi.f128c.isChecked() ? "3" : "0");
        f1.put("f128d", bi.f128d.isChecked() ? "4" : "0");
        f1.put("f128e", bi.f128e.isChecked() ? "5" : "0");
        f1.put("f128f", bi.f128f.isChecked() ? "6" : "0");
        f1.put("f128g", bi.f128g.isChecked() ? "7" : "0");

        f1.put("f129", bi.f129a.isChecked() ? "1" :
                bi.f129b.isChecked() ? "2" : "0");

        f1.put("f130a", bi.f130a.isChecked() ? "1" : "0");
        f1.put("f130b", bi.f130b.isChecked() ? "2" : "0");
        f1.put("f130c", bi.f130c.isChecked() ? "3" : "0");
        f1.put("f130d", bi.f130d.isChecked() ? "4" : "0");
        f1.put("f130e", bi.f130e.isChecked() ? "5" : "0");
        f1.put("f130f", bi.f130f.isChecked() ? "6" : "0");
        f1.put("f130g", bi.f130g.isChecked() ? "7" : "0");
        f1.put("f130h", bi.f130h.isChecked() ? "8" : "0");
        f1.put("f130i", bi.f130i.isChecked() ? "9" : "0");
        f1.put("f101aj", bi.f130j.isChecked() ? "10" : "0");
        f1.put("f101ak", bi.f130k.isChecked() ? "11" : "0");
        f1.put("f101al", bi.f130l.isChecked() ? "12" : "0");
        f1.put("f101am", bi.f130m.isChecked() ? "13" : "0");
        f1.put("f101an", bi.f130n.isChecked() ? "14" : "0");
        f1.put("f101ao", bi.f130o.isChecked() ? "15" : "0");

        f1.put("f131", bi.f131a.isChecked() ? "1" :
                bi.f131b.isChecked() ? "2" :
                        bi.f131c.isChecked() ? "3" : "0");

        f1.put("f132", bi.f132a.isChecked() ? "1" :
                bi.f132b.isChecked() ? "2" :
                        bi.f132c.isChecked() ? "3" : "0");

        f1.put("f133", bi.f133a.isChecked() ? "1" :
                bi.f133b.isChecked() ? "2" :
                        bi.f133c.isChecked() ? "3" : "0");

        f1.put("f134a", bi.f134a.isChecked() ? "1" : "0");
        f1.put("f134b", bi.f134b.isChecked() ? "2" : "0");
        f1.put("f134c", bi.f134c.isChecked() ? "3" : "0");
        f1.put("f134d", bi.f134d.isChecked() ? "4" : "0");
        f1.put("f134e", bi.f134e.isChecked() ? "5" : "0");
        f1.put("f134f", bi.f134f.isChecked() ? "6" : "0");
        f1.put("f134g", bi.f134g.isChecked() ? "7" : "0");
        f1.put("f134h", bi.f134h.isChecked() ? "8" : "0");

        try {
            JSONObject s4_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.kish.getsF()), f1);

            MainApp.kish.setsF(String.valueOf(s4_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionf02);

    }

    public void BtnEnd() {

        Util.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


}
