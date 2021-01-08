package com.codechasers.license.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class ApplicationContextProvider implements ApplicationContextAware {

   private static ApplicationContext context;

   private static final Logger logger = LoggerFactory.getLogger(ApplicationContextProvider.class);

   public ApplicationContext getApplicationContext() {
      logger.debug("Getting ApplicationContext {}", context);
      return context;
   }

   @Override
   public void setApplicationContext(ApplicationContext ac) throws BeansException {
      logger.info("Setting ApplicationContext {}", ac);
      context = ac;
   }
}
