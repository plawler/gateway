# Gateway Acceptance Tests

##Assumptions:
1. the mysql database has been created and the mysql server is running (`mysql.server start`)
2. the application is running on the localhost at port 9001 (`cd gateway/gateway-core && mvn jetty:run`)

### Environment Variables
- **GATEWAY_URL** - the full URL to the gateway (e.g. `http://localhost:9001/gateway`)

##Running the tests

Execute the following command from the acceptance-tests directory:

    thor test:features