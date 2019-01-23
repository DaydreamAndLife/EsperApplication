package com.lckj.alarmer.controller.esper;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.lckj.alarmer.controller.indexobj.CpuUtilization;
import com.lckj.alarmer.controller.indexobj.MemoryFreeutilization;
import com.lckj.alarmer.controller.util.AlarmRule;
public class EsperClient {

    private EPServiceProvider engine;
    public static int count = 0;

    // 构造方法，获取默认的引擎实例
    public EsperClient() {
        this.engine = EPServiceProviderManager.getDefaultProvider();
    }

    // 处理事件
    public void eventProcess(AlarmRule alarmRule) {
        EPAdministrator admin = engine.getEPAdministrator();

        // 添加事件类型
        admin.getConfiguration().addEventType(CpuUtilization.class);
        admin.getConfiguration().addEventType(MemoryFreeutilization.class);

        // 创建EPL语句处理事件
        EPStatement statement1 = admin.createEPL(alarmRule.getEpl());
        EPStatement statement2 = admin.createEPL(alarmRule.getR_epl());

        // 关联监听器进行回调
        statement1.addListener(new TargetListener(alarmRule));
        statement2.addListener(new TargetListener1(alarmRule));
    }

    // 发送事件
    public void send(Object event) {
            engine.getEPRuntime().sendEvent(event);
    }
}





/*
    之前的EPL语句
 */

//        String epl = "select date, average, count(*) as alarm_count from com.lckj.indexobj.CpuUtilization.win:length(3) where average > 0.8 ";
//        String ep2 = "select date, average, count(*) as remove_alarm_count from com.lckj.indexobj.CpuUtilization.win:length(3) where average < 0.8 ";

//        String epl = "select * from com.lckj.indexobj.CpuUtilization "
//                + "match_recognize ( "
//                    + "measures A as cpu1, B as cpu2 "
//                    + "pattern (A B) "
//                        + "define "
//                        + "A as A.average > 0.8, "
//                        + "B as B.average > 0.8 )";
//        String ep2 = "select * from com.lckj.indexobj.CpuUtilization "
//                + "match_recognize ( "
//                + "measures A as cpu1, B as cpu2 "
//                + "pattern (A B) "
//                + "define "
//                + "A as A.average < 0.8, "
//                + "B as B.average < 0.8 )";