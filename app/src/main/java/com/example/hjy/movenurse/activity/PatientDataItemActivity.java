package com.example.hjy.movenurse.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.utils.MyUtils;
import com.fy.base.BaseActivity;
import com.fy.entity.PatientsBean;
import com.fy.entity.RxBean;
import com.fy.eventbus.RxBus;
import com.fy.utils.T;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 患者资料详情
 * Created by Gab on 2017/9/1 0001.
 */
public class PatientDataItemActivity extends BaseActivity {

    //住院号
    @BindView(R.id.tv_hospital_id)
    TextView tv_hospital_id;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_age)
    TextView tv_age;
    //证件类别
    @BindView(R.id.tv_num_genre)
    TextView tv_num_genre;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_nationality)
    TextView tv_nationality;
    @BindView(R.id.tv_Blood)
    TextView tv_Blood;
    @BindView(R.id.tv_isMarried)
    TextView tv_isMarried;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_Address)
    TextView tv_Address;
    @BindView(R.id.tv_ContName)
    TextView tv_ContName;
    @BindView(R.id.tv_ContRelation)
    TextView tv_ContRelation;
    @BindView(R.id.tv_ContTel)
    TextView tv_ContTel;
    @BindView(R.id.tv_ContAddr)
    TextView tv_ContAddr;
    @BindView(R.id.tv_Fee_Sour_Type)
    TextView tv_Fee_Sour_Type;
    @BindView(R.id.tv_Fee_Sour_Name)
    TextView tv_Fee_Sour_Name;
    @BindView(R.id.tv_MedicareType)
    TextView tv_MedicareType;
    @BindView(R.id.tv_MedicareNo)
    TextView tv_MedicareNo;
    @BindView(R.id.tv_In_Dept_Name)
    TextView tv_In_Dept_Name;
    @BindView(R.id.tv_In_Dept_ID)
    TextView tv_In_Dept_ID;
    @BindView(R.id.tv_In_Type)
    TextView tv_In_Type;
    @BindView(R.id.tv_In_Date)
    TextView tv_In_Date;
    @BindView(R.id.tv_In_Room)
    TextView tv_In_Room;
    @BindView(R.id.tv_In_Bed)
    TextView tv_In_Bed;
    @BindView(R.id.tv_ICD_Name)
    TextView tv_ICD_Name;
    @BindView(R.id.tv_ICD)
    TextView tv_ICD;
    @BindView(R.id.tv_Attend_PSID)
    TextView tv_Attend_PSID;
    @BindView(R.id.tv_Attend_PSName)
    TextView tv_Attend_PSName;
    @BindView(R.id.tv_DoctorIn_PSID)
    TextView tv_DoctorIn_PSID;
    @BindView(R.id.tv_DoctorIn_PSName)
    TextView tv_DoctorIn_PSName;
    @BindView(R.id.tv_Pri_NurseID)
    TextView tv_Pri_NurseID;
    @BindView(R.id.tv_Pri_NurseName)
    TextView tv_Pri_NurseName;
    @BindView(R.id.tv_Day_Type)
    TextView tv_Day_Type;
    @BindView(R.id.tv_IsSick)
    TextView tv_IsSick;
    @BindView(R.id.tv_Allergy)
    TextView tv_Allergy;
    @BindView(R.id.tv_Prepay_Banalce)
    TextView tv_Prepay_Banalce;
    @BindView(R.id.tv_Total_Fee)
    TextView tv_Total_Fee;
    @BindView(R.id.tv_Status)
    TextView tv_Status;
    @BindView(R.id.tv_IsMedicare)
    TextView tv_IsMedicare;
    @BindView(R.id.tv_reception)
    TextView tv_reception;
    @BindView(R.id.tv_illness)
    TextView tv_illness;
    @BindView(R.id.tv_purpose)
    TextView tv_purpose;

    PatientsBean bean;

    @Override
    protected int getContentView() {
        return R.layout.patientdataitem_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initRxEvent();
        tvTitle.setText("患者详情信息");
        llTitle.setOnClickListener(this);
        imgDropDown.setVisibility(View.VISIBLE);
        setModuleTag("患者资料");

        bean = (PatientsBean) getIntent().getExtras().getSerializable("PatientsBean");

        setBean(bean);
        setShowData();
    }

    //设置 患者详情信息 显示
    private void setShowData() {
        if (null == bean) return;

        tv_hospital_id.setText("住院号：" + bean.getPatID());
        tv_name.setText("姓名：" + bean.getPatName());
        tv_sex.setText("性别：" + MyUtils.getSex(bean.getSex()));//性别
        String format = new SimpleDateFormat("yyyy").format(new Date());
        int Year = Integer.parseInt(format);
        int NewTime = Integer.parseInt(bean.getBirthDay().substring(0, 4));
//        tv_age.setText("年龄：" + (Year - NewTime) + "岁");//年龄
        tv_age.setText("年龄：" + bean.getAge());//年龄
        tv_num_genre.setText("证件类别：" + bean.getCertType());
        tv_num.setText("证件号码：" + bean.getCertNo());
        tv_nationality.setText("民族：" + bean.getNation());
        tv_Blood.setText("血型：" + bean.getBlood());
        tv_isMarried.setText("婚姻状态：" + MyUtils.getMarried(bean.getMarried()));
        tv_phone.setText("联系电话：" + bean.getTelPhone());
        tv_Address.setText("地址：" + bean.getAddress());
        tv_ContName.setText("联系人姓名：" + bean.getContName());
        tv_ContRelation.setText("与患者关系：" + bean.getContRelation());
        tv_ContTel.setText("联系人电话：" + bean.getContTel());
        tv_ContAddr.setText("联系人地址：" + bean.getContAddr());
        tv_Fee_Sour_Type.setText("费用来源：" + bean.getFee_Sour_Type());
        tv_Fee_Sour_Name.setText("费用名称：" + bean.getFee_Sour_Name());
        tv_MedicareType.setText("医保类别：" + bean.getMedicareType());
        tv_MedicareNo.setText("医保号码：" + bean.getMedicareNo());

        PatientsBean.ZyDetailBean ZyDetail = bean.getZyDetail();
        if (null == ZyDetail) return;

        tv_In_Dept_Name.setText("入院科室名称：" + ZyDetail.getIn_Dept_Name());
        tv_In_Dept_ID.setText("入院科室代码：" + ZyDetail.getIn_Dept_ID());
        tv_In_Type.setText("入院方式：" + ZyDetail.getIn_Type());
        tv_In_Date.setText("入院日期：" + ZyDetail.getIn_Date());
        tv_In_Room.setText("入院病房：" + ZyDetail.getIn_Room());
        tv_In_Bed.setText("入院床位：" + ZyDetail.getIn_Bed());
        tv_ICD_Name.setText("入院诊断名称：" + ZyDetail.getICD_Name());
        tv_ICD.setText("入院诊断代码：" + ZyDetail.getICD());
        tv_Attend_PSID.setText("主治医师工号：" + ZyDetail.getAttend_PSID());
        tv_Attend_PSName.setText("主治医师姓名：" + ZyDetail.getAttend_PSName());
        tv_DoctorIn_PSID.setText("住院医师工号：" + ZyDetail.getDoctorIn_PSID());
        tv_DoctorIn_PSName.setText("住院医师姓名：" + ZyDetail.getDoctorIn_PSName());
        tv_Pri_NurseID.setText("责任护士工号：" + ZyDetail.getPri_NurseID());
        tv_Pri_NurseName.setText("责任护士姓名：" + ZyDetail.getPri_NurseName());
        tv_Day_Type.setText("护理级别：" + MyUtils.getNurse(ZyDetail.getDay_Type()));
        if (ZyDetail.getIsSick().equals("")) {
            tv_IsSick.setText("");
        } else if (ZyDetail.getIsSick().equals("0")) {
            tv_IsSick.setText("是否随诊： 否");
        } else {
            tv_IsSick.setText("是否随诊： 是");
        }
        tv_Allergy.setText("药物过敏：" + ZyDetail.getAllergy());
        tv_Prepay_Banalce.setText("预交金：" + ZyDetail.getPrepay_Banalce());
        tv_Total_Fee.setText("总费用：" + ZyDetail.getTotal_Fee());
        tv_IsMedicare.setText("是否医保：" + ZyDetail.getIsMedicare());
//        if (ZyDetail.getIsMedicare().length() == 0) {
//            tv_IsMedicare.setText("是否医保： 是");
//        } else {
//            tv_IsMedicare.setText("是否医保： 否");
//        }
        tv_reception.setText("接诊日期：" + ZyDetail.getReceive_Date());
        tv_illness.setText("病情状态：" + ZyDetail.getIllness_Status());
        tv_purpose.setText("住院目的：" + ZyDetail.getIn_Aim());
        if (ZyDetail.getStatus().equals("0")) {
            tv_Status.setText("状态： 出院");
        } else {
            tv_Status.setText("状态： 在院");
        }
    }

    protected void initRxEvent() {
        Flowable<RxBean> f1 = RxBus.getInstance().register(RxBean.class);
        disposable = f1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    if (event.getSendAction().equals("SCAN_RESULT#")) {
                        if (null != bean) {
                            if (event.getContent().equals(bean.getPatName())) {
                                T.showLong("病人名称验证通过!!!");
                            }
                        }
                    }
                });
    }
}
