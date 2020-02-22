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
import edu.aku.hassannaqvi.uen_midline.contracts.KishMWRAContract;
import edu.aku.hassannaqvi.uen_midline.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_midline.core.MainApp;
import edu.aku.hassannaqvi.uen_midline.databinding.ActivitySectionFBinding;
import edu.aku.hassannaqvi.uen_midline.utils.Util;

public class SectionFActivity extends AppCompatActivity {

    ActivitySectionFBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f);
        bi.setCallback(this);

        setUIComponent();
    }

    void setUIComponent() {

        bi.f101Name.setText(new StringBuilder(MainApp.selectedKishMWRA.getName().toUpperCase()).append("\n")
                .append(getResources().getString(R.string.d101))
                .append(":")
                .append(MainApp.selectedKishMWRA.getSerialno()));

        bi.f101.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.f101a.getId()) {
                Clear.clearAllFields(bi.fldGrpCVf101a);
            } else {
                Clear.clearAllFields(bi.fldGrpSectionFA);
                Clear.clearAllFields(bi.fldGrpSectionFB);
            }
        }));


        //f110i
        bi.f11097.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.f110check, false);
                bi.f110check.setTag("-1");
            } else {
                Clear.clearAllFields(bi.f110check, true);
                bi.f110check.setTag("0");
            }
        });

        bi.f112.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.f112a.getId()) {
                Clear.clearAllFields(bi.fldGrpCVf113);
            }
        }));

        bi.f114.setOnCheckedChangeListener((radioGroup, i) -> {

            if (i != bi.f114a.getId()) {
                Clear.clearAllFields(bi.fldGrp1520);
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
                startActivity(new Intent(SectionFActivity.this, SectionF02Activity.class));
            }

        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addKishMWRA(MainApp.kish);
        MainApp.kish.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            MainApp.kish.setUID(MainApp.kish.getDeviceId() + MainApp.kish.get_ID());
            db.updatesKishMWRAColumn(KishMWRAContract.SingleKishMWRA.COLUMN_UID, MainApp.kish.getUID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void SaveDraft() throws JSONException {

        MainApp.kish = new KishMWRAContract();

        JSONObject f1 = new JSONObject();

        f1.put("f101", bi.f101a.isChecked() ? "1" :
                bi.f101b.isChecked() ? "2" : "0");

        f1.put("f101aa", bi.f101aa.isChecked() ? "1" : "0");
        f1.put("f101ab", bi.f101ab.isChecked() ? "2" : "0");
        f1.put("f101ac", bi.f101ac.isChecked() ? "3" : "0");
        f1.put("f101ad", bi.f101ad.isChecked() ? "4" : "0");
        f1.put("f101ae", bi.f101ae.isChecked() ? "5" : "0");
        f1.put("f101af", bi.f101af.isChecked() ? "6" : "0");
        f1.put("f101ag", bi.f101ag.isChecked() ? "7" : "0");
        f1.put("f101ah", bi.f101ah.isChecked() ? "8" : "0");
        f1.put("f101ai", bi.f101ai.isChecked() ? "9" : "0");

        f1.put("f102a", bi.f102a.isChecked() ? "1" : "0");
        f1.put("f102b", bi.f102b.isChecked() ? "2" : "0");
        f1.put("f102c", bi.f102c.isChecked() ? "3" : "0");
        f1.put("f102d", bi.f102d.isChecked() ? "4" : "0");

        f1.put("f103a", bi.f103a.isChecked() ? "1" : "0");
        f1.put("f103b", bi.f103b.isChecked() ? "2" : "0");
        f1.put("f103c", bi.f103c.isChecked() ? "3" : "0");
        f1.put("f103d", bi.f103d.isChecked() ? "4" : "0");
        f1.put("f103e", bi.f103e.isChecked() ? "5" : "0");

        f1.put("f104", bi.f104.getText().toString());

        f1.put("f105", bi.f105a.isChecked() ? "1" :
                bi.f105b.isChecked() ? "2" :
                        bi.f105c.isChecked() ? "3" :
                                bi.f105d.isChecked() ? "4" : "0");

        f1.put("f106", bi.f106.getText().toString());

        f1.put("f107", bi.f107a.isChecked() ? "1" :
                bi.f107b.isChecked() ? "2" :
                        bi.f10796.isChecked() ? "96" : "0");
        f1.put("f10796x", bi.f10796x.getText().toString());

        f1.put("f108", bi.f108a.isChecked() ? "1" :
                bi.f108b.isChecked() ? "2" :
                        bi.f10898.isChecked() ? "98" : "0");
        f1.put("f108aw", bi.f108aw.getText().toString());
        f1.put("f108bm", bi.f108bm.getText().toString());

        f1.put("f109", bi.f109b.isChecked() ? "1" : "0");
        f1.put("f109a", bi.f109a.getText().toString());

        f1.put("f110a", bi.f110a.isChecked() ? "1" : "0");
        f1.put("f110b", bi.f110b.isChecked() ? "2" : "0");
        f1.put("f110c", bi.f110c.isChecked() ? "3" : "0");
        f1.put("f110d", bi.f110d.isChecked() ? "4" : "0");
        f1.put("f110e", bi.f110e.isChecked() ? "5" : "0");
        f1.put("f110f", bi.f110f.isChecked() ? "6" : "0");
        f1.put("f110g", bi.f110g.isChecked() ? "7" : "0");
        f1.put("f110h", bi.f110h.isChecked() ? "8" : "0");
        f1.put("f11097", bi.f11097.isChecked() ? "97" : "0");
        f1.put("f11096", bi.f11096.isChecked() ? "96" : "0");
        f1.put("f11096x", bi.f11096x.getText().toString());

        f1.put("f111", bi.f111a.isChecked() ? "1" :
                bi.f111b.isChecked() ? "2" : "0");

        f1.put("f112", bi.f112a.isChecked() ? "1" :
                bi.f112b.isChecked() ? "2" :
                        bi.f112c.isChecked() ? "98" : "0");

        f1.put("f113", bi.f11398.isChecked() ? "98" :
                bi.f113.getText().toString());

        f1.put("f114", bi.f114a.isChecked() ? "1" :
                bi.f114b.isChecked() ? "2" : "0");

        f1.put("f115a", bi.f115a.isChecked() ? "1" : "0");
        f1.put("f115b", bi.f115b.isChecked() ? "2" : "0");
        f1.put("f115c", bi.f115c.isChecked() ? "3" : "0");
        f1.put("f115d", bi.f115d.isChecked() ? "4" : "0");
        f1.put("f115e", bi.f115e.isChecked() ? "5" : "0");
        f1.put("f115f", bi.f115f.isChecked() ? "6" : "0");

        f1.put("f116a", bi.f116a.isChecked() ? "1" : "0");
        f1.put("f116b", bi.f116b.isChecked() ? "2" : "0");
        f1.put("f116c", bi.f116c.isChecked() ? "3" : "0");
        f1.put("f116d", bi.f116d.isChecked() ? "4" : "0");
        f1.put("f116e", bi.f116e.isChecked() ? "5" : "0");
        f1.put("f116f", bi.f116f.isChecked() ? "6" : "0");
        f1.put("f116g", bi.f116g.isChecked() ? "7" : "0");
        f1.put("f116h", bi.f116h.isChecked() ? "8" : "0");
        f1.put("f116i", bi.f116i.isChecked() ? "9" : "0");

        f1.put("f117", bi.f117a.isChecked() ? "1" :
                bi.f117b.isChecked() ? "2" :
                        bi.f117c.isChecked() ? "3" :
                                bi.f117d.isChecked() ? "4" :
                                        bi.f117e.isChecked() ? "5" : "0");

        f1.put("f118a", bi.f118a.getText().toString());
        f1.put("f118b", bi.f118b.getText().toString());
        f1.put("f118", bi.f11898.isChecked() ? "98" : "0");

        f1.put("f119", bi.f119a.isChecked() ? "1" :
                bi.f119b.isChecked() ? "2" : "0");

        f1.put("f120a", bi.f120a.getText().toString());
        f1.put("f12098", bi.f12098.isChecked() ? "1" : "0");

        MainApp.kish.setsF(String.valueOf(f1));

    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionF);

    }

    public void BtnEnd() {

        Util.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
