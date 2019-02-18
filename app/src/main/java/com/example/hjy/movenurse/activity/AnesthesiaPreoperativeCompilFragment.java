package com.example.hjy.movenurse.activity;

import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.upload.UpLoadUtils;
import com.example.hjy.movenurse.widget.CustomDatePicker;
import com.fy.base.BaseFragment;
import com.fy.entity.AnesthesiaPreoperativeBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.JumpUtils;
import com.fy.utils.T;
import com.fy.utils.TimeUtils;
import com.fy.widget.MyRadioGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;

/**
 * 麻醉术前编辑页面
 * <p/>Created by Gab on 2017/9/11 0011.
 */
public class AnesthesiaPreoperativeCompilFragment extends BaseFragment {

    @BindView(R.id.save_btn)
    Button mSaveBtn;
    @BindView(R.id.preoperative_diagnose_et)
    TextView mDiagnose_et;
    @BindView(R.id.modus_operandi)
    RadioGroup modus_operandi;
    //既往体健
    @BindView(R.id.mHealth_yes_cb)
    CheckBox mHealth_yes_cb;
    @BindView(R.id.mHealth_cb1)
    CheckBox mHealth_cb1;
    @BindView(R.id.mHealth_cb2)
    CheckBox mHealth_cb2;
    @BindView(R.id.mHealth_cb3)
    CheckBox mHealth_cb3;
    @BindView(R.id.mHealth_cb4)
    CheckBox mHealth_cb4;
    @BindView(R.id.mHealth_cb_other1)
    CheckBox mHealth_cb_other1;
    @BindView(R.id.mHealth_cb_other2)
    CheckBox mHealth_cb_other2;
    @BindView(R.id.mHealth_et_other1)
    EditText mHealth_et_other1;
    @BindView(R.id.mHealth_et_other2)
    EditText mHealth_et_other2;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.respiratory_rg)
    RadioGroup respiratory_rg;
    @BindView(R.id.respiratory_rb)
    RadioButton respiratory_rb;
    @BindView(R.id.respiratory_et)
    EditText respiratory_et;
    @BindView(R.id.anesthesia_rg)
    RadioGroup anesthesia_rg;
    @BindView(R.id.anesthesia_rb)
    RadioButton anesthesia_rb;
    @BindView(R.id.HeartGrade)
    RadioGroup HeartGrade;
    @BindView(R.id.anesthesia_et)
    EditText anesthesia_et;

    @BindView(R.id.condition1_et)
    EditText condition1_et;
    @BindView(R.id.p_et)
    EditText p_et;
    @BindView(R.id.r_et)
    EditText r_et;
    @BindView(R.id.bp_q_et)
    EditText bp_q_et;
    @BindView(R.id.bp_h_et)
    EditText bp_h_et;
    @BindView(R.id.consciousness_rg)
    RadioGroup consciousness_rg;
    @BindView(R.id.tooth_rg)
    RadioGroup tooth_rg;
    @BindView(R.id.cardiopulmonary_rg)
    RadioGroup cardiopulmonary_rg;
    @BindView(R.id.cardiopulmonary_rb)
    RadioButton cardiopulmonary_rb;
    @BindView(R.id.cardiopulmonary_et)
    EditText cardiopulmonary_et;
    @BindView(R.id.spine_mrg)
    MyRadioGroup spine_mrg;
    @BindView(R.id.health_mrg)
    MyRadioGroup health_mrg;

    @BindView(R.id.normal)
    RadioButton normal;

    @BindView(R.id.malformation)
    RadioButton malformation;
    @BindView(R.id.toothache)
    RadioButton toothache;
    @BindView(R.id.infection)
    RadioButton infection;
    @BindView(R.id.lumbar)
    RadioButton lumbar;
    @BindView(R.id.spine_rb)
    RadioButton spine_rb;
    @BindView(R.id.spine_et)
    EditText spine_et;
    @BindView(R.id.cannula)
    RadioGroup cannula;
    @BindView(R.id.ECG1)
    CheckBox ECG1;
    @BindView(R.id.ECG3)
    CheckBox ECG3;
    @BindView(R.id.ECG4)
    CheckBox ECG4;
    @BindView(R.id.ECG5)
    CheckBox ECG5;
    @BindView(R.id.ECG7)
    CheckBox ECG7;
    @BindView(R.id.ECG_other)
    CheckBox ECG_other;
    @BindView(R.id.ECG_other_et)
    EditText ECG_other_et;
    @BindView(R.id.pulmonary_rg)
    RadioGroup pulmonary_rg;
    @BindView(R.id.pulmonary_rb)
    RadioButton pulmonary_rb;
    @BindView(R.id.pulmonary_et)
    EditText pulmonary_et;
    @BindView(R.id.CBC_rg)
    RadioGroup CBC_rg;
    @BindView(R.id.coagulation_function_rg)
    RadioGroup coagulation_function_rg;
    @BindView(R.id.liver_rg)
    RadioGroup liver_rg;
    @BindView(R.id.renal_rg)
    RadioGroup renal_rg;
    @BindView(R.id.blood_rg)
    RadioGroup blood_rg;
    @BindView(R.id.others_et)
    EditText others_et;
    @BindView(R.id.ASA_rg)
    RadioGroup ASA_rg;
    @BindView(R.id.anesthesia_advice_et)
    EditText anesthesia_advice_et;

    @BindView(R.id.anesthesia_mode_et)
    EditText anesthesia_mode_et;
    @BindView(R.id.narcosis_adapt)
    EditText narcosis_adapt;
    @BindView(R.id.other_matter)
    EditText other_matter;
    @BindView(R.id.rabat_rg)
    RadioGroup mCXR_rg;
    @BindView(R.id.operation_date)
    TextView operation_date;//手术日期

    private String mDateKey = "";
    private String mPatID = "";
    private String mUserName = "";
    private String mUserID = "";
    private String mCreateDate = "";
    private String mOperationDataStr = "";
    private String mOperandi = ""; //拟施行手术及方式
    private String mHealthStr = ""; //既往体健
    private String mHealthStr1 = ""; //既往体健
    private String mHealthStr2 = ""; //既往体健
    private String mHealthStr3 = ""; //既往体健
    private String mHealthStr4 = ""; //既往体健
    private String mHealthStr5 = ""; //既往体健
    private String mHealthStr6 = ""; //既往体健
    private String mRespiratoryStr = ""; //呼吸系统疾病
    private String VitalSign_T = ""; //体温
    private String VitalSign_P = ""; //脉搏
    private String VitalSign_R = ""; //血压
    private String VitalSign_q = ""; //呼吸
    private String VitalSign_h = ""; //呼吸
    private String mAnesthesiaStr = ""; //手术麻醉史
    private String mHeartGradeStr = ""; //心功能分级
    private String mConsciousnessStr = ""; //神志
    private String mToothStr = ""; //牙刷
    private String mCardiopulmonaryStr = ""; //心肺腑
    private String mSpineStr = ""; //脊柱情况
    private String mCannula = ""; //困难插管
    private String mECGStr1 = ""; //心电图
    private String mECGStr2 = ""; //心电图
    private String mECGStr3 = ""; //心电图
    private String mECGStr4 = ""; //心电图
    private String mECGStr5 = ""; //心电图
    private String mECGOtherStr = ""; //心电图其他
    private String mCXR = ""; //胸片
    private String mLung = ""; //肺功能
    private String mCBCStr = ""; //血常规
    private String mMCoagulationStr = ""; //凝血功能
    private String mMLiverStr = "";//肝功能
    private String mRenalStr = ""; //肾功能
    private String mBlood = ""; //血糖
    private String mOthers_et = "";//其他
    private String mASA = "";
    private String mAnesthesia = ""; //术前麻醉医嘱
    private String mAdapt = ""; //拟行麻醉方式
    private String mMatter = ""; //麻醉适应症
    private String mMode = "";//麻醉中注意的事项
    private String mRyDisStr = "";//术前诊断
    private CustomDatePicker mDiagnoseDatePicker;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_anesthesia_preoperative_compile;
    }

    @Override
    protected void baseInitView() {
        super.baseInitView();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        mCreateDate = formatter.format(curDate);

        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");

        //入院诊断
        mRyDisStr = patin.getZyDetail().getICD_Name();
        mDiagnose_et.setText(mRyDisStr);
        mPatID = patin.getPatID();
        mUserName = loginBean.getUserName();
        mUserID = loginBean.getUserID();
        initListener();
    }

    private void initListener() {

        //手术时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        operation_date.setText(now);
        operation_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDiagnoseDatePicker.show(operation_date.getText().toString());
            }
        });
        mDiagnoseDatePicker = new CustomDatePicker(mContext, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {// 回调接口，获得选中的时间
                operation_date.setText(time);
                mOperationDataStr = time;
            }
        }, "2010-01-01 00:00", now);// 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        mDiagnoseDatePicker.showSpecificTime(true); // 显示时和分
        mDiagnoseDatePicker.setIsLoop(true); // 允许循环滚动


        //拟施行手术及方式
        modus_operandi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mOperandi = rb.getText().toString();
            }
        });

        //既往体健
        mHealth_yes_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mHealthStr = buttonView.getText().toString();
                    mHealth_cb1.setChecked(false);
                    mHealth_cb2.setChecked(false);
                    mHealth_cb3.setChecked(false);
                    mHealth_cb4.setChecked(false);
                    mHealth_cb_other1.setChecked(false);
                    mHealth_cb_other2.setChecked(false);
                } else {
                    mHealth_yes_cb.setChecked(false);
                    mHealthStr = "";
                }
            }
        });

        mHealth_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mHealth_yes_cb.setChecked(false);
                    mHealthStr1 = buttonView.getText().toString();
                } else {
                    mHealthStr1 = "";
                }
            }
        });
        mHealth_cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mHealth_yes_cb.setChecked(false);
                    mHealthStr2 = buttonView.getText().toString();
                } else {
                    mHealthStr2 = "";
                }
            }
        });
        mHealth_cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mHealth_yes_cb.setChecked(false);
                    mHealthStr3 = buttonView.getText().toString();
                } else {
                    mHealthStr3 = "";
                }
            }
        });
        mHealth_cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mHealth_yes_cb.setChecked(false);
                    mHealthStr4 = buttonView.getText().toString();
                } else {
                    mHealthStr4 = "";
                }
            }
        });
        mHealth_cb_other1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mHealth_yes_cb.setChecked(false);
                    mHealth_et_other1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mHealthStr5 = "过敏史:" + s.toString();
                        }
                    });
                } else {
                    mHealthStr5 = "";
                }
            }
        });
        mHealth_cb_other2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mHealth_yes_cb.setChecked(false);
                    mHealth_et_other2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mHealthStr6 = "其他:" + s.toString();
                        }
                    });
                }
            }
        });


        //呼吸系统疾病
        respiratory_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mRespiratoryStr = rb.getText().toString();
                if (checkedId == respiratory_rb.getId()) {
                    respiratory_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mRespiratoryStr = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
        });

        HeartGrade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mHeartGradeStr = rb.getText().toString();
            }
        });
        //手术麻醉史
        anesthesia_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mAnesthesiaStr = rb.getText().toString();
                if (checkedId == anesthesia_rb.getId()) {
                    anesthesia_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mAnesthesiaStr = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
        });

        //体温
        condition1_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VitalSign_T = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //脉搏
        p_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VitalSign_P = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        r_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VitalSign_R = s.toString();//血压
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //呼吸
        bp_q_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VitalSign_q = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bp_h_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VitalSign_h = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        VitalSign_T = condition1_et.getText().toString();//体温
