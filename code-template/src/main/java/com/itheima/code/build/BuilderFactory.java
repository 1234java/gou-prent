package com.itheima.code.build;

import freemarker.template.Template;

import java.io.File;
import java.util.Map;

/****
 * @Author:admin
 * @Description:构建对象的工厂
 * @Date 2019/6/14 23:21
 *****/
public class BuilderFactory {

    /***
     * 构建Controller
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap,//数据模型
                                String templatePath, //模板路径
                                String templateFile, //模板文件
                                String storePath,    //存储路径
                                String suffix){      //生成文件后缀名字
        try {
            //获取模板对象
            Template template = TemplateUtil.loadTemplate(ControllerBuilder.class.getResource(templatePath).getPath(), templateFile);

            //创建文件夹
            String path = TemplateBuilder.PROJECT_PATH+storePath.replace(".","/");
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }

//            System.out.println(suffix);
            if(suffix.equals("Service.java")){
                TemplateUtil.writer(template,modelMap,path+"/I"+modelMap.get("Table")+suffix);
            }else{
                TemplateUtil.writer(template,modelMap,path+"/"+modelMap.get("Table")+suffix);
            }
//            System.out.println(template);
//            System.out.println(modelMap);
//            System.out.println(path+"/"+modelMap.get("Table")+suffix);
            //创建文件




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
