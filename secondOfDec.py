import re

words = list()
firstNum = list()
sndNum   = list()
char     = list()
with open ('input2.txt', 'r') as f:
    for line in f:
        x = line.split('-')
        firstNum.append(int(x[0]))
        y = x[1].split(' ', 1)
        sndNum.append(int(y[0]))
        char.append(y[1][0])
        z = y[1].split(' ')
        words.append(z[1])

i = 0
count = 0
for word in words:
    if(((word[(firstNum[i]-1)] == char[i]) and (word[(sndNum[i]-1)] != char[i])) or (word[(firstNum[i]-1)] != char[i]) and (word[(sndNum[i]-1)] == char[i])):
        count = count+1
    i = i+1

print(count)
print(len(words))