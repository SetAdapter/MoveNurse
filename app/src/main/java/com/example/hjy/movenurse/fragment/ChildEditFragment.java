package com.example.hjy.movenurse.fragment;

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
import com.example.hjy.movenurse.activity.ChildConditionActivity;
import com.example.hjy.movenurse.upload.UpLoadUtils;
import com.fy.base.BaseFragment;
import com.fy.entity.ChildFirstNurseBean;
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
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Gab on 2017/9/15 0015.
 * 儿童首次护理记录  编辑 fragment
 */

public class ChildEditFragment extends BaseFragment {

    @BindView(R.id.tv_CreateDate)
    TextView tv_CreateDate;
    @BindView(R.id.tv_FZ_Nurse)
    TextView tv_FZ_Nurse;

    @BindView(R.id.environment)
    MyRadioGroup mLifeEnvironment;
    @BindView(R.id.hospitalized)
    MyRadioGroup mHospitalized;
    @BindView(R.id.favourer_rg)
    MyRadioGroup mFavourer;
    @BindView(R.id.defecate)
    MyRadioGroup mDefecate;
    @BindView(R.id.urinate_mrg)
    MyRadioGroup mUrinateMrg;

    @BindView(R.id.language)
    RadioGroup language;
    @BindView(R.id.source_rg)
    RadioGroup mSource;
    @BindView(R.id.mConsciousness)
    RadioGroup rg_mConsciousness;
    @BindView(R.id.mConsciousness_other)
    RadioGroup mConsciousness_other;
    @BindView(R.id.consciousness_duida)
    RadioGroup consciousness_dui;
    @BindView(R.id.chest)
    RadioGroup chest;

