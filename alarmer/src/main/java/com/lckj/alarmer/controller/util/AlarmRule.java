package com.lckj.alarmer.controller.util;

import java.util.UUID;

public class AlarmRule {

    public String ruleId;          // 告警规则ID
    public String ruleName;        // 告警规则名称

    public String target;          // 目标，CpuUtilization / MemoryFreeutilization
    public String valueType;       // 数值类型，max / min / average
    public String compare;         // 比较符，> / = / < / >= / <=
    public double threshold;       // 阈值
    public int count;              // 连续比较的节点个数
    public String epl;             // esper接纳的epl语句
    public String eplTemplate = "select date, %s, count(*) as alarm_count from com.lckj.alarmer.controller.indexobj.%s.win:length(%d) where %s %s %f";
//    public String eplTemplate = " select c.date,m.date,c.average,m.average,count(c.average> %d )as cpu_count,count(m.average> %d )as mem_count";
    public int flag;               // 告警状态（用于告警还是恢复告警）

    // 以下均是对应的恢复告警
    public String r_target;
    public String r_valueType;
    public String r_compare;
    public double r_threshold;
    public int r_count;
    public String r_epl;
    public String r_eplTemplate = "select date, %s, count(*) as remove_alarm_count from com.lckj.alarmer.controller.indexobj.%s.win:length(%d) where %s %s %f";
    public AlarmRule() {
    }

    public AlarmRule(String ruleName, String target, String valueType, String compare, double threshold, int count) {
        this.ruleId = UUID.randomUUID().toString().replace("-", "");
        this.ruleName = ruleName;

        this.target = target;
        this.valueType = valueType;
        this.compare = compare;
        this.threshold = threshold;
        this.count = count;
        this.epl = String.format(this.eplTemplate, this.valueType, this.target, this.count, this.valueType, this.compare, this.threshold);
    }

    public AlarmRule(String ruleName, String target, String valueType, String compare, double threshold, int count, String _target, String _valueType, String _compare, double _threshold, int _count) {
        this.ruleId = UUID.randomUUID().toString().replace("-", "");
        this.ruleName = ruleName;

        this.target = target;
        this.valueType = valueType;
        this.compare = compare;
        this.threshold = threshold;
        this.count = count;
        this.epl = String.format(this.eplTemplate, this.valueType, this.target, this.count, this.valueType, this.compare, this.threshold);
        this.flag = 0;

        this.r_target = _target;
        this.r_valueType = _valueType;
        this.r_compare = _compare;
        this.r_threshold = _threshold;
        this.r_count = _count;
        this.r_epl = String.format(this.r_eplTemplate, this.r_valueType, this.r_target, this.r_count, this.r_valueType, this.r_compare, this.r_threshold);
    }

    public String getRuleId() {
        return ruleId;
    }

    public String  setRuleId(String ruleName , String target ,String valueType ,String compare , double threshold ,int count , String r_target ,String r_valueType ,String r_compare , double r_threshold , int r_count) {
        String ruleId = UUID.randomUUID().toString().replace("-", "");
        this.ruleId = ruleId;
        return this.ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEpl() {
        return epl;
    }

//    public void setEpl(String epl) {
//        this.epl = epl;
//    }

    public String getEplTemplate() {
        return eplTemplate;
    }

    public void setEplTemplate(String eplTemplate) {
        this.eplTemplate = eplTemplate;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getR_target() {
        return r_target;
    }

    public void setR_target(String _target) {
        this.r_target = _target;
    }

    public String getR_valueType() {
        return r_valueType;
    }

    public void setR_valueType(String _valueType) {
        this.r_valueType = _valueType;
    }

    public String getR_compare() {
        return r_compare;
    }

    public void setR_compare(String _compare) {
        this.r_compare = _compare;
    }

    public double getR_threshold() {
        return r_threshold;
    }

    public void setR_threshold(double _threshold) {
        this.r_threshold = _threshold;
    }

    public int getR_count() {
        return r_count;
    }

    public void setR_count(int _count) {
        this.r_count = _count;
    }

    public String getR_epl() {
        return r_epl;
    }

//    public void setR_epl(String _epl) {
//        this._epl = _epl;
//    }

    public String getR_eplTemplate() {
        return r_eplTemplate;
    }

    public void setR_eplTemplate(String _eplTemplate) {
        this.r_eplTemplate = _eplTemplate;
    }
}
