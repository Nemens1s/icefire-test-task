# icefire-test-task solution
Let's assume that if I managed to install backdoor then I would most likely found a way to find out how is my data hashed and therefore I would know my hash code.
This gives me a way to not use name anywhere in the code because I can compare objects with hash codes and if they are equal then I know that this is right one, so other prisoners couldn't use my backdoor.
After that I need to give myself permission to enter any room. To do that I can just iterate over all neigbour rooms and recursively go over their neighbours and so on. Since allowedPerson field is private and no getter methods are available, then it is needed to use reflection to get access to that field and change its value. Now allowePerson hash set also contains my name and last name, so I am able to go any room I want. Keycard parser's read method still returns my name and last name because I didn't do anything with them.

CLEARING LOGS
Not implemented but here is one possible solution. Since we know id of each room then we can have a hashmap where key is id and value is set of initially allowed people. When toString method is called then we could somehow change allowedPeople back to original.
