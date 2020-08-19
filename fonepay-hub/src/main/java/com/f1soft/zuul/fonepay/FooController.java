package com.f1soft.zuul.fonepay;


import com.f1soft.zuul.fonepay.dto.Foo;
import com.f1soft.zuul.fonepay.dto.Merchants;
import com.f1soft.zuul.fonepay.dto.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * @author Rashim Dhaubanjar
 */
@Slf4j
@RestController
public class FooController {

    @GetMapping("/fonepay/merchants")
    public Merchants findById(HttpServletRequest req, HttpServletResponse res) {
        log.info("received get merchants");
        log.info("Authorization header set by proxy : {}", req.getHeader("Authorization"));
        return new Merchants(1L, "Esewa");
    }

    @PostMapping(value = "/fonepay/payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Foo paymentRequest(@NotNull @RequestBody PaymentRequest paymentRequest,
                              HttpServletRequest req, HttpServletResponse res) {
        log.info("proxy passed payment request : {}", paymentRequest.toString());
        log.info("req: {}", req);
        log.info("Authorization header set by proxy : {}", req.getHeader("Authorization"));
        log.info("res: {}", res);
        return new
                Foo(1L, "payment success");
    }
}
