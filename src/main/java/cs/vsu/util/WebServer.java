package cs.vsu.util;

import cs.vsu.config.WebConfig;
import cs.vsu.service.DepartmentService;
import cs.vsu.service.ProductService;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebServer {

    public static void start(DepartmentService deptService, ProductService prodService, int port) throws Exception {
        GenericApplicationContext parentCtx = new GenericApplicationContext();
        parentCtx.getBeanFactory().registerSingleton("departmentService", deptService);
        parentCtx.getBeanFactory().registerSingleton("productService", prodService);
        parentCtx.refresh();

        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.setParent(parentCtx);
        webCtx.register(WebConfig.class);

        DispatcherServlet servlet = new DispatcherServlet(webCtx);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);
        Wrapper wrapper = Tomcat.addServlet(ctx, "dispatcher", servlet);
        wrapper.setLoadOnStartup(1);
        ctx.addServletMappingDecoded("/*", "dispatcher");

        tomcat.start();
        System.out.println("Server started on http://localhost:" + port);
        tomcat.getServer().await();
    }
}
