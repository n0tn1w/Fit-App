package com.ex.FitApp.handling;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NotFoundController implements ErrorController {

    @PreAuthorize("permitAll()")
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) return "errors/error-404";
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) return "errors/error-500";
        }
        return "errors/error-default";
    }

    @Override
    public String getErrorPath() {
        return "errors/error-404";
    }
}
