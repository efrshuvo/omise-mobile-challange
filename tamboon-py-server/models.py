from flask import jsonify

class Models :

    def __init__(self):
        self.charities = {
                 'total': 4,
                 'data':[
                         {"id": 0, "name": "Ban Khru Noi", "logo_url": "http://rkdretailiq.com/news/img-corporate-baankrunoi.jpg"},
                         {"id": 1, "name": "Habitat for Humanity Thailand", "logo_url": "http://www.adamandlianne.com/uploads/2/2/1/6/2216267/3231127.gif"},
                         {"id": 2, "name": "Paper Ranger", "logo_url": "https://myfreezer.files.wordpress.com/2007/06/paperranger.jpg"},
                         {"id": 3, "name": "Makhampom", "logo_url": "http://www.makhampom.net/makhampom/ppcms/uploads/UserFiles/Image/Thai/T14Publice/2554/January/Newyear/logoweb.jpg"}
                        ]
                 }

    def get_charities(self):
        return jsonify(self.charities);