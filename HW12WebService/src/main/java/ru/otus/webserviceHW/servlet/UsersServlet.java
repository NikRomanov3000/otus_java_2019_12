package ru.otus.webserviceHW.servlet;

import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUser;
import ru.otus.webserviceHW.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsersServlet extends HttpServlet {
    private static final String USERS_PAGE_TEMPLATE = "users.html";
    private static final String TEMPLATE_ATTR_RANDOM_USER = "randomUser";

    private final DbServiceUser dbServiceUser;
    private final TemplateProcessor templateProcessor;

    public UsersServlet(DbServiceUser dbServiceUser, TemplateProcessor templateProcessor) {
        this.dbServiceUser = dbServiceUser;
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        Optional<User> user = dbServiceUser.getRandomUser();
        user.ifPresent(randomUser -> paramsMap.put(TEMPLATE_ATTR_RANDOM_USER, randomUser));

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }
}
