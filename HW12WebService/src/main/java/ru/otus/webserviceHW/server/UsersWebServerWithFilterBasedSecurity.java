package ru.otus.webserviceHW.server;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.core.dao.UserDao;
import ru.otus.core.service.DbServiceUser;
import ru.otus.webserviceHW.services.TemplateProcessor;
import ru.otus.webserviceHW.services.UserAuthService;
import ru.otus.webserviceHW.servlet.AuthorizationFilter;
import ru.otus.webserviceHW.servlet.LoginServlet;

import java.util.Arrays;

public class UsersWebServerWithFilterBasedSecurity extends  UsersWebServerImpl {
    private final UserAuthService authService;

    public UsersWebServerWithFilterBasedSecurity(int port,
                                                 UserAuthService authService,
                                                 DbServiceUser dbServiceUser,
                                                 Gson gson,
                                                 TemplateProcessor templateProcessor) {
        super(port, dbServiceUser, gson, templateProcessor);
        this.authService = authService;
    }

    @Override
    protected Handler applySecurity(ServletContextHandler servletContextHandler, String... paths){
        servletContextHandler.addServlet(new ServletHolder(new LoginServlet(templateProcessor, authService)), "/login");
        AuthorizationFilter authorizationFilter = new AuthorizationFilter();
        Arrays.stream(paths).forEachOrdered(path -> servletContextHandler.addFilter(new FilterHolder(authorizationFilter), path, null));
        return servletContextHandler;
    }
}
