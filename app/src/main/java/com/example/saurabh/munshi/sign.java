package com.example.saurabh.munshi;

/**
 * Created by ADMIN on 3/26/2018.
 */

public class sign {



    String personId;
    String personEmail;
//    String personPassword;

    public sign(String personId ,String personEmail)
    {
        this.personId = personId;
        this.personEmail = personEmail;
//        this.personPassword = personPassword;
    }

    public String getPersonId() {
        return personId;
    }

    public String getPersonEmail() {
        return personEmail;
    }

//    public String getPersonPassword() {
//        return personPassword;
//    }
}

