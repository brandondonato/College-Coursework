
def ack(m,n)
    if m == 0
        n+1
    elsif n == 0 and m > 0
        ack(m-1, 1)
    elsif n > 0 and m > 0
        ack(m-1, ack(m, n-1) )
    end
end

def main()
    puts "Please enter a single integer: "
    n = gets.to_i
    
    puts "Your inputted number (n) is: " + n.to_s
    result = ack(3,n)
    puts "This is the ackerman number for ack(3, N): " + result.to_s
    
end

main()
