from flask import Flask
from flask import jsonify, request

from sklearn.externals import joblib

from sklearn.ensemble import RandomForestClassifier
from sklearn.feature_extraction.text import CountVectorizer
#from nltk.corpus import stopwords

import string



app=Flask(__name__)



def text_process(mess):
    """
    Takes in a string of text, then performs the following:
    1. Remove all punctuation
    2. Remove all stopwords
    3. Returns a list of the cleaned text
    """
    # Check characters to see if they are in punctuation
    nopunc = [char for char in mess if char not in string.punctuation]

    # Join the characters again to form the string.
    nopunc = ''.join(nopunc)

    ff = open('stopwords.txt','r')
    ll = ff.read().split()
    # Now just remove any stopwords
    return [word for word in nopunc.split() if word.lower() not in ll]


#@app.route('/',methods=['GET'])
#def hello():
#	return "hellothere"


@app.route('/',methods=['POST'])
def pred():
    
    classifier=joblib.load('FinalFile11.pkl')
    msg1 = request.json['msg']
    String1 = classifier.predict([msg1])
    
    return jsonify({"result":String1[0]}),220


if  __name__=="__main__":
    app.run(port=8000,debug=True)
