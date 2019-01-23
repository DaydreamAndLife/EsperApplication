package com.lckj.alarmer.controller.util;

import com.lckj.alarmer.controller.esper.EsperClient;
import com.lckj.alarmer.controller.indexobj.CpuUtilization;
import com.lckj.alarmer.controller.indexobj.MemoryFreeutilization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFilesAndCreateObjects {

    public static String log_path = "C:\\Users\\84575\\Desktop\\测试数据集\\1000W-3000W mixture\\TestDatetest1000W.txt";

    private List<CpuUtilization> cpu_utilizations = new ArrayList<>();
    private List<MemoryFreeutilization> memory_freeutilizations = new ArrayList<>();

    public List<CpuUtilization> getCpu_utilizations() {
        return cpu_utilizations;
    }

    public List<MemoryFreeutilization> getMemory_freeutilizations() {
        return memory_freeutilizations;
    }

    public ReadFilesAndCreateObjects() {
    }

    /**
     * 读取某个文件夹下的所有文件
     */
    public boolean readfile(String filepath, EsperClient client) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            // 文件 - 读内容，创建对象
            if (!file.isDirectory()) {
//                System.out.println(readToString(filepath, "UTF-8"));
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String str = "";
                while((str = reader.readLine()) != null) {
                    String[] args = str.split(",");
                    //createIndexObject(line.split(","));
                    if(args[0].equals("CPUUtilization")) {
                        client.send(new CpuUtilization(args[1], args[2], args[3], args[4], args[5], args[6]));
                    } else if(args[0].equals("memory_freeutilization")) {
                        client.send(new MemoryFreeutilization(args[1], args[2], args[3], args[4], args[5], args[6]));
                    }
                }
            } else if (file.isDirectory()) {    // 文件夹 - 递归下一层
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    readfile(filepath + "\\" + filelist[i],client);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }

    /**
     * 文本文件按照指定编码读入字符串
     * @param fileName, encoding
     * @return
     */
    public String readToString(String fileName, String encoding) {
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];  //最大值 Integer.MAX_VALUE - 8 不到2G
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public void createIndexObject(String... args) {
//        System.out.println(args[0] + args[1] + args[2] + args[3] + args[4] + args[5] + args[6]);
        if(args[0].equals("CPUUtilization")) {
            this.cpu_utilizations.add(new CpuUtilization(args[1], args[2], args[3], args[4], args[5], args[6]));
        } else if(args[0].equals("memory_freeutilization")) {
            this.memory_freeutilizations.add(new MemoryFreeutilization(args[1], args[2], args[3], args[4], args[5], args[6]));
        }
    }
}
