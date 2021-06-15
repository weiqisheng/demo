package com.example.demo.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.demo.common.CommonResult;
import com.example.demo.common.annotation.Authority;
import com.example.demo.config.ApplicationProperties;
import com.example.demo.config.TestProperties;
import com.example.demo.model.UmsAdmin;
import com.example.demo.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weiqisheng
 * @Title: TestController
 * @ProjectName myProject
 * @Description: TODO
 * @date 2020/12/1117:01
 */
@RestController
@Api(tags = "测试")
@Authority(name = "tt",pid = "123")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestProperties testProperties;

    @Autowired
    private ApplicationProperties applicationProperties;


    @GetMapping("/pr")
    @ApiOperation("属性")
    public CommonResult pr(){
        return CommonResult.success(testProperties.getName() + "," + testProperties.getAge());
    }

    @GetMapping("/action")
    @ApiOperation("action")
    public CommonResult action(){
        return CommonResult.success(applicationProperties.getBoy());
    }


    @PostMapping("/export")
    @ApiOperation("导入")
    public CommonResult test(@RequestParam MultipartFile multipartFile) throws IOException {

        return CommonResult.success(ExcelUtils.importExcel(multipartFile,UmsAdmin.class));
    }


    @GetMapping("/getCode/{id}")
    @ApiOperation("验证码")
    @Authority(name = "TEST_EDIT",pid = "001001")
    public void getCode(HttpServletResponse response){
        System.out.println(VerifyCodeUtil.lineCatcha(response));
    }
    @GetMapping("/getCode1")
    @ApiOperation("验证码1")
    public void getCode1(HttpServletResponse response){
        System.out.println(VerifyCodeUtil.circleCaptcha(response));
    }

    @GetMapping("/getCode2")
    @ApiOperation("验证码3")
    public void getCode3(HttpServletResponse response){
        System.out.println(VerifyCodeUtil.shearCaptcha(response));
    }

    @GetMapping("/image")
    @ApiOperation("图片url下载")
    public void image(@RequestParam String url,@RequestParam String imageName,HttpServletResponse response){
        ImageUtil.imageDownload(url,imageName,response);
    }

    @GetMapping("/location")
    @ApiOperation("本地图片下载")
    public void location(@RequestParam String imageName,HttpServletResponse response){
        ImageUtil.locationPath(imageName,response);
    }

    @GetMapping("/download")
    @ApiOperation("读片下载到本地")
    public void download(@RequestParam String imageName,String name){
        ImageUtil.downloadImage(imageName,name);
    }

    @GetMapping("/load")
    @ApiOperation("导出")
    public void load(HttpServletResponse response){
        List<UmsAdmin> umsAdmins = new ArrayList<>();
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1L);
        umsAdmin.setUsername("admin");
        umsAdmin.setPassword("123456");
        umsAdmin.setStatus(1);
        umsAdmins.add(umsAdmin);

        UmsAdmin umsAdmin1 = new UmsAdmin();
        umsAdmin1.setId(2L);
        umsAdmin1.setUsername("weiqisheng");
        umsAdmin1.setPassword("123456");
        umsAdmin1.setStatus(1);
        umsAdmins.add(umsAdmin1);

        UmsAdmin umsAdmin2 = new UmsAdmin();
        umsAdmin2.setId(3L);
        umsAdmin2.setUsername("zhangyuhua");
        umsAdmin2.setPassword("123456");
        umsAdmin2.setStatus(1);
        umsAdmins.add(umsAdmin2);

        UmsAdmin umsAdmin3 = new UmsAdmin();
        umsAdmin3.setId(4L);
        umsAdmin3.setUsername("lisan");
        umsAdmin3.setPassword("123456");
        umsAdmin3.setStatus(1);
        umsAdmins.add(umsAdmin3);

        UmsAdmin umsAdmin4 = new UmsAdmin();
        umsAdmin4.setId(5L);
        umsAdmin4.setUsername("lisi");
        umsAdmin4.setPassword("123456");
        umsAdmin4.setStatus(1);
        umsAdmins.add(umsAdmin4);

        String[] cloumnName = new String[]{"id","username","password","status"};
        String[] keys = new String[]{"用户id","姓名","密码","状态"};
        ExcelUtils.export(response,"测试.xls",umsAdmins,cloumnName,keys);
    }

    /**
     * 导入学生信息
     *
     * @param file
     * @throws IOException
     */
    @RequestMapping(value = "import")
    public List<UmsAdmin> importStudentInfos(MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        reader.addHeaderAlias("学号", "sno");
        reader.addHeaderAlias("姓名", "name");
        reader.addHeaderAlias("年龄", "age");
        reader.addHeaderAlias("性别", "gender");
        reader.addHeaderAlias("籍贯", "nativePlace");
        reader.addHeaderAlias("入学时间", "enrollmentTime");
        List<UmsAdmin> studentList = reader.readAll(UmsAdmin.class);
        return studentList;
    }



}
