import csv
import pandas as pd




a = open("user.csv", "r", encoding='UTF-8')

Hlength=len(a.readlines())
data_x= pd.read_csv(filepath_or_buffer = 'user.csv', sep = ',')["user_id"].values
data_y= pd.read_csv(filepath_or_buffer = 'user.csv', sep = ',')["friends"].values
header = ["u_id1", "friends"]

print(Hlength)
with open('newFriends.csv', 'w',  newline='', encoding='UTF-8') as f:
    writer = csv.writer(f)
    writer.writerow(header)
    for i in range(0,Hlength):
        #for j,y in enumerate(str(data_y[i]).split(",")):
        fs = str(data_y[i]).split(",")
        f_list = []
        for j, s in enumerate(fs):
            f_list += s.lstrip(),
            if j >= 20:
                break
        y = ",".join(f_list)
        tmp = [str(data_x[i])]
        tmp += y,
        writer.writerow(tmp)
          
    f.close()




