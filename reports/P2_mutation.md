## Reflection
![mutant coverage](../images/Screenshot%202023-03-06%20at%208.55.24%20PM.png)

## Company
Our mutation coverage and test strength are very good for Company.java. We did not get to 100% but
were able to identify many new tests and get up to 98%. Given the nature of this code, lots of the mutants which
needed to be killed revolved around swapped conditionals. 

## Project
Running mutation tests on project showed that
one of our tests was not actually testing what we thought it was.
The mutation was a change in the return value from the hashCode() method, it changed from the 
correct value to constant 0. This mutant survived a test that we had which was comparing two distinct
projects for equality based on their hashcode. Given that this mutation survived, we rewrote our tests for equality.

## Worker
Worker also has a very strong score. This class was so thoroughly tested before running the mutation test that 
we didn't really have too much to change afterwards.

## Qualification
Qualification has a similar story to the other classes. Just like the others one of the main mutants we see surviving
is the reverse iteration mutant. We have not seen a similar mutant for for-each loops and wonder if this is because they are
safer. 
