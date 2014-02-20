# Gateway Acceptance Tests

##Assumptions

1. the mysql database has been created and the mysql server is running (`mysql.server start`)
2. the application is running on the localhost at port 9001 (`cd gateway/gateway-core && mvn jetty:run`)

## Environment Variables

The tests make use of the following environment variables for parameterizing external services
(including the gateway application itself). For testing purposes, default values for the environment variables used by
the tests are specified in the `.env` file.

- **GATEWAY_URL** - the full URL to the gateway (e.g. `http://localhost:9001/gateway`)

##Running the tests

Execute the following command from the acceptance-tests directory:

    thor test:features

## Writing tests

One aspect of the acceptance tests that must be considered (and an appropriate strategy discussed) is how data is
managed. A tenet of any good test design is that the test itself should be responsible for creating any required data
and then cleaning data up (i.e. not leaving any side effects) after the test completes. One strategy for accomplishing
this is by using a separate test database that is setup after every scenario run.