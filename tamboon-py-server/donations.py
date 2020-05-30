import omise
from flask import request, jsonify
from flask_restful import Resource
from chargeoperation import ChargeOperation

class Donations(Resource):
    def post(self):
        donation_data = request.get_json(force=True) # return dict
        charge = ChargeOperation.Instance().create_charge(donation_data)
        if isinstance(charge,omise.Charge):
            return jsonify({"success":True,"message":"Donation completed"})
        return jsonify({"success":False,"message":charge.message})


