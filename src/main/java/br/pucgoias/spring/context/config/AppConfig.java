package br.pucgoias.spring.context.config;

import br.pucgoias.spring.context.scope.ViewScope;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de configuracao da aplicacao
 * @author gilcimar
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("br.pucgoias.viagem"),
@ComponentScan("br.pucgoias.viagem") })
public class AppConfig  extends CustomScopeConfigurer {

   public AppConfig() {
      Map<String, Object> map = new HashMap<>();
      map.put("view", new ViewScope());
      super.setScopes(map);
   }
   @Bean
   public LocalEntityManagerFactoryBean geEntityManagerFactoryBean() {
      LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
      factoryBean.setPersistenceUnitName("viagem");
      return factoryBean;
   }

   @Bean
   public JpaTransactionManager geJpaTransactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(geEntityManagerFactoryBean().getObject());
      return transactionManager;
   }
}
