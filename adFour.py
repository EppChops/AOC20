

def validation(pwString):
    fields = pwString.split()
    print(fields)
    for field in fields:
        print(field)
        if not fieldsVal(field):
            return False
    return True


def fieldsVal(field):
    f = field.split(':')
    #print(f[0])
    if f[0] == 'byr':
        if (int(f[1]) >= 1920 and int(f[1]) <= 2002):
            print("byr was true")
            return True
    elif f[0] == 'iyr':
        if(int(f[1]) >= 2010 and int(f[1]) <= 2020):
            print("iyr was true")
            return True
    elif f[0] == 'eyr':
        if(int(f[1]) >= 2020 and int(f[1]) <= 2030):
            print("eyr was true")
            return True               
    elif f[0] == 'hgt':
        if(f[1].find('cm') != -1):
            i = f[1].find('cm')
            if(int(f[1][0:i]) <= 193 and int(f[1][0:i]) >= 150):
                print("hgt was true")
                return True
        elif(f[1].find('in') != -1):
            print("found inches")
            i = f[1].find('in')
            if(int(f[1][0:i]) <= 76 and int(f[1][0:i]) >= 59):
                    print("hgt was true")
                    return True
    elif f[0] == 'hcl':
        valids = '0123456789abcdef#'
        if f[1][0] == '#':
            for c in f[1]:
                if c not in valids:
                    return False
            print("hcl was true")
            return True
    elif f[0] == 'ecl':
        s = f[1]
        validStrings = ['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth']
        if s in validStrings:
            print("ecl was true")
            return True
    elif f[0] == 'pid':
        if len(f[1]) == 9 and f[1].isdigit():
            print("pid was true")
            return True
    elif f[0] == 'cid':
        return True
    else:
        return False

def exists(p):
    if(p.find('byr') != -1 and p.find('iyr') != -1 and p.find('eyr') != -1 and p.find('hgt') != -1 and p.find('hcl') != -1 and p.find('ecl') != -1 and p.find('pid') != -1):
        return True
    return False


with open('input.txt', 'r') as f:
    input = f.read().split('\n\n')
    count = 0
    for p in input:
        if validation(p) and exists(p):
            count += 1 


print(count)





