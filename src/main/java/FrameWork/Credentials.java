package FrameWork;

/**
 * Created by ariel.hazan on 14-Dec-17
 */
public class Credentials {

    public static String HOST, REPORTER_HOST, PORT, REPORTER_PORT, USER, PROJECT, PASS, AK;
    public static boolean SECURE, REPORTER_SECURE;

    public enum CloudServerName {
        NAVOT, STAGE_CLOUD_ADMIN, VM, QA, MIRRON, PUBLIC, EYAl, QA_Not_Secured, MY_N_S, ARIEL_WIN_ADMIN, ARIEL_WIN_SECURE_ADMIN, ARIEL_WIN_PRO_ADMIN, ARIEL_WIN_USER, ARIEL_MAC_ADMIN, ARIEL_MAC_PRO_ADMIN, ARIEL_MAC_USER, YEHUDA, RELEASE_CLOUD, MASTER_CLOUD, QA_SECURE_USER, QA_SECURE_ADMIN, QA_SECURE_PRO, SHELI, IGAL, SELENIUM, YORAM, ARIEL_MAC_SECURE_PRO_ADMIN, ARIEL_MAC_SECURE_USER, ARIEL_MAC_SECURE_ADMIN;
    }

    public static void updateServerCredentials(CloudServerName cloudName) {
        switch (cloudName) {
            case NAVOT:
                HOST = "192.168.2.13";
                PORT = "80";
                USER = "admin";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case YORAM:
                HOST = "192.168.4.34";
                PORT = "8080";
                USER = "admin";
                PROJECT = "Default";
                PASS = "Admin123";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case IGAL:
                HOST = "demo.experitest.com";
                PORT = "443";
                USER = "admin";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = true;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51Ijo1LCJ4cC5wIjoyLCJ4cC5tIjoiTVRVeE5EazVOamd6TlRJMk9RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MzUzMzU5OTgsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.agx6xnExSFSDNYhx_ZGZQeTwLZ3dNX2cYgBSN8S2lbQ";
                break;
            case SHELI:
                HOST = "192.168.2.73";
                PORT = "80";
                USER = "sheli";
                PROJECT = "";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case STAGE_CLOUD_ADMIN:
                HOST = "stage.experitest.com";
                PORT = "443";
                USER = "ariel";
                PROJECT = "";
                PASS = "Experitest2012";
                SECURE = true;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjozOSwieHAucCI6MiwieHAubSI6Ik1UVXdNek01TnpBNU5Ea3lOdyIsImFsZyI6IkhTMjU2In0.eyJleHAiOjE4MzA5NTcxNDMsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.0CL9CIH-5432p1jTl6WAP09xqOWQn6BOw5id12Q6Cgc";
                break;
            case VM:
                HOST = "192.168.2.139";
                PORT = "80";
                USER = "admin";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case ARIEL_MAC_ADMIN:
                HOST = "192.168.2.91";
                PORT = "80";
                USER = "admin";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "Arielhazan-lt";
                REPORTER_PORT = "9000";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjoxLCJ4cC5wIjoyLCJ4cC5tIjoiTVRVeE9EWXdNelF4TWpBME1nIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MzM5ODQxMDMsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.mwIcIV17dNWsdGa4dJMbeE3GFL_2s-1XQ3VgB-HXD80";

                break;
            case ARIEL_MAC_PRO_ADMIN:
                HOST = "192.168.2.91";
                PORT = "80";
                USER = "ariel-pro";
//                PROJECT = "Test@ariel.com";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "Arielhazan-lt";
                REPORTER_PORT = "9000";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjoxLCJ4cC5wIjoyLCJ4cC5tIjoiTVRVeE9EWXdNelF4TWpBME1nIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MzkyMjg0NzcsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.w_ZksPf-y1715fE_E3c6SZvmFk926evXfSW3MSySzTA";
                break;
            case ARIEL_MAC_USER:
                HOST = "192.168.2.91";
                PORT = "80";
                USER = "ariel-user";
                PROJECT = "ariel@blabla.com6";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "Arielhazan-lt";
                REPORTER_PORT = "9000";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51Ijo1LCJ4cC5wIjo1LCJ4cC5tIjoiTUEiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4MzM4OTc2MTIsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.m3yY2bfY1XGVLvK35jKTuq930t3jFgoWS2qkPeFhnQg";
                break;
             case ARIEL_MAC_SECURE_ADMIN:
                HOST = "ariel-mac.experitest.local";
                PORT = "443";
                USER = "admin";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = true;
                REPORTER_HOST = "Arielhazan-lt";
                REPORTER_PORT = "9000";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjoxLCJ4cC5wIjoyLCJ4cC5tIjoiTVRVeE9EWXdNelF4TWpBME1nIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MzM5ODQxMDMsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.mwIcIV17dNWsdGa4dJMbeE3GFL_2s-1XQ3VgB-HXD80";

                break;
            case ARIEL_MAC_SECURE_PRO_ADMIN:
                HOST = "ariel-mac.experitest.local";
                PORT = "443";
                USER = "ariel-pro";
//                PROJECT = "Test@ariel.com";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = true;
                REPORTER_HOST = "Arielhazan-lt";
                REPORTER_PORT = "9000";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjoxLCJ4cC5wIjoyLCJ4cC5tIjoiTVRVeE9EWXdNelF4TWpBME1nIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MzkyMjg0NzcsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.w_ZksPf-y1715fE_E3c6SZvmFk926evXfSW3MSySzTA";
                break;
            case ARIEL_MAC_SECURE_USER:
                HOST = "ariel-mac.experitest.local";
                PORT = "443";
                USER = "ariel-user";
                PROJECT = "ariel@blabla.com6";
                PASS = "Experitest2012";
                SECURE = true;
                REPORTER_HOST = "Arielhazan-lt";
                REPORTER_PORT = "9000";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51Ijo1LCJ4cC5wIjo1LCJ4cC5tIjoiTUEiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4MzM4OTc2MTIsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.m3yY2bfY1XGVLvK35jKTuq930t3jFgoWS2qkPeFhnQg";
                break;

            case ARIEL_WIN_ADMIN:
                HOST = "192.168.2.108";
                PORT = "80";
                USER = "admin";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjoxLCJ4cC5wIjoyLCJ4cC5tIjoiTVRVeU16RTJPRGc1T1RZME1RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4Mzg3OTk2NDIsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.5eltWSjQ5yswDToNIab9C08AX9QTXhKkyQnJm4VPLCk";
                break;
            case ARIEL_WIN_SECURE_ADMIN:
                HOST = "ArielHazan-LT";
                PORT = "443";
                USER = "admin";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = true;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjoxLCJ4cC5wIjoyLCJ4cC5tIjoiTVRVeU16RTJPRGc1T1RZME1RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4Mzg3OTk2NDIsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.5eltWSjQ5yswDToNIab9C08AX9QTXhKkyQnJm4VPLCk";
                break;
            case ARIEL_WIN_PRO_ADMIN:
                HOST = "192.168.2.108";
                PORT = "80";
                USER = "ariel-pro";
                PROJECT = "";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case ARIEL_WIN_USER:
                HOST = "192.168.2.108";
                PORT = "80";
                USER = "ariel-user";
                PROJECT = "";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case YEHUDA:
                HOST = "192.168.2.31";
                PORT = "1111";
                USER = "yehuda";
                PROJECT = "";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case QA_SECURE_ADMIN:
                HOST = "qacloud.experitest.com";
                PORT = "443";
                USER = "ariel";
                PROJECT = "";
                PASS = "Experitest2012";
                SECURE = true;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjoyMTgyLCJ4cC5wIjoyLCJ4cC5tIjoiTVRVeE1qWTBNRFV4TnpZNU53IiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjQ2NzUyMjIwNTEsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.mf-0uqbsYrjv3jRgyjG1-WjlCDX5S5dImTJgDr-z1Uc";
                break;
            case SELENIUM:
                HOST = "192.168.2.167";
                PORT = "4444";
                USER = "";
                PROJECT = "";
                PASS = "";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case QA_SECURE_PRO:
                HOST = "qacloud.experitest.com";
                PORT = "443";
                USER = " ariel-pro";
                PROJECT = "";
                PASS = "Experitest2012";
                SECURE = true;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjoyMzcxNDMsInhwLnAiOjIsInhwLm0iOiJNVFV3TnpVMk5USTBPVEE0TlEiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjQ2NzIxOTUyODksImlzcyI6ImNvbS5leHBlcml0ZXN0In0.f4FZnlC3mvcIqRyvn6Ya7ZhWRCa0EUUozAD-R8LxaF4";
                break;
            case QA_SECURE_USER:
                HOST = "qacloud.experitest.com";
                PORT = "443";
                USER = "ariel-user";
                PROJECT = "arielProject";
                PASS = "G123l321";
                SECURE = true;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjoyNjkxLCJ4cC5wIjoyLCJ4cC5tIjoiTVRRNU9UTTFNVE16TkRrNE5nIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjQ2NzIxOTU0MDAsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.XUOSiX6WGh8sF-l86TuTemYHES1s7QXD2UBKKKUUEqI";
                break;
            case QA_Not_Secured:
                HOST = "qacloud.experitest.com";
                PORT = "80";
                USER = "ariel";
                PROJECT = "";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case MIRRON:
                HOST = "192.168.2.71";
                PORT = "8080";
                USER = "user1";
                PROJECT = "";
                PASS = "Welc0me!";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case PUBLIC:
                HOST = "https://cloud.experitest.com";
                PORT = "443";
                USER = "ariel.hazan@experitest.com";
                PROJECT = "";
                PASS = "Experitest2012";
                SECURE = true;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case EYAl:
                HOST = "eyalneumann.experitest.local";
                PORT = "8091";
                USER = "admin";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case RELEASE_CLOUD:
                HOST = "192.168.2.114";
                PORT = "80";
                USER = "ariel";
                PROJECT = "";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
            case MASTER_CLOUD:
                HOST = "192.168.1.64";
                PORT = "80";
                USER = "ariel";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "eyJ4cC51IjozLCJ4cC5wIjoyLCJ4cC5tIjoiTVRVd09UVXlNVFl6TVRFMk9RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MzgyNzIxOTIsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.0RjegPr5KyKsIr65N2wSkMDCEFo49icXxD4kj7s5HNY";

                break;
            default:
                HOST = "192.168.2.108";
                PORT = "80";
                USER = "admin";
                PROJECT = "Default";
                PASS = "Experitest2012";
                SECURE = false;
                REPORTER_HOST = "";
                REPORTER_PORT = "";
                REPORTER_SECURE = false;
                AK = "";
                break;
        }
    }

}
