package com.company;

import com.mongodb.MongoClient;


public class databaseInitialization {
    //class for connecting database
    public MongoClient connectingDB() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        return mongoClient;
    }
}


