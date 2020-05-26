from flask import Flask
from flask_restful import Api

from charities import Charities
from donations import Donations

app = Flask(__name__)
api = Api(app)

api.add_resource(Charities, '/charities')
api.add_resource(Donations, '/donations')

if __name__ == '__main__':
     app.run(port='5002')