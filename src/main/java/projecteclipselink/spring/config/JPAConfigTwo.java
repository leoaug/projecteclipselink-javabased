package projecteclipselink.spring.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"projecteclipselink.spring.config"})
@EnableTransactionManagement
public class JPAConfigTwo {
	
	@Bean
	public JpaTransactionManager transactionManagerSadc() {
		JpaTransactionManager jtManager = new JpaTransactionManager(getEntityManagerFactoryBean().getObject());
		return jtManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setPersistenceUnitName("bossanovadata");
		lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
		lcemfb.setLoadTimeWeaver(getLoadTimeWeaver());
		return lcemfb;
	}

	@Bean
	public LoadTimeWeaver getLoadTimeWeaver() {
	    LoadTimeWeaver loadTimeWeaver = new ReflectiveLoadTimeWeaver();
	    return loadTimeWeaver;
	}
	
	@Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter eclipseLinkJpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
        eclipseLinkJpaVendorAdapter.setShowSql(false);
        eclipseLinkJpaVendorAdapter.setGenerateDdl(true);
        //eclipseLinkJpaVendorAdapter.setDatabase(Database.MYSQL);
        return eclipseLinkJpaVendorAdapter;
    }
	
	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		// enable injection of EntityManager to beans with @PersistenceContext annotation
		return new PersistenceAnnotationBeanPostProcessor();
	}

}