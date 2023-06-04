///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package sdm.gcms.controller.advice;
//
//import java.util.Map;
//import org.springframework.boot.web.error.ErrorAttributeOptions;
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.WebRequest;
//
///**
// *
// * @author Matthias
// */
//@Component
//public class CustomErrorDisplay extends DefaultErrorAttributes {
//
//    @Override
//    public Map<String, Object> getErrorAttributes(
//      WebRequest webRequest, ErrorAttributeOptions options) {
//        Map<String, Object> errorAttributes = 
//          super.getErrorAttributes(webRequest, options);
//        errorAttributes.put("locale", webRequest.getLocale()
//            .toString());
//        errorAttributes.remove("error");
//
//        //...
//
//        return errorAttributes;
//    }
//}