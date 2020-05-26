# Omise-mobile-challange
This repo contains code both for a donation server and a mobile client applicaition. Both server and client application uses omnise api/sdk to process online paayment.

## Tamboon server
Currently only two api endpoint has been developed.
1. /charities : This api is used to get the list of the organization asking for donation. 
2. /donations : This api is usde to send donation to a perticular organization.

#### Dependency
1. Python 2.7
2. flask
3. flask-jsonpify
4. flask-restful
5. omnise

#### Installation and execution
```sh
$ virtualenv venv
$ source venv/bin/activate
$ pip install flask flask-jsonpify flask-restful omise
$ python tamboon-server.py
```
