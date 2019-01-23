package com.lckj.alarmer.controller;

import com.alibaba.fastjson.JSONObject;

import com.lckj.alarmer.controller.esper.EsperClient;
import com.lckj.alarmer.controller.indexobj.CpuUtilization;
import com.lckj.alarmer.controller.indexobj.MemoryFreeutilization;
import com.lckj.alarmer.controller.util.AlarmRule;
import com.lckj.alarmer.controller.util.ReadFilesAndCreateObjects;
import com.lckj.alarmer.controller.database.Database;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value="/alarm")
public class ApiController {

    private static EsperClient eclient = new EsperClient();
    private static int alarm_rule_count = 0;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping(value="/create_rule", method= RequestMethod.POST)
    public String createRule(@ModelAttribute AlarmRule rule) {

        JSONObject result = new JSONObject();

        try {
            // 创建规则
            AlarmRule alarmRule = new AlarmRule(rule.getRuleName(), rule.getTarget(), rule.getValueType(), rule.getCompare(), rule.getThreshold(),
                            rule.getCount(), rule.getR_target(), rule.getR_valueType(), rule.getR_compare(), rule.getR_threshold(), rule.getR_count());
            System.out.println(String.format(" RuleName : %s \n Alarm_EPL : %s \n Remove_Alarm_EPL : %s \n",
                    alarmRule.getRuleName(), alarmRule.getEpl(), alarmRule.getR_epl()));
            eclient.eventProcess(alarmRule);
            //规则存储到数据库
            Database Database_entity = new Database() ;
//            System.out.println(rule);
            Database_entity.insert_work(rule.getRuleName(), rule.getTarget(), rule.getValueType(), rule.getCompare(), rule.getThreshold(),
                    rule.getCount(), rule.getR_target(), rule.getR_valueType(), rule.getR_compare(), rule.getR_threshold(), rule.getR_count(),alarmRule.getRuleId());
            result.put("ruleId", alarmRule.getRuleId());

        } catch (Exception e) {
            result.put("isOK", false);
            result.put("msg", e.getMessage());
            result.put("ruleId", "-1");
            e.printStackTrace();
        }

        alarm_rule_count += 1;
        result.put("isOK", true);

        result.put("msg", "success");

        return result.toJSONString();
    }

    @RequestMapping(value="/send_log", method= RequestMethod.GET)
    public String sendLog() throws IOException {
        // 读取日志文件
        long startTime_readfile = System.currentTimeMillis();
        ReadFilesAndCreateObjects rfco = new ReadFilesAndCreateObjects();
        rfco.readfile(ReadFilesAndCreateObjects.log_path,eclient);
        long endTime_readfile = System.currentTimeMillis();
        System.out.println("数据集名称：" +ReadFilesAndCreateObjects.log_path );
        System.out.println("程序运行时间：" + (endTime_readfile - startTime_readfile) + "ms");
//        long startTime_process = System.currentTimeMillis();
//        for (CpuUtilization cpu_utilization : rfco.getCpu_utilizations()) {
//            eclient.send(cpu_utilization);
//        }
//        for (MemoryFreeutilization memory_freeutilization : rfco.getMemory_freeutilizations()) {
//            eclient.send(memory_freeutilization);
//        }
//        long endTime_process = System.currentTimeMillis();
        return String.valueOf(alarm_rule_count);
        //alarm_rule_count 表示这个程序运行以来，有多少条规则被输入了
    }
}