# ITEA 11 - Red Grade: Root Cause Analysis

### Introduction

The ***itea furniture store*** is a new startup that primarily sells furniture
and home decoration in their stores. You have been hired as a consultant to
help them in their digital transformation.

After the Christmas holidays, you came back to work.
You are full of energy, so you want to start with cool new features.
You also saw some nice features for your programming language in a magazine that you would like to test out.
Unfortunately, you only see bug tickets in the current sprint. "What happened here?" you think. It was not Santa, this is sure!
You figure out that the colleagues, that were working between the holidays, made some big mistakes. And now you have to fix them.
So let's go to it.

### Task 1 - Look through the code and spot the bugs and smash them 🐛

1. What is a bug in general?

Discuss your opinions in the Group
<details>
<summary>Solution</summary>
History:
The term "bug" was used in an account by computer pioneer Grace Hopper, who publicized the cause of a malfunction in an early electromechanical computer. A typical version of the story is:

    In 1946, when Hopper was released from active duty, she joined the Harvard Faculty at the Computation Laboratory where she continued her work on the Mark II and Mark III.
    Operators traced an error in the Mark II to a moth trapped in a relay, coining the term bug. This bug was carefully removed and taped to the log book.
    Stemming from the first bug, today we call errors or glitches in a program a bug.
</details>

---

2. What is a bug in the code and Why? 

Discuss every bug with your group and answer the Question: Why does this bug got into the code?

<details>
<summary>Hint</summary>

1. You do not have to look in a java class for this one. Maybe there are too many lines somewhere?
2. One bug ticket has the following description: "After we implemented the 'return Quantities' feature the Quantities are negative sometimes"
</details>
<details>
<summary>Solution</summary>

1. user.csv has a bug
2. A validation is missing in the `Quantity` class
</details>

---

3. Is it a technical issue or a misunderstanding of the requirements?

What are the different and why is it important?
<details>
<summary>Solution</summary>

1. user.csv has a bug => Technical issue
2. A validation is missing in the `Quantity` class => Requirements (The old logic should stay but should be extended, not replaced with a new logic)
</details>

---
4. How would you fix the bugs?

<details>
<summary>Solution</summary>

1. user.csv has a bug - Remove the last line of the csv file that is not used?
2. A validation is missing in the `Quantity` class - Add the validation and have a thought how you would implement the `return Quantity` feature. Instead of allowing negative Quantities.
</details>

--- 

### Task 2 - Smash the bugs 🐛


5. Fix the error in the users.csv files

<details>
<summary>Solution</summary>
There is an additional new line in the end of the csv file that can be removed but does this fix the problem?
Have a look at the tests. Do they really test the complete csv file?
</details>

---

6. Is the bug really fixed or did you only eliminate symptoms but not the root cause?

<details>
<summary>Solution</summary>
The real bug is in the CsvFileUserRepository class.
Instead of using a stream the developer uses an indexed for loop. 
That is not the problem either but in the line `43` the end range is not correct.
Insted of going to the end of the list with `i < lines.size()` the developer added a `-1`.
Perhabs the developer wanted to have something like this `i <= lines.size() -1` to go through the list of elements.
Unfortunately, the developer forgot the equals symbol and the algorithm is wrong.
Instead of investigating the mistake an additional line was added in the users.csv file.
This just postpones the bug until someone creates a new csv file or updates the existing one.
</details>

### Task 3 - Merge conflicts

Commit aa57406 is a merge commit where a conflict has been resolved.
What caused that conflict? What may have caused the cause of the conflict?
How could similar merge conflicts be prevented in the future?

### Root Cause Analysis

A rule from the very first day as a CCD should be to always search for the root cause of an issue.
Clean code developers do not consider themselves satisfied with healing symptoms. 
Example: Sorting data in memory is too slow. A symptom cure would strive to speed up single instructions or instruction blocks.
Maybe unsafe code or parallelization becomes an option. A thorough root cause analysis would have shown that the chosen sort algorithm is the real culprit.
Hard to understand low level optimizations hence can be avoided by choosing a better algorithm.

## Refactoring: Instructions

We have two units that are in really bad conditions. They are based on a very quick implementation of our last release.
Please  refactor these technical debts to a 'better' code state. Please apply all rules and procedures of refactoring
for following classes:
- GardenBench
- ReceiptPresenter
