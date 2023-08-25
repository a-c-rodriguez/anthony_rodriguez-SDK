# Design Doc

I chose a simple design to try and remain as flexible as possible to support
addition of new endpoints and functionality.

## Project Structure
```
- src
  - main
    - java
      - org.anthonyrodriguez.TheOne.Api
        - dto
        - helpers
        TheOneClient.java
  - test
    - java
      - org.anthonyrodriguez.TheOne.Api
      TheOneClientTest.java
pom.xml    
```

Most of the code for the SDK is contained in `TheOneClient.java` class. This
is where the Apache Commons HttpClient is used to make calls out to the api.
The user must add the base url to the api along with the api key to the constructor.
An optional Apache Commons Log object can also be added to the constructor to log all
messages in the SDK.
Data transfer objects are kept in the dto folder. Only MovieDTO and QuoteDTO are needed
at this time but other dtos can be added here in the future.
Helper and utility files are kept in the helper directory. NameValuePair implementations
to enable filtering, sorting, and pagination are kept here. Misc utility functions can also
be kept here (i.e. merging 2 string arrays).
A comprehensive Junit test class is kept in the test directory for any devs to check usage
of the client methods as well as add their own tests if they fork the repo.

## Remarks
During the creation of the Junit tests, I found that not all of the NameValuePairs can
be used in conjunction. For example, the api supports using both a limit and greater than
filter. However, regex NameValuePairs can only be used alone. Trying to combine regex with
another NameValuePair will result in a failure from the api. 
The best way to address would be to in the api itself. Barring that, extra work in the SDK
would have to be done to return the results of the regex and then apply other NameValuePairs
outside of the api.