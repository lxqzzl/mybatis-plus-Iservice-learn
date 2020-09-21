package com.generator.mppre.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.generator.mppre.entity.GlobalErrorDTO;
/**
 * @Description 全局异常类            
 * @author lxq
 * @since Sun Sep 20 18:19:07 CST 2020
 */
 
//@RestControllerAdvice
public class GlobalExceptionManager {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionManager.class);

	
	/**
	* 参数格式错误2
	* @param ex UnauthorizedException
	* @return
	*/
	@ExceptionHandler(MismatchedInputException.class)
    @ResponseBody
    public GlobalErrorDTO mismatchedInputExceptionHandler(
    		MismatchedInputException ex){
	    logger.error("参数格式类型错误，{}", ex.getMessage());
	    String errorMsg = ex.getMessage();
	    errorMsg = errorMsg.substring(errorMsg.indexOf("\"")+1);
	    String errorValue = errorMsg.substring(0, errorMsg.indexOf("\""));
	    return new GlobalErrorDTO(40002, "参数格式类型错误;错误字段："+errorValue, null);
    }
	/**
	* 全局异常
	* @param ex Exception
	* @return
	*/
	@ExceptionHandler(Exception.class)
    @ResponseBody
    public GlobalErrorDTO globalExceptionHandler(
    	Exception ex){
        return new GlobalErrorDTO(500,  "发生错误", ex.getMessage());
    }
}