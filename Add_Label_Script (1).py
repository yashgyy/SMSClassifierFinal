
# coding: utf-8

# In[2]:

import numpy as np
import pandas as pd
import nltk
import string
from nltk.corpus import stopwords


# In[3]:

train = pd.read_csv('myfile3.txt',delimiter='$')


# In[4]:

#train.info()


# In[5]:

train['Length'] = train['Body'].apply(len)


# In[6]:

train.head()


# In[7]:

train.rename(columns={'Unnamed: 5':'waste'}, inplace=True)


# In[8]:

train.drop('waste',axis=1,inplace=True)


# In[9]:

#train.head()


# In[10]:

def process_text(mess):
    # Check characters to see if they are in punctuation
    nopunc = [char for char in mess if char not in string.punctuation]

    # Join the characters again to form the string.
    nopunc = ''.join(nopunc)
    #print(nopunc)
    # Now just remove any stopwords
    return [word.lower() for word in nopunc.split() if word.lower() not in stopwords.words('english')]


# In[11]:

def correct_text(cols):
    return process_text(cols)


# In[12]:

train['Body'] = train['Body'].apply(correct_text)


# In[13]:

#train.head()


# In[14]:

fashion = ['fashion','myntra','fbb','fashion','amazon','flipkart','clothes','gifts','festive','season','sale','jewellery','shopping','shop','voucher','offer','home','center']
food = ['food','dominos','pizza','hut','cheese','medium','pizzahut','food','veg','groceries','burger','sandwhich','foodpanda','grofers','bazaar','milkshake','baker','cake','cookies']
finance = ['finance','recharge','paytm','mobile','balance','bank','inr','debit','card','credit','acct','icici','hdfc','sbi','pnb','fixed','deposit','property','estate','sell','central','market','invest','investment','buy','lacs','emi','loan','investor']
health = ['health','weight','blood','test','medicines','medicine','disease','loss','water','purifier','homeopathy','homeopathic','ayurvedic']
#education = ['education','exam','education','college','quiz','tutorial','course','school','college','result']
education = ['cabs','pool','uber','ola','ride','discount','uberGO','share','city','cab','PNR','book','pnr','booking','order']
telecom = ['telecom','customer','airtel','jio','vodafone','idea','call','unlimited','balance','recharge']


# Aur categories daalni hai.... please daal do achi achi categories isme


# In[15]:

#food


# In[16]:

def label_it(cols):
    
    fashion_count = 0
    food_count = 0
    finance_count = 0
    health_count = 0
    education_count = 0
    telecom_count = 0

    
    for word in cols:
        #print(word)
        if word in fashion:
            fashion_count += 1
        
        if word in food:
            food_count += 1
        
        if word in finance:
            finance_count += 1  
            
        if word in health:
            health_count += 1   
            
        if word in education:
            education_count += 1
            
        if word in telecom:
            telecom_count += 1
            #print('Hello',finance_count)
            
    total = int(fashion_count + food_count + finance_count + health_count + education_count + telecom_count)
    
    if total==0:
        total = 1
    
    fashion_avg = float(fashion_count/total)
    food_avg = float(food_count/total)
    finance_avg = float(finance_count/total)
    health_avg = float(health_count/total)    
    education_avg = float(education_count/total)    
    telecom_avg = float(telecom_count/total)
    
    
    maxx = max(fashion_avg,food_avg,finance_avg,health_avg,education_avg,telecom_avg)
    if maxx==0:
        return 'other'
    elif maxx==fashion_avg:
        return 'fashion'    
    elif maxx==food_avg:
        return 'food'
    elif maxx==finance_avg:
        return 'finance'
    elif maxx==health_avg:
        return 'health'
    elif maxx==education_avg:
        return 'education'
    elif maxx==telecom_avg:
        return 'telecom'


# In[17]:

train['Label'] = train['Body'].apply(label_it)


# In[18]:

#train.head()


# In[19]:

#train[train['Label']=='education']


# In[20]:

#train.drop(train[train['Label']=='other'].index,inplace=True)


# In[21]:

#train.drop(train[train['Label']=='telecom'].index,inplace=True)


# In[22]:

#train[train['Label']=='other']


# In[23]:

#train.groupby('Label')['Label'].describe()


# In[24]:

