package com.project.lms.controller;

import com.project.lms.dto.CreateTransactionReq;
import com.project.lms.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        return txnService.initiateTxn(txnRq);
    }

    @PostMapping("/transaction/payment")
    public void makePayment(@RequestParam("amount") Integer amt,
                            @RequestParam("studentId") Integer studentId,
                            @RequestParam("txnId") String txnId) throws Exception {
        txnService.payFine(amt,studentId,txnId);
    }
}
