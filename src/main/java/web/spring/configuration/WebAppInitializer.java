package web.spring.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Register configuration in Spring Context.
 */
public class WebAppInitializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    /**
     * Registering in the context of the configuration class @see package.web.spring.configuration
     * Adding to the context listener to our configuration.
     * Configure mapping the Dispatcher Servlet.
     *
     * @param container
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext container) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfig.class);
        container.addListener(new ContextLoaderListener(ctx));
        ctx.setServletContext(container);

        ServletRegistration.Dynamic servlet = container.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