    @BindView(R.id.head_et)
    EditText head_et;
    @BindView(R.id.rb_mDefecate1)
    RadioButton rb_mDefecate1;
    @BindView(R.id.rb_mDefecate2)
    RadioButton rb_mDefecate2;
    @BindView(R.id.rb_mDefecate3)
    RadioButton rb_mDefecate3;
    @BindView(R.id.rb_mDefecate4)
    RadioButton rb_mDefecate4;
    @BindView(R.id.limb_movement_rb1)
    CheckBox limb_movement_rb1;
    @BindView(R.id.limb_movement_rb2)
    CheckBox limb_movement_rb2;
    @BindView(R.id.limb_movement_rb3)
    CheckBox limb_movement_rb3;
    @BindView(R.id.limb_movement_rb4)
    CheckBox limb_movement_rb4;
    @BindView(R.id.limb_movement_rb5)
    CheckBox limb_movement_rb5;
    @BindView(R.id.limb_movement_rb6)
    CheckBox limb_movement_rb6;
    @BindView(R.id.limb_movement_rb7)
    CheckBox limb_movement_rb7;
    @BindView(R.id.limb_movement_rb8)
    CheckBox limb_movement_rb8;
    @BindView(R.id.limb_movement_rb9)
    CheckBox limb_movement_rb9;
    @BindView(R.id.limb_movement_other)
    CheckBox limb_movement_other;
    @BindView(R.id.radio_middle_school)
    RadioButton radio_middle_school;
    @BindView(R.id.radio_primary_school)
    RadioButton radio_primary_school;
    @BindView(R.id.kindergarten)
    RadioButton kindergarten;
    @BindView(R.id.from_home)
    RadioButton from_home;
    @BindView(R.id.house_home)
    RadioButton house_home;
    @BindView(R.id.surroundings_rb)
    RadioButton surroundings_rb;
    @BindView(R.id.source_other)
    RadioButton source_other;
    @BindView(R.id.walk)
    RadioButton walk;
    @BindView(R.id.hand)
    RadioButton hand;
    @BindView(R.id.help_line)
    RadioButton help_line;
    @BindView(R.id.wheelchair)
    RadioButton wheelchair;
    @BindView(R.id.flatcar)
    RadioButton flatcar;
    @BindView(R.id.rb_type_other)
    RadioButton rb_type_other;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb5)
    RadioButton rb5;
    @BindView(R.id.rb6)
    RadioButton rb6;
    @BindView(R.id.rb7)
    RadioButton rb7;
    @BindView(R.id.consciousness_duida_rb)
    RadioButton consciousness_duida_rb;
    @BindView(R.id.deglutition_other)
    RadioButton deglutition_other;
    @BindView(R.id.language_other)
    RadioButton language_other;
    @BindView(R.id.urinate_rb1)
    CheckBox mUrinateRb1;
    @BindView(R.id.urinate_rb2)
    CheckBox mUrinateRb2;
    @BindView(R.id.urinate_rb3)
    CheckBox mUrinateRb3;
    @BindView(R.id.urinate_rb4)
    CheckBox mUrinateRb4;
    @BindView(R.id.urinate_rb5)
    CheckBox mUrinateRb5;
    @BindView(R.id.urinate_rb6)
    CheckBox mUrinateRb6;
    @BindView(R.id.urinate_rb7)
    CheckBox mUrinateRb7;
    @BindView(R.id.urinate_rb8)
    CheckBox mUrinateRb8;
    @BindView(R.id.urinate_rb9)
    CheckBox mUrinateRb9;
    @BindView(R.id.urinate_rb10)
    CheckBox mUrinateRb10;
    @BindView(R.id.urinate_rb_other)
    CheckBox urinate_rb_other;
    @BindView(R.id.bregma_1)
    CheckBox bregma_1;
    @BindView(R.id.bregma_2)
    CheckBox bregma_2;
    @BindView(R.id.bregma_3)
    CheckBox bregma_3;
    @BindView(R.id.bregma_4)
    CheckBox bregma_4;
    @BindView(R.id.bregma_5)
    CheckBox bregma_5;
    @BindView(R.id.chest_rb)
    RadioButton chest_rb;
    @BindView(R.id.skin_condition_rb1)
    CheckBox skin_condition_rb1;
    @BindView(R.id.skin_condition_rb2)
    CheckBox skin_condition_rb2;
    @BindView(R.id.skin_condition_rb3)
    CheckBox skin_condition_rb3;
    @BindView(R.id.skin_condition_rb4)
    CheckBox skin_condition_rb4;
    @BindView(R.id.skin_condition_rb5)
    CheckBox skin_condition_rb5;
    @BindView(R.id.skin_condition_rb6)
    CheckBox skin_condition_rb6;
    @BindView(R.id.skin_condition_rb7)
    CheckBox skin_condition_rb7;
    @BindView(R.id.skin_condition_rb8)
    CheckBox skin_condition_rb8;
    @BindView(R.id.skin_condition_rb9)
    CheckBox skin_condition_rb9;
    @BindView(R.id.skin_condition_rb10)
    CheckBox skin_condition_rb10;
    @BindView(R.id.skin_condition_rb11)
    CheckBox skin_condition_rb11;
    @BindView(R.id.skin_condition_rb12)
    CheckBox skin_condition_rb12;
    @BindView(R.id.skin_condition_rb13)
    CheckBox skin_condition_rb13;
    @BindView(R.id.skin_condition_rb14)
    CheckBox skin_condition_rb14;
    @BindView(R.id.skin_condition_rb15)
    CheckBox skin_condition_rb15;
    @BindView(R.id.skin_condition_other)
    CheckBox skin_condition_other;
    @BindView(R.id.skin_condition_YCType)
    RadioGroup mSkinConditionYCType;
    @BindView(R.id.diet_1)
    RadioButton diet_1;
    @BindView(R.id.diet_2)
    RadioButton diet_2;
    @BindView(R.id.diet_3)
    RadioButton diet_3;
    @BindView(R.id.diet_4)
    RadioButton diet_4;
    @BindView(R.id.diet_5)
    RadioButton diet_5;
    @BindView(R.id.diet_6)
    RadioButton diet_6;
    @BindView(R.id.diet_other)
    RadioButton diet_other;

    @BindView(R.id.sleep)
    CheckBox sleep;
    @BindView(R.id.sleep1)
    CheckBox sleep1;
    @BindView(R.id.sleep2)
    CheckBox sleep2;
    @BindView(R.id.sleep3)
    CheckBox sleep3;
    @BindView(R.id.sleep4)
    CheckBox sleep4;
    @BindView(R.id.sleep5)
    CheckBox sleep5;
    @BindView(R.id.sleep_other)
    CheckBox sleep_other;

    @BindView(R.id.diet)
    MyRadioGroup diet;
    @BindView(R.id.nutrition)
    RadioGroup nutrition;

    @BindView(R.id.deglutition)
    RadioGroup mDeglutition;

    @BindView(R.id.et_surroundings)
    EditText et_surroundings;
    @BindView(R.id.sleep_et)
    EditText sleep_et;
    @BindView(R.id.deglutition_other_et)
    EditText deglutition_other_et;
    @BindView(R.id.chest_et)
    EditText chest_et;
    @BindView(R.id.other_dict)
    EditText mOtherDict;
    @BindView(R.id.source_other_et)
    EditText source_other_et;
    @BindView(R.id.ry_dis)
    TextView mRyDis;
    @BindView(R.id.ry_type_other)
    EditText ry_type_other;
    @BindView(R.id.allergic_other)
    EditText mAllergicOther;
    @BindView(R.id.favourer_other)
    EditText favourer_other;
    @BindView(R.id.consciousness_duida_ed)
    EditText mConsciousness_duida_ed;
    @BindView(R.id.relation_emotion_et)
    EditText relation_emotion_et;
    @BindView(R.id.emotion_et)
    EditText emotion_et;
    @BindView(R.id.skin_condition_et)
    EditText skin_condition_et;
    @BindView(R.id.language_other_et)
    EditText language_other_et;
    @BindView(R.id.ed_frequency)
    EditText ed_frequency;
    @BindView(R.id.ed_day)
    EditText ed_day;
    @BindView(R.id.diarrhea_frequency)
    EditText diarrhea_frequency;
    @BindView(R.id.diarrhea_day)
    EditText diarrhea_day;
    @BindView(R.id.Defecate_other)
    EditText mDefecateother;
    @BindView(R.id.urinate_other)
    EditText mUrinateOther;
    @BindView(R.id.bregma_et)
    EditText bregma_et;
    @BindView(R.id.limb_movement_et)
    EditText limb_movement_et;
    @BindView(R.id.diet_et)
    EditText diet_et;
    @BindView(R.id.save_btn)
    Button mSaveBtn;
    @BindView(R.id.cb_1)
    CheckBox cb_1;
    @BindView(R.id.cb_2)
    CheckBox cb_2;
    @BindView(R.id.cb_3)
    CheckBox cb_3;

    @BindView(R.id.OralMucosa1)
    CheckBox OralMucosa1;//
    @BindView(R.id.OralMucosa2)
    CheckBox OralMucosa2;//
    @BindView(R.id.OralMucosa3)
    CheckBox OralMucosa3;//
    @BindView(R.id.OralMucosa4)
    CheckBox OralMucosa4;//
    @BindView(R.id.OralMucosa5)
    CheckBox OralMucosa5;//
    @BindView(R.id.OralMucosa6)
    CheckBox OralMucosa6;//

    @BindView(R.id.Allergic1)
    CheckBox mAllergic1;//
    @BindView(R.id.Allergic2)
    CheckBox mAllergic2;//
    @BindView(R.id.Allergic3)
    CheckBox mAllergic3;//
    @BindView(R.id.Allergic4)
    CheckBox mAllergic4;//
    @BindView(R.id.Allergic5)
    CheckBox mAllergic5;//

    @BindView(R.id.emotion1)
    CheckBox emotion1;//
    @BindView(R.id.emotion2)
    CheckBox emotion2;//
    @BindView(R.id.emotion3)
    CheckBox emotion3;//
    @BindView(R.id.emotion4)
    CheckBox emotion4;//
    @BindView(R.id.emotion5)
    CheckBox emotion5;//

    @BindView(R.id.relation_emotion1)
    CheckBox relation_emotion1;//
    @BindView(R.id.relation_emotion2)
    CheckBox relation_emotion2;//
    @BindView(R.id.relation_emotion3)
    CheckBox relation_emotion3;//
    @BindView(R.id.relation_emotion4)
    CheckBox relation_emotion4;//
    @BindView(R.id.relation_emotion5)
    CheckBox relation_emotion5;//家属情绪

    @BindView(R.id.head_cb1)
    CheckBox head_cb1;//
    @BindView(R.id.head_cb2)
    CheckBox head_cb2;//
    @BindView(R.id.head_cb3)
    CheckBox head_cb3;//
    @BindView(R.id.head_other_rb)
    CheckBox head_other_rb;//

    @BindView(R.id.Ability1)
    CheckBox Ability1;//
    @BindView(R.id.Ability2)
    CheckBox Ability2;//
    @BindView(R.id.Ability3)
    CheckBox Ability3;//
    @BindView(R.id.Ability4)
    CheckBox Ability4;//
    @BindView(R.id.Ability5)
    CheckBox Ability5;//

    @BindView(R.id.rb_SkinCondition)
    RadioButton rb_SkinCondition;//

    @BindView(R.id.SkinCondition)
    TextView SkinCondition;
    @BindView(R.id.SkinCondition_Y)
    EditText SkinCondition_Y;
    @BindView(R.id.SkinCondition_YC)
    EditText SkinCondition_YC_tv;
    @BindView(R.id.SkinCondition_YCM)
    EditText SkinCondition_YCM;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    private String OralMucosaRg1 = "";//选中的口腔黏膜
    private String OralMucosaRg2 = "";//选中的口腔黏膜
    private String OralMucosaRg3 = "";//选中的口腔黏膜
    private String OralMucosaRg4 = "";//选中的口腔黏膜
    private String OralMucosaRg5 = "";//选中的口腔黏膜
    private String OralMucosaRg6 = "";//选中的口腔黏膜

    private String mPatID = "";
    private String mUserName = "";
    private String mUrinateRbStr1 = "";
    private String mUrinateRbStr2 = "";
    private String mUrinateRbStr3 = "";
    private String mUrinateRbStr4 = "";
    private String mUrinateRbStr5 = "";
    private String mUrinateRbStr6 = "";
    private String mUrinateRbStr7 = "";
    private String mUrinateRbStr8 = "";
    private String mUrinateRbStr9 = "";
    private String mUrinateRbStr10 = "";
    private String mUrinateOtherRbStr = "";
    private String mCreateDate = "";
    private String mDateKey = "";
    private String mCheckBox1 = "";
    private String mCheckBox2 = "";
    private String mCheckBox3 = "";
    private String mLifeEnvironment_Str = "";
    private String mHospitalized_Str = "";
    private String DataSources = "";
    private String mRyDisStr = "";
    private String mAllergicStr1 = "";
    private String mAllergicStr2 = "";
    private String mAllergicStr3 = "";
    private String mAllergicStr4 = "";
    private String mAllergicStr5 = "";
    private String mFavourerStr = "";
    private String mConsciousness = "";
    private String mConsciousnessohter = "";
    private String consciousness_duida = "";
    private String EmotionStr1 = "";
    private String EmotionStr2 = "";
    private String EmotionStr3 = "";
    private String EmotionStr4 = "";
    private String EmotionStr5 = "";
    private String Relation_emotionStr1 = "";
    private String Relation_emotionStr2 = "";
    private String Relation_emotionStr3 = "";
    private String Relation_emotionStr4 = "";
    private String Relation_emotionStr5 = "";
    private String mLanguageotherStr = "";
    private String DietStr = "";
    private String NutritionStr = "";
    private String mSwallowStr = "";
    private String mSleepStr1 = "";
    private String mSleepStr2 = "";
    private String mSleepStr3 = "";
    private String mSleepStr4 = "";
    private String mSleepStr5 = "";
    private String mSleepOtherStr = "";
    private String mDeglutitionStr1 = "";
    private String mDeglutitionStr2 = "";
    private String mDeglutitionStr3 = "";
    private String mDeglutitionOtherStr = "";
    private String mChestStr = "";
    private String mAbilityStr1 = "";
    private String mAbilityStr2 = "";
    private String mAbilityStr3 = "";
    private String mAbilityStr4 = "";
    private String mAbilityStr5 = "";
    private String ed_frequency_str = "";
    private String ed_day_str = "";
    private String rb_mNormal_str = "";
    private String diarrhea_frequency_str = "";
    private String diarrhea_day_str = "";
    private String mDefecateotherStr = "";
    private String mBregmaStr1 = "";
    private String mBregmaStr2 = "";
    private String mBregmaStr3 = "";
    private String mBregmaStr4 = "";
    private String mBregmaStr5 = "";
    private String mOtherStr = "";
    private String mSkinConditionStr1 = "";
    private String mSkinConditionStr2 = "";
    private String mSkinConditionStr3 = "";
    private String mSkinConditionStr4 = "";
    private String mSkinConditionStr5 = "";
    private String mSkinConditionStr6 = "";
    private String mSkinConditionStr7 = "";
    private String mSkinConditionStr8 = "";
    private String mSkinConditionStr9 = "";
    private String mSkinConditionStr10 = "";
    private String mSkinConditionStr11 = "";
    private String mSkinConditionStr12 = "";
    private String mSkinConditionStr13 = "";
    private String mSkinConditionStr14 = "";
    private String mSkinConditionStr15 = "";
    private String mSkinConditionStr = "";
    private String mSkinConditionYCTypeStr = "";
    private String mLimbMovementRbStr1 = "";
    private String mLimbMovementRbStr2 = "";
    private String mLimbMovementRbStr3 = "";
    private String mLimbMovementRbStr4 = "";
    private String mLimbMovementRbStr5 = "";
    private String mLimbMovementRbStr6 = "";
    private String mLimbMovementRbStr7 = "";
    private String mLimbMovementRbStr8 = "";
    private String mLimbMovementRbStr9 = "";
    private String mLimbMovementOtherStr = "";
    private String mSkinCondition_YCMStr = "";
    private String mSkinCondition_YCStr = "";
    private String mSkinCondition_YStr = "";
    private String rb_mDefecate1_str = "";

    @Override
    protected int getContentLayout() {
        return R.layout.childfirstnurseorderfragment;
    }

    @Override
    protected void baseInitView() {
        super.baseInitView();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        mCreateDate = formatter.format(curDate);
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        mUserName = loginBean.getUserName();
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        mPatID = patin.getPatID();
        mCache.remove("Tag");
        initListener();
        //入院诊断
        mRyDisStr = patin.getZyDetail().getICD_Name();
        mRyDis.setText(mRyDisStr);
        tv_CreateDate.setText("记录时间:" + mCreateDate);
        tv_FZ_Nurse.setText("负责护士签名:" + mUserName);
    }

    @Override
    public void onResume() {
        super.onResume();
        SkinCondition.setText(mCache.getAsString("Tag"));
    }


    private void initListener() {
        //生活环境
        mLifeEnvironment.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {

                if (checkedId == radio_middle_school.getId()) {
                    mLifeEnvironment_Str = radio_middle_school.getText().toString();
                }
                if (checkedId == radio_primary_school.getId()) {
                    mLifeEnvironment_Str = radio_primary_school.getText().toString();
                }
                if (checkedId == kindergarten.getId()) {
                    mLifeEnvironment_Str = kindergarten.getText().toString();
                }
                if (checkedId == from_home.getId()) {
                    mLifeEnvironment_Str = from_home.getText().toString();
                }
                if (checkedId == house_home.getId()) {
                    mLifeEnvironment_Str = house_home.getText().toString();
                }
                //生活环境 其他
                if (checkedId == surroundings_rb.getId()) {
                    et_surroundings.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mLifeEnvironment_Str = s.toString();
                        }
                    });
                }
            }
        });
        //资料来源
        mSource.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                DataSources = rb.getText().toString();
                if (checkedId == source_other.getId()) {
                    //资料来源其他
                    source_other_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            DataSources = s.toString();
                        }
                    });
                }
            }
        });
        //入院方式
        mHospitalized.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {

                if (checkedId == walk.getId()) {
                    mHospitalized_Str = walk.getText().toString();
                }
                if (checkedId == hand.getId()) {
                    mHospitalized_Str = hand.getText().toString();
                }
                if (checkedId == help_line.getId()) {
                    mHospitalized_Str = help_line.getText().toString();
                }
                if (checkedId == wheelchair.getId()) {
                    mHospitalized_Str = wheelchair.getText().toString();
                }
                if (checkedId == flatcar.getId()) {
                    mHospitalized_Str = flatcar.getText().toString();
                }
                //入院方式 其他
                if (checkedId == rb_type_other.getId()) {
                    ry_type_other.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mHospitalized_Str = s.toString();
                        }
                    });
                }
            }
        });

        mAllergic1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAllergic2.setChecked(false);
                    mAllergic3.setChecked(false);
                    mAllergic4.setChecked(false);
                    mAllergic5.setChecked(false);
                    mAllergicStr1 = buttonView.getText().toString();
                } else {
                    mAllergic1.setChecked(false);
                    mAllergicStr1 = "";
                }
            }
        });
        mAllergic2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAllergicStr2 = buttonView.getText().toString();
                    mAllergic1.setChecked(false);
                } else {
                    mAllergicStr2 = "";
                }
            }
        });
        mAllergic3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAllergicStr3 = buttonView.getText().toString();
                    mAllergic1.setChecked(false);
                } else {
                    mAllergicStr3 = "";
                }
            }
        });
        mAllergic4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAllergicStr4 = buttonView.getText().toString();
                    mAllergic1.setChecked(false);
                } else {
                    mAllergicStr4 = "";
                }
            }
        });
        mAllergic5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAllergic1.setChecked(false);
                    //过敏史 其他
                    mAllergicOther.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mAllergicStr5 = s.toString();
                        }
                    });
                } else {
                    mAllergicStr5 = "";
                }
            }
        });

        //日常照顾者
        mFavourer.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {

                if (checkedId == rb1.getId()) {
                    mFavourerStr = rb1.getText().toString();
                }
                if (checkedId == rb2.getId()) {
                    mFavourerStr = rb2.getText().toString();
                }
                if (checkedId == rb3.getId()) {
                    mFavourerStr = rb3.getText().toString();
                }
                if (checkedId == rb5.getId()) {
                    mFavourerStr = rb5.getText().toString();
                }
                if (checkedId == rb6.getId()) {
                    mFavourerStr = rb6.getText().toString();
                }
                //日常照顾者 其他
                if (checkedId == rb7.getId()) {
                    favourer_other.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mFavourerStr = s.toString();
                        }
                    });
                }
            }
        });

        //意识状态
        //呼之
        rg_mConsciousness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mConsciousness = "呼之:" + rb.getText().toString();
            }
        });
        //意识状态 其他
        mConsciousness_other.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //其他
                if (checkedId == consciousness_duida_rb.getId()) {
                    mConsciousness_duida_ed.setFocusable(true);
                    mConsciousness_duida_ed.setFocusableInTouchMode(true);
                    mConsciousness_duida_ed.requestFocus();
                    mConsciousness_duida_ed.findFocus();
                    mConsciousness_duida_ed.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mConsciousnessohter = "其他:" + s.toString();
                        }
                    });
                }
            }
        });
        //对答
        consciousness_dui.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = mRootView.findViewById(checkedRadioButtonId);
                consciousness_duida = "对答:"+rb.getText().toString();
            }
        });

        //患儿情绪
        emotion1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EmotionStr1 = buttonView.getText().toString();
                } else {
                    EmotionStr1 = "";
                }
            }
        });

        emotion2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EmotionStr2 = buttonView.getText().toString();
                } else {
                    EmotionStr2 = "";
                }
            }
        });

        emotion3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EmotionStr3 = buttonView.getText().toString();
                } else {
                    EmotionStr3 = "";
                }
            }
        });

        emotion4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EmotionStr4 = buttonView.getText().toString();
                } else {
                    EmotionStr4 = "";
                }
            }
        });

        emotion5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    emotion_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            EmotionStr5 = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } else {
                    EmotionStr5 = "";
                }
            }
        });
        //家属情绪
        relation_emotion1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    relation_emotion2.setChecked(false);
                    relation_emotion3.setChecked(false);
                    relation_emotion4.setChecked(false);
                    relation_emotion5.setChecked(false);
                    Relation_emotionStr1 = buttonView.getText().toString();
                } else {
                    relation_emotion5.setChecked(false);
                    Relation_emotionStr1 = "";
                }
            }
        });
        relation_emotion2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Relation_emotionStr2 = buttonView.getText().toString();
                } else {
                    Relation_emotionStr2 = "";
                }
            }
        });
        relation_emotion3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Relation_emotionStr3 = buttonView.getText().toString();
                } else {
                    Relation_emotionStr3 = "";
                }
            }
        });
        relation_emotion4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Relation_emotionStr4 = buttonView.getText().toString();
                } else {
                    Relation_emotionStr4 = "";
                }
            }
        });
        relation_emotion5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    relation_emotion_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Relation_emotionStr5 = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } else {
                    Relation_emotionStr5 = "";
                }
            }
        });
        //语言
        language.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mLanguageotherStr = rb.getText().toString();
                if (checkedId == language_other.getId()) {
                    language_other_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mLanguageotherStr = s.toString();
                        }
                    });
                }
            }
        });

        //饮食
        diet.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {

                if (checkedId == diet_1.getId()) {
                    DietStr = diet_1.getText().toString();
                }
                if (checkedId == diet_2.getId()) {
                    DietStr = diet_2.getText().toString();
                }
                if (checkedId == diet_3.getId()) {
                    DietStr = diet_3.getText().toString();
                }
                if (checkedId == diet_4.getId()) {
                    DietStr = diet_4.getText().toString();
                }
                if (checkedId == diet_5.getId()) {
                    DietStr = diet_5.getText().toString();
                }
                if (checkedId == diet_6.getId()) {
                    DietStr = diet_6.getText().toString();
                }
                if (checkedId == diet_other.getId()) {
                    diet_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            DietStr = s.toString();
                        }
                    });
                }
            }
        });

        //营养状况
        nutrition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                NutritionStr = rb.getText().toString();
            }
        });
        //口腔黏膜
        OralMucosa1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OralMucosa2.setChecked(false);
                    OralMucosa3.setChecked(false);
                    OralMucosa4.setChecked(false);
                    OralMucosa5.setChecked(false);
                    OralMucosa6.setChecked(false);
                    OralMucosaRg1 = buttonView.getText().toString();
                } else {
                    OralMucosa1.setChecked(false);
                    OralMucosaRg1 = "";
                }
            }
        });
        OralMucosa2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OralMucosa1.setChecked(false);
                    OralMucosaRg2 = buttonView.getText().toString();
                } else {
                    OralMucosaRg2 = "";
                }
            }
        });
        OralMucosa3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OralMucosa1.setChecked(false);
                    OralMucosaRg3 = buttonView.getText().toString();
                } else {
                    OralMucosaRg3 = "";
                }
            }
        });
        OralMucosa4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OralMucosa1.setChecked(false);
                    OralMucosaRg4 = buttonView.getText().toString();
                } else {
                    OralMucosaRg4 = "";
                }
            }
        });
        OralMucosa5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OralMucosa1.setChecked(false);
                    OralMucosaRg5 = buttonView.getText().toString();
                } else {
                    OralMucosaRg5 = "";
                }
            }
        });
        OralMucosa6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OralMucosa1.setChecked(false);
                    OralMucosaRg6 = buttonView.getText().toString();
                } else {
                    OralMucosaRg6 = "";
                }
            }
        });

        //吞咽情况
        mDeglutition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mSwallowStr = rb.getText().toString();
                if (checkedId == deglutition_other.getId()) {
                    deglutition_other_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mSwallowStr = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }

            }
        });
        //睡眠情况
        sleep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sleep1.setChecked(false);
                    sleep2.setChecked(false);
                    sleep3.setChecked(false);
                    sleep4.setChecked(false);
                    sleep5.setChecked(false);
                    sleep_other.setChecked(false);
                    mSleepStr1 = buttonView.getText().toString();
                } else {
                    sleep.setChecked(false);
                    mSleepStr1 = "";
                }
            }
        });
        sleep1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sleep.setChecked(false);
                    mSleepStr1 = buttonView.getText().toString();
                } else {
                    mSleepStr1 = "";
                }
            }
        });
        sleep2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sleep.setChecked(false);
                    mSleepStr2 = buttonView.getText().toString();
                } else {
                    mSleepStr2 = "";
                }
            }
        });
        sleep3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sleep.setChecked(false);
                    mSleepStr3 = buttonView.getText().toString();
                } else {
                    mSleepStr3 = "";
                }
            }
        });
        sleep4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sleep.setChecked(false);
                    mSleepStr4 = buttonView.getText().toString();
                } else {
                    mSleepStr4 = "";
                }
            }
        });
        sleep5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sleep.setChecked(false);
                    mSleepStr5 = buttonView.getText().toString();
                } else {
                    mSleepStr5 = "";
                }
            }
        });
        sleep_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sleep.setChecked(false);
                    sleep_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mSleepOtherStr = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } else {
                    mSleepOtherStr = "";
                }
            }
        });

        //排便状况
        mDefecate.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                //正常
                if (checkedId == rb_mDefecate1.getId()) {
                    rb_mNormal_str = rb_mDefecate1.getText().toString();
                } else {
                    rb_mNormal_str = "";
                }
                //便秘
                if (checkedId == rb_mDefecate2.getId()) {
                    ed_frequency.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            ed_frequency_str = "便秘:" + ed_frequency.getText().toString() + "次/";

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    ed_day.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            ed_day_str = s.toString() + "天";
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }

                //腹泻
                if (checkedId == rb_mDefecate3.getId()) {
                    diarrhea_frequency.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            diarrhea_frequency_str = "腹泻:" + s.toString() + "次/";

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    diarrhea_day.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            diarrhea_day_str = s.toString() + "天";
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                if (checkedId == rb_mDefecate4.getId()) {
                    //排便状况 其他
                    mDefecateother.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            rb_mDefecate1_str = s.toString();

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
        });


        //四肢活动
        limb_movement_rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limb_movement_rb2.setChecked(false);
                    limb_movement_rb3.setChecked(false);
                    limb_movement_rb4.setChecked(false);
                    limb_movement_rb5.setChecked(false);
                    limb_movement_rb6.setChecked(false);
                    limb_movement_rb7.setChecked(false);
                    limb_movement_rb8.setChecked(false);
                    limb_movement_rb9.setChecked(false);
                    limb_movement_other.setChecked(false);
                    mLimbMovementRbStr1 = buttonView.getText().toString();
                } else {
                    limb_movement_rb1.setChecked(false);
                    mLimbMovementRbStr1 = "";
                }
            }
        });
        limb_movement_rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limb_movement_rb1.setChecked(false);
                    mLimbMovementRbStr2 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr2 = "";
                }
            }
        });
        limb_movement_rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limb_movement_rb1.setChecked(false);
                    mLimbMovementRbStr3 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr3 = "";
                }
            }
        });
        limb_movement_rb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limb_movement_rb1.setChecked(false);
                    mLimbMovementRbStr4 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr4 = "";
                }
            }
        });
        limb_movement_rb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limb_movement_rb1.setChecked(false);
                    mLimbMovementRbStr5 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr5 = "";
                }
            }
        });
        limb_movement_rb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limb_movement_rb1.setChecked(false);
                    mLimbMovementRbStr6 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr6 = "";
                }
            }
        });
        limb_movement_rb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limb_movement_rb1.setChecked(false);
                    mLimbMovementRbStr7 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr7 = "";
                }
            }
        });
        limb_movement_rb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limb_movement_rb1.setChecked(false);
                    mLimbMovementRbStr8 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr8 = "";
                }
            }
        });
        limb_movement_rb9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limb_movement_rb1.setChecked(false);
                    mLimbMovementRbStr9 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr9 = "";
                }
            }
        });
        limb_movement_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limb_movement_rb1.setChecked(false);
                    limb_movement_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mLimbMovementOtherStr = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } else {
                    mLimbMovementOtherStr = "";
                }
            }
        });

        //排尿情况
        mUrinateRb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb2.setChecked(false);
                    mUrinateRb3.setChecked(false);
                    mUrinateRb4.setChecked(false);
                    mUrinateRb5.setChecked(false);
                    mUrinateRb6.setChecked(false);
                    mUrinateRb7.setChecked(false);
                    mUrinateRb8.setChecked(false);
                    mUrinateRb9.setChecked(false);
                    mUrinateRb10.setChecked(false);
                    urinate_rb_other.setChecked(false);
                    mUrinateRbStr1 = buttonView.getText().toString();
                } else {
                    mUrinateRb1.setChecked(false);
                    mUrinateRbStr1 = "";
                }
            }
        });
        mUrinateRb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb1.setChecked(false);
                    mUrinateRbStr2 = buttonView.getText().toString();
                } else {
                    mUrinateRbStr2 = "";
                }
            }
        });
        mUrinateRb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb1.setChecked(false);
                    mUrinateRbStr3 = buttonView.getText().toString();
                } else {
                    mUrinateRbStr3 = "";
                }
            }
        });
        mUrinateRb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb1.setChecked(false);
                    mUrinateRbStr4 = buttonView.getText().toString();
                } else {
                    mUrinateRbStr4 = "";
                }
            }
        });
        mUrinateRb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb1.setChecked(false);
                    mUrinateRbStr5 = buttonView.getText().toString();
                } else {
                    mUrinateRbStr5 = "";
                }
            }
        });
        mUrinateRb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb1.setChecked(false);
                    mUrinateRbStr6 = buttonView.getText().toString();
                } else {
                    mUrinateRbStr6 = "";
                }
            }
        });
        mUrinateRb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb1.setChecked(false);
                    mUrinateRbStr7 = buttonView.getText().toString();
                } else {
                    mUrinateRbStr7 = "";
                }
            }
        });
        mUrinateRb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb1.setChecked(false);
                    mUrinateRbStr8 = buttonView.getText().toString();
                } else {
                    mUrinateRbStr8 = "";
                }
            }
        });
        mUrinateRb9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb1.setChecked(false);
                    mUrinateRbStr9 = buttonView.getText().toString();
                } else {
                    mUrinateRbStr9 = "";
                }
            }
        });
        mUrinateRb10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb1.setChecked(false);
                    mUrinateRbStr10 = buttonView.getText().toString();
                } else {
                    mUrinateRbStr10 = "";
                }
            }
        });
        urinate_rb_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUrinateRb1.setChecked(false);
                    mUrinateOther.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mUrinateOtherRbStr = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } else {
                    mUrinateOtherRbStr = "";
                }
            }
        });


        //压疮部分 面积
        //高
        SkinCondition_YCM.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mSkinCondition_YCMStr = "高:" + s.toString();
            }
        });
        //宽
        SkinCondition_YC_tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mSkinCondition_YCStr = "宽:" + s.toString();
            }
        });
        //长
        SkinCondition_Y.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mSkinCondition_YStr = "长:" + s.toString();
            }
        });

        //压疮部位选择
        rb_SkinCondition.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                SkinCondition.setOnClickListener(v -> JumpUtils.jump(mContext, ChildConditionActivity.class, null));
            }
        });
        rb_SkinCondition.setOnClickListener(v -> JumpUtils.jump(mContext, ChildConditionActivity.class, null));

        //头面部 头颅
        head_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    head_cb2.setChecked(false);
                    head_cb3.setChecked(false);
                    head_other_rb.setChecked(false);
                    mDeglutitionStr1 = buttonView.getText().toString();
                } else {
                    head_cb1.setChecked(false);
                    mDeglutitionStr1 = "";
                }
            }
        });

        head_cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    head_cb1.setChecked(false);
                    mDeglutitionStr2 = buttonView.getText().toString();
                } else {
                    mDeglutitionStr2 = "";
                }
            }
        });

        head_cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    head_cb1.setChecked(false);
                    mDeglutitionStr3 = buttonView.getText().toString();
                } else {
                    mDeglutitionStr3 = "";
                }
            }
        });

        head_other_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    head_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mDeglutitionOtherStr = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    head_cb1.setChecked(false);
                } else {
                    mDeglutitionOtherStr = "";
                }
            }
        });


        //头面部 前囟
        bregma_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBregmaStr1 = buttonView.getText().toString();
                } else {
                    mBregmaStr1 = "";
                }
            }
        });
        bregma_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBregmaStr2 = buttonView.getText().toString();
                } else {
                    mBregmaStr2 = "";
                }
            }
        });
        bregma_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBregmaStr3 = buttonView.getText().toString();
                } else {
                    mBregmaStr3 = "";
                }
            }
        });
        bregma_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBregmaStr4 = buttonView.getText().toString();
                } else {
                    mBregmaStr4 = "";
                }
            }
        });
        bregma_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bregma_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mBregmaStr5 = s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } else {
                    mBregmaStr5 = "";
                }
            }
        });

        //胸部外观
        chest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mChestStr = rb.getText().toString();
                if (checkedId == chest_rb.getId()) {
                    chest_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mChestStr = s.toString();
                        }
                    });
                }
            }
        });


        //皮肤黏膜
        skin_condition_rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb2.setChecked(false);
                    skin_condition_rb3.setChecked(false);
                    skin_condition_rb4.setChecked(false);
                    skin_condition_rb5.setChecked(false);
                    skin_condition_rb6.setChecked(false);
                    skin_condition_rb7.setChecked(false);
                    skin_condition_rb8.setChecked(false);
                    skin_condition_rb9.setChecked(false);
                    skin_condition_rb10.setChecked(false);
                    skin_condition_rb11.setChecked(false);
                    skin_condition_rb12.setChecked(false);
                    skin_condition_rb13.setChecked(false);
                    skin_condition_rb14.setChecked(false);
                    skin_condition_rb15.setChecked(false);
                    skin_condition_other.setChecked(false);
                    mSkinConditionStr1 = buttonView.getText().toString();
                } else {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr1 = "";
                }
            }
        });
        skin_condition_rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr2 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr2 = "";
                }
            }
        });
        skin_condition_rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr3 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr3 = "";
                }
            }
        });
        skin_condition_rb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr4 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr4 = "";
                }
            }
        });
        skin_condition_rb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr5 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr5 = "";
                }
            }
        });
        skin_condition_rb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr6 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr6 = "";
                }
            }
        });
        skin_condition_rb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr7 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr7 = "";
                }
            }
        });
        skin_condition_rb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr8 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr8 = "";
                }
            }
        });
        skin_condition_rb9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr9 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr9 = "";
                }
            }
        });
        skin_condition_rb10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr10 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr10 = "";
                }
            }
        });
        skin_condition_rb11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr11 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr11 = "";
                }
            }
        });
        skin_condition_rb12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr12 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr12 = "";
                }
            }
        });
        skin_condition_rb13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr13 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr13 = "";
                }
            }
        });
        skin_condition_rb14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr14 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr14 = "";
                }
            }
        });
        skin_condition_rb15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    mSkinConditionStr15 = buttonView.getText().toString();
                } else {
                    mSkinConditionStr15 = "";
                }
            }
        });
        skin_condition_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    skin_condition_rb1.setChecked(false);
                    skin_condition_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mSkinConditionStr = s.toString();
                        }
                    });
                } else {
                    mSkinConditionStr = "";
                }
            }
        });

        //生活能力
        Ability1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAbilityStr1 = buttonView.getText().toString();
                } else {
                    mAbilityStr1 = "";
                }
            }
        });
        Ability2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAbilityStr2 = buttonView.getText().toString();
                } else {
                    mAbilityStr2 = "";
                }
            }
        });
        Ability3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAbilityStr3 = buttonView.getText().toString();
                } else {
                    mAbilityStr3 = "";
                }
            }
        });
        Ability4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAbilityStr4 = buttonView.getText().toString();
                } else {
                    mAbilityStr4 = "";
                }
            }
        });
        Ability5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAbilityStr5 = buttonView.getText().toString();
                } else {
                    mAbilityStr5 = "";
                }
            }
        });


        //分期
        mSkinConditionYCType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
                mSkinConditionYCTypeStr = rb.getText().toString();
            }
        });

        //其他情况
        mOtherDict.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mOtherStr = s.toString();
            }
        });

        cb_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCheckBox1 = buttonView.getText().toString();
                } else {
                    mCheckBox1 = "";
                }
            }
        });
        cb_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCheckBox2 = buttonView.getText().toString();
                } else {
                    mCheckBox2 = "";
                }
            }
        });
        cb_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCheckBox3 = buttonView.getText().toString();
                } else {
                    mCheckBox3 = "";
                }
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb_mDefecate1.isChecked()) {
                    ed_day_str = "";
                    diarrhea_frequency_str = "";
                    diarrhea_day_str = "";
                    rb_mDefecate1_str = "";
                    ed_frequency_str = "";
                }
                if (rb_mDefecate2.isChecked()) {
                    rb_mDefecate1_str = "";
                    diarrhea_frequency_str = "";
                    diarrhea_day_str = "";
                    rb_mNormal_str = "";
                }
                if (rb_mDefecate3.isChecked()) {
                    rb_mDefecate1_str = "";
                    ed_frequency_str = "";
                    ed_day_str = "";
                    rb_mNormal_str = "";
                }
                if (rb_mDefecate4.isChecked()) {
                    ed_frequency_str = "";
                    ed_day_str = "";
                    diarrhea_frequency_str = "";
                    diarrhea_day_str = "";
                    rb_mNormal_str = "";
                }

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
                Map<String, Object> params = PatientCacheData();
                PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
                UpLoadUtils.cacheRequest(mCache, patin, Api.FirstChildNursingRecord2, params);
                T.showShort("临时保存成功");
            }
        });
    }

    /**
     * 保存儿童页面护理记录单
     */
    private void patientData() {
        Map<String, Object> params = PatientCacheData();
        NetRequest.getInstens()
                .requestDate(params, Api.FirstChildNursingRecord2, false, new NetCallBack<ArrayList<ChildFirstNurseBean>>(mContext, R.string.loading_save) {
                    @Override
                    public void onSuccess(ArrayList<ChildFirstNurseBean> bean) {
                        JumpUtils.exitActivity(mContext);
                        T.showShort("保存成功");
                    }

                    @Override
                    public void updataLayout(int flag) {
//                        if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID){
//                            PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                            UpLoadUtils.cacheRequest(mCache, patin, Api.FirstChildNursingRecord2, params);
//                        }
                    }

                    @Override
                    protected void onFlaiCacheRequest() {
                    }
                });
    }


    /**
     * 临时保存儿童页面护理记录单
     */
    private Map<String, Object> PatientCacheData() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        params.put("PA_ID", mPatID);
        params.put("OrderType", "2");
        params.put("Habitation", mLifeEnvironment_Str);//生活环境
        params.put("DataSources", DataSources);//资料来源
        params.put("RY_Type", mHospitalized_Str);//入院方式
        params.put("RY_DIS", mRyDisStr);//入院诊断
        params.put("Allergic", mAllergicStr1 + " " + mAllergicStr2 + " " + mAllergicStr3 + " " + mAllergicStr4 + " " + mAllergicStr5);//过敏史
        params.put("Favourer", mFavourerStr);//日常照顾者
        params.put("ConsciousState", mConsciousness);//意识状态 呼之
        params.put("ConsciousState_DD", consciousness_duida + mConsciousnessohter);//意识状态 对答
        params.put("Mood_Chird", EmotionStr1 + " " + EmotionStr2 + " " + EmotionStr3 + " " + EmotionStr4 + " " + EmotionStr5);//情绪_儿童
        params.put("Mood_Adult", Relation_emotionStr1 + " " + Relation_emotionStr2 + " " + Relation_emotionStr3 + " " + Relation_emotionStr4 + " " + Relation_emotionStr5);//情绪_家属
        params.put("LanguagePerformance", mLanguageotherStr);//语言表达
        params.put("Diet", DietStr);//饮食
        params.put("Nutriture", NutritionStr);//营养状况
        params.put("OralMucosa", OralMucosaRg1 + " " + OralMucosaRg2 + " " + OralMucosaRg3 + " " + OralMucosaRg4 + " " + OralMucosaRg5 + " " + OralMucosaRg6);//口腔黏膜
        params.put("Dysphagia", mSwallowStr);//吞咽困难
        params.put("Sleep", mSleepStr1 + " " + mSleepStr2 + " " + mSleepStr3 + " " + mSleepStr4 + " " + mSleepStr5 + " " + mSleepOtherStr);//睡眠情况
        params.put("DefecateType", rb_mDefecate1_str + rb_mNormal_str);//排便类型
        params.put("DefecateTime", ed_frequency_str + " " + ed_day_str + "    " + diarrhea_frequency_str + " " + diarrhea_day_str);//排便情况
        params.put("Urinate", mUrinateRbStr1 + " " + mUrinateRbStr2 + " " + mUrinateRbStr3 + " " + mUrinateRbStr4 + " " + mUrinateRbStr5
                + " " + mUrinateRbStr6 + " " + mUrinateRbStr7 + " " + mUrinateRbStr8 + " " + mUrinateRbStr9 + " " + mUrinateRbStr10 + " " + mUrinateOtherRbStr);//排尿情况
        params.put("LimbMovement", mLimbMovementRbStr1 + " " + mLimbMovementRbStr2 + " " + mLimbMovementRbStr3 + " " + mLimbMovementRbStr4 + " " + mLimbMovementRbStr5
                + " " + mLimbMovementRbStr6 + " " + mLimbMovementRbStr7 + " " + mLimbMovementRbStr8 + " " + mLimbMovementRbStr9 + " " + mLimbMovementOtherStr);//四肢活动
        params.put("LimbMovement_BW", null);//活动障碍部位
        params.put("FrontalSuture", mBregmaStr1 + " " + mBregmaStr2 + " " + mBregmaStr3 + " " + mBregmaStr4 + " " + mBregmaStr5);//前囟情况
        params.put("HeadCondition", mDeglutitionStr1 + " " + mDeglutitionStr2 + " " + mDeglutitionStr3 + " " + mDeglutitionOtherStr);//头颅情况
        params.put("SelfcareAbility", mAbilityStr1 + " " + mAbilityStr2 + " " + mAbilityStr3 + " " + mAbilityStr4 + " " + mAbilityStr5);//自理能力
        params.put("SkinCondition", mSkinConditionStr1 + " " + mSkinConditionStr2 + " " + mSkinConditionStr3 + " " + mSkinConditionStr4 + " " + mSkinConditionStr5
                + " " + mSkinConditionStr6 + " " + mSkinConditionStr7 + " " + mSkinConditionStr8 + " " + mSkinConditionStr9 + " " + mSkinConditionStr10 + " " + mSkinConditionStr11
                + " " + mSkinConditionStr12 + " " + mSkinConditionStr13 + " " + mSkinConditionStr14 + " " + mSkinConditionStr15 + " " + mSkinConditionStr);//皮肤状况
        params.put("SkinCondition_YCType", mSkinConditionYCTypeStr);//压疮分级
        params.put("SkinCondition_YC", SkinCondition.getText().toString());//压疮部位
        params.put("SkinCondition_YCMJ", mSkinCondition_YStr + " " + mSkinCondition_YCStr + " " + mSkinCondition_YCMStr);//压疮面积
        params.put("ChestAppearance", mChestStr);//胸部外观
        params.put("HabitTime", "2"); //嗜好_次数
        params.put("OtherDict", mOtherStr); //其他情况
        params.put("In_Notify", mCheckBox1 + "\n" + mCheckBox2 + "\n" + mCheckBox3); //住院告知
        params.put("CreateDate", mCreateDate);//记录时间
        params.put("FZ_Nurse", mUserName);//责任护士
        params.put("DateKey", mDateKey); //时间戳
        return params;
    }
}