//        VitalSign_P = p_et.getText().toString(); //脉搏
//        VitalSign_R = r_et.getText().toString();//血压
//        VitalSign_q = bp_q_et.getText().toString();//呼吸
//        VitalSign_h = bp_h_et.getText().toString();//呼吸


        //神志
        consciousness_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mConsciousnessStr = rb.getText().toString();
            }
        });

        //牙齿
        tooth_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mToothStr = rb.getText().toString();
            }
        });

        //心肺腹
        cardiopulmonary_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mCardiopulmonaryStr = rb.getText().toString();
                if (checkedId == cardiopulmonary_rb.getId()) {
                    cardiopulmonary_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mCardiopulmonaryStr = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
        });

        //脊柱情况
        spine_mrg.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {

                if (checkedId == normal.getId()) {
                    mSpineStr = normal.getText().toString();
                }
                if (checkedId == malformation.getId()) {
                    mSpineStr = malformation.getText().toString();
                }
                if (checkedId == toothache.getId()) {
                    mSpineStr = toothache.getText().toString();
                }
                if (checkedId == infection.getId()) {
                    mSpineStr = infection.getText().toString();
                }
                if (checkedId == lumbar.getId()) {
                    mSpineStr = lumbar.getText().toString();
                }
                if (checkedId == spine_rb.getId()) {
                    //既往体健 其他
                    spine_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mSpineStr = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
        });

        cannula.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mCannula = rb.getText().toString();
            }
        });

        //
        ECG1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ECG3.setChecked(false);
                    ECG4.setChecked(false);
                    ECG5.setChecked(false);
                    ECG7.setChecked(false);
                    ECG_other.setChecked(false);
                    mECGStr1 = buttonView.getText().toString();
                } else {
                    ECG1.setChecked(false);
                    mECGStr1 = "";
                }
            }
        });
        ECG3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ECG1.setChecked(false);
                    mECGStr2 = buttonView.getText().toString();
                } else {
                    mECGStr2 = "";
                }
            }
        });
        ECG4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ECG1.setChecked(false);
                    mECGStr3 = buttonView.getText().toString();
                } else {
                    mECGStr3 = "";
                }
            }
        });
        ECG5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ECG1.setChecked(false);
                    mECGStr4 = buttonView.getText().toString();
                } else {
                    mECGStr4 = "";
                }
            }
        });
        ECG7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ECG1.setChecked(false);
                    mECGStr5 = buttonView.getText().toString();
                } else {
                    mECGStr5 = "";
                }
            }
        });
        ECG_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ECG1.setChecked(false);
                    //心电图 其他
                    ECG_other_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mECGOtherStr = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } else {
                    mECGOtherStr = "";
                }
            }
        });


        //胸片
        mCXR_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mCXR = rb.getText().toString();
            }
        });

        //肺功能
        pulmonary_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mLung = rb.getText().toString();
                if (checkedId == pulmonary_rb.getId()) {
                    pulmonary_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mLung = s.toString() +  "度肺功能障碍";
                        }
                    });
                }
            }
        });

        //血常规
        CBC_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mCBCStr = rb.getText().toString();
            }
        });

        //凝血功能
        coagulation_function_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mMCoagulationStr = rb.getText().toString();
            }
        });
        //肝功能
        liver_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mMLiverStr = rb.getText().toString();
            }
        });
        //肾功能
        renal_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mRenalStr = rb.getText().toString();
            }
        });
        //血糖
        blood_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mBlood = rb.getText().toString();
            }
        });
        //其他
