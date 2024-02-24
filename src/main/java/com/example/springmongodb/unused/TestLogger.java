//package com.example.springmongodb;
//
//
//import org.slf4j.Logger;
//
//public class TestLogger {
//
//    private static final Logger logger = LogManager.getLogger(TestLogger.class);
//
//    public static void main(final String... args)
//    {
//        logger.debug("Debug Message Logged !!!");
//        logger.info("Info Message Logged !!!");
//
//        logger.debug("Hello from Log4j 2");
//
//        // in old days, we need to check the log level log to increase performance
//        /*if (logger.isDebugEnabled()) {
//            logger.debug("{}", getNumber());
//        }*/
//
//        // with Java 8, we can do this, no need to check the log level
//     for(int i=0;i<100;i++)
//            logger.info("hello {}", () -> getNumber());
//
//    }
//
//    static int getNumber() {
//        return 5;
//    }
//        //logger.error("Error Message Logged !!!", new NullPointerException("NullError"));
//
//}
