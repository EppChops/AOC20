
i = 0

count1 = 0
count2 = 0
count3 = 0
count4 = 0
count5 = 0

row = 0
with open ('input3.txt', 'r') as f:
    for line in f:
        c1 = line[i % (len(line) - 1)] 
        c2 = line[3*i % (len(line) - 1)]
        c3 = line[5*i % (len(line) - 1)]
        c4 = line[7*i % (len(line) - 1)]
        i += 1
        if(c1 == '#'):
            count1 += 1
        if(c2 == '#'):
            count2 += 1
        if(c3 == '#'):
            count3 += 1
        if(c4 == '#'):
            count4 += 1
        if(c1 == '#' and row % 2 == 0):
            count5 += 1
        row += 1


print(count1)
print(count5)
print(count1 * count2 * count3 * count4 * count5)