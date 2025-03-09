package com.crud.back.utils;

import com.auth0.jwt.algorithms.Algorithm;


public class Constants {
    public static final String AUTH0 = "auth0";
    public static final Algorithm ALGORITH = Algorithm.HMAC256("SchoolSecret");
    public static final String NOT_FOUND_ERROR = "Not found in database";
    public static final String LOG_ERROR_TOKEN = "error in decode token. Error; {}";
    public static final String MESSAGE_401 = "no esta autorizado";
}
