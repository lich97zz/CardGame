#Author Yitao HE, Jan 21, 2022
This program is used to calculate number of SETs and the max number of disjoint SETS with a given collection of SET cards, rules for the SET game can be found from http://www.setgame.com/.

Run with cmd(require java preinstalled):
cat testcase.txt | java SetGame

Input:
User can either manually input number of cards and card description, or use a file with context as testcase.txt,
This program assumes number of cards N is in [3,30] and all cards are valid, no repeated cards.
Which means color are restricted in {yellow, blue, green}, symbols can only be A,S,H, shading are restricted to {A,a,@, S,s,$, H,h,#}, and the character repeats 1-3 times for each card.

Output:
It outputs(should have run time <5 secs) number of SETs max number of disjoint SETS with the given collection.
output format
[number of possible SETs]
[max number of disjoint SETS]
[a possible disjoint SETS with max number]

Notes:
By SET, we mean a combination of 3 cards which share same/different color, symbol, shading, and number.
eg. {blue H, green S, yellow A} is a SET while {green HHH, blue hhh, blue HHH} is not(color neither same nor different)
By disjoint SETs, we mean SETs that does not share common cards.
eg. {green $, yellow SS, blue sss} and {blue H, green S, yellow A}

Time:
The runtime can grow rapidly with card number, it runs with <2 seconds on my laptop with card number N=30.
