package com.example.hjy.movenurse.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.upload.UpLoadListActivity;
import com.example.hjy.movenurse.upload.UpLoadUtils;
import com.fy.base.BaseFragment;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.PressureSoreBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.JumpUtils;
import com.fy.utils.L;
import com.fy.utils.T;
import com.fy.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Gab on 2017/9/11 0011.
 * 压疮风险评估_编辑
 */
public class PressureSoresRiskFragment extends BaseFragment {

    @BindView(R.id.preoperativeDiagnosis)//术前诊断
            TextView preoperativeDiagnosis;
    @BindView(R.id.operationName)//手术名称
            EditText operationName;
    @BindView(R.id.source_rg)
    RadioGroup source_rg;
    @BindView(R.id.allergic_rg)
    RadioGroup allergic_rg;
    //    @BindView(R.id.tv_sex)
//    TextView tv_sex;
    @BindView(R.id.relation_emotion)
    RadioGroup relation_emotion;
    @BindView(R.id.rg_sex)
    RadioGroup rg_sex;

    @BindView(R.id.hazards1)
    CheckBox hazards1_cb;
    @BindView(R.id.hazards2)
    CheckBox hazards2_cb;
    @BindView(R.id.hazards3)
    CheckBox hazards3_cb;
    @BindView(R.id.hazards4)
    CheckBox hazards4_cb;
    @BindView(R.id.hazards5)
    CheckBox hazards5_cb;

