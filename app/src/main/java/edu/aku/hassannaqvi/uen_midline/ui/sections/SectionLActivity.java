package edu.aku.hassannaqvi.uen_midline.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import edu.aku.hassannaqvi.uen_midline.R;
import edu.aku.hassannaqvi.uen_midline.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.uen_midline.contracts.KishMWRAContract;
import edu.aku.hassannaqvi.uen_midline.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_midline.core.MainApp;
import edu.aku.hassannaqvi.uen_midline.databinding.ActivitySectionLBinding;
import edu.aku.hassannaqvi.uen_midline.utils.Util;
import edu.aku.hassannaqvi.uen_midline.validator.ClearClass;

import static edu.aku.hassannaqvi.uen_midline.ui.list_activity.FamilyMembersListActivity.mainVModel;

public class SectionLActivity extends AppCompatActivity {

    ActivitySectionLBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_l);
        bi.setCallback(this);


        setUIComponent();
    }

    private void setUIComponent() {

        bi.l102.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.l102a.getId()) {
                ClearClass.ClearAllFields(bi.fldGrpCVl103, null);
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
                List<FamilyMembersContract> lstU5 = mainVModel.getChildLstU5().getValue();
                Class nextClass = lstU5 != null ? lstU5.size() > 0 ? SectionI1Activity.class : SectionMActivity.class : SectionMActivity.class;
                startActivity(new Intent(this, nextClass));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);
        int updcount = db.updatesKishMWRAColumn(KishMWRAContract.SingleKishMWRA.COLUMN_SL, MainApp.kish.getsL());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() throws JSONException {

        JSONObject f1 = new JSONObject();
        f1.put("l101",
                bi.l101a.isChecked() ? "1" :
                        bi.l101b.isChecked() ? "2" :
                                "0");
        f1.put("l102",
                bi.l102a.isChecked() ? "1" :
                        bi.l102b.isChecked() ? "2" :
                                "0");
        f1.put("l103",
                bi.l103a.isChecked() ? "1" :
                        bi.l103b.isChecked() ? "2" :
                                "0");
        f1.put("l104",
                bi.l104a.isChecked() ? "1" :
                        bi.l104b.isChecked() ? "2" :
                                bi.l104c.isChecked() ? "3" :
                                        bi.l104d.isChecked() ? "4" :
                                                bi.l104e.isChecked() ? "5" :
                                                        bi.l104x.isChecked() ? "96" :
                                                                "0");
        f1.put("l104xt", bi.l104xt.getText().toString());
        f1.put("l105",
                bi.l105a.isChecked() ? "1" :
                        bi.l105b.isChecked() ? "2" :
                                bi.l105c.isChecked() ? "3" :
                                        bi.l105d.isChecked() ? "4" :
                                                "0");
        f1.put("l106",
                bi.l106a.isChecked() ? "1" :
                        bi.l106b.isChecked() ? "2" :
                                bi.l106c.isChecked() ? "3" :
                                        bi.l106d.isChecked() ? "96" :
                                                "0");
        f1.put("l106dt", bi.l106dt.getText().toString());
        f1.put("l107",
                bi.l107a.isChecked() ? "1" :
                        bi.l107b.isChecked() ? "2" :
                                bi.l107c.isChecked() ? "3" :
                                        bi.l107d.isChecked() ? "4" :
                                                bi.l107x.isChecked() ? "96" :
                                                        "0");
        f1.put("l107xt", bi.l107xt.getText().toString());
        f1.put("l108",
                bi.l108a.isChecked() ? "1" :
                        bi.l108b.isChecked() ? "2" :
                                bi.l108c.isChecked() ? "3" :
                                        bi.l108x.isChecked() ? "96" :
                                                "0");
        f1.put("l108xt", bi.l108xt.getText().toString());
        f1.put("l109",
                bi.l109a.isChecked() ? "1" :
                        bi.l109b.isChecked() ? "2" :
                                bi.l109c.isChecked() ? "3" :
                                        bi.l10996.isChecked() ? "96" :
                                                "0");
        f1.put("l10996x", bi.l10996x.getText().toString());
        f1.put("l110",
                bi.l110a.isChecked() ? "1" :
                        bi.l110b.isChecked() ? "2" :
                                bi.l110c.isChecked() ? "3" :
                                        bi.l110d.isChecked() ? "4" :
                                                "0");
        f1.put("l111",
                bi.l111a.isChecked() ? "1" :
                        bi.l111b.isChecked() ? "2" :
                                bi.l111c.isChecked() ? "98" :
                                        bi.l111d.isChecked() ? "444" :
                                                "0");
        f1.put("l112a",
                bi.l112aa.isChecked() ? "1" :
                        bi.l112ab.isChecked() ? "2" :
                                bi.l112ac.isChecked() ? "98" :
                                        "0");
        f1.put("l112b",
                bi.l112ba.isChecked() ? "1" :
                        bi.l112bb.isChecked() ? "2" :
                                bi.l112bc.isChecked() ? "98" :
                                        "0");
        f1.put("l112c",
                bi.l112ca.isChecked() ? "1" :
                        bi.l112cb.isChecked() ? "2" :
                                bi.l112cc.isChecked() ? "98" :
                                        "0");
        f1.put("l112d",
                bi.l112da.isChecked() ? "1" :
                        bi.l112db.isChecked() ? "2" :
                                bi.l112dc.isChecked() ? "98" :
                                        "0");
        f1.put("l112e",
                bi.l112ea.isChecked() ? "1" :
                        bi.l112eb.isChecked() ? "2" :
                                bi.l112ec.isChecked() ? "98" :
                                        "0");
        f1.put("l113a",
                bi.l113aa.isChecked() ? "1" :
                        bi.l113ab.isChecked() ? "2" :
                                bi.l113ac.isChecked() ? "98" :
                                        "0");
        f1.put("l113b",
                bi.l113ba.isChecked() ? "1" :
                        bi.l113bb.isChecked() ? "2" :
                                bi.l113bc.isChecked() ? "98" :
                                        "0");
        f1.put("l113c",
                bi.l113ca.isChecked() ? "1" :
                        bi.l113cb.isChecked() ? "2" :
                                bi.l113cc.isChecked() ? "98" :
                                        "0");
        f1.put("l113d",
                bi.l113da.isChecked() ? "1" :
                        bi.l113db.isChecked() ? "2" :
                                bi.l113dc.isChecked() ? "98" :
                                        "0");
        f1.put("l114",
                bi.l114a.isChecked() ? "1" :
                        bi.l114b.isChecked() ? "2" :
                                bi.l114c.isChecked() ? "3" :
                                        bi.l114x.isChecked() ? "96" :
                                                "0");
        f1.put("l114xt", bi.l114xt.getText().toString());
        f1.put("l115",
                bi.l115a.isChecked() ? "1" :
                        bi.l115b.isChecked() ? "2" :
                                bi.l115c.isChecked() ? "3" :
                                        bi.l115x.isChecked() ? "96" :
                                                "0");
        f1.put("l115xt", bi.l115xt.getText().toString());
        f1.put("l116",
                bi.l116a.isChecked() ? "1" :
                        bi.l116b.isChecked() ? "2" :
                                bi.l116c.isChecked() ? "3" :
                                        bi.l116x.isChecked() ? "96" :
                                                "0");
        f1.put("l116xt", bi.l116xt.getText().toString());
        f1.put("l117",
                bi.l117a.isChecked() ? "1" :
                        bi.l117b.isChecked() ? "2" :
                                bi.l117c.isChecked() ? "3" :
                                        bi.l117x.isChecked() ? "96" :
                                                "0");
        f1.put("l117xt", bi.l117xt.getText().toString());

    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionL);

    }

    public void BtnEnd() {

        Util.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
