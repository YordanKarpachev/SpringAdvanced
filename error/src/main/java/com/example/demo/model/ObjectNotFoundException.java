package com.example.demo.model;


public class ObjectNotFoundException extends RuntimeException{


    private final long objectId;
    private final String objectType;


    public ObjectNotFoundException(long objectId ,  String objectType1)  {
        super("Product with ID " + objectId +  " and type " + objectType1 +" not found");
        this.objectId = objectId;
        this.objectType = objectType1;
    }

    public long getObjectId() {
        return objectId;
    }

    public String getObjectType() {
        return objectType;
    }
}