    @BindView(R.id.highAlcohol)
    CheckBox highAlcohol;
    @BindView(R.id.cytotoxicAgents)
    CheckBox cytotoxicAgents;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.nutrition)
    RadioGroup nutrition;
    @BindView(R.id.activities)
    RadioGroup activities;
    @BindView(R.id.deglutition)
    RadioGroup deglutition;
    @BindView(R.id.trauma_rg)
    RadioGroup trauma_rg;
    @BindView(R.id.preventive_measure_1)
    RadioGroup preventive_measure_1;
    @BindView(R.id.preventive_measure_2)
    RadioGroup preventive_measure_2;
    @BindView(R.id.preventive_measure_3)
    RadioGroup preventive_measure_3;
    @BindView(R.id.preventive_measure_4)
    RadioGroup preventive_measure_4;
    @BindView(R.id.preventive_measure_5)
    RadioGroup preventive_measure_5;
    @BindView(R.id.preventive_measure_6)
    RadioGroup preventive_measure_6;

    @BindView(R.id.Diabetes_rg)
    RadioGroup Diabetes_rg;
    @BindView(R.id.Diabetes_rg1)
    RadioGroup Diabetes_rg1;
    @BindView(R.id.Diabetes_rg2)
    RadioGroup Diabetes_rg2;
    @BindView(R.id.tv_CreateDate)
    TextView tv_CreateDate;
    @BindView(R.id.tv_FZ_Nurse)
    TextView tv_FZ_Nurse;
    @BindView(R.id.save_btn)
    Button save_btn;

    @BindView(R.id.preventive_measure_rb1)
    RadioButton preventive_measure_rb1;
    @BindView(R.id.preventive_measure_rb2)
    RadioButton preventive_measure_rb2;
    @BindView(R.id.preventive_measure_rb3)
    RadioButton preventive_measure_rb3;
    @BindView(R.id.preventive_measure_rb4)
    RadioButton preventive_measure_rb4;
    @BindView(R.id.preventive_measure_rb5)
    RadioButton preventive_measure_rb5;
    @BindView(R.id.preventive_measure_rb6)
    RadioButton preventive_measure_rb6;
    @BindView(R.id.preventive_measure_et)
    EditText preventive_measure_et;

    @BindView(R.id.preventive_measure_tv1)
    TextView preventive_measure_tv1;
    @BindView(R.id.preventive_measure_tv2)
    TextView preventive_measure_tv2;
    @BindView(R.id.preventive_measure_tv3)
    TextView preventive_measure_tv3;
    @BindView(R.id.preventive_measure_tv4)
    TextView preventive_measure_tv4;
    @BindView(R.id.preventive_measure_tv5)
    TextView preventive_measure_tv5;

    @BindView(R.id.bt_compile)
    TextView bt_compile; //评分

    private String mUserID = "";
    private String mSources = "";
    private String mAllergic = "";
    private String mEmotion = "";
    private String mRelation = "";
    private String mNutrition = "";
    private String mActivities = "";
    private String mAeglutition = "";
    private String mTrauma = "";
    private String mRyDisStr = "";//术前诊断
    private String mPreventive_measure_1 = "";
    private String mPreventive_measure_2 = "";
    private String mPreventive_measure_3 = "";
    private String mPreventive_measure_4 = "";
    private String mPreventive_measure_5 = "";
    private String mPreventive_measure_et = "";
    private String mPatID = "";
    private String mToken = "";
    private String mUserName = "";
    private String mCreateDate = "";
    private String mAllergicId = "";
    private String mEmotionId = "";
    private String mSourcesId = "";
    private String mRelationId = "";
    private String mNutritionId = "";
    private String mActivitiesId = "";
    private String mAeglutitionId = "";
    private String mTraumaId = "";
    private String Diabetes1 = "";
    private String Diabetes2 = "";
    private String Diabetes3 = "";
    private String DiabetesStr1 = "糖尿病";
    private String DiabetesStr2 = "多发性硬化";
    private String DiabetesStr3 = "脑血管意外";
    private String highAlcohol1 = "";
    private String highAlcoholid = "";
    private String cytotoxicAgents1 = "";
    private String cytotoxicAgents1id = "";

    private String hazards1 = "";
    private String hazards2 = "";
    private String hazards3 = "";
    private String hazards4 = "";
    private String hazards5 = "";

    //其他危险因素评分
    private String hazardsid1;
    private String hazardsid2;
    private String hazardsid3;
    private String hazardsid4;
    private String hazardsid5;

    //其他危险因素评分
    private int hazardsId1;
    private int hazardsId2;
    private int hazardsId3;
    private int hazardsId4;
    private int hazardsId5;
    private int OverallScore;
    private int mEmotions;
    private int mSourcesIds = 0;
    private int mAllergicIds;
    private int mRelationIds;
    private int mNutritionIds;
    private int mActivitiesIds;
    private int mAeglutitionIds;
    private int mTraumaIds;
    private int mDiabete;
    private int mMultipl;
    private int mAcciden;
    private int cytotoxicAgents1ids;
    private int highAlcoholids;
    private int total = 0;
    UpLoadListActivity mLoadListActivity;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_pressure_sores_risk;
    }

    @Override
    protected void baseInitView() {
        super.baseInitView();
        //术前诊断
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        //入院诊断
        mRyDisStr = patin.getZyDetail().getICD_Name();
        preoperativeDiagnosis.setText(mRyDisStr);

        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(operationName.getWindowToken(), 0);
        //手术名称
//        operationName.setText(patin.getZyDetail().getICD_Name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        mCreateDate = formatter.format(curDate);

        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        mUserID = loginBean.getUserID();
        tv_CreateDate.setText("记录时间:" + mCreateDate);
        tv_FZ_Nurse.setText("负责护士签名:" + mUserName);

        mUserName = loginBean.getUserName();
        mPatID = patin.getPatID();
        initListener();
    }

    private void initListener() {
        //体型
        source_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mSourcesIds = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mSources = rb.getText().toString();
                //体型评分
                if (mSources.equals("正常")) {
                    mSourcesId = "0";
                } else if (mSources.equals("偏胖")) {
                    mSourcesId = "1";
                } else if (mSources.equals("肥胖")) {
                    mSourcesId = "2";
                } else {
                    mSourcesId = "3";
                }
                mSourcesIds = Integer.parseInt(mSourcesId);//体型分数
                total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                        mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                bt_compile.setText("评估:" + total + "分");//评估:100 分
            }
        });
        //性别
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mEmotions = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = getActivity().findViewById(checkedRadioButtonId);
                mEmotion = rb.getText().toString();
                //性别评分
                if (mEmotion.equals("男")) {
                    mEmotionId = "1";
                } else if (mEmotion.equals("女")) {
                    mEmotionId = "2";
                }
                mEmotions = Integer.parseInt(mEmotionId);//性别分数
                total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                        mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                bt_compile.setText("评估:" + total + "分");//评估:100 分
            }
        });
        //危险部位的皮肤类型
        allergic_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mAllergicIds = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mAllergic = rb.getText().toString();
                //皮肤类型评分
                if (mAllergic.equals("正常")) {
                    mAllergicId = "0";
                    mAllergic = "正常";
                } else if (mAllergic.equals("菲薄")) {
                    mAllergicId = "1";
                    mAllergic = "菲薄";
                } else if (mAllergic.equals("干燥")) {
                    mAllergicId = "1";
                    mAllergic = "干燥";
                } else if (mAllergic.equals("水肿")) {
                    mAllergicId = "1";
                    mAllergic = "水肿";
                } else if (mAllergic.equals("潮湿")) {
                    mAllergicId = "1";
                    mAllergic = "潮湿";
                } else if (mAllergic.equals("颜色异常")) {
                    mAllergicId = "2";
                    mAllergic = "颜色异常";
                }
                mAllergicIds = Integer.parseInt(mAllergicId);//体型分数
                total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                        mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                bt_compile.setText("评估:" + total + "分");//评估:100 分
            }
        });
        //年龄
        relation_emotion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mRelationIds = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mRelation = rb.getText().toString();
                //年龄评分
                if (mRelation.equals("14-19")) {
                    mRelationId = "1";
                } else if (mRelation.equals("50-64")) {
                    mRelationId = "2";
                } else if (mRelation.equals("65-74")) {
                    mRelationId = "3";
                } else if (mRelation.equals("75-80")) {
                    mRelationId = "4";
                } else if (mRelation.equals("80岁以上")) {
                    mRelationId = "5";
                }
                try {
                    mRelationIds = (int) Double.parseDouble(mRelationId);
//                    mRelationIds = Integer.valueOf(mRelationId);//年龄分数
                    total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        //控便能力
        nutrition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mNutritionIds = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mNutrition = rb.getText().toString();
                //控便能力评分
                if (mNutrition.equals("正常/留置尿管")) {
                    mNutritionId = "0";
                } else if (mNutrition.equals("偶失禁")) {
                    mNutritionId = "1";
                } else if (mNutrition.equals("腹泻/尿/大便失禁")) {
                    mNutritionId = "2";
                } else if (mNutrition.equals("大小便失禁")) {
                    mNutritionId = "3";
                }
                mNutritionIds = Integer.parseInt(mNutritionId);//控便能力 分数
                total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                        mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                bt_compile.setText("评估:" + total + "分");//评估:100 分
            }
        });
        //活动情况
        activities.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mActivitiesIds = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mActivities = rb.getText().toString();
                //活动情况评分
                if (mActivities.equals("正常")) {
                    mActivitiesId = "0";
                } else if (mActivities.equals("躁动")) {
                    mActivitiesId = "1";
                } else if (mActivities.equals("懒动")) {
                    mActivitiesId = "2";
                } else if (mActivities.equals("活动受限")) {
                    mActivitiesId = "3";
                } else if (mActivities.equals("活动迟缓/牵引")) {
                    mActivitiesId = "4";
                } else if (mActivities.equals("固定体位")) {
                    mActivitiesId = "5";
                }
                mActivitiesIds = Integer.parseInt(mActivitiesId);//活动情况 分数
                total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                        mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                bt_compile.setText("评估:" + total + "分");//评估:100 分
            }
        });
        //饮食与食欲
        deglutition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mAeglutitionIds = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mAeglutition = rb.getText().toString();
                //饮食与食欲评分
                if (mAeglutition.equals("正常")) {
                    mAeglutitionId = "0";
                } else if (mAeglutition.equals("差")) {
                    mAeglutitionId = "1";
                } else if (mAeglutition.equals("鼻饲")) {
                    mAeglutitionId = "2";
                } else if (mAeglutition.equals("禁食")) {
                    mAeglutitionId = "3";
                } else if (mAeglutition.equals("厌食")) {
                    mAeglutitionId = "3";
                }
                mAeglutitionIds = Integer.parseInt(mAeglutitionId);//饮食与食欲 分数
                total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                        mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                bt_compile.setText("评估:" + total + "分");//评估:100 分
            }
        });
        //大手术/创伤
        trauma_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mTraumaIds = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                mTraumaId = checkedId + "";//大手术/创伤评分
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mTrauma = rb.getText().toString();
                //大手术/创伤评分
                if (mTrauma.equals("腰以下/脊椎")) {
                    mTraumaId = "5";
                } else if (mTrauma.equals("手术时间>2小时")) {
                    mTraumaId = "5";
                }
                mTraumaIds = Integer.parseInt(mTraumaId);//大手术 分数
                total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                        mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                bt_compile.setText("评估:" + total + "分");//评估:100 分

            }
        });


        //神经性障碍
        //神经性障碍分数
        Diabetes_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mDiabete = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = mRootView.findViewById(checkedRadioButtonId);
                Diabetes1 = rb.getText().toString();
                mDiabete = Integer.parseInt(Diabetes1);
                total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                        mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                bt_compile.setText("评估:" + total + "分");//评估:100 分
            }
        });
        Diabetes_rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mMultipl = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                Diabetes2 = rb.getText().toString();
                mMultipl = Integer.parseInt(Diabetes2);
                total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                        mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                bt_compile.setText("评估:" + total + "分");//评估:100 分
            }
        });
        Diabetes_rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mAcciden = 0;
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                Diabetes3 = rb.getText().toString();
                mAcciden = Integer.parseInt(Diabetes3);
                total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                        mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                bt_compile.setText("评估:" + total + "分");//评估:100 分
            }
        });

        //药物治疗
        highAlcohol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    highAlcoholids = 0;
                    highAlcohol1 = buttonView.getText().toString();
                    highAlcoholid = "4";
                    highAlcoholids = Integer.parseInt(highAlcoholid);//药物治疗 分数
                    total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                }
            }
        });
        cytotoxicAgents.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cytotoxicAgents1ids = 0;
                    cytotoxicAgents1 = buttonView.getText().toString();
                    cytotoxicAgents1id = "4";
                    cytotoxicAgents1ids = Integer.parseInt(cytotoxicAgents1id);//药物治疗 分数
                    total = mEmotions + mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                }
            }
        });

        //预防措施
        preventive_measure_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == preventive_measure_rb1.getId()) {
                    int checkedRadioButtonId = group.getCheckedRadioButtonId();
                    RadioButton rb = getActivity().findViewById(checkedRadioButtonId);
                    mPreventive_measure_1 = rb.getText().toString();
//                    mPreventive_measure_1 = preventive_measure_tv1.getText().toString();
//                    mPreventive_measure_1 ="全面评估患者发生压疮的危险因素";
                } else {
                    mPreventive_measure_1 = "";
                }
            }
        });

        preventive_measure_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == preventive_measure_rb2.getId()) {
                    int checkedRadioButtonId = group.getCheckedRadioButtonId();
                    RadioButton rb = getActivity().findViewById(checkedRadioButtonId);
                    mPreventive_measure_2 = rb.getText().toString();
//                    mPreventive_measure_2 = preventive_measure_tv2.getText().toString();
//                    mPreventive_measure_2 = "合理安置手术体位,选择合适体位垫";
                } else {
                    mPreventive_measure_2 = "";
                }
            }
        });
        preventive_measure_3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == preventive_measure_rb3.getId()) {
                    int checkedRadioButtonId = group.getCheckedRadioButtonId();
                    RadioButton rb = getActivity().findViewById(checkedRadioButtonId);
                    mPreventive_measure_3 = rb.getText().toString();
//                    mPreventive_measure_3 = "保持床单平整,干燥,保护受压部位皮肤,合理使用抗压软垫";
                } else {
                    mPreventive_measure_3 = "";
                }
            }
        });
        preventive_measure_4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == preventive_measure_rb4.getId()) {
                    int checkedRadioButtonId = group.getCheckedRadioButtonId();
                    RadioButton rb = getActivity().findViewById(checkedRadioButtonId);
                    mPreventive_measure_4 = rb.getText().toString();
//                    mPreventive_measure_4 ="保障组织有效灌注,注意观察出血量与血压变化";
                } else {
                    mPreventive_measure_4 = "";
                }
            }
        });
        preventive_measure_5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == preventive_measure_rb5.getId()) {
                    int checkedRadioButtonId = group.getCheckedRadioButtonId();
                    RadioButton rb = getActivity().findViewById(checkedRadioButtonId);
                    mPreventive_measure_5 = rb.getText().toString();
//                    mPreventive_measure_5 = "注意保暖,维持患者正常体温";
                } else {
                    mPreventive_measure_5 = "";
                }
            }
        });
        preventive_measure_6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == preventive_measure_rb6.getId()) {
                    int checkedRadioButtonId = group.getCheckedRadioButtonId();
                    RadioButton rb = getActivity().findViewById(checkedRadioButtonId);
                    mPreventive_measure_2 = rb.getText().toString();
                    preventive_measure_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mPreventive_measure_et = "其他:" + s.toString();
                        }
                    });
                } else {
                    mPreventive_measure_et = "";
                }
            }
        });

        //临时保存
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> params = patientCacheData();
                mLoadListActivity = new UpLoadListActivity();
                PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
                UpLoadUtils.cacheRequest(mCache, patin, Api.PSEvaluation, params);
                T.showShort("临时保存成功");
            }
        });

        /**
         * 提交保存
         */
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientData();
            }
        });

        //其他危险因素
        hazards1_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hazardsId1 = 0;
                    hazards1 = buttonView.getText().toString();
                    hazardsid1 = "8";
                    hazardsId1 = Integer.parseInt(hazardsid1);//其他危险因素 分数
                    total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                } else {
                    hazardsId1 = 0;
                    total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                }
            }
        });
        hazards2_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hazardsId2 = 0;
                    hazards2 = buttonView.getText().toString();
                    hazardsid2 = "5";
                    hazardsId2 = Integer.parseInt(hazardsid2);//其他危险因素 分数
                    total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                } else {
                    hazardsId2 = 0;
                    total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                }
            }
        });
        hazards3_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hazardsId3 = 0;
                    hazards3 = buttonView.getText().toString();
                    hazardsid3 = "5";
                    hazardsId3 = Integer.parseInt(hazardsid3);//其他危险因素 分数
                    total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                } else {
                    hazardsId3 = 0;
                    total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                }
            }
        });
        hazards4_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hazardsId4 = 0;
                    hazards4 = buttonView.getText().toString();
                    hazardsid4 = "2";
                    hazardsId4 = Integer.parseInt(hazardsid4);//其他危险因素 分数
                    total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分

                } else {
                    hazardsId4 = 0;
                    total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                }
            }
        });
        hazards5_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hazardsId5 = 0;
                    hazards5 = buttonView.getText().toString();
                    hazardsid5 = "1";
                    hazardsId5 = Integer.parseInt(hazardsid5);//其他危险因素 分数
                    total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                } else {
                    hazardsId5 = 0;
                    total = mSourcesIds + mAllergicIds + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete + mMultipl +
                            mAcciden + highAlcoholids + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
                    bt_compile.setText("评估:" + total + "分");//评估:100 分
                }
            }
        });
    }


    /**
     * 保存患者压疮风险评估单
     */
    private void patientData() {
        Map<String, Object> params = patientCacheData();
        NetRequest.getInstens().requestDate(params, Api.PSEvaluation, false, new NetCallBack<ArrayList<PressureSoreBean>>() {
            @Override
            public void onSuccess(ArrayList<PressureSoreBean> bean) {
                if (null != bean) {
                    JumpUtils.exitActivity(mContext);
                    T.showShort("保存成功");
                }
            }

            @Override
            public void updataLayout(int flag) {
//                if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID) {
//                    PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                    UpLoadUtils.cacheRequest(mCache, patin, Api.PSEvaluation, params);
//                }
            }

            @Override
            protected void onFlaiCacheRequest() {

            }
        });
    }


    /**
     * 临时保存患者压疮风险评估单
     */
    private Map<String, Object> patientCacheData() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("Token", loginBean.getToken());
        params.put("DateKey", System.currentTimeMillis()); //时间戳
        params.put("PA_ID", mPatID);
        params.put("UserID", loginBean.getUserID());
        params.put("In_Dis", mRyDisStr);//术前诊断
        params.put("OP_Name", operationName.getText().toString().trim());//手术名称
        //总分
        OverallScore = mEmotions + mSourcesIds + mAllergicIds + highAlcoholids + mRelationIds + mNutritionIds + mActivitiesIds + mAeglutitionIds + mTraumaIds + mDiabete
                + mMultipl + mAcciden + cytotoxicAgents1ids + hazardsId1 + hazardsId2 + hazardsId3 + hazardsId4 + hazardsId5;
        params.put("OverallScore", OverallScore);//评估总分
        params.put("SomatoType", mSources);//体型
        params.put("SomatoScore", mSourcesId);//体型评分
        params.put("SkinType", mAllergic);//危险部位的皮肤类型
        params.put("SkinScore", mAllergicId);//皮肤类型评分
        params.put("Sex", mEmotion);//性别
        params.put("SexScore", mEmotionId);//性别评分
        params.put("Age", mRelation);//年龄
        params.put("AgeScore", mRelationId);//年龄评分
        params.put("OtherHazard", hazards1 + " " + hazards2 + " " + hazards3 + " " + hazards4 + " " + hazards5);//其他危险因素
        params.put("OHScore", hazardsId1 + " " + hazardsId2 + " " + hazardsId3 + " " + hazardsId4 + " " + hazardsId5);//其他危险因素评分
        params.put("ContinenceAbility", mNutrition);//控便能力
        params.put("CAScore", mNutritionId);//控便能力评分
        params.put("Medication", highAlcohol1 + " " + cytotoxicAgents1);//药物治疗
        params.put("MedicationScore", highAlcoholid + " " + cytotoxicAgents1id);//药物治疗评分
        params.put("Activities", mActivities);//活动情况
        params.put("ActivitiesScore", mActivitiesId);//活动情况评分
        params.put("Appetite", mAeglutition);//饮食与食欲
        params.put("AppetiteScore", mAeglutitionId);//饮食与食欲评分
        params.put("NeuroticDisor", DiabetesStr1 + " " + DiabetesStr2 + " " + DiabetesStr3);//神经性障碍
        params.put("NDScore", mDiabete + ";" + mMultipl + ";" + mAcciden);//神经性障碍评分
        params.put("Major_OP", mTrauma);//大手术/创伤
        params.put("Major_OPScore", mTraumaId);//大手术/创伤评分
        params.put("PM1", mPreventive_measure_1);//预防措施条例1
        params.put("PM2", mPreventive_measure_2);//预防措施条例2
        params.put("PM3", mPreventive_measure_3);//预防措施条例3
        params.put("PM4", mPreventive_measure_4);//预防措施条例4
        params.put("PM5", mPreventive_measure_5);//预防措施条例5
        params.put("PM6", mPreventive_measure_et);//预防措施条例6 其他
        params.put("AssessmentNurseID", mUserID); //访视护士ID
        params.put("AssessmentNurse", mUserName); //访视护士
        params.put("AssessmentDate", mCreateDate); //护士访视时间
        return params;
    }
}