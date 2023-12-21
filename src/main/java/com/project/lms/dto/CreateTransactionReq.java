package com.project.lms.dto;

import com.project.lms.entity.Transaction;
import com.project.lms.entity.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionReq {
    @NotNull
    private Integer studentId;

    @NotNull
    private Integer bookId;

    @NotNull
    private TransactionType txnType;

//    public Transaction toTxn(){
//        return  Tra
//    }
}
