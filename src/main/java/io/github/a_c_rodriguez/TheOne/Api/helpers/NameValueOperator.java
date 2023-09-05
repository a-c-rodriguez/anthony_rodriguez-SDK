package io.github.a_c_rodriguez.TheOne.Api.helpers;

import java.nio.charset.Charset;

public interface NameValueOperator {
    String getName();
    String getValue();
    String getOperator();
    String toEncodedString(Charset charset);
}
