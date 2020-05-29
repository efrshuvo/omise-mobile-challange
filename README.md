# Omise-mobile-challange
This repo contains code both for a donation server and a mobile client applicaition. Both server and client application uses omnise api/sdk to process online paayment. This codes are basically for the response of this : https://github.com/omise/challenges/tree/challenge-mobile

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

#### Installation
```sh
$ virtualenv venv
$ source venv/bin/activate
$ pip install flask flask-jsonpify flask-restful omise gunicorn
```

### Execution
At first need to set omise secrate key.
```sh
$ export OMISE_SKEY=<Secret Key>
```

Now execute the applicaiton by using the following command:
```sh
$ gunicorn tamboon-server:app
```
### Deployment
If anyone wish to deploy this code in any vps or vm can follow this tutorial:
https://www.markusdosch.com/2019/03/how-to-deploy-a-python-flask-application-to-the-web-with-google-cloud-platform-for-free/

