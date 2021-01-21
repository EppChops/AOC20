numbers = list()

with open ('input.txt', 'r') as f:
    for line in f:
        for word in line.split():
            numbers.append(int(word))


def getNs():
    for n in numbers:
        for j in numbers:
            for p in numbers:
                if n + j + p == 2020: 
                    return (n,j, p)


(n,j,p) = getNs()
print(n*j*p)