finan = train[train['Label']=='finance']
fash = train[train['Label']=='fashion']
foo = train[train['Label']=='food']
edu = train[train['Label']=='education']
heal = train[train['Label']=='health']
oth = train[train['Label']=='other']
telec = train[train['Label']=='telecom']


# In[25]:

finan.info()
foo.info()
fash.info()
edu.info()
heal.info()
oth.info()
telec.info()


# In[26]:

#finan.head()


# In[27]:

start = 145
finan = finan.drop(finan.index[[ind for ind in range(start,1197)]],axis=0)
#foo = foo.drop(foo.index[[ind for ind in range(start,149)]],axis=0)
fash = fash.drop(fash.index[[ind for ind in range(start,976)]],axis=0)
edu = edu.drop(edu.index[[ind for ind in range(start,350)]],axis=0)
heal = heal.drop(heal.index[[ind for ind in range(start,266)]],axis=0)
oth = oth.drop(oth.index[[ind for ind in range(start,3600)]],axis=0)
telec = telec.drop(telec.index[[ind for ind in range(start,1194)]],axis=0)


# In[28]:

#finan


# In[29]:

#finan.info()


# In[30]:

train = [finan,foo,fash,edu,oth,heal,telec]


# In[31]:

train = pd.concat(train)


# In[32]:

#train.info()


# In[33]:

#train['Label'].describe()


# In[34]:

#train.groupby('Label')['Label'].describe()


# In[35]:

#train


# In[36]:

train.head()


# In[37]:

train.drop(['Thread_Id','Date','Date_Sent','Address'],axis=1,inplace=True)


# In[38]:

train.head()


# In[39]:

from sklearn.utils import shuffle
train = shuffle(train)


# In[40]:

train.head()


# In[41]:

Features = train['Label']


# In[42]:

train.drop('Label',axis=1,inplace=True)


# In[43]:

from sklearn.feature_extraction.text import CountVectorizer


# In[44]:

def tostring(cols):
    
    ch = ''
    
    for word in cols:
        ch += word + ' '
    
    return ch


# In[45]:

train['Body'] = train['Body'].apply(tostring)


# In[46]:

#train.head()


# In[47]:

bow_transformer = CountVectorizer().fit(train['Body'])
Bow4=bow_transformer.transform(train['Body'])
print(bow_transformer.get_feature_names()[22])


# In[48]:

from sklearn.feature_extraction.text import TfidfTransformer
tfidf_transformer=TfidfTransformer().fit(Bow4)
Message=tfidf_transformer.transform(Bow4)


# In[49]:

from sklearn.naive_bayes import MultinomialNB
Fitter=MultinomialNB().fit(Message,Features)


# In[50]:

all_predict=Fitter.predict(Bow4)


# In[51]:

#all_predict


# In[52]:

from sklearn.metrics import classification_report
print(classification_report(Features,all_predict))


# In[53]:

#filter.predict(['dominos'])


# In[54]:

from sklearn.model_selection import train_test_split
msg_train,msg_test,label_train,label_test=train_test_split(train['Body'],Features,test_size=0.2, random_state=42)


# In[55]:

#msg_train


# In[56]:

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


# In[57]:

from sklearn.pipeline import Pipeline
pipeline = Pipeline([
    ('bow', CountVectorizer(analyzer=text_process)),  # strings to token integer counts
    ('tfidf', TfidfTransformer()),  # integer counts to weighted TF-IDF scores
    ('classifier', MultinomialNB()),  # train on TF-IDF vectors w/ Naive Bayes classifier
])


# In[58]:

pipeline.fit(msg_train,label_train)


# In[59]:

predictions=pipeline.predict(msg_test)


# In[60]:

print(classification_report(label_test,predictions))


# In[61]:

pipeline.predict(["Uber : You've got 50% off your 10 next 10 non-uberPOOL rides in Delhi NCR until 20-11-2017"])


# In[62]:

pipeline.predict(['medicine'])


# In[63]:

from sklearn.externals import joblib


# In[64]:

joblib.dump(pipeline,'FinalFile.pkl')


# In[65]:

Classifier=joblib.load('FinalFile.pkl')


# In[66]:

Classifier.predict(['Hello there, you have unlocked 50 rides'])


# In[ ]:




# In[ ]:




# In[ ]:



