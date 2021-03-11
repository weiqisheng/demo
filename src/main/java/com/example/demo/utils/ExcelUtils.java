package com.example.demo.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author weiqisheng
 * @Title: ExcelUtils
 * @ProjectName utils
 * @Description: TODO
 * @date 2020/12/2316:51
 */
public class ExcelUtils {
    private static List<List<Object>> lineList = new ArrayList<>();

    /**
     *
     * @param response
     * @param fileName 文件名
     * @param projects 数据
     * @param cloumnNames 属性名
     * @param keys 标题
     */
    public static void export(HttpServletResponse response, String fileName, List<?> projects, String[] cloumnNames, String[] keys){
        ExcelWriter bigWriter = ExcelUtil.getBigWriter();

        for (int i=0;i<cloumnNames.length;i++){
            bigWriter.addHeaderAlias(cloumnNames[i],keys[i]);
            bigWriter.setColumnWidth(i,20);
        }

        //一次性写出内容，使用默认样式，强制输入标题
        bigWriter.write(projects,true);
        //respose为httpServletResponse对象
       // response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setContentType("application/octet-stream");
        try {
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition","attachment;filename=" +fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            bigWriter.flush(outputStream,true);
            //关闭writer,释放内存
            bigWriter.close();
            //此处记得关闭servlet流
            IoUtil.close(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<List<Object>> importExcel(MultipartFile file){
        try {
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            List<List<Object>> read = reader.read();
            read.remove(0);
            return read;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 标题必须为数据的属性字段
     * @param file
     * @param c
     * @param <T>
     * @return
     */
    public static <T>List<T> importExcel(MultipartFile file,Class c){
        try {
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            List list = reader.readAll(c);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


//    /**
//     *
//     * @param file
//     * @param cloumnNames 数据属性字段
//     * @return
//     */
//    public static List<Map<String,Object>> importExcel(MultipartFile file, String[] cloumnNames){
//        String fileName = file.getOriginalFilename();
//        if (StringUtils.isEmpty(fileName)){
//            throw new RuntimeException("没有导入文件");
//        }
//        if (file.getSize() > 1024 * 1024 * 10){
//            throw new RuntimeException("上传失败: 文件大小不能超过10M!");
//        }
//        if (fileName.lastIndexOf(".") != -1 && !StringUtils.equals(fileName.substring(fileName.lastIndexOf(".")),".xlsx")){
//            throw new RuntimeException("文件名格式不正确, 请使用后缀名为.XLSX的文件");
//        }
//        //读取数据
//        try {
//            ExcelUtil.read07BySax(file.getInputStream(),0,createRowHandler());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //将数据封装到list<Map>中
//        List<Map<String,Object>> dataList = new ArrayList<>();
//        if (CollectionUtils.isEmpty(lineList)){
//           return dataList;
//        }
//        //去除excel中的第一行数据
//        lineList.remove(0);
//        lineList.parallelStream().forEach(line ->{
//            if (!Objects.isNull(line)){
//                Map<String,Object> hashMap = new HashMap<>();
//               for (int i =0;i<cloumnNames.length;i++){
//                   Object property = line.get(i);
//                   hashMap.put(cloumnNames[i],property);
//
//               }
//               dataList.add(hashMap);
//            }
//        });
//        return dataList;
//
//    }
//
//
//    /**
//     * 通过实现handle方法编写我们要对每行数据的操作方式
//     */
//    private static RowHandler createRowHandler(){
//        //清空集合
//        lineList.removeAll(lineList);
//        return new RowHandler() {
//            @Override
//            public void handle(int i, int i1, List<Object> rowList) {
//                //将读到的每一行数据放入list集合
//                JSONArray jsonArray = new JSONArray(rowList);
//                lineList.add(jsonArray.toJavaList(Object.class));
//            }
//        };
//    }
}
