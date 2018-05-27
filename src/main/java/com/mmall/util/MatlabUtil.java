package com.mmall.util;

import com.mathworks.toolbox.javabuilder.MWException;
import com.mmall.pojo.CheckWithBLOBs;
import com.mmall.pojo.Model;
import main2018.Main2018main;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;

public class MatlabUtil {
    public static String process(CheckWithBLOBs check, Model model,HttpServletRequest request) throws MWException, IOException {
        /*
        第二个路径--得到原始数据
         */
        String targetFileName = check.getNote();
       targetFileName= targetFileName.replace("\\\\","\\");

       /* byte[] originTxt = check.getOriginalData();
        String txtFilename = "tFilename.txt";
        File file = new File(txtFilename);
        OutputStream output = new FileOutputStream(file);
        BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
        bufferedOutput.write(originTxt);
        bufferedOutput.close();
        String inputDataPath = file.getPath();//第二个路径--原始检测数据*/
        String inputDataPath = targetFileName;//
        /*
        第一个路径--得到数值参数
         */
        String canshuTxtPath = "canshu.txt";
        FileWriter fileWriter = new FileWriter(canshuTxtPath);
        fileWriter.write(model.getRowNodeNum() + " " + model.getCloumnNodeNum() + " " + model.getRowFixedDistance() + " " + model.getCloumnFixedDistance() + " " + check.getLineMethod());//横向节点数，纵向节点数，横向导体长度，纵向导体长度，通道数（4,8,16）
        fileWriter.close();
        /*
       第三个参数--接线细节文件
         */
        String jiexianxijiePath = "jiexian.txt";
        FileWriter fileWriterJiexian = new FileWriter(jiexianxijiePath);
        fileWriterJiexian.write(check.getLineDetail());
        fileWriterJiexian.close();

        Main2018main main2018main = new Main2018main();
        Object[] result = main2018main.main2018(1, canshuTxtPath, inputDataPath, jiexianxijiePath);
        String s = result[0].toString();
        String[] resultS = s.split("\n");
        StringBuffer cunchuResult = new StringBuffer();
        for (String tmps : resultS) {
            double j = Double.valueOf(tmps);
            System.out.println("double::" + j);
            tmps.replaceAll("\\s*", "");
            cunchuResult.append(j);
            cunchuResult.append(",");
        }
        cunchuResult.deleteCharAt(cunchuResult.length()-1);
        return cunchuResult.toString();
    }
}
