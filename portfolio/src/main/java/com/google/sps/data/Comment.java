package com.google.sps.data;

/* A class which implements the structure of a comment */
public final class Comment{
    private final long id;
    private final String Email;
    private final String Name;
    private final String Message;
    private final long timestamp;

    public Comment(long id,String email,String name,String message,long timestamp){
        this.id=id;
        this.Email=email;
        this.Name=name;
        this.Message=message;
        this.timestamp=timestamp;
    }
} 