package com.example.hjy.movenurse.restraint;

import com.fy.entity.RestraintStrapBean;

import java.util.List;

/**
 * Created by Gab on 2017/10/31 0031.
 */

class Restraint {
    public Restraint(List<RestraintStrapBean> datas) {
        mDatas = datas;
    }

    private List<RestraintStrapBean> mDatas;

    public List<RestraintStrapBean> getDatas() {
        return mDatas;
    }

    public void setDatas(List<RestraintStrapBean> datas) {
        mDatas = datas;
    }
}