//        mOthers_et = this.others_et.getText().toString();
        others_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mOthers_et = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ASA_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mASA = rb.getText().toString();
            }
        });
        //术前麻醉医嘱
        anesthesia_advice_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mAnesthesia = s.toString();
            }
        });
        //拟行麻醉方式
//        mMode = anesthesia_mode_et.getText().toString();
        anesthesia_mode_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mAdapt = s.toString();
            }
        });
        //麻醉适应症
//        mAdapt = narcosis_adapt.getText().toString();
        narcosis_adapt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mMatter = s.toString();
            }
        });
        //麻醉中注意的事项
//        mMatter = other_matter.getText().toString();
        other_matter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mMode = "5.其他：" + s.toString();
            }
        });


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(System.currentTimeMillis());//获取当前时间
                String mDateKeys = format.format(date);
                long l = TimeUtils.timeString2long(mDateKeys, "yyyy-MM-dd HH:mm");
                mDateKey = l + "";
                patientData();
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> params = patientCashData();
                PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
                UpLoadUtils.cacheRequest(mCache, patin, Api.AnesthesiaVisit1, params);
                T.showShort("临时保存成功");
            }
        });
    }

    /**
     * 麻醉术前编辑页面
     */
    private void patientData() {
        if (!checkInput()) {
            return;
        }
        Map<String, Object> params = patientCashData();
        NetRequest.getInstens()
                .requestDate(params, Api.AnesthesiaVisit1, false, new NetCallBack<ArrayList<AnesthesiaPreoperativeBean>>(mContext, R.string.loading_save) {
                    @Override
                    protected void onSuccess(ArrayList<AnesthesiaPreoperativeBean> anesthesiaPreoperativeBeen) {
                        JumpUtils.exitActivity(mContext);
                        T.showShort("保存成功");
                    }

                    @Override
                    public void updataLayout(int flag) {
//                        if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID){
//                            PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                            UpLoadUtils.cacheRequest(mCache, patin, Api.AnesthesiaVisit, params);
//                        }
                    }

                    @Override
                    protected void onFlaiCacheRequest() {

                    }
                });
    }

    private boolean checkInput() {
        mOperationDataStr = operation_date.getText().toString();
        if (mOperationDataStr.isEmpty()) {
            T.showLong("手术时间不能为空!");
            return false;
        }
        return true;
    }

    /**
     * 临时麻醉术前编辑页面
     */
    private Map<String, Object> patientCashData() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("Token",  loginBean.getToken());
        params.put("UserID", loginBean.getUserID());
        params.put("PA_ID", mPatID); //住院号
        params.put("OrderType", "1");//1麻醉前、2麻醉后
        params.put("In_Dis", mRyDisStr);//1麻醉前、2麻醉后
        params.put("DateKey", mDateKey); //时间戳
        params.put("VisitNurseID", mUserID); //访视护士ID
        params.put("VisitNurse", mUserName); //访视护士
        params.put("VisitNurseDate", mCreateDate); //护士访视时间
        params.put("SurgeryDate", mOperationDataStr); //手术时间
        params.put("HasAnesthesia", mAnesthesiaStr); //手术麻醉史
        params.put("ModusOperandi", mOperandi); //拟施行手术及方式
        params.put("HasHXSick", mRespiratoryStr); //呼吸系统疾病
        params.put("IsHealthy", mHealthStr + " " + mHealthStr1 + " " + mHealthStr2 + " " + mHealthStr3 + " " + mHealthStr4 + " " + mHealthStr5 + " " + mHealthStr6);  //既往体健
        params.put("VitalSign_T", VitalSign_T); //体温
        params.put("VitalSign_P", VitalSign_P); //脉搏
        params.put("VitalSign_R", VitalSign_R); //血压
        params.put("VitalSign_BP", VitalSign_q + "/" + VitalSign_h);//呼吸
        params.put("Consciousness", mConsciousnessStr); //神志
        params.put("ToothStatus", mToothStr); //牙齿
        params.put("XFFStatus", mCardiopulmonaryStr); //心肺腑
        params.put("VertebraStatus", mSpineStr); //脊柱情况
        params.put("MallampatiGrade", mCannula); //困难插管
        params.put("HeartGrade", mHeartGradeStr); //心功能分级
        params.put("ECG", mECGStr1 + " " + mECGStr2 + " " + mECGStr3 + " " + mECGStr4 + " " + mECGStr5 + " " + mECGOtherStr); //心电图
        params.put("CXR", mCXR); //胸片
        params.put("Lung", mLung ); //肺功能
        params.put("CBC", mCBCStr); //血常规
        params.put("Coagulation", mMCoagulationStr); //凝血功能
        params.put("LFT", mMLiverStr); //肝功能
        params.put("Renal", mRenalStr); //肾功能
        params.put("GLU", mBlood); //血糖
        params.put("OtherEx", mOthers_et); //其他
        params.put("ASA", mASA); //ASA
        params.put("OrderOther", mAnesthesia); //术前麻醉医嘱
        params.put("AnesthesiaType", mAdapt ); //拟行麻醉方式
        params.put("AnesthesiaIndication", mMatter); //麻醉适应症
        params.put("AnesthesiaOther", mMode); //麻醉中注意的事项
        return params;
    }

}