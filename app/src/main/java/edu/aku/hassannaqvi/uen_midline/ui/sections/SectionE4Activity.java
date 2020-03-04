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

import edu.aku.hassannaqvi.uen_midline.CONSTANTS;
import edu.aku.hassannaqvi.uen_midline.R;
import edu.aku.hassannaqvi.uen_midline.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.uen_midline.contracts.MortalityContract;
import edu.aku.hassannaqvi.uen_midline.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_midline.core.MainApp;
import edu.aku.hassannaqvi.uen_midline.databinding.ActivitySectionE4Binding;
import edu.aku.hassannaqvi.uen_midline.utils.Util;

import static edu.aku.hassannaqvi.uen_midline.ui.list_activity.FamilyMembersListActivity.mainVModel;

public class SectionE4Activity extends AppCompatActivity {

    ActivitySectionE4Binding bi;
    private MortalityContract morc;
    private static int noOfDeath = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e4);
        bi.setCallback(this);

        setListeners();

    }

    private void setListeners() {

        noOfDeath++;

        bi.txtPreDeathLbl.setText(new StringBuilder("Total: ").append(noOfDeath).append(" out of ").append(MainApp.deathCount));
        bi.btnNext.setText(noOfDeath == MainApp.deathCount ? getString(R.string.nextSection) : getString(R.string.nextMortality));

        bi.e119c.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.e119c.setMinvalue(CONSTANTS.MORTALITY_INFO);

    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                if (MainApp.deathCount != noOfDeath) {
                    finish();
                    startActivity(new Intent(this, SectionE4Activity.class));
                } else {
                    noOfDeath = 0;
                    finish();
                    List<FamilyMembersContract> lstU5 = mainVModel.getChildLstU5().getValue();
                    Class nextClass = lstU5 != null ? lstU5.size() > 0 ? SectionI1Activity.class : SectionMActivity.class : SectionMActivity.class;
                    startActivity(new Intent(this, MainApp.selectedKishMWRA != null ? SectionFActivity.class : nextClass));
                }


            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addMortality(morc);
        morc.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            morc.setUID(morc.getDeviceId() + morc.get_ID());
            db.updatesMortalityColumn(MortalityContract.SingleMortality.COLUMN_UID, morc.getUID(), morc);
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void SaveDraft() throws JSONException {

        morc = new MortalityContract();

        morc.set_UUID(MainApp.fc.get_UID());
        morc.setDeviceId(MainApp.appInfo.getDeviceID());
        morc.setFormDate(MainApp.fc.getFormDate());
        morc.setUser(MainApp.fc.getUser());
        morc.setDevicetagID(MainApp.fc.getDevicetagID());

        JSONObject f1 = new JSONObject();
        f1.put("hhno", MainApp.fc.getHhno());
        f1.put("cluster_no", MainApp.fc.getClusterCode());
        f1.put("counter", noOfDeath);
        f1.put("_luid", MainApp.fc.getLuid());
        f1.put("appversion", MainApp.appInfo.getAppVersion());
        f1.put("e118", bi.e118.getText().toString());
        f1.put("e119a", bi.e119a.getText().toString());
        f1.put("e119b", bi.e119b.getText().toString());
        f1.put("e119c", bi.e119c.getText().toString());
        f1.put("e120", bi.e120.getText().toString());

        f1.put("e121", bi.e121a.isChecked() ? "1" :
                bi.e121b.isChecked() ? "2" :
                        bi.e121c.isChecked() ? "3" :
                                bi.e121d.isChecked() ? "4" :
                                        bi.e121e.isChecked() ? "5" :
                                                bi.e121f.isChecked() ? "6" :
                                                        bi.e121g.isChecked() ? "7" :
                                                                bi.e121h.isChecked() ? "98" :
                                                                        bi.e12196.isChecked() ? "96" : "0");

        f1.put("e12196x", bi.e12196x.getText().toString());

        f1.put("e122", bi.e122a.isChecked() ? "1" :
                bi.e122b.isChecked() ? "2" :
                        bi.e122c.isChecked() ? "3" :
                                bi.e122d.isChecked() ? "4" :
                                        bi.e122e.isChecked() ? "5" : "0");

        morc.setsE3(String.valueOf(f1));

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionE4);
    }

    public void BtnEnd() {

        Util.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
