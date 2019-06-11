def remove_vowels(s,b):
    s_without_vowels = ""
    for letter in s:
        if letter not in b:
            s_without_vowels += letter
    return s_without_vowels

print(remove_vowels('ayeeee',"aeiouAEIOU"))

