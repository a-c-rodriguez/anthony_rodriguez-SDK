package org.anthonyrodriguez.TheOne.Api.helpers;

/***
 * Extension of OperatorNameValuePair that allows for only an
 * operator and name. This allows params that don't need a value
 * (i.e. check for the existence of a param: /movie?name or
 *  non-existence of a param: /movie?!name)
 */
public class OperatorNameMonad extends OperatorNameValuePair {

    private static final String EMPTY_VALUE = "";

    public OperatorNameMonad(String name, String operator) {
        super(name, EMPTY_VALUE, operator);
    }

    @Override
    public String getName() {
        return operator + super.getName();
    }

    @Override
    public String toString() {
        return getName();
    }
}
