package com.example.hjy.movenurse.activity;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
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
import com.fy.entity.AnesthesiaPostoperationBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.JumpUtils;
import com.fy.utils.T;
import com.fy.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Gab on 2017/9/11 0011.
 * 麻醉术后编辑页面
 */

public class AnesthesiaPostoperationFragment extends BaseFragment {

    @BindView(R.id.save_btn)
    Button mSaveBtn;
    @BindView(R.id.preoperative_diagnose_et)
    TextView mDiagnose_tv;

    @BindView(R.id.postoperation_rg)
    RadioGroup postoperation_rg;
    @BindView(R.id.hr_et)
    EditText hr_et;
    @BindView(R.id.r_et)
    EditText r_et;
    @BindView(R.id.bp_qet)
    EditText bp_qet;
    @BindView(R.id.bp_het)
    EditText bp_het;
    @BindView(R.id.SpO_et)
    EditText SpO_et;

    @BindView(R.id.consciousness_et)
    TextView ConsciousnessData_tv;

    @BindView(R.id.breathe)
    RadioGroup breathe;
    @BindView(R.id.body_rg)
    RadioGroup body_rg;
    @BindView(R.id.reason_et)
    EditText reason_et;
    @BindView(R.id.flat_et)
    EditText flat_et;
    @BindView(R.id.OffGoingPerson_et)
    EditText OffGoingPerson_et;
    @BindView(R.id.OnComingPerson_et)
    EditText OnComingPerson_et;
    @BindView(R.id.sign_rg)
    RadioGroup sign_rg;

    @BindView(R.id.conscious_state)
    RadioGroup conscious_state;
    @BindView(R.id.psychosis)
    RadioGroup psychosis;
    @BindView(R.id.cough_rg)
    RadioGroup cough_rg;
    @BindView(R.id.muscular_rg)
    RadioGroup muscular_rg;
    @BindView(R.id.nausea_rg)
    RadioGroup nausea_rg;
    @BindView(R.id.headache_rg)
    RadioGroup headache_rg;
    @BindView(R.id.uroschesis)
    RadioGroup uroschesis;
    @BindView(R.id.extremities_rg)
    RadioGroup extremities_rg;
    @BindView(R.id.hoarse_rg)
    RadioGroup hoarse_rg;
    @BindView(R.id.exercise_rg)
    RadioGroup exercise_rg;
    @BindView(R.id.OtherAf)
    EditText OtherAf;
    @BindView(R.id.exceptional)
    EditText exceptional;
    @BindView(R.id.OrderOtherAf)
    EditText OrderOtherAf;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    @BindView(R.id.tv_CreateDate)
    TextView CreateDate;
    @BindView(R.id.tv_FZ_Nurse)
    TextView FZ_Nurse;

    @BindView(R.id.cb1)
    CheckBox mNothingCb;
    @BindView(R.id.cb2)
    CheckBox mCigaretteCb;
    @BindView(R.id.cb3)
    CheckBox mLiquorCb;
    @BindView(R.id.liquor_other_cb)
    CheckBox liquor_other_cb;

    private String mDateKey = "";
    private String mPatID = "";
    private String mUserName = "";
    private String mUserID = "";
    private String mCreateDate = "";
    private String mDiagnoseStr = "";
    private String mPostoperationStr = "";//术后
    private String mHR = ""; //Hr
    private String mBQ_Q = ""; //
    private String mBQ_H = ""; //
    private String mSpO = ""; //
    private String mR = "";//R
    private String mConsciousnessStr = ""; //意识
    private String mConsciousnessStr1 = ""; //意识 清醒时间
    private String mBreatheStr = "";//呼吸
    private String mBodyStr = "";//全身麻醉
    private String mReason = "";//延迟拔管的原因
    private String mFlatStr = "";//脊椎内麻醉麻醉平面
    private String mJbStr = "";//接班人
    private String mMJbrtStr = ""; //交班人
    private String mSignStr = ""; //生命体征
    private String mConsciousStr = ""; //麻醉恢复意识
    private String mPsychosisStr = "";//精神状态
    private String mCoughStr = "";//咳嗽排痰
    private String mMuscularStr = "";//肌张力
    private String mNauseaStr = "";//恶心呕吐
    private String mHeadacheStr = "";//头痛
    private String mUroschesisStr = "";//尿潴留
    private String mExtremitiesStr = "";//双下肢感觉、运动
    private String mHoarseStr = "";//声音嘶哑
    private String mExerciseStr = "";//患肢感觉运动
    private String mOtheret = "";//其他
    private String mException = "";//特殊情况记录
    private String mOrderOther = "";//术后麻醉医嘱
    private CustomDatePicker mDiagnoseDatePicker, mDiagnoseDatePicker1;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_anesthesia_postoperation_compile;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void baseInitView() {
        super.baseInitView();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        mCreateDate = formatter.format(curDate);
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        mPatID = patin.getPatID();
        mUserName = loginBean.getUserName();
        mUserID = loginBean.getUserID();
        CreateDate.setText("记录时间:" + mCreateDate);
        FZ_Nurse.setText("负责护士签名:" + mUserName);
        initListener();

    }

