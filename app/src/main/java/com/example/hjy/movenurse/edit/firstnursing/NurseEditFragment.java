package com.example.hjy.movenurse.edit.firstnursing;

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
import com.example.hjy.movenurse.activity.SkinConditionActivity;
import com.example.hjy.movenurse.upload.UpLoadUtils;
import com.fy.base.BaseFragment;
import com.fy.entity.FirstNurseBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.UploadBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.JumpUtils;
import com.fy.utils.ResourceUtils;
import com.fy.utils.T;
import com.fy.utils.TimeUtils;
import com.fy.widget.MyRadioGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首次护理记录  编辑 fragment
 * Created by fangs on 2017/9/12.
 */
public class NurseEditFragment extends BaseFragment {

    private String CacheKey;

    @Override
    protected int getContentLayout() {
        return R.layout.first_nurse_order_fragment;
    }

    @Override
    protected void baseInit() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        mCreateDate = formatter.format(curDate);
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        mCache.remove("Tag");
        initCacheData(loginBean, patin);
        mUserName = loginBean.getUserName();
        mFZ_NurseID = loginBean.getUserID();

        if (null != patin) mPatID = patin.getPatID();
        initListener();
        tv_CreateDate.setText("记录时间:" + mCreateDate);
        ResourceUtils.setText(mContext, tv_FZ_Nurse, R.string.ExecutiveNurse, loginBean.getUserName());
        //入院诊断
        RY_DISStr = patin.getZyDetail().getICD_Name();
        mRyDis.setText(RY_DISStr);
        limb_movement_other_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mLimbMovementOther.setFocusable(true);
                mLimbMovementOther.setFocusableInTouchMode(true);
                mLimbMovementOther.requestFocus();
                mLimbMovementOther.findFocus();
                mLimbMovementOther.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        mLimbMovementOtherStr = s.toString();
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Skin.setText(mCache.getAsString("Tag"));
    }

    private void initListener() {
        //教育程度
        mRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                mEducationLevel = rb.getText().toString();
            }
        });
        //资料来源
        mDataSources.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                DataSources = rb.getText().toString();
                if (checkedId == sources_rg_other.getId()) {
                    mDataSourcesOther.setFocusable(true);
                    mDataSourcesOther.setFocusableInTouchMode(true);
                    mDataSourcesOther.requestFocus();
                    mDataSourcesOther.findFocus();
                    //资料来源其他a
                    mDataSourcesOther.addTextChangedListener(new TextWatcher() {
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
        mRyType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                mRyTypeStr = rb.getText().toString();
                if (checkedId == rb_type_other.getId()) {
                    mRyTypeOther.setFocusable(true);
                    mRyTypeOther.setFocusableInTouchMode(true);
                    mRyTypeOther.requestFocus();
                    mRyTypeOther.findFocus();
                    //入院方式 其他
                    mRyTypeOther.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mRyTypeStr = s.toString();
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
                    mAllergic_other.setChecked(false);
                    mAllergicStr = buttonView.getText().toString();
                } else {
                    mAllergic1.setChecked(false);
                    mAllergicStr = "";
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
        mAllergic_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAllergicOther.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mAllergicOtherStr = s.toString();
                        }
                    });
                    mAllergic1.setChecked(false);
                } else {
                    mAllergicOtherStr = "";
                }
            }
        });
        //日常照顾者
        mFavourerRg.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {

                if (checkedId == rb1.getId()) {
                    rb_favourer = rb1.getText().toString();
                }
                if (checkedId == rb2.getId()) {
                    rb_favourer = rb2.getText().toString();
                }
                if (checkedId == rb3.getId()) {
                    rb_favourer = rb3.getText().toString();
                }
                if (checkedId == rb4.getId()) {
                    rb_favourer = rb4.getText().toString();
                }
                if (checkedId == rb5.getId()) {
                    rb_favourer = rb5.getText().toString();
                }
                if (checkedId == rb6.getId()) {
                    rb_favourer = rb6.getText().toString();
                }
                if (checkedId == rb7.getId()) {
                    rb_favourer = rb7.getText().toString();
                }
                if (checkedId == rb8.getId()) {
                    mFavourerOther.setFocusable(true);
                    mFavourerOther.setFocusableInTouchMode(true);
                    mFavourerOther.requestFocus();
                    mFavourerOther.findFocus();

                    //日常照顾者 其他
                    mFavourerOther.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            rb_favourer = s.toString();
                        }
                    });
                }
            }
        });

        //意识状态 呼之
        rg_mConsciousness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
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
        // 对答
        consciousness_dui.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                consciousness_duida = "对答:" + rb.getText().toString();
            }
        });
        //情绪
        emotion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    qingxu = buttonView.getText().toString();
                } else {
                    qingxu = "";
                }
            }
        });
        emotion1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    qingxu1 = buttonView.getText().toString();
                } else {
                    qingxu1 = "";
                }
            }
        });
        emotion2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    qingxu2 = buttonView.getText().toString();
                } else {
                    qingxu2 = "";
                }
            }
        });
        emotion3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    qingxu3 = buttonView.getText().toString();
                } else {
                    qingxu3 = "";
                }
            }
        });
        emotion4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    qingxu4 = buttonView.getText().toString();
                } else {
                    qingxu4 = "";
                }
            }
        });
        emotion5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    qingxu5 = buttonView.getText().toString();
                } else {
                    qingxu5 = "";
                }
            }
        });
        emotion6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    qingxu6 = buttonView.getText().toString();
                } else {
                    qingxu6 = "";
                }
            }
        });

        //语言
        language.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                mLanguageotherStr = rb.getText().toString();
                if (checkedId == radioButton.getId()) {
                    mLanguageother.setFocusable(true);
                    mLanguageother.setFocusableInTouchMode(true);
                    mLanguageother.requestFocus();
                    mLanguageother.findFocus();
                    mLanguageother.addTextChangedListener(new TextWatcher() {
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
        diet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                diets = rb.getText().toString();

            }
        });
        //营养状况
        nutrition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                nutritions = rb.getText().toString();
            }
        });
        //口腔黏膜

        mMucous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OralMucosaStr = buttonView.getText().toString();
                } else {
                    OralMucosaStr = "";
                }
            }
        });
        mMucous1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OralMucosaStr1 = buttonView.getText().toString();
                } else {
                    OralMucosaStr1 = "";
                }
            }
        });
        mMucous2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OralMucosaStr2 = buttonView.getText().toString();
                } else {
                    OralMucosaStr2 = "";
                }
            }
        });
        mMucous3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OralMucosaStr3 = buttonView.getText().toString();
                } else {
                    OralMucosaStr3 = "";
                }
            }
        });
        mMucous4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMucous_Et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            OralMucosaStr4 = s.toString();
                        }
                    });
                } else {
                    OralMucosaStr4 = "";
                }
            }
        });

        //吞咽情况
        deglutition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                mEdDeglutitionStr = rb.getText().toString();
                if (checkedId == deglutition_other.getId()) {
                    mEdDeglutition.setFocusable(true);
                    mEdDeglutition.setFocusableInTouchMode(true);
                    mEdDeglutition.requestFocus();
                    mEdDeglutition.findFocus();
                    mEdDeglutition.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mEdDeglutitionStr = s.toString();
                        }
                    });
                }

            }
        });
        //睡眠情况
        sleep.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                sleep_str = rb.getText().toString();
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

        //排尿情况
        mUrinateMrg.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                if (checkedId == mUrinateRb1.getId()) {
                    mUrinateRbStr = mUrinateRb1.getText().toString();
                }
                if (checkedId == mUrinateRb2.getId()) {
                    mUrinateRbStr = mUrinateRb2.getText().toString();
                }
                if (checkedId == mUrinateRb3.getId()) {
                    mUrinateRbStr = mUrinateRb3.getText().toString();
                }
                if (checkedId == mUrinateRb4.getId()) {
                    mUrinateRbStr = mUrinateRb4.getText().toString();
                }
                if (checkedId == mUrinateRb5.getId()) {
                    mUrinateRbStr = mUrinateRb5.getText().toString();
                }
                if (checkedId == mUrinateRb6.getId()) {
                    mUrinateRbStr = mUrinateRb6.getText().toString();
                }
                if (checkedId == mUrinateRb7.getId()) {
                    mUrinateRbStr = mUrinateRb7.getText().toString();
                }
                if (checkedId == mUrinateRb8.getId()) {
                    mUrinateRbStr = mUrinateRb8.getText().toString();
                }
                if (checkedId == mUrinateRb9.getId()) {
                    mUrinateRbStr = mUrinateRb9.getText().toString();
                }
                if (checkedId == mUrinateRb10.getId()) {
                    mUrinateRbStr = mUrinateRb10.getText().toString();
                }
                if (checkedId == urinate_rb_other.getId()) {
                    mUrinateOther.setFocusable(true);
                    mUrinateOther.setFocusableInTouchMode(true);
                    mUrinateOther.requestFocus();
                    mUrinateOther.findFocus();
                    mUrinateOther.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mUrinateRbStr = s.toString();
                        }
                    });
                }

            }
        });

        mLimbMovementRb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLimbMovementRbStr1 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr1 = "";
                }
            }
        });
        mLimbMovementRb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLimbMovementRbStr2 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr2 = "";
                }
            }
        });
        mLimbMovementRb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLimbMovementRbStr3 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr3 = "";
                }
            }
        });
        mLimbMovementRb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLimbMovementRbStr4 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr4 = "";
                }
            }
        });
        mLimbMovementRb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLimbMovementRbStr5 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr5 = "";
                }
            }
        });
        mLimbMovementRb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLimbMovementRbStr6 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr6 = "";
                }
            }
        });
        mLimbMovementRb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLimbMovementRbStr7 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr7 = "";
                }
            }
        });
        mLimbMovementRb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLimbMovementRbStr8 = buttonView.getText().toString();
                } else {
                    mLimbMovementRbStr8 = "";
                }
            }
        });
        limb_movement_other_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLimbMovementOther.setFocusable(true);
                    mLimbMovementOther.setFocusableInTouchMode(true);
                    mLimbMovementOther.requestFocus();
                    mLimbMovementOther.findFocus();
                    mLimbMovementOther.addTextChangedListener(new TextWatcher() {
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

        //自理能力
        mSelfCareAbilityRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                mSelfCareAbilityStr = rb.getText().toString();
            }
        });

        //皮肤状况
        SkinCondition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr = "";
                }
            }
        });
        //皮肤状况
        SkinCondition1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr1 = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr1 = "";
                }
            }
        });
        //皮肤状况
        SkinCondition2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr2 = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr2 = "";
                }
            }
        });
        //皮肤状况
        SkinCondition3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr3 = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr3 = "";
                }
            }
        });
        //皮肤状况
        SkinCondition4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr4 = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr4 = "";
                }
            }
        });
        //皮肤状况
        SkinCondition5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr5 = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr5 = "";
                }
            }
        });
        //皮肤状况
        SkinCondition6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr6 = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr6 = "";
                }
            }
        });
        //皮肤状况
        SkinCondition7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr7 = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr7 = "";
                }
            }
        });
        //皮肤状况
        SkinCondition8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr8 = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr8 = "";
                }
            }
        });
        //皮肤状况
        SkinCondition9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr9 = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr9 = "";
                }
            }
        });
        //皮肤状况
        SkinCondition10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSkinConditionRbStr10 = buttonView.getText().toString();
                } else {
                    mSkinConditionRbStr10 = "";
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
        SkinCondition_YC.addTextChangedListener(new TextWatcher() {
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
        SkinCondition_YC_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Skin.setOnClickListener(v -> JumpUtils.jump(mContext, SkinConditionActivity.class, null));
                }
            }
        });
        SkinCondition_YC_rb.setOnClickListener(v -> JumpUtils.jump(mContext, SkinConditionActivity.class, null));
        //分期
        mSkinConditionYCType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) mRootView.findViewById(checkedRadioButtonId);
                mSkinConditionYCTypeStr = rb.getText().toString();
            }
        });

        //嗜好
        mNothingCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLiquorCbStr = buttonView.getText().toString();
                    mCigaretteCb.setChecked(false);
                    mLiquorCb.setChecked(false);
                    liquor_other_cb.setChecked(false);
                } else {
                    mNothingCb.setChecked(false);
                }
                mCigaretteCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            mNothingCb.setChecked(false);
                        }
                    }
                });
                mLiquorCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            mNothingCb.setChecked(false);
                        }
                    }
                });
                liquor_other_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            mNothingCb.setChecked(false);
                        }
                    }
                });
            }
        });

        //烟
        mCigaretteCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cigarette_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mHabitTimeStr = "烟:" + s.toString() + "支/天";
                        }
                    });
                }

            }
        });
        //酒
        mLiquorCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    liquor_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mHabitTime_WineStr = "酒:" + s.toString() + "两/天";
                        }
                    });
                }
            }
        });
        //其他
        liquor_other_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mHabitOther.setFocusable(true);
                    mHabitOther.setFocusableInTouchMode(true);
                    mHabitOther.requestFocus();
                    mHabitOther.findFocus();
                    mHabitOther.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            mHabitOtherStr = "其他：" + s.toString();
                        }
                    });
                }
            }
        });

        //住院告知
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

        //其他情况
