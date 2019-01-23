package com.lckj.alarmer.controller.esper;


import java.text.*;
import java.util.Calendar;
import java.util.Date;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.lckj.alarmer.controller.util.AlarmRule;
import com.lckj.alarmer.controller.util.WriteStringToFile;
import com.lckj.alarmer.controller.Redis.*;
import com.lckj.alarmer.controller.util.GlobalVar;


/** 实现UpdateListener接口，来定义事件的后置处理过程 **/
public class TargetListener implements UpdateListener {
    private static String File_path = "C:\\Users\\84575\\Desktop\\测试数据集\\result.txt";
    private AlarmRule alarmRule;


    public TargetListener(AlarmRule alarmRule) {
        this.alarmRule = alarmRule;
    }

    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {

        for (EventBean event : newEvents) {
            if ( Integer.parseInt(String.valueOf(event.get("alarm_count"))) == this.alarmRule.getCount() && this.alarmRule.getFlag() == 0)
            {
                String valueType = this.alarmRule.getValueType();
                //event.get("c.date") 转换成String 格式
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(event.get("date") );
//                String temp_info = String.format("-- Alarm --\n Name : %s \n Time : %s \n %s : %f \n", this.alarmRule.getRuleName(), dateString, valueType, event.get(valueType));
                String temp_info = String.format("-- Alarm --\n Name : %s \n Time : %s \n %s : %s \n", this.alarmRule.getRuleName(), dateString , valueType,event.get(valueType));
//
//                if (this.alarmRule.getRuleName().equals("Mem占用率过高")){
//                    GlobalVar.setCpu_alarm_flag(true);
//                }
//                else if (this.alarmRule.getRuleName().equals("CPU占用率过高")){
//                    GlobalVar.setMem_alarm_flag(true);
//                }
//                else{
//                    System.out.println("规则名称错误！");
//                }
//                if(GlobalVar.getCpu_alarm_flag() && GlobalVar.getMem_alarm_flag()){
//                    System.out.println("混合告警规则生效");
//                    String mixture_info = String.format("-- Alarm --\n Time : %s \n %s : %s \n",dateString , valueType,event.get(valueType));;
//                }

//
//                WriteStringToFile File_entity = new WriteStringToFile();
//                File_entity.Write(File_path,temp_info);


//
//                String key =  "Alarm --" + event.get("date");
//                String name = this.alarmRule.getRuleName() + "--" + event.get(valueType);
//                redis redis_entity = new redis();
//                redis_entity.ConnectAndWrite(key,name);
                this.alarmRule.setFlag(1);
            }
        }
    }
}
