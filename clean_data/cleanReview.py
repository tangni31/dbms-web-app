import csv
import pandas as pd


#funny,useful,review_id,text,business_id,stars,date,user_id,cool
filename = "review.csv"

header = ["funny","useful","review_id","text","business_id","stars","date","user_id","cool"]
chunks = pd.read_csv('review.csv',iterator = True)
for chunk in chunks:
    chunk['text']=chunk['text'].str.replace("\n"," ")
    chunk['text']=chunk['text'].str.replace("\r"," ")
    chunk.to_csv('newReview.csv')








