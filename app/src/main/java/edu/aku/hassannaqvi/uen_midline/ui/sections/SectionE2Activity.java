package edu.aku.hassannaqvi.uen_midline.ui.sections;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.edittextpicker.aliazaz.EditTextPicker;
import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.aku.hassannaqvi.uen_midline.CONSTANTS;
import edu.aku.hassannaqvi.uen_midline.R;
import edu.aku.hassannaqvi.uen_midline.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.uen_midline.contracts.MWRAContract;
import edu.aku.hassannaqvi.uen_midline.contracts.MWRA_PREContract;
import edu.aku.hassannaqvi.uen_midline.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_midline.core.MainApp;
import edu.aku.hassannaqvi.uen_midline.databinding.ActivitySectionE2Binding;
import edu.aku.hassannaqvi.uen_midline.datecollection.AgeModel;
import edu.aku.hassannaqvi.uen_midline.datecollection.DateRepository;
import edu.aku.hassannaqvi.uen_midline.utils.Util;

import static edu.aku.hassannaqvi.uen_midline.ui.list_activity.FamilyMembersListActivity.mainVModel;

public class SectionE2Activity extends AppCompatActivity {

    ActivitySectionE2Binding bi;
    private MWRAContract mwraContract;
    MWRA_PREContract mwraPre;
    public static int noOfPreCounter = 0;
    boolean imFlag = false;
    int position;
    private FamilyMembersContract fmc_child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e2);
        bi.setCallback(this);

        setUIComponent();
        setChildSpinner();
    }

    private void setUIComponent() {

        noOfPreCounter++;

        mwraContract = getIntent().getParcelableExtra(CONSTANTS.MWRA_INFO);

        bi.txtPreCounterLbl.setText(new StringBuilder(mwraContract.getMwra_name().toUpperCase()).append("\n")
                .append("Pregnancies Total: ").append(noOfPreCounter).append(" out of ").append(MainApp.noOfPragnencies));
        bi.btnNext.setText(noOfPreCounter == MainApp.noOfPragnencies ? getString(R.string.nextSection) : getString(R.string.nextPregnancy));

        bi.e105.setOnCheckedChangeListener(((radioGroup, i) -> {

            MainApp.twinFlag = i == bi.e105c.getId();
            if (i == bi.e105b.getId()
                    || i == bi.e105e.getId()
                    || i == bi.e105f.getId()) {

                bi.fldGrpCVd108.setVisibility(View.GONE);
                bi.fldGrpCVe108.setVisibility(View.GONE);
                bi.fldGrpCVd107.setVisibility(View.GONE);
                bi.fldGrpCVe107.setVisibility(View.GONE);
                bi.fldGrpCVe110.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVd108);
                Clear.clearAllFields(bi.fldGrpCVe108);
                Clear.clearAllFields(bi.fldGrpCVd107);
                Clear.clearAllFields(bi.fldGrpCVe107);
                Clear.clearAllFields(bi.fldGrpCVe110);
                bi.mainContainer2.setVisibility(View.VISIBLE);
            } else {
                bi.fldGrpCVd108.setVisibility(View.VISIBLE);
                bi.fldGrpCVe108.setVisibility(View.VISIBLE);
                bi.fldGrpCVd107.setVisibility(View.VISIBLE);
                bi.fldGrpCVe107.setVisibility(View.VISIBLE);
                bi.fldGrpCVe110.setVisibility(View.VISIBLE);
                bi.mainContainer2.setVisibility(View.GONE);
                Clear.clearAllFields(bi.mainContainer2);
            }

        }));


        bi.e108.setOnCheckedChangeListener(((radioGroup, i) -> {

            /*if (i == bi.e108a.getId()) {
                bi.mainContainer2.setVisibility(View.VISIBLE);
                bi.fldGrpCVe109.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVe109);
                bi.fldGrpCVd107.setVisibility(View.VISIBLE);
            } else {
                bi.mainContainer2.setVisibility(View.VISIBLE);
                bi.fldGrpCVe113.setVisibility(View.GONE);
                bi.fldGrpCVe114.setVisibility(View.GONE);
                bi.fldGrpCVe115.setVisibility(View.GONE);
                bi.fldGrpCVd107.setVisibility(View.GONE);

                Clear.clearAllFields(bi.fldGrpCVe113);
                Clear.clearAllFields(bi.fldGrpCVe114);
                Clear.clearAllFields(bi.fldGrpCVe115);
                Clear.clearAllFields(bi.fldGrpCVd107);

                if (!(bi.e105b.isChecked() || bi.e105e.isChecked() || bi.e105f.isChecked()))
                    bi.fldGrpCVe109.setVisibility(View.VISIBLE);
            }*/


            if (i == bi.e108b.getId()) {
                bi.mainContainer2.setVisibility(View.VISIBLE);
                bi.fldGrpCVe113.setVisibility(View.GONE);
                bi.fldGrpCVe114.setVisibility(View.GONE);
                bi.fldGrpCVe115.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVe113);
                Clear.clearAllFields(bi.fldGrpCVe114);
                Clear.clearAllFields(bi.fldGrpCVe115);

                bi.fldGrpCVd107.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVd107);
                bi.fldGrpCVe109.setVisibility(View.VISIBLE);
            } else {
                bi.mainContainer2.setVisibility(View.GONE);
                Clear.clearAllFields(bi.mainContainer2);


                bi.fldGrpCVe113.setVisibility(View.VISIBLE);
                bi.fldGrpCVe114.setVisibility(View.VISIBLE);
                bi.fldGrpCVe115.setVisibility(View.VISIBLE);
                bi.fldGrpCVd107.setVisibility(View.VISIBLE);
                bi.fldGrpCVe109.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVe109);
            }


            if (bi.e105b.isChecked()
                    || bi.e105e.isChecked()
                    || bi.e105f.isChecked()) {
                bi.mainContainer2.setVisibility(View.VISIBLE);


                bi.fldGrpCVd107.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVd107);
                bi.fldGrpCVe109.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVe109);
            }


        }));

        bi.e106c.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.e106c.setMinvalue(CONSTANTS.MINYEAR);

        bi.e113y.setMaxvalue(CONSTANTS.MAXYEAR);
        bi.e113y.setMinvalue(CONSTANTS.MINYEAR);

        editTextImplementation(new EditTextPicker[]{bi.e106a, bi.e106b, bi.e106c});

    }

    private void setChildSpinner() {

        List<String> childLst = new ArrayList<String>() {
            {
                add("....");
                add("Not defined in List");
                addAll(MainApp.selectedMWRAChildLst.getSecond());
            }
        };

        bi.e100.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, childLst));

        bi.e100.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SectionE2Activity.this.position = position;
                bi.fldGrpCVe109.setVisibility(View.GONE);
                bi.e109.setText(null);
                if (position == 0) return;
                if (position == 1) {
                    bi.fldGrpCVe109.setVisibility(View.VISIBLE);
                    return;
                }
                fmc_child = mainVModel.getMemberInfo(MainApp.selectedMWRAChildLst.getFirst().get(position - 2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                if (MainApp.twinFlag) {
                    openDialog();
                } else {
                    if (MainApp.noOfPragnencies != noOfPreCounter) {
                        finish();
                        startActivity(new Intent(SectionE2Activity.this, SectionE2Activity.class)
                                .putExtra(CONSTANTS.MWRA_INFO, mwraContract));
                    } else {
                        noOfPreCounter = 0;
                        if (MainApp.pragnantWoman.getFirst().size() > 0) {
                            finish();
                            startActivity(new Intent(SectionE2Activity.this, SectionE1Activity.class));
                        } else {
                            finish();
                            startActivity(new Intent(SectionE2Activity.this, SectionE3Activity.class));
                        }
                    }

                }
            }

        }
    }

    private void openDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_dialog);
        dialog.setCancelable(false);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(dialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(params);

        dialog.findViewById(R.id.btnOk).setOnClickListener(view -> {
            clearContainer();
            dialog.dismiss();

        });

    }

    private void clearContainer() {
        Clear.clearAllFields(bi.container1);
        Clear.clearAllFields(bi.mainContainer2);
        bi.e104015.setVisibility(View.GONE);
        MainApp.twinFlag = false;
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long rowID = db.addPregnantMWRA(mwraPre);
        if (rowID > 0) {
            mwraPre.set_ID(String.valueOf(rowID));
            mwraPre.setUID(mwraPre.getDeviceId() + mwraPre.get_ID());
            db.updatesMWRAPREColumn(mwraPre);
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        mwraPre = new MWRA_PREContract();
        mwraPre.set_UUID(MainApp.fc.get_UID());
        mwraPre.setDeviceId(MainApp.appInfo.getDeviceID());
        mwraPre.setDevicetagID(MainApp.fc.getDevicetagID());
        mwraPre.setFormDate(MainApp.fc.getFormDate());
        mwraPre.setUser(MainApp.fc.getUser());

        JSONObject json = new JSONObject();
        json.put("mw_uid", mwraContract.getUID());
        json.put("fm_serial", mwraContract.getFm_serial());
        json.put("fm_uid", mwraContract.getFmuid());
        json.put("hhno", MainApp.fc.getHhno());
        json.put("cluster_no", MainApp.fc.getClusterCode());
        json.put("_luid", MainApp.fc.getLuid());
        json.put("appversion", MainApp.appInfo.getAppVersion());
        json.put("counter", noOfPreCounter);

        json.put("e104", bi.e104a.isChecked() ? "1"
                : bi.e104b.isChecked() ? "2"
                : "0");

        json.put("e105", bi.e105a.isChecked() ? "1"
                : bi.e105b.isChecked() ? "2"
                : bi.e105c.isChecked() ? "3"
                : bi.e105d.isChecked() ? "4"
                : bi.e105e.isChecked() ? "5"
                : bi.e105f.isChecked() ? "6"
                : "0");

        json.put("e106a", bi.e106a.getText().toString());
        json.put("e106b", bi.e106b.getText().toString());
        json.put("e106c", bi.e106c.getText().toString());
        json.put("e10698", bi.e10698.isChecked() ? "1" : "0");

        json.put("e107", bi.e107a.isChecked() ? "1"
                : bi.e107b.isChecked() ? "2"
                : "0");

        json.put("e108", bi.e108a.isChecked() ? "1"
                : bi.e108b.isChecked() ? "2"
                : "0");

        json.put("e109", bi.e109.getText().toString());

        if (position != 1) {
            json.put("ch_serial", fmc_child.getSerialno());
            json.put("ch_name", fmc_child.getName());
            json.put("ch_uid", fmc_child.getUid());
        }

        json.put("e110a", bi.e110a.getText().toString());
        json.put("e110b", bi.e110b.getText().toString());
        json.put("e110c", bi.e110c.getText().toString());
        json.put("e11098", bi.e11098.isChecked() ? "1" : "0");

        json.put("e111", bi.e111a.isChecked() ? "1"
                : bi.e111b.isChecked() ? "2"
                : bi.e111c.isChecked() ? "3"
                : bi.e111d.isChecked() ? "4"
                : bi.e111e.isChecked() ? "5"
                : bi.e111f.isChecked() ? "6"
                : bi.e111g.isChecked() ? "7"
                : bi.e11196.isChecked() ? "96"
                : "0");
        json.put("e11196x", bi.e11196x.getText().toString());

        json.put("e112", bi.e112a.isChecked() ? "1"
                : bi.e112b.isChecked() ? "2"
                : bi.e112c.isChecked() ? "3"
                : bi.e112d.isChecked() ? "4"
                : bi.e112e.isChecked() ? "5"
                : "0");

        json.put("e113m", bi.e113m.getText().toString());
        json.put("e113y", bi.e113y.getText().toString());

        json.put("e114", bi.e114.getText().toString());

        json.put("e115", bi.e115a.isChecked() ? "1"
                : bi.e115b.isChecked() ? "2"
                : "0");

        mwraPre.setsE2(String.valueOf(json));

        // Update Corona Check
        if (MainApp.selectedKishMWRA.getSerialno().equals(mwraContract.getFm_serial()) && !MainApp.selectedKishMWRA.isCoronaCase()) {
            if (bi.fldGrpCVd108.getVisibility() == View.VISIBLE) {
                MainApp.selectedKishMWRA.setCoronaCase(Integer.parseInt(bi.e106c.getText().toString()) == 2020);
            }
        }

        // Deleting item in list
        if (position != 1) {
            MainApp.selectedMWRAChildLst.getFirst().remove(position - 2);
            MainApp.selectedMWRAChildLst.getSecond().remove(position - 2);
        }

    }


    public void editTextImplementation(EditTextPicker[] editTextsArray) {
        if (editTextsArray.length != 3) return;
        EditTextPicker editTextPicker01 = editTextsArray[0];
        EditTextPicker editTextPicker02 = editTextsArray[1];
        EditTextPicker editTextPicker03 = editTextsArray[2];

        for (EditTextPicker item : new EditTextPicker[]{editTextPicker01, editTextPicker02})
            item.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    editTextPicker03.setText(null);
                    editTextPicker03.setError(null);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        editTextPicker03.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String txt01, txt02, txt03;
                editTextPicker01.setEnabled(true);
                editTextPicker02.setEnabled(true);
                if (!TextUtils.isEmpty(editTextPicker01.getText()) && !TextUtils.isEmpty(editTextPicker02.getText()) && !TextUtils.isEmpty(editTextPicker03.getText())) {
                    txt01 = editTextPicker01.getText().toString();
                    txt02 = editTextPicker02.getText().toString();
                    txt03 = editTextPicker03.getText().toString();
                } else return;
                if (!editTextPicker01.isRangeTextValidate() || txt01.trim().equals("00") || !editTextPicker02.isRangeTextValidate() || !editTextPicker03.isRangeTextValidate())
                    return;
                int day = txt01.equals("00") ? 15 : Integer.parseInt(txt01);
                int month = Integer.parseInt(txt02);
                int year = Integer.parseInt(txt03);
                AgeModel age = DateRepository.Companion.getCalculatedAge(year, month, day);
                if (age == null) {
                    editTextPicker03.setError("Invalid date!!");
                    imFlag = false;
                } else {
                    imFlag = true;
                    editTextPicker01.setEnabled(false);
                    editTextPicker02.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.fldGrpSectionE2))
            return false;

        if (bi.fldGrpCVd108.getVisibility() == View.VISIBLE) {
            if (!imFlag) {
                Toast.makeText(this, "Invalid date!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if (!(bi.e105b.isChecked() || bi.e105e.isChecked() || bi.e105f.isChecked() || bi.e108a.isChecked()))
            if ((Integer.parseInt(bi.e110a.getText().toString()) == 0 && Integer.parseInt(bi.e110b.getText().toString()) == 0 && Integer.parseInt(bi.e110c.getText().toString()) == 0)) {
                Toast.makeText(this, "Invalid Date of Death!", Toast.LENGTH_SHORT).show();
                return false;
            }

        return true;
    }

    public void BtnEnd() {

        Util.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
