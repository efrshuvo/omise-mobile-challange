from flask_restful import Resource
from models import Models

class Charities (Resource):
    def get(self):
        return Models().get_charities();
