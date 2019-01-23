package com.lckj.alarmer.controller.esper;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.lckj.alarmer.controller.util.AlarmRule;
import com.lckj.alarmer.controller.util.WriteStringToFile;
import com.lckj.alarmer.controller.util.GlobalVar;
import java.text.SimpleDateFormat;

/** 实现UpdateListener接口，来定义事件的后置处理过程 **/

public class TargetListener1 implements UpdateListener {

    private AlarmRule alarmRule;
    private static String File_path = "C:\\Users\\84575\\Desktop\\测试数据集\\result.txt";
    public TargetListener1(AlarmRule alarmRule) {
        this.alarmRule = alarmRule;
    }

    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        for (EventBean event : newEvents) {
            if ( Integer.parseInt(String.valueOf(event.get("remove_alarm_count"))) == this.alarmRule.getR_count() && this.alarmRule.getFlag() == 1)
            {
                String valueType = this.alarmRule.getValueType();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(event.get("date") );


                String temp_info = String.format("-- remove_alarm_count --\n Name : %s \n Time : %s \n %s : %s \n", this.alarmRule.getRuleName(), dateString , valueType,event.get(valueType));

//                if (this.alarmRule.getRuleName().equals("Mem占用率过高")){
//                    GlobalVar.setCpu_alarm_flag(false);
//                }
//                else if (this.alarmRule.getRuleName().equals("CPU占用率过高")){
//                    GlobalVar.setMem_alarm_flag(false);
//                }
//                else{
//                    System.out.println("规则名称错误！");
//                }
//                if((!GlobalVar.getCpu_alarm_flag() || !GlobalVar.getMem_alarm_flag()) && GlobalVar.FirstUse){
//                    GlobalVar.setFirstUse(false);
//                }
//                else if ((!GlobalVar.getCpu_alarm_flag() || !GlobalVar.getMem_alarm_flag()) && !GlobalVar.FirstUse ){
//                    System.out.println("混合规则消除生效");
//                    String mixture_info = String.format("-- Alarm --\n %s : %s \n", valueType,event.get(valueType));;
//                }

//
//
//                WriteStringToFile File_entity = new WriteStringToFile();
//                File_entity.Write(File_path,temp_info);
//                this.alarmRule.setFlag(0);
            }
        }
    }
}