package com.skiply.studentManagement.feign;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class CustomFeignErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {
        // You can use methodKey to log or handle errors differently based on the method
        HttpStatus status = HttpStatus.resolve(response.status());

        // Handle specific HTTP status codes
        switch (status) {
            case BAD_REQUEST:
                return new BadRequestException("Bad Request: " + response.body());
            case INTERNAL_SERVER_ERROR:
                return new InternalServerErrorException("Internal Server Error: " + response.body());
            default:
                return new Exception("General Error: " + response.status());
        }
    }

}
