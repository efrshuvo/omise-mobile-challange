import os

from flask import Flask
from flask_restful import Api

from charities import Charities
from donations import Donations
from chargeoperation import ChargeOperation

secret_key = os.getenv("OMISE_SKEY")
public_key = os.getenv("OMISE_PKEY")

print 'Secrete Key: '+secret_key
print 'Public Key: '+secret_key

ChargeOperation.Instance().set_secret_key(secret_key)
app = Flask(__name__)
api = Api(app)
api.add_resource(Charities, '/charities')
api.add_resource(Donations, '/donations')