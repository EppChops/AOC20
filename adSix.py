import collections

with open ('six.txt', 'r') as f:
    input = f.read().split('\n\n')
    output = 0
    for group in input:
        ls = group.split()
        charmap = collections.ChainMap()
        for line in ls:
            for c in line:
                if(charmap.get(c) == None):
                    charmap.update(c, 0)
                else:
                    charmap.update(c, charmap.get(c) + 1)
        output = output + len (charmap.keys())

print(output)