package com.socialnetwork.socialnetwork.user;

import com.socialnetwork.socialnetwork.error.ApiError;
import com.socialnetwork.socialnetwork.shared.GenericResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/1.0/users")
    GenericResponse createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return new GenericResponse("User saved");
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request){
        ApiError apiError = new ApiError(400,"validation error",request.getServletPath());

        BindingResult result = exception.getBindingResult();
        Map<String,String> validationErrors = new HashMap<>();
        for (FieldError field : result.getFieldErrors()) {
            validationErrors.put(field.getField(),field.getDefaultMessage());
        }
        apiError.setValidationErrors(validationErrors);
        return  apiError;
    }
}
