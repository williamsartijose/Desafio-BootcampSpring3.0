package com.wsj.bootcampspring.services.exceptions;

public class ClientNotFoundException extends  RuntimeException{
    private static final long serialVersioUID = 1L;
    public ClientNotFoundException(String msg){
        super(msg);
    }
}
