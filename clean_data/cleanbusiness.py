import csv
import pandas as pd

filename = "business.csv"
a = open(filename, "r", encoding='UTF-8')




Hlength=len(a.readlines())
data_1= pd.read_csv(filepath_or_buffer = filename, sep = ',')["business_id"].values
data_2= pd.read_csv(filepath_or_buffer = filename, sep = ',')["name"].values
data_3= pd.read_csv(filepath_or_buffer = filename, sep = ',')["stars"].values
data_4= pd.read_csv(filepath_or_buffer = filename, sep = ',')["categories"].values
data_5= pd.read_csv(filepath_or_buffer = filename, sep = ',')["attributes.RestaurantsPriceRange2"].values
data_6= pd.read_csv(filepath_or_buffer = filename, sep = ',')["address"].values
data_7= pd.read_csv(filepath_or_buffer = filename, sep = ',')["city"].values
data_8= pd.read_csv(filepath_or_buffer = filename, sep = ',')["state"].values
data_9= pd.read_csv(filepath_or_buffer = filename, sep = ',')["postal_code"].values
data_10= pd.read_csv(filepath_or_buffer = filename, sep = ',')["review_count"].values
data_11= pd.read_csv(filepath_or_buffer = filename, sep = ',')["attributes.HasTV"].values
data_12= pd.read_csv(filepath_or_buffer = filename, sep = ',')["attributes.WiFi"].values
data_13= pd.read_csv(filepath_or_buffer = filename, sep = ',')["attributes.GoodForKids"].values

header = ["business_id","name","stars","categories","attributes.RestaurantsPriceRange2","address",
          "city","state","postal_code", "review_count", "attributes.HasTV", "attributes.WiFi",
          "attributes.GoodForKids"]

print(Hlength)
with open('newBusiness.csv', 'w',  newline='', encoding='UTF-8') as f:
    writer = csv.writer(f)
    writer.writerow(header)
    for i in range(0,Hlength):
        row = []
        row += str(data_1[i]),
        row += str(data_2[i]),
        row += str(data_3[i]),
        row += str(data_4[i]),
        row += str(data_5[i]),
        row += str(data_6[i]),
        row += str(data_7[i]),
        row += str(data_8[i]),
        row += str(data_9[i]),
        row += str(data_10[i]),
        row += str(data_11[i]),
        row += str(data_12[i]),
        row += str(data_13[i]),
        writer.writerow(row)

    f.close()
