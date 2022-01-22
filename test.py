class Deck:
    def __init__(self):
        self.cards = []

class Card:
    def __init__(self, cardStr):
        parseArr = cardStr.split(' ')
        if len(parseArr) != 2:
            raise ValueError('Cannot parse card '+cardStr+" , required parameter size=2!")

        color = parseArr[0]
        syms = parseArr[1]
        if color not in colorVal:
            raise ValueError('Invalid card color within \"'+cardStr+"\" !")
        if len(set(syms)) != 1:
            raise ValueError('Inconsistent card shading within \"'+cardStr+"\" !")
        if len(syms)==0 or len(syms)>3:
            raise ValueError('Invalid card number within \"'+cardStr+"\" !")
        if syms[0] not in shadingVal:
            raise ValueError('Invalid card symbol within \"'+cardStr+"\" !")
        
        self.color = color
        self.shading = charToShading[syms[0]]
        self.symbol = charToSymbol[syms[0]]
        self.number = len(syms)

    def giveColor(self):
        return self.color
    
    def giveSymbol(self):
        return self.symbol
    
    def giveShading(self):
        return self.shading
    
    def giveNumber(self):
        return self.number

stdInStrs = set()
N = 0
colorVal = {"blue","green","yellow"}
symbolVal = {"A","S","H"}
shadingVal = {"A","a","@","S","s","$","H","h","#"}
charToSymbol = {"A":"A", "a":"A", "@":"A",
                "S":"S", "s":"S", "$":"S",
                "H":"H", "h":"H", "#":"H"}
charToShading = {"A":"Upper", "a":"Lower", "@":"Symbol",
                 "S":"Upper", "s":"Lower", "$":"Symbol",
                 "H":"Upper", "h":"Lower", "#":"Symbol"}

##main

