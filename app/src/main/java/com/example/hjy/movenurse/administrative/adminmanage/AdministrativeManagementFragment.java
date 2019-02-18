package com.example.hjy.movenurse.administrative.adminmanage;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.PatientsBean;
import com.fy.utils.ResourceUtils;

import butterknife.BindView;

/**
 * 行政管理 侧边Fragment
 * Created by Stefan on 2017/9/22.
 */

public class AdministrativeManagementFragment extends BaseFragment {

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
    protected void baseInitView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        PatientsBean bean = (PatientsBean) bundle.getSerializable("PatientsBean");
        PatientsBean.ZyDetailBean zyDetailBean = bean.getZyDetail();

        ResourceUtils.setText(mContext, tv_patient_id, R.string.patientId, bean.getPainID());
        ResourceUtils.setText(mContext, tv_area, R.string.area, zyDetailBean.getIn_AreaID());
        ResourceUtils.setText(mContext, tv_dept, R.string.dept, zyDetailBean.getIn_Dept_Name());

        ResourceUtils.setText(mContext, tv_bed_num, R.string.bedNum, zyDetailBean.getIn_Bed());
        ResourceUtils.setText(mContext, tv_name, R.string.name, bean.getPatName());
        String sexSign = bean.getSex();
        String sex = sexSign.equals("F") ? "女" : (sexSign.equals("M") ? "男" : "");//三目运算符里的嵌套
        ResourceUtils.setText(mContext, tv_sex, R.string.sex, sex);
        ResourceUtils.setText(mContext, tv_age, R.string.age, bean.getAge());
        ResourceUtils.setText(mContext, tv_hospital_id, R.string.hospitalId, bean.getPatID());

        String marriage = bean.getMarried().equals("0") ? "未婚" : (bean.getMarried().equals("1") ? "已婚" : "");
        ResourceUtils.setText(mContext, tv_marriage, R.string.marriage, marriage);
        ResourceUtils.setText(mContext, tv_profession, R.string.profession, bean.getOccupation());
    }
}
