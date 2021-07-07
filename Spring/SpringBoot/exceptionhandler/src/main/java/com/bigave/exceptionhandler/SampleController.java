package com.bigave.exceptionhandler;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/MyError")
    public String error(){
        throw new CustomException();
    }

    @ExceptionHandler(CustomException.class)
    public AppError sampleError(CustomException e){
        AppError appError = new AppError();
        appError.setMessage("app.error.key");
        appError.setReason("일부러 발생.");

        return appError;
    }
}
