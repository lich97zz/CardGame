import random

def toStr(card):
    color_,symbol_,shading_,number_ = card
    colorArr = ["yellow","green","blue"]
    charArr = ["A","a","@","S","s","$","H","h","#"]
    res = ""
    color = colorArr[color_]
    char = charArr[symbol_*3+shading_]
    return color+" "+number_*char

def generate_random(N=15):
    with open(file_name, 'w') as f:
        f.write(str(N)+'\n')
        for i in range(N):
            f.write(toStr(cards[i])+"\n")
        
cards = []
for color in range(3):
    for symbol in range(3):
        for shading in range(3):
            for number in range(1,4):
                cards.append([color,symbol,shading,number])
    
random.shuffle(cards)
N = 30
file_name = "testcase1.txt"

for i in range(N):
    cards = cards[:N]

generate_random(N)
