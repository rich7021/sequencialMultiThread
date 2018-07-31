# sequencialMultiThread

## Scenario

create 2 threads, run together, but they can't run simutaneously.
A, B trigerred together, If A get execution authentication, B need to wait.
After A finish, return the execution priotiry. B get the priority, and strat to work.
Now, A wait....

Whole process keep 3 minutes.
