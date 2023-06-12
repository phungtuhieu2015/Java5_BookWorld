package com.poly.service;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.context.annotation.Configuration;
   import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
  import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


  @Configuration
  public class InterConfig implements WebMvcConfigurer {
      @Autowired
      AuthInterceptor auth;

      @Override
      public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(auth)
                  .addPathPatterns("/admin/index", "/account/profile","/admin/authors",
                  "/admin/publishers","/admin/categories","/admin/products","/admin/orders-pending","/admin/orders-pending","/admin/favorite-books",
                  "/admin/sold-books","/admin/revenue","/admin/users" ,"/order/**", "/admin/**,")
                  .excludePathPatterns("/assets/**", "/admin/home/index","/user/**");
      }
  }