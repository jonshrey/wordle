# 8 May 2025

Your mission, should you choose to accept it, is:

1. In the /cycles/all table, add a button "borrow" alongside each row.
    1. Clicking the borrow button should borrow, if possible, one unit of the cycle and modify stock accordingly.
    2. If not possible, there should be some way of communicating that nothing happened
    3. Remember, the currently logged in user is available in the session.

2. In the /cycles/all table, add a button "return" next to the borrow button.
    1. Clicking the return button should return, if possible, the earliest borrowed cycle and modify the db accordingly.
    2. Calculate the rent due and display it.
    3. If not possible, there should be some way of communicating that there is nothing to return
    4. Remember, the currently logged in user is available in the session.

3. Add a logout button
    1. What should happen to the session key "loggedinuser"?


Crowd wisdom:
1. You could write a little bit of javascript that disables the borrow button if there isn't stock
2. If we're writing javascript, we might have to figure out how "static" file handling works in a spring boot server. (look, there's a main/resources/static folder, it must be there for a reason right?)

Deadline: 4pm.
This is a GRADED exercise.