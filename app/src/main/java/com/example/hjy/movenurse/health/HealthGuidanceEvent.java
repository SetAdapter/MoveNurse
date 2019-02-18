package com.example.hjy.movenurse.health;

import com.fy.entity.HealthGuidancesBean;

import java.util.List;

/**
 * Created by Gab on 2017/10/31 0031.
 */

class HealthGuidanceEvent {
    public HealthGuidanceEvent(List<HealthGuidancesBean> datas) {
        mDatas = datas;
    }

    private List<HealthGuidancesBean> mDatas;

    public List<HealthGuidancesBean> getDatas() {
        return mDatas;
    }

    public void setDatas(List<HealthGuidancesBean> datas) {
        mDatas = datas;
    }
}
