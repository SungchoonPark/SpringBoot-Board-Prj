package com.choon.noticeBoard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 개잘자가 ErrorCode에 직접 정의한 Custom 예외를 처리할 Exception 클래스
public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;
}
