///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package sdm.gcms.filters;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
//
///**
// *
// * @author Matthias
// */
//public class ApiKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {
//
//  private final String headerName;
//
//  public ApiKeyAuthFilter(final String headerName) {
//    this.headerName = headerName;
//  }
//
//  @Override
//  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
//    return request.getHeader(headerName);
//  }
//
//  @Override
//  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
//    // No credentials when using API key
//    return null;
//  }
//}