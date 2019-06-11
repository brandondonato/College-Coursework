#!/usr/bin/env ruby

#This is the iterative gcd function
def gcdI(i, j)
    while i != j
        if i > j
            i = i - j
        else
            j = j - i
        end
    end
    return i
end

#This is the recursice gcd function
def gcdR(i, j)
    if j != 0
        return gcdR(j, i%j)
    else
        return i
    end
end

#This is the main function used to execute the gcd loops 
def main()
    puts "Please enter a single integer: "
    num1 = gets.to_i
    puts "Please enter a second integer: "
    num2 = gets.to_i
    
    res1 = gcdI(num1, num2)
    puts "This is the interative gcd function: " + res1.to_s
    
    res2 = gcdR(num1, num2)
    puts "This is the recursive gcd function: " + res2.to_s
end

main()