//        mOtherStr = mOtherDict.getText().toString();
        mOtherDict.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mOtherStr = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.fab, R.id.save_btn})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.fab:
                Map<String, Object> params = PatientCacheData();
                PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
                UpLoadUtils.cacheRequest(mCache, patin, Api.FirstNursingRecord, params);
                T.showShort("临时保存成功");
                break;
            case R.id.save_btn:
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
                if (mNothingCb.isChecked()) {
                    mHabitTimeStr = "";//烟
                    mHabitTime_WineStr = "";//酒
                    mHabitOtherStr = ""; //其他
                }
                if (!mCigaretteCb.isChecked()) {
                    mHabitTimeStr = "";//烟
                } else if (!mLiquorCb.isChecked()) {
                    mHabitTime_WineStr = "";//酒
                } else if (!liquor_other_cb.isChecked()) {
                    mHabitOtherStr = ""; //其他
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(System.currentTimeMillis());//获取当前时间
                String mDateKeys = format.format(date);
                long l = TimeUtils.timeString2long(mDateKeys, "yyyy-MM-dd HH:mm");
                mDateKey = l + "";
                patientData();
                break;
        }
    }


    /**
     * 保存患者首次护理记录单
     */
    private void patientData() {
        Map<String, Object> params = PatientCacheData();
        NetRequest.getInstens().requestDate(params, Api.FirstNursingRecord, false, new NetCallBack<ArrayList<FirstNurseBean>>(mContext, R.string.loading_save) {
            @Override
            public void onSuccess(ArrayList<FirstNurseBean> bean) {
                if (null != bean) {
                    FirstNurseBean firstNurseBean = bean.get(0);
                    mCache.put("Allergic", firstNurseBean);
                    JumpUtils.exitActivity(mContext);
                    T.showShort("保存成功");
                    mCache.remove(CacheKey);
                }
            }

            @Override
            public void updataLayout(int flag) {
                if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID) {
                    PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
                    UpLoadUtils.cacheRequest(mCache, patin, Api.FirstNursingRecord, params);
                }
            }

            @Override
            protected void onFlaiCacheRequest() {
            }
        });
    }

    /**
     * 临时保存患者首次护理记录单
     */
    private Map<String, Object> PatientCacheData() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("Token", loginBean.getToken());
        params.put("PA_ID", mPatID);
        params.put("UserID", loginBean.getUserID());
        params.put("OrderType", "1");
        params.put("DateKey", mDateKey); //时间戳
        params.put("EducationLevel", mEducationLevel);
        params.put("DataSources", DataSources);//资料来源
        params.put("RY_DIS", RY_DISStr);//入院诊断
        params.put("RY_Type", mRyTypeStr);//入院方式
        params.put("Allergic", mAllergicStr + " " + mAllergicStr2 + " " + mAllergicStr3 + " " + mAllergicStr4 + " " + mAllergicOtherStr);//过敏史
        params.put("Favourer", rb_favourer);//日常照顾者
        params.put("ConsciousState", mConsciousness);//意识状态 呼之
        params.put("ConsciousState_DD", consciousness_duida + mConsciousnessohter);//意识状态 对答
        params.put("Mood_Adult", qingxu + " " + qingxu1 + " " + qingxu2 + " " + qingxu3 + " " + qingxu4 + " " + qingxu5 + " " + qingxu6);//情绪_成人
        params.put("LanguagePerformance", mLanguageotherStr);//语言表达
        params.put("Diet", diets);//饮食
        params.put("Nutriture", nutritions);//营养状况
        params.put("OralMucosa", OralMucosaStr + " " + OralMucosaStr1 + " " + OralMucosaStr2 + " " + OralMucosaStr3 + " " + OralMucosaStr4 + " " + OralMucosaStr5);//口腔黏膜
        params.put("Dysphagia", mEdDeglutitionStr);//吞咽困难
        params.put("Sleep", sleep_str);//睡眠情况
        params.put("DefecateType", rb_mDefecate1_str + rb_mNormal_str);//排便类型
        params.put("DefecateTime", ed_frequency_str + ed_day_str + diarrhea_frequency_str + diarrhea_day_str);//排便情况
        params.put("Urinate", mUrinateRbStr);//排尿情况
        params.put("LimbMovement", mLimbMovementRbStr1 + " " + mLimbMovementRbStr2 + " " + mLimbMovementRbStr3 + " " + mLimbMovementRbStr4 + " " + mLimbMovementRbStr5
                + " " + mLimbMovementRbStr6 + " " + mLimbMovementRbStr7 + " " + mLimbMovementRbStr8 + " " + mLimbMovementOtherStr);//四肢活动
        params.put("SelfcareAbility", mSelfCareAbilityStr);//自理能力
        params.put("SkinCondition", mSkinConditionRbStr + " " + mSkinConditionRbStr1 + " " + mSkinConditionRbStr2 + " " + mSkinConditionRbStr3 + " " + mSkinConditionRbStr4 + " " + mSkinConditionRbStr5
                + " " + mSkinConditionRbStr6 + " " + mSkinConditionRbStr7 + " " + mSkinConditionRbStr8 + " " + mSkinConditionRbStr9 + " " + mSkinConditionRbStr10); //皮肤状况
        params.put("SkinCondition_YCType", mSkinConditionYCTypeStr);//压疮分级
        params.put("SkinCondition_YC", Skin.getText().toString());//压疮部位
        params.put("SkinCondition_YCMJ", mSkinCondition_YStr + " " + mSkinCondition_YCStr + " " + mSkinCondition_YCMStr);//压疮面积
        params.put("Habit", mLiquorCbStr + mHabitOtherStr); //嗜好
        params.put("HabitTime", mHabitTimeStr); //嗜好_次数 烟
        params.put("HabitTime_Wine", mHabitTime_WineStr); //嗜好_次数 酒
        params.put("OtherDict", mOtherStr); //其他情况
        params.put("In_Notify", mCheckBox1 + "\n" + mCheckBox2 + "\n" + mCheckBox3); //住院告知
        params.put("CreateDate", mCreateDate);//记录时间
        params.put("FZ_Nurse", mUserName);//责任护士
        params.put("FZ_NurseID", mFZ_NurseID);//责任护士
