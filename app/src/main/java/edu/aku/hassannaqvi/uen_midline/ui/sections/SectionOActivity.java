package edu.aku.hassannaqvi.uen_midline.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.uen_midline.R;
import edu.aku.hassannaqvi.uen_midline.contracts.FormsContract;
import edu.aku.hassannaqvi.uen_midline.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_midline.core.MainApp;
import edu.aku.hassannaqvi.uen_midline.databinding.ActivitySectionOBinding;
import edu.aku.hassannaqvi.uen_midline.ui.other.EndingActivity;
import edu.aku.hassannaqvi.uen_midline.utils.Util;

public class SectionOActivity extends AppCompatActivity {

    ActivitySectionOBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_o);
        bi.setCallback(this);

        setUIComponent();
    }

    private void setUIComponent() {

        bi.o101.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.o101b.getId() || i == bi.o101c.getId()) {
                Clear.clearAllFields(bi.fldGrp102103);
            }
        }));

        bi.o103.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.o103b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVo104);
            }
        }));

        bi.o105.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.o105b.getId()) {
                Clear.clearAllFields(bi.fldGrp060708);
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
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SO, MainApp.fc.getsO());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("o101",
                bi.o101a.isChecked() ? "1" :
                        bi.o101b.isChecked() ? "2" :
                                bi.o101c.isChecked() ? "3" :
                                        bi.o10196.isChecked() ? "96" :
                                                "0");
        json.put("o10196x", bi.o10196x.getText().toString());

        json.put("o102",
                bi.o102a.isChecked() ? "1" :
                        bi.o102b.isChecked() ? "2" :
                                "0");

        json.put("o103",
                bi.o103a.isChecked() ? "1" :
                        bi.o103b.isChecked() ? "2" :
                                "0");

        json.put("o104a", bi.o104a.isChecked() ? "1" : "0");
        json.put("o104b", bi.o104b.isChecked() ? "2" : "0");
        json.put("o104c", bi.o104c.isChecked() ? "3" : "0");
        json.put("o104d", bi.o104d.isChecked() ? "4" : "0");
        json.put("o10497", bi.o10497.isChecked() ? "97" : "0");
        /*f1.put("o104xt", bi.o104xt.getText().toString());*/

        json.put("o105", bi.o105a.isChecked() ? "1"
                : bi.o105b.isChecked() ? "2"
                : "0");

        json.put("o106", bi.o106a.isChecked() ? "1"
                : bi.o106b.isChecked() ? "2"
                : "0");

        json.put("o107a", bi.o107a.isChecked() ? "1" : "0");
        json.put("o107b", bi.o107b.isChecked() ? "2" : "0");
        json.put("o107c", bi.o107c.isChecked() ? "3" : "0");
        json.put("o107d", bi.o107d.isChecked() ? "4" : "0");
        json.put("o10797", bi.o10797.isChecked() ? "97" : "0");

        MainApp.fc.setsO(String.valueOf(json));

    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionO);

    }

    public void BtnEnd() {

        Util.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
