package org.anthonyrodriguez.TheOne.Api.helpers;

import org.apache.http.message.BasicNameValuePair;

/***
 * Extension of the BasicNameValuePair class that allows the user to
 * change the default operator "=" that separates the pair to any
 * operator that can be represented as a string with a URI (i.e.
 * ">", "<", ">=", etc)
 */
public class OperatorNameValuePair extends BasicNameValuePair {
    protected String operator = "=";

    public OperatorNameValuePair(String name, String value, String operator) {
        super(name, value);
        if(null != operator) {
            this.operator = operator;
        }
    }

    @Override
    public String toString() {
        String value = getValue();
        String name = getName();
        if (value == null) {
            return name;
        }
        final int len = name.length() + operator.length() + value.length();
        final StringBuilder buffer = new StringBuilder(len);
        buffer.append(name);
        buffer.append(operator);
        buffer.append(value);
        return buffer.toString();
    }
}
