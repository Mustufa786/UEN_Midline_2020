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

import edu.aku.hassannaqvi.uen_midline.CONSTANTS;
import edu.aku.hassannaqvi.uen_midline.R;
import edu.aku.hassannaqvi.uen_midline.contracts.ChildContract;
import edu.aku.hassannaqvi.uen_midline.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_midline.core.MainApp;
import edu.aku.hassannaqvi.uen_midline.databinding.ActivitySectionJ03Binding;
import edu.aku.hassannaqvi.uen_midline.utils.JSONUtils;
import edu.aku.hassannaqvi.uen_midline.utils.Util;

public class SectionJ03Activity extends AppCompatActivity {

    ActivitySectionJ03Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_j03);
        bi.setCallback(this);
        setupSkips();

        if (getIntent().getBooleanExtra(CONSTANTS.SEC_J_FLAG, false)) {
            bi.fldGrpCVj105.setVisibility(View.GONE);
            bi.fldGrpCVj106.setVisibility(View.GONE);
        }

        //setCoronaFields();
    }

    private void setCoronaFields() {

        if (!MainApp.selectedKishMWRA.isCoronaCase()) {
            bi.j123q.setVisibility(View.GONE);
            bi.j123r.setVisibility(View.GONE);
            bi.j123s.setVisibility(View.GONE);
            bi.j123t.setVisibility(View.GONE);
            bi.j123u.setVisibility(View.GONE);
            bi.j123v.setVisibility(View.GONE);
        }
    }


    private void setupSkips() {

        //j105
        bi.j105.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.j105a.getId()) {
                bi.fldGrpCVj106.setVisibility(View.GONE);
                bi.fldGrpCVj108.setVisibility(View.GONE);
                bi.fldGrpCVj109.setVisibility(View.GONE);
                bi.fldGrpCVj111.setVisibility(View.GONE);
                bi.fldGrpCVj112.setVisibility(View.GONE);
                bi.fldGrpCVj113.setVisibility(View.GONE);
                bi.fldGrpCVj114.setVisibility(View.GONE);
                bi.fldGrpCVj115.setVisibility(View.GONE);
                bi.fldGrpCVj116.setVisibility(View.GONE);
                bi.fldGrpCVj117.setVisibility(View.GONE);
                bi.fldGrpCVj118.setVisibility(View.GONE);
                bi.fldGrpCVj119.setVisibility(View.GONE);
                bi.fldGrpCVj120.setVisibility(View.GONE);
                bi.fldGrpCVj121.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVj106);
                Clear.clearAllFields(bi.fldGrpCVj108);
                Clear.clearAllFields(bi.fldGrpCVj109);
                Clear.clearAllFields(bi.fldGrpCVj111);
                Clear.clearAllFields(bi.fldGrpCVj112);
                Clear.clearAllFields(bi.fldGrpCVj113);
                Clear.clearAllFields(bi.fldGrpCVj114);
                Clear.clearAllFields(bi.fldGrpCVj115);
                Clear.clearAllFields(bi.fldGrpCVj116);
                Clear.clearAllFields(bi.fldGrpCVj117);
                Clear.clearAllFields(bi.fldGrpCVj118);
                Clear.clearAllFields(bi.fldGrpCVj119);
                Clear.clearAllFields(bi.fldGrpCVj120);
                Clear.clearAllFields(bi.fldGrpCVj121);
            } else {
                bi.fldGrpCVj106.setVisibility(View.VISIBLE);
                bi.fldGrpCVj108.setVisibility(View.VISIBLE);
                bi.fldGrpCVj109.setVisibility(View.VISIBLE);
                bi.fldGrpCVj111.setVisibility(View.VISIBLE);
                bi.fldGrpCVj112.setVisibility(View.VISIBLE);
                bi.fldGrpCVj113.setVisibility(View.VISIBLE);
                bi.fldGrpCVj114.setVisibility(View.VISIBLE);
                bi.fldGrpCVj115.setVisibility(View.VISIBLE);
                bi.fldGrpCVj116.setVisibility(View.VISIBLE);
                bi.fldGrpCVj117.setVisibility(View.VISIBLE);
                bi.fldGrpCVj118.setVisibility(View.VISIBLE);
                bi.fldGrpCVj119.setVisibility(View.VISIBLE);
                bi.fldGrpCVj120.setVisibility(View.VISIBLE);
                bi.fldGrpCVj121.setVisibility(View.VISIBLE);
            }
        });


        //j106
        bi.j106.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.j106a.getId()) {
                bi.fldGrpCVj108.setVisibility(View.VISIBLE);
                bi.fldGrpCVj109.setVisibility(View.VISIBLE);
                bi.fldGrpCVj111.setVisibility(View.VISIBLE);
                bi.fldGrpCVj112.setVisibility(View.VISIBLE);
                bi.fldGrpCVj113.setVisibility(View.VISIBLE);
                bi.fldGrpCVj114.setVisibility(View.VISIBLE);
                bi.fldGrpCVj115.setVisibility(View.VISIBLE);
                bi.fldGrpCVj116.setVisibility(View.VISIBLE);
                bi.fldGrpCVj117.setVisibility(View.VISIBLE);
                bi.fldGrpCVj118.setVisibility(View.VISIBLE);
                bi.fldGrpCVj119.setVisibility(View.VISIBLE);
                bi.fldGrpCVj120.setVisibility(View.VISIBLE);
                bi.fldGrpCVj121.setVisibility(View.VISIBLE);
            } else {
                bi.fldGrpCVj108.setVisibility(View.GONE);
                bi.fldGrpCVj109.setVisibility(View.GONE);
                bi.fldGrpCVj111.setVisibility(View.GONE);
                bi.fldGrpCVj112.setVisibility(View.GONE);
                bi.fldGrpCVj113.setVisibility(View.GONE);
                bi.fldGrpCVj114.setVisibility(View.GONE);
                bi.fldGrpCVj115.setVisibility(View.GONE);
                bi.fldGrpCVj116.setVisibility(View.GONE);
                bi.fldGrpCVj117.setVisibility(View.GONE);
                bi.fldGrpCVj118.setVisibility(View.GONE);
                bi.fldGrpCVj119.setVisibility(View.GONE);
                bi.fldGrpCVj120.setVisibility(View.GONE);
                bi.fldGrpCVj121.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVj108);
                Clear.clearAllFields(bi.fldGrpCVj109);
                Clear.clearAllFields(bi.fldGrpCVj111);
                Clear.clearAllFields(bi.fldGrpCVj112);
                Clear.clearAllFields(bi.fldGrpCVj113);
                Clear.clearAllFields(bi.fldGrpCVj114);
                Clear.clearAllFields(bi.fldGrpCVj115);
                Clear.clearAllFields(bi.fldGrpCVj116);
                Clear.clearAllFields(bi.fldGrpCVj117);
                Clear.clearAllFields(bi.fldGrpCVj118);
                Clear.clearAllFields(bi.fldGrpCVj119);
                Clear.clearAllFields(bi.fldGrpCVj120);
                Clear.clearAllFields(bi.fldGrpCVj121);
            }
        });

        bi.j108.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.j108b.getId()) {
                Clear.clearAllFields(bi.fldGrpSectionJ0301);
                Clear.clearAllFields(bi.fldGrpCVj123);
            }

        }));

        bi.j111.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i != bi.j111a.getId()) {
                Clear.clearAllFields(bi.fldGrpSectionJ0302);
            }

        }));

        bi.j115.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i != bi.j115a.getId()) {
                Clear.clearAllFields(bi.fldGrpCVj116);
            }

        }));


        //j117
        bi.j117.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.j117a.getId()) {
                bi.fldGrpCVj118.setVisibility(View.VISIBLE);
            } else {
                bi.fldGrpCVj118.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVj118);
            }
        });

        /*bi.j117.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i != bi.j117a.getId()) {
                Clear.clearAllFields(bi.fldGrpCVj118);
            }

        }));*/

        bi.j120.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i != bi.j120a.getId()) {
                Clear.clearAllFields(bi.fldGrpCVj121);
            }

        }));

        //j122
        /*bi.j122.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.j122a.getId()) {
                bi.fldGrpCVj123.setVisibility(View.VISIBLE);
            } else {
                bi.fldGrpCVj123.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVj123);
            }
        });*/

        //j12398
        bi.j12398.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.j123check, false);
                bi.j123check.setTag("-1");
            } else {
                Clear.clearAllFields(bi.j123check, true);
                bi.j123check.setTag("0");
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
                startActivity(new Intent(this, SectionMActivity.class));
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

        JSONObject json = new JSONObject();

        json.put("j105",
                bi.j105a.isChecked() ? "1" :
                        bi.j105b.isChecked() ? "2" :
                                "0");

        json.put("j106",
                bi.j106a.isChecked() ? "1" :
                        bi.j106b.isChecked() ? "2" :
                                bi.j106c.isChecked() ? "98" :
                                        "0");

        json.put("j108",
                bi.j108a.isChecked() ? "1" :
                        bi.j108b.isChecked() ? "2" :
                                bi.j108c.isChecked() ? "98" :
                                        "0");

        json.put("j109",
                bi.j109a.isChecked() ? "1" :
                        bi.j109b.isChecked() ? "2" :
                                bi.j109c.isChecked() ? "98" :
                                        "0");

        json.put("j111",
                bi.j111a.isChecked() ? "1" :
                        bi.j111b.isChecked() ? "2" :
                                bi.j111c.isChecked() ? "98" :
                                        "0");

        json.put("j112",
                bi.j112a.isChecked() ? "1" :
                        bi.j112b.isChecked() ? "2" :
                                bi.j112c.isChecked() ? "98" :
                                        "0");

        json.put("j113", bi.j113.getText().toString());

        json.put("j114",
                bi.j114a.isChecked() ? "1" :
                        bi.j114b.isChecked() ? "2" :
                                bi.j114c.isChecked() ? "98" :
                                        "0");

        json.put("j115",
                bi.j115a.isChecked() ? "1" :
                        bi.j115b.isChecked() ? "2" :
                                bi.j115c.isChecked() ? "98" :
                                        "0");

        json.put("j116", bi.j116.getText().toString());

        json.put("j117",
                bi.j117a.isChecked() ? "1" :
                        bi.j117b.isChecked() ? "2" :
                                bi.j117c.isChecked() ? "98" :
                                        "0");

        json.put("j118", bi.j118.getText().toString());

        json.put("j119",
                bi.j119a.isChecked() ? "1" :
                        bi.j119b.isChecked() ? "2" :
                                bi.j119c.isChecked() ? "98" :
                                        "0");

        json.put("j120",
                bi.j120a.isChecked() ? "1" :
                        bi.j120b.isChecked() ? "2" :
                                bi.j120c.isChecked() ? "98" :
                                        "0");

        json.put("j121", bi.j121.getText().toString());

        json.put("j122",
                bi.j122a.isChecked() ? "1" :
                        bi.j122b.isChecked() ? "2" :
//                                bi.j122c.isChecked() ? "98" :
                                "0");

        json.put("j123a", bi.j123a.isChecked() ? "1" : "0");
        json.put("j123b", bi.j123b.isChecked() ? "2" : "0");
        json.put("j123c", bi.j123c.isChecked() ? "3" : "0");
        json.put("j123d", bi.j123d.isChecked() ? "4" : "0");
        json.put("j123e", bi.j123e.isChecked() ? "5" : "0");
        json.put("j123f", bi.j123f.isChecked() ? "6" : "0");
        json.put("j123g", bi.j123g.isChecked() ? "7" : "0");
        json.put("j123h", bi.j123h.isChecked() ? "8" : "0");
        json.put("j123i", bi.j123i.isChecked() ? "9" : "0");
        json.put("j123j", bi.j123j.isChecked() ? "10" : "0");
        json.put("j123k", bi.j123k.isChecked() ? "11" : "0");
        json.put("j123l", bi.j123l.isChecked() ? "12" : "0");
        json.put("j123m", bi.j123m.isChecked() ? "13" : "0");
        json.put("j123n", bi.j123n.isChecked() ? "14" : "0");
        json.put("j123o", bi.j123o.isChecked() ? "15" : "0");
        json.put("j123p", bi.j123p.isChecked() ? "16" : "0");
        json.put("j123q", bi.j123q.isChecked() ? "17" : "0");
        json.put("j123r", bi.j123r.isChecked() ? "18" : "0");
        json.put("j123s", bi.j123s.isChecked() ? "19" : "0");
        json.put("j123t", bi.j123t.isChecked() ? "20" : "0");
        json.put("j123u", bi.j123u.isChecked() ? "21" : "0");
        json.put("j123v", bi.j123v.isChecked() ? "22" : "0");
        json.put("j12398", bi.j12398.isChecked() ? "98" : "0");
        json.put("j12396", bi.j12396.isChecked() ? "96" : "0");
        json.put("j12396x", bi.j12396x.getText().toString());

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.child.getsJ()), json);

            MainApp.child.setsJ(String.valueOf(json_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionJ03);
    }

    public void BtnEnd() {
        Util.openEndActivity(this);
    }

}
