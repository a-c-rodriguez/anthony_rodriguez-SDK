package io.github.a_c_rodriguez.TheOne.Api.helpers;

import org.apache.http.util.Args;

import java.net.URLEncoder;
import java.nio.charset.Charset;

/***
 * Extension of the BasicNameValuePair class that allows the user to
 * change the default operator "=" that separates the pair to any
 * operator that can be represented as a string with a URI (i.e.
 * &quot;&gt;%quot; &quot;&lt;&quot;, &quot;&gt;=&quot;, etc)
 */
public class OperatorNameValuePair implements NameValueOperator {
    private final String name;
    private final String value;

    protected String operator;

    public OperatorNameValuePair(String name, String value, String operator) {
        this.name = (String) Args.notNull(name, "Name");
        this.value = value;
        this.operator = (null != operator) ? operator : "=";
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public String getOperator() {
        return this.operator;
    }

    public String toEncodedString(Charset charset) {
        String value = getValue();
        String name = getName();
        String operator = getOperator();
        if (value == null) {
            return URLEncoder.encode(name, charset);
        }
        final int len = name.length() + operator.length() + value.length();
        final StringBuilder buffer = new StringBuilder(len);
        buffer.append(URLEncoder.encode(name, charset));
        buffer.append(operator);
        buffer.append(URLEncoder.encode(value, charset));
        return buffer.toString();
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
