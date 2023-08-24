package org.anthonyrodriguez.TheOne.Api;

import org.apache.commons.logging.Log;

public class TestLog implements Log {

    @Override
    public void debug(Object message) {
        System.err.println(message);
    }

    @Override
    public void debug(Object message, Throwable t) {
        System.err.println(message + "\n" + t.getMessage());
        t.printStackTrace();
    }

    @Override
    public void error(Object message) {
        System.err.println(message);
    }

    @Override
    public void error(Object message, Throwable t) {
        System.err.println(message + "\n" + t.getMessage());
        t.printStackTrace();
    }

    @Override
    public void fatal(Object message) {
        System.err.println(message);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        System.err.println(message + "\n" + t.getMessage());
        t.printStackTrace();
    }

    @Override
    public void info(Object message) {
        System.err.println(message);
    }

    @Override
    public void info(Object message, Throwable t) {
        System.err.println(message + "\n" + t.getMessage());
        t.printStackTrace();
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public boolean isFatalEnabled() {
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public void trace(Object message) {
        System.err.println(message);
    }

    @Override
    public void trace(Object message, Throwable t) {
        System.err.println(message + "\n" + t.getMessage());
        t.printStackTrace();
    }

    @Override
    public void warn(Object message) {
        System.err.println(message);
    }

    @Override
    public void warn(Object message, Throwable t) {
        System.err.println(message + "\n" + t.getMessage());
        t.printStackTrace();
    }
}
