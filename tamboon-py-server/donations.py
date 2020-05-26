from flask import request
from flask_restful import Resource

class Donations(Resource):
    def post(self):
        donation_data = request.get_json(force=True)
        return donation_data





