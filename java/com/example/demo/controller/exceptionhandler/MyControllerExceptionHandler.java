package com.example.demo.controller.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.NoDataFoundException;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestControllerAdvice
public class MyControllerExceptionHandler
{

    private static final Logger LOG = LoggerFactory.getLogger(MyControllerExceptionHandler.class);
    
    @ExceptionHandler({ NoDataFoundException.class, IllegalStateException.class })
    //both resp-entity and respstatus are not needed. res-status would override the other, bit API Docs will pick it.
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "ERR001 - Reason blah blah")
    public ErrorMessage noDataFound(HttpServletRequest request, Exception ex)
    {
        return new ErrorMessage("NO_CONTENT_STATUS", ex.getMessage(), "ERR001 - Blah..Blah");

    }
    
    
    @ExceptionHandler({ IllegalArgumentException.class })
    
  //both resp-entity and respstatus are not needed. res-status would override the other, unless overridden with @ApiResponse
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "ERR002 - Reason blah blah")
    public void inValidInput(HttpServletRequest request, Exception ex)
    {
        LOG.error("ERR001:  I am a teapot", ex);
        // return new ErrorMessage("I_AM_A_TEAPOT_STATUS", ex.getMessage(), "ERR001 - Blah..Blah");
        
    }
    
    /*
        HttpInputMessage inputMessage = new HttpInputMessage() {
                @Override
                public InputStream getBody() throws IOException {
                    MockClientHttpRequest mockRequest = (MockClientHttpRequest) request;
                    return new ByteArrayInputStream(mockRequest.getBodyAsBytes());
                }
                @Override
                public HttpHeaders getHeaders() {
                    return request.getHeaders();
                }
            }; 
      
     
     */

}

@Data
@AllArgsConstructor
class ErrorMessage
{
    String status;
    String errMessage;
    String descprion;
}
