import os

from flask import Flask
from flask_restful import Api

from charities import Charities
from donations import Donations
from chargeoperation import ChargeOperation

secret_key = os.getenv("OMISE_SKEY")
public_key = os.getenv("OMISE_PKEY")

if secret_key != None:
     print 'Secrete Key: '+secret_key
else:
     print 'Secret key is required.'
     print 'Without Secrete key /donation api is not work properly.'
     print 'Please stop server using Ctrl+C command.'
     print 'You can set Secrete key using following command.'
     print '\t\texport OMISE_SKEY=<Your Secrete Key>'


if public_key != None:
     print 'Public Key: '+public_key
else:
     print 'Public key is optional.'
     print 'You can set Public key using following command.'
     print '\t\texport OMISE_PKEY=<Your Public Key>'

ChargeOperation.Instance().set_secret_key(secret_key)

app = Flask(__name__)
api = Api(app)
api.add_resource(Charities, '/charities')
api.add_resource(Donations, '/donations')

