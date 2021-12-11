package com.company.constants;

public class Constants {
    public static final int PORT = 2000;

    public static final String  HOST_DATABASE ="jdbc:mysql://127.0.0.1:3306/Kursach1?autoReconnect=true&useSSL=false";
    public static final String  NAME_DATABASE ="Kursach1";
    public static final String  USER_DATABASE ="root";
    public static final String  PORT_DATABASE ="3306";
    public static final String  PASSWORD_DATABASE ="389335sidor";

    public static final String USER_TABLE = "users";
    public static final String USER_ID = "id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";

    public static final String COMPANY_TABLE = "companies";
    public static final String COMPANY_ID = "id";
    public static final String COMPANY_NAME = "name";
    public static final String COMPANY_FIELD = "field";

    public static final String STATISTICS_TABLE = "statistics";
    public static final String STATISTICS_ID = "id";
    public static final String STATISTICS_IDCOMPANY = "idCompany";
    public static final String STATISTICS_NAME = "name";
    public static final String STATISTICS_DATE = "date";
    public static final String STATISTICS_COST = "cost";
}