    private void initListener() {
        //手术结束时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        mDiagnose_tv.setText(now);
        mDiagnose_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDiagnoseDatePicker.show(mDiagnose_tv.getText().toString());
            }
        });
        mDiagnoseDatePicker = new CustomDatePicker(mContext, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {// 回调接口，获得选中的时间
                mDiagnose_tv.setText(time);
                mDiagnoseStr = time;
            }
        }, "2010-01-01 00:00", now);// 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        mDiagnoseDatePicker.showSpecificTime(true); // 显示时和分
        mDiagnoseDatePicker.setIsLoop(true); // 允许循环滚动

        //术后
        postoperation_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mPostoperationStr = rb.getText().toString();
            }
        });
        //生命体征
        //HR
        hr_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mHR = s.toString();
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
                mR = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bp_qet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBQ_Q = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bp_het.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBQ_H = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        SpO_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSpO = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //意识
        //清醒
        mNothingCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCigaretteCb.setChecked(false);
                    mLiquorCb.setChecked(false);
                    mConsciousnessStr = buttonView.getText().toString();
                    if (mConsciousnessStr1.equals("")) {
                        T.showShort("请选择清醒时间");
                    }
                }
            }
        });
        //嗜睡
        mCigaretteCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mNothingCb.setChecked(false);
                    mLiquorCb.setChecked(false);
                    liquor_other_cb.setChecked(false);
                    mConsciousnessStr = buttonView.getText().toString();
                }
            }
        });
        //未清醒
        mLiquorCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mNothingCb.setChecked(false);
                    mCigaretteCb.setChecked(false);
                    liquor_other_cb.setChecked(false);
                    mConsciousnessStr = buttonView.getText().toString();
                }
            }
        });

        liquor_other_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    T.showShort("请选择麻醉清醒时间");
                    mCigaretteCb.setChecked(false);
                    mLiquorCb.setChecked(false);

                    ConsciousnessData_tv.setText(now);
                    ConsciousnessData_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDiagnoseDatePicker1.show(ConsciousnessData_tv.getText().toString());
                        }
                    });
                    mDiagnoseDatePicker1 = new CustomDatePicker(mContext, new CustomDatePicker.ResultHandler() {
                        @Override
                        public void handle(String time) {// 回调接口，获得选中的时间
                            ConsciousnessData_tv.setText(time);
                            mConsciousnessStr1 =  time;
                        }
                    }, "2010-01-01 00:00", now);// 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
                    mDiagnoseDatePicker1.showSpecificTime(true); // 显示时和分
                    mDiagnoseDatePicker1.setIsLoop(true); // 允许循环滚动
                }
            }
        });

        //呼吸
        breathe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mBreatheStr = rb.getText().toString();
            }
        });
        //全身麻醉
        body_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mBodyStr = rb.getText().toString();
            }
        });

        reason_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mReason = s.toString();
            }
        });
        //麻醉平面
        flat_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mFlatStr = s.toString();
            }
        });
        //交班人 接班人
        OffGoingPerson_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mJbStr = s.toString();
            }
        });
        OnComingPerson_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mMJbrtStr = s.toString();
            }
        });

        //患者生命体征
        sign_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mSignStr = rb.getText().toString();
            }
        });

        //意识状态
        conscious_state.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mConsciousStr = rb.getText().toString();
            }
        });

        //精神状态
        psychosis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mPsychosisStr = rb.getText().toString();
            }
        });


        //咳嗽排痰
        cough_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mCoughStr = rb.getText().toString();
            }
        });


        //肌张力
        muscular_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mMuscularStr = rb.getText().toString();
            }
        });

        //恶心呕吐
        nausea_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mNauseaStr = rb.getText().toString();
            }
        });

        //头痛
        headache_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mHeadacheStr = rb.getText().toString();
            }
        });
        //尿潴留
        uroschesis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mUroschesisStr = rb.getText().toString();
            }
        });
        //双下肢感觉、运动
        extremities_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mExtremitiesStr = rb.getText().toString();
            }
        });
        //声音嘶哑
        hoarse_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mHoarseStr = rb.getText().toString();
            }
        });
        //患肢感觉运动
        exercise_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mExerciseStr = rb.getText().toString();
            }
        });

        //其他
        OtherAf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mOtheret = s.toString();
            }
        });
        //特殊情况记录
        exceptional.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mException = s.toString();
            }
        });
        //其他
        OrderOtherAf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mOrderOther = s.toString();
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

        //临时保存
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> params = patientCashData();
                PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
                UpLoadUtils.cacheRequest(mCache, patin, Api.AnesthesiaVisit2, params);
                T.showShort("临时保存成功");
            }
        });

    }

    private boolean checkInput() {
        mDiagnoseStr = mDiagnose_tv.getText().toString().trim();
        if (mDiagnoseStr.isEmpty()) {
            T.showLong("手术结束时间不能为空!");
            return false;
        }
        mConsciousnessStr1 = "麻醉清醒时间：" + ConsciousnessData_tv.getText().toString().trim();
        if (mConsciousnessStr1.equals("")) {
            T.showShort("麻醉清醒时间不能为空!");
            return false;
        }
        return true;
    }

    /**
     * 麻醉术后编辑页面
     */
    private void patientData() {
        if (!checkInput()) {
            return;
        }
        Map<String, Object> params = patientCashData();
        NetRequest.getInstens()
                .requestDate(params, Api.AnesthesiaVisit2, false, new NetCallBack<ArrayList<AnesthesiaPostoperationBean>>(mContext, R.string.loading_save) {
                    @Override
                    protected void onSuccess(ArrayList<AnesthesiaPostoperationBean> postoperationBeen) {
                        JumpUtils.exitActivity(mContext);
                        T.showShort("保存成功");
                    }

                    @Override
                    public void updataLayout(int flag) {
//                        if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID){
//                            PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                            UpLoadUtils.cacheRequest(mCache, patin, Api.AnesthesiaVisit2, params);
//                        }
                    }

                    @Override
                    protected void onFlaiCacheRequest() {

                    }
                });
    }

    /**
     * 临时麻醉术后编辑页面
     */
    private Map<String, Object> patientCashData() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("Token", loginBean.getToken());
        params.put("UserID", loginBean.getUserID());
        params.put("PA_ID", mPatID); //住院号
        params.put("OrderType", "2");//1麻醉前、2麻醉后
        params.put("DateKey", mDateKey); //时间戳
        params.put("VisitNurseID", mUserID); //访视护士ID
        params.put("VisitNurse", mUserName); //访视护士
        params.put("VisitNurseDate", mCreateDate); //护士访视时间
        params.put("SurgeryEndDate", mDiagnoseStr); //手术结束时间
        params.put("Postoperative", mPostoperationStr); //术后
        params.put("VitalSign_HR", mHR); //Hr
        params.put("VitalSign_R", mR); //
        params.put("VitalSign_BP", mBQ_Q + "/" + mBQ_H);//呼吸
        params.put("VitalSign_SPO2", mSpO); //
        params.put("Consciousness", mConsciousnessStr + " " + mConsciousnessStr1); //意识
        params.put("BreathingStatus", mBreatheStr); //呼吸
        params.put("GA", mBodyStr); //全身麻醉
        params.put("GA_BLReason", mReason); //延迟拔管的原因
        params.put("SA", mFlatStr); //脊椎内麻醉麻醉平面
        params.put("OffGoingPerson", mJbStr); //接班人
        params.put("OnComingPerson", mMJbrtStr); //交班人
        params.put("VitalSign", mSignStr); //患者生命体征
        params.put("RecConsciousness", mConsciousStr); //麻醉恢复意识状态
        params.put("MentalStatus", mPsychosisStr); //精神状况
        params.put("GA_KP", mCoughStr); //咳嗽排痰
        params.put("GA_JZL", mMuscularStr); //肌张力
        params.put("GA_EO", mNauseaStr); //恶心呕吐
        params.put("SA_TT", mHeadacheStr); //头痛
        params.put("SA_NZL", mUroschesisStr); //尿潴留
        params.put("SA_SXGY", mExtremitiesStr); //尿潴留
        params.put("NB_SS", mHoarseStr); //声音嘶哑
        params.put("NB_HGY", mExerciseStr); //患肢感觉运动
        params.put("OtherAf", mOtheret); //其他
        params.put("Exceptional", mException); //特殊情况记录
        params.put("OrderOtherAf", mOrderOther); //术后麻醉医嘱
        return params;
    }

}
