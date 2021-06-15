package com.example.demo.config;

import com.example.demo.common.annotation.Authority;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author weiqisheng
 * @Title: Test
 * @ProjectName demo
 * @Description: TODO
 * @date 2021/4/2710:07
 */
@Component
@Order(1)
public class Test implements CommandLineRunner,ApplicationContextAware {

    /**
     * 获取Spring框架的上下文
     */
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext arg0) {
        this.applicationContext = arg0;
    }

    @Override
    public void run(String... args) throws Exception {
//在这里可以调用applicationContext了
        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(Authority.class);
        for (String key :controllers.keySet()){
            System.out.println(controllers.get(key));

            //Type superclassType = beanClazz.getGenericSuperclass()； 反射代理的时候

            // superclassType.getTypeName()获取的是原始类的完全限定名，包路径+类名



            //AnnotationUtils.findAnnotation
            Class<?> aClass = controllers.get(key).getClass();
            //AnnotationUtils.findAnnotation(aClass,RequestMapping.class);  通过反射获取
            RequestMapping annotation = aClass.getDeclaredAnnotation(RequestMapping.class);
            String s = annotation.value()[0];
            List<Method> methods = Arrays.asList(aClass.getDeclaredMethods());
            for (Method method : methods){
                Authority declaredAnnotation = method.getDeclaredAnnotation(Authority.class);
                if (!Objects.isNull(declaredAnnotation)){

                    if (method.isAnnotationPresent(PostMapping.class)){
                        PostMapping declaredAnnotation1 = method.getDeclaredAnnotation(PostMapping.class);
                        System.out.println(s + declaredAnnotation1.value()[0].replaceAll("\\{(.*?)}", "*"));
                    }else {
                        GetMapping declaredAnnotation2 = method.getDeclaredAnnotation(GetMapping.class);
                        System.out.println(s + declaredAnnotation2.value()[0].replaceAll("\\{(.*?)}", "*"));
                    }
                    System.out.println(declaredAnnotation.name());

                }
            }

        }
    }

    public static void main(String[] args) {
        BigDecimal qu = new BigDecimal(15);
      String a = "12";
      try {
          System.out.println(qu.subtract(BigDecimal.valueOf(Double.parseDouble(a))));
      }catch (Exception e){

      }

    }
}
