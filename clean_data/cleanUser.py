import csv
import pandas as pd

filename = "user.csv"
a = open(filename, "r", encoding='UTF-8')


Hlength=len(a.readlines())
data_u= pd.read_csv(filepath_or_buffer = filename, sep = ',')["user_id"].values
data_n= pd.read_csv(filepath_or_buffer = filename, sep = ',')["name"].values
data_y= pd.read_csv(filepath_or_buffer = filename, sep = ',')["yelping_since"].values
data_r= pd.read_csv(filepath_or_buffer = filename, sep = ',')["review_count"].values
data_f= pd.read_csv(filepath_or_buffer = filename, sep = ',')["fans"].values
data_a= pd.read_csv(filepath_or_buffer = filename, sep = ',')["average_stars"].values
data_fu= pd.read_csv(filepath_or_buffer = filename, sep = ',')["funny"].values
data_us= pd.read_csv(filepath_or_buffer = filename, sep = ',')["useful"].values
data_c= pd.read_csv(filepath_or_buffer = filename, sep = ',')["cool"].values

header = ["user_id","name","yelping_since","review_count","fans","average_stars","funny","useful","cool"]

print(Hlength)
with open('newUser.csv', 'w',  newline='', encoding='UTF-8') as f:
    writer = csv.writer(f)
    writer.writerow(header)
    for i in range(0,Hlength):
        row = []
        row += str(data_u[i]),
        row += str(data_n[i]),
        row += str(data_y[i]),
        row += str(data_r[i]),
        row += str(data_f[i]),
        row += str(data_a[i]),
        row += str(data_fu[i]),
        row += str(data_us[i]),
        row += str(data_c[i]),
        writer.writerow(row)

    f.close()
