package tk.aizydorczyk.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {"tk.aizydorczyk"})
@EnableJpaRepositories(basePackages = {"tk.aizydorczyk.repository"})
@EnableAutoConfiguration
@EntityScan(value = "tk.aizydorczyk.model.jpa")
public class AppConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addViewControllers( ViewControllerRegistry registry ) {
            registry.addViewController( "/" ).setViewName( "forward:/index.html" );
            registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
            super.addViewControllers( registry );
        }

}
