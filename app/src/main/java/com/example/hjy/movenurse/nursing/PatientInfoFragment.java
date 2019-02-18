package com.example.hjy.movenurse.nursing;

import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.PatientsBean;
import com.fy.utils.ResourceUtils;

import butterknife.BindView;

/**
 * 护理 --> 病人信息 Fragment
 * Created by fangs on 2017/9/11.
 */
public class PatientInfoFragment extends BaseFragment {

    @BindView(R.id.tv_patient_id)
    TextView tv_patient_id;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_bed_num)
    TextView tv_bed_num;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_age)
    TextView tv_age;
    @BindView(R.id.tv_hospital_id)
    TextView tv_hospital_id;
    @BindView(R.id.tv_marriage)
    TextView tv_marriage;
    @BindView(R.id.tv_profession)
    TextView tv_profession;
    @BindView(R.id.tv_dept)
    TextView tv_dept;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_patient_info;
    }

    @Override
    protected void baseInit() {
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");

        if (null == patin) return;
        ResourceUtils.setText(mContext, tv_patient_id, R.string.patientId, patin.getPainID());//病人ID

        PatientsBean.ZyDetailBean zyDetailBean = patin.getZyDetail();
        if (null != zyDetailBean) {
            ResourceUtils.setText(mContext, tv_area, R.string.area, zyDetailBean.getIn_AreaID());//病区
            ResourceUtils.setText(mContext, tv_dept, R.string.dept, zyDetailBean.getIn_Dept_Name());//科室
            ResourceUtils.setText(mContext, tv_bed_num, R.string.bedNum, zyDetailBean.getIn_Bed());//床号
        }

        ResourceUtils.setText(mContext, tv_name, R.string.name, patin.getPatName());//姓名
        String sex = "";
        if (patin.getSex().equals("M")) {
            sex = "男";
        } else if (patin.getSex().equals("F")) {
            sex = "女";
        } else {
            sex = "--";//未知
        }
        ResourceUtils.setText(mContext, tv_sex, R.string.sex, sex);//性别

        ResourceUtils.setText(mContext, tv_age, R.string.age, patin.getAge().substring(1, patin.getAge().length()));//年龄
        ResourceUtils.setText(mContext, tv_hospital_id, R.string.hospitalId, patin.getPatID());//住院号

        String marriage = "";
        if (patin.getMarried().equals("0")) {
            marriage = "未婚";
        } else if (patin.getMarried().equals("1")) {
            marriage = "已婚";
        } else {
            marriage = "--";//未知
        }
        ResourceUtils.setText(mContext, tv_marriage, R.string.marriage, marriage);//婚姻
        ResourceUtils.setText(mContext, tv_profession, R.string.profession, patin.getOccupation());//职业
    }
}
