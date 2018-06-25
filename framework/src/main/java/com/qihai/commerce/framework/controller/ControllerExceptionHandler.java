package com.qihai.commerce.framework.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.StringUtil;

/**
 * 通用错误处理器.
 * @author Chenmm
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ControllerExceptionHandler extends AbstractErrorController {
    
    @Value("${server.error.path:${error.path:/error}}")
    private static String errorPath = "/error";
    
    @Autowired
    private ServerProperties serverProperties;
    
    public ControllerExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    @ResponseBody
    public R<Map<String, Object>> handleErrors(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
    	HttpStatus status = getStatus(request);
        if (StringUtil.isNotBlank((String)body.get("exception")) && body.get("exception").equals(Exception.class.getName())){
            body.put("status", HttpStatus.FORBIDDEN.value());
            status = HttpStatus.FORBIDDEN;
        }
        
        R<Map<String, Object>> result = new R<Map<String, Object>>();
        result.setCode(status.value() + "");
        if (403 == status.value()) {
        	result.setMsg("无权限访问");
		}else {
			result.setMsg(body.get("message") == null ? "未知异常，请联系管理员!" : body.get("message").toString());
		}
        return result;
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView handleHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    protected ModelAndView handleViewError(String url, String errorStack, String errorMessage, String viewName) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", errorStack);
        mav.addObject("url", url);
        mav.addObject("msg", errorMessage);
        mav.addObject("timestamp", new Date());
        mav.setViewName(viewName);
        return mav;
    }

    protected ModelAndView handleJSONError(HttpServletResponse rsp, String errorMessage, HttpStatus status) throws IOException {
        rsp.setCharacterEncoding("UTF-8");
        rsp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        rsp.setStatus(status.value());
        PrintWriter writer = rsp.getWriter();
        writer.write(errorMessage);
        writer.flush();
        writer.close();
        return null;
    }
    
    /**
     * Determine if the stacktrace attribute should be included.
     * @param request the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request,
                                          MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