//        params.put("ApplyDate", mCreateDate); //审核时间
//        params.put("ApplyNurse", "419"); //审核护士
        return params;
    }

    //初始化 缓存的数据
    private void initCacheData(LoginBean loginBean, PatientsBean patin) {
        if (null == loginBean || null == patin) return;
        String userID = loginBean.getUserID();
        String patID = patin.getPatID();
        CacheKey = userID + patID + "&" + Api.FirstNursingRecord;
        UploadBean uploadBean = (UploadBean) mCache.getAsObject(CacheKey);

        if (null != uploadBean) {
            Map<String, Object> request = uploadBean.getRequest();
            //教育程度
            mEducationLevel = (String) request.get("EducationLevel");
            setRadioGCheck(mRadioGroup1, mEducationLevel);
            //资料来源
            DataSources = (String) request.get("DataSources");
            setRadioGCheck(mDataSources, DataSources);
            //入院方式
            mRyTypeStr = (String) request.get("RY_Type");
            setRadioGCheck(mRyType, mRyTypeStr);
            //日常照顾者
            rb_favourer = (String) request.get("Favourer");
            setRadioGChecks(mFavourerRg, rb_favourer);
            //意识
            mConsciousness = (String) request.get("ConsciousState");
            setRadioGCheck(rg_mConsciousness, mConsciousness);
            //意识对答
            consciousness_duida = (String) request.get("ConsciousState_DD");
            setRadioGCheck(consciousness_dui, consciousness_duida);
            //語言表達
            mLanguageotherStr = (String) request.get("LanguagePerformance");
            setRadioGCheck(language, mLanguageotherStr);
            //飲食
            diets = (String) request.get("Diet");
            setRadioGCheck(diet, diets);
            //营养状况
            nutritions = (String) request.get("Nutriture");
            setRadioGCheck(nutrition, nutritions);
            //吞咽困难
            mEdDeglutitionStr = (String) request.get("Dysphagia");
            setRadioGCheck(deglutition, mEdDeglutitionStr);
            //睡眠情况
            sleep_str = (String) request.get("Sleep");
            setRadioGCheck(sleep, sleep_str);
            //排便情况
            rb_mDefecate1_str = (String) request.get("DefecateType");
            setRadioGChecks(mDefecate, rb_mDefecate1_str);
            //排尿情况
            mUrinateRbStr = (String) request.get("Urinate");
            setRadioGChecks(mUrinateMrg, mUrinateRbStr);
            //自理能力
            mSelfCareAbilityStr = (String) request.get("SelfcareAbility");
            setRadioGCheck(mSelfCareAbilityRg, mSelfCareAbilityStr);
            //压疮分期
            mSkinConditionYCTypeStr = (String) request.get("SkinCondition_YCType");
            setRadioGCheck(mSkinConditionYCType, mSkinConditionYCTypeStr);
        }
    }

    @BindView(R.id.tv_nationality)
    TextView mTvNationality;
    @BindView(R.id.radioGroup1)
    RadioGroup mRadioGroup1;
    @BindView(R.id.data_sources_rg)
    RadioGroup mDataSources;
    @BindView(R.id.ry_dis)
    TextView mRyDis;
    @BindView(R.id.data_sources_other)
    EditText mDataSourcesOther;
    @BindView(R.id.ry_type_other)
    EditText mRyTypeOther;
    @BindView(R.id.ry_type)
    RadioGroup mRyType;
    @BindView(R.id.mConsciousness)
    RadioGroup rg_mConsciousness;
    @BindView(R.id.mConsciousness_other)
    RadioGroup mConsciousness_other;
    @BindView(R.id.consciousness_duida)
    RadioGroup consciousness_dui;
    @BindView(R.id.language)
    RadioGroup language;
    @BindView(R.id.diet)
    RadioGroup diet;
    @BindView(R.id.nutrition)
    RadioGroup nutrition;
    @BindView(R.id.deglutition)
    RadioGroup deglutition;
    @BindView(R.id.sleep)
    RadioGroup sleep;
    @BindView(R.id.allergic_other)
    EditText mAllergicOther;
    @BindView(R.id.favourer_rg)
    MyRadioGroup mFavourerRg;
    @BindView(R.id.defecate)
    MyRadioGroup mDefecate;
    @BindView(R.id.favourer_other)
    EditText mFavourerOther;
    @BindView(R.id.consciousness_duida_ed)
    EditText mConsciousness_duida_ed;
    @BindView(R.id.language_other)
    EditText mLanguageother;
    @BindView(R.id.mucous_et)
    EditText mMucous_Et;
    @BindView(R.id.Defecate_other)
    EditText mDefecateother;
    @BindView(R.id.diarrhea_day)
    EditText diarrhea_day;
    @BindView(R.id.diarrhea_frequency)
    EditText diarrhea_frequency;
    @BindView(R.id.ed_frequency)
    EditText ed_frequency;
    @BindView(R.id.ed_day)
    EditText ed_day;
    @BindView(R.id.SkinCondition_Y)
    EditText SkinCondition_Y;
    @BindView(R.id.Skin)
    TextView Skin;
    @BindView(R.id.SkinCondition_YCM)
    EditText SkinCondition_YCM;
    @BindView(R.id.SkinCondition_YC)
    EditText SkinCondition_YC;
    @BindView(R.id.cigarette_et)
    EditText cigarette_et;
    @BindView(R.id.liquor_et)
    EditText liquor_et;
    @BindView(R.id.urinate_other)
    EditText mUrinateOther;
    @BindView(R.id.urinate_mrg)
    MyRadioGroup mUrinateMrg;
    @BindView(R.id.ed_deglutition)
    EditText mEdDeglutition;
    @BindView(R.id.urinate_rb1)
    RadioButton mUrinateRb1;
    @BindView(R.id.urinate_rb2)
    RadioButton mUrinateRb2;
    @BindView(R.id.urinate_rb3)
    RadioButton mUrinateRb3;
    @BindView(R.id.urinate_rb4)
    RadioButton mUrinateRb4;
    @BindView(R.id.urinate_rb5)
    RadioButton mUrinateRb5;
    @BindView(R.id.urinate_rb6)
    RadioButton mUrinateRb6;
    @BindView(R.id.urinate_rb7)
    RadioButton mUrinateRb7;
    @BindView(R.id.urinate_rb8)
    RadioButton mUrinateRb8;
    @BindView(R.id.urinate_rb9)
    RadioButton mUrinateRb9;
    @BindView(R.id.urinate_rb10)
    RadioButton mUrinateRb10;
    @BindView(R.id.limb_movement_rb1)
    CheckBox mLimbMovementRb1;
    @BindView(R.id.limb_movement_rb2)
    CheckBox mLimbMovementRb2;
    @BindView(R.id.limb_movement_rb3)
    CheckBox mLimbMovementRb3;
    @BindView(R.id.limb_movement_rb4)
    CheckBox mLimbMovementRb4;
    @BindView(R.id.limb_movement_rb5)
    CheckBox mLimbMovementRb5;
    @BindView(R.id.limb_movement_rb6)
    CheckBox mLimbMovementRb6;
    @BindView(R.id.limb_movement_rb7)
    CheckBox mLimbMovementRb7;
    @BindView(R.id.limb_movement_rb8)
    CheckBox mLimbMovementRb8;
    @BindView(R.id.limb_movement_other_rb)
    CheckBox limb_movement_other_rb;
    @BindView(R.id.limb_movement_other)
    EditText mLimbMovementOther;
    @BindView(R.id.self_care_ability_rg)
    RadioGroup mSelfCareAbilityRg;
    @BindView(R.id.skin_condition_YCType)
    RadioGroup mSkinConditionYCType;
    @BindView(R.id.nothing_cb)
    CheckBox mNothingCb;
    @BindView(R.id.cigarette_cb)
    CheckBox mCigaretteCb;
    @BindView(R.id.liquor_cb)
    CheckBox mLiquorCb;
    @BindView(R.id.liquor_other_cb)
    CheckBox liquor_other_cb;
    @BindView(R.id.habit_other)
    EditText mHabitOther;
    @BindView(R.id.other_dict)
    EditText mOtherDict;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rb5)
    RadioButton rb5;
    @BindView(R.id.rb6)
    RadioButton rb6;
    @BindView(R.id.rb7)
    RadioButton rb7;
    @BindView(R.id.rb8)
    RadioButton rb8;
    @BindView(R.id.radioButton)
    RadioButton radioButton;
    @BindView(R.id.urinate_rb_other)
    RadioButton urinate_rb_other;
    @BindView(R.id.deglutition_other)
    RadioButton deglutition_other;
    @BindView(R.id.consciousness_duida_rb)
    RadioButton consciousness_duida_rb;
    @BindView(R.id.sources_rg_other)
    RadioButton sources_rg_other;
    @BindView(R.id.rb_type_other)
    RadioButton rb_type_other;
    @BindView(R.id.rb_mDefecate1)
    RadioButton rb_mDefecate1;
    @BindView(R.id.rb_mDefecate2)
    RadioButton rb_mDefecate2;
    @BindView(R.id.rb_mDefecate3)
    RadioButton rb_mDefecate3;
    @BindView(R.id.rb_mDefecate4)
    RadioButton rb_mDefecate4;
    @BindView(R.id.SkinCondition_YC_rb)
    RadioButton SkinCondition_YC_rb;
    @BindView(R.id.tv_CreateDate)
    TextView tv_CreateDate;
    @BindView(R.id.tv_FZ_Nurse)
    TextView tv_FZ_Nurse;
    @BindView(R.id.cb_1)
    CheckBox cb_1;
    @BindView(R.id.cb_2)
    CheckBox cb_2;
    @BindView(R.id.cb_3)
    CheckBox cb_3;
    @BindView(R.id.emotion)
    CheckBox emotion;
    @BindView(R.id.emotion1)
    CheckBox emotion1;
    @BindView(R.id.emotion2)
    CheckBox emotion2;
    @BindView(R.id.emotion3)
    CheckBox emotion3;
    @BindView(R.id.emotion4)
    CheckBox emotion4;
    @BindView(R.id.emotion5)
    CheckBox emotion5;
    @BindView(R.id.emotion6)
    CheckBox emotion6;
    @BindView(R.id.SkinCondition)
    CheckBox SkinCondition;
    @BindView(R.id.SkinCondition1)
    CheckBox SkinCondition1;
    @BindView(R.id.SkinCondition2)
    CheckBox SkinCondition2;
    @BindView(R.id.SkinCondition3)
    CheckBox SkinCondition3;
    @BindView(R.id.SkinCondition4)
    CheckBox SkinCondition4;
    @BindView(R.id.SkinCondition5)
    CheckBox SkinCondition5;
    @BindView(R.id.SkinCondition6)
    CheckBox SkinCondition6;
    @BindView(R.id.SkinCondition7)
    CheckBox SkinCondition7;
    @BindView(R.id.SkinCondition8)
    CheckBox SkinCondition8;
    @BindView(R.id.SkinCondition9)
    CheckBox SkinCondition9;
    @BindView(R.id.SkinCondition10)
    CheckBox SkinCondition10;
    @BindView(R.id.mMucous)
    CheckBox mMucous;
    @BindView(R.id.mMucous1)
    CheckBox mMucous1;
    @BindView(R.id.mMucous2)
    CheckBox mMucous2;
    @BindView(R.id.mMucous3)
    CheckBox mMucous3;
    @BindView(R.id.mMucous4)
    CheckBox mMucous4;
    @BindView(R.id.mAllergic1)
    CheckBox mAllergic1;
    @BindView(R.id.mAllergic2)
    CheckBox mAllergic2;
    @BindView(R.id.mAllergic3)
    CheckBox mAllergic3;
    @BindView(R.id.mAllergic4)
    CheckBox mAllergic4;
    @BindView(R.id.mAllergic_other)
    CheckBox mAllergic_other;

    private String RY_DISStr = "";
    private String mEducationLevel = "";
    private String DataSources;
    private String mRyTypeStr = "";
    private String mAllergicStr = "";
    private String mAllergicStr2 = "";
    private String mAllergicStr3 = "";
    private String mAllergicStr4 = "";
    private String mAllergicOtherStr = "";
    private String mLanguageotherStr = "";
    private String rb_favourer = "";
    private String mConsciousness = "";
    private String mConsciousnessohter = "";
    private String consciousness_duida = "";
    private String qingxu = "";//情绪
    private String qingxu1 = "";//情绪
    private String qingxu2 = "";//情绪
    private String qingxu3 = "";//情绪
    private String qingxu4 = "";//情绪
    private String qingxu5 = "";//情绪
    private String qingxu6 = "";//情绪
    private String diets = "";
    private String nutritions = "";
    private String sleep_str = "";
    private String rb_mNormal_str = "";
    private String rb_mDefecate1_str = "";
    private String ed_frequency_str = "";
    private String ed_day_str = "";
    private String diarrhea_frequency_str = "";
    private String diarrhea_day_str = "";
    private String mEdDeglutitionStr = "";
    private String mLimbMovementRbStr1 = "";//四肢活动
    private String mLimbMovementRbStr2 = "";
    private String mLimbMovementRbStr3 = "";
    private String mLimbMovementRbStr4 = "";
    private String mLimbMovementRbStr5 = "";
    private String mLimbMovementRbStr6 = "";
    private String mLimbMovementRbStr7 = "";
    private String mLimbMovementRbStr8 = "";
    private String mLimbMovementOtherStr = "";
    private String mSelfCareAbilityStr = "";
    private String mSkinConditionRbStr = "";//皮肤情况
    private String mSkinConditionRbStr1 = "";//皮肤情况
    private String mSkinConditionRbStr2 = "";//皮肤情况
    private String mSkinConditionRbStr3 = "";//皮肤情况
    private String mSkinConditionRbStr4 = "";//皮肤情况
    private String mSkinConditionRbStr5 = "";//皮肤情况
    private String mSkinConditionRbStr6 = "";//皮肤情况
    private String mSkinConditionRbStr7 = "";//皮肤情况
    private String mSkinConditionRbStr8 = "";//皮肤情况
    private String mSkinConditionRbStr9 = "";//皮肤情况
    private String mSkinConditionRbStr10 = "";//皮肤情况
    private String mSkinConditionYCTypeStr = "";
    private String mLiquorCbStr = "";//无
    private String mHabitTime_WineStr = "";//酒
    private String mHabitTimeStr = "";//烟
    private String mHabitOtherStr = "";//嗜好 其他
    private String mOtherStr = "";
    private String mPatID = "";
    private String mUserName = "";
    private String mFZ_NurseID = "";
    private String OralMucosaStr = ""; //口腔黏膜
    private String OralMucosaStr1 = ""; //口腔黏膜
    private String OralMucosaStr2 = ""; //口腔黏膜
    private String OralMucosaStr3 = ""; //口腔黏膜
    private String OralMucosaStr4 = ""; //口腔黏膜
    private String OralMucosaStr5 = ""; //口腔黏膜
    private String mUrinateRbStr = "";
    private String mCreateDate = "";
    private String mDateKey = "";
    private String mCheckBox1 = "";
    private String mCheckBox2 = "";
    private String mCheckBox3 = "";
    private String mSkinCondition_YCMStr = "";
    private String mSkinCondition_YCStr = "";
    private String mSkinCondition_YStr = "";


    /**
     * 遍历RadioGroup 设置默认选中(单选)
     *
     * @param rg
     * @param consciousState
     */
    public static void setRadioGCheck(RadioGroup rg, String consciousState) {
        int count = rg.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = rg.getChildAt(i);
            if (childView instanceof RadioButton) {
                RadioButton rb = (RadioButton) childView;
                if (rb.getText().toString().trim().equals(consciousState)) {
                    rb.setChecked(true);
                }
            }
        }
    }

    public static void setRadioGChecks(MyRadioGroup rg, String consciousState) {
        int count = rg.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = rg.getChildAt(i);
            if (childView instanceof RadioButton) {
                RadioButton rb = (RadioButton) childView;
                if (rb.getText().toString().trim().equals(consciousState)) {
                    rb.setChecked(true);
                }
            }
        }
    }

    /**
     * 遍历RadioGroup 设置默认选中(多选)
     *
     * @param rg
     * @param consciousState
     */
    public static void setCheckBoxCheck(RadioGroup rg, String consciousState) {
        int count = rg.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = rg.getChildAt(i);
            if (childView instanceof CheckBox) {
                CheckBox rb = (CheckBox) childView;
                if (rb.getText().toString().trim().equals(consciousState)) {
                    rb.setChecked(true);
                }
            }
        }
    }
}
