package com.lckj.alarmer.controller.util;

public class GlobalVar {
    public static boolean Cpu_alarm_flag = false;
    public static boolean Mem_alarm_flag = false;
    public static boolean FirstUse = true;
    private static GlobalVar VarSet = new GlobalVar();
    public static boolean getCpu_alarm_flag() {
        return VarSet.Cpu_alarm_flag;
    }

    public static boolean getMem_alarm_flag() {
        return VarSet.Mem_alarm_flag;
    }

    public static void setFirstUse(boolean FirstUse) {
        VarSet.FirstUse = FirstUse;
    }
    public static void setCpu_alarm_flag(boolean cpu_alarm_flag) {
        VarSet.Cpu_alarm_flag = cpu_alarm_flag;
    }

    public static void setMem_alarm_flag(boolean mem_alarm_flag) {
        VarSet.Mem_alarm_flag = mem_alarm_flag;
    }
}
