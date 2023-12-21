package com.project.lms.controller;

import com.project.lms.dto.CreateTransactionReq;
import com.project.lms.entity.AuthUser;
import com.project.lms.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService txnService;

    @PostMapping("/transaction")
    public String initiateTxn(@RequestBody @Valid CreateTransactionReq txnRq) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Integer adminId = authUser.getAdmin().getId();
        return txnService.initiateTxn(txnRq, adminId);
            
    }

    @PostMapping("/transaction/payment")
    public void makePayment(@RequestParam("amount") Integer amt,
                            @RequestParam("txnId") String txnId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Integer studentId = authUser.getStudent().getId();
        txnService.payFine(amt, studentId, txnId);
    }
}
