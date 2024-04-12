# ITEA 14 - Orange Grade: Single Responsibility Principle (SRP) and Separation of Concerns (SoC)

## Intro

### Background story

The ***ITEA Furniture Store*** is a company that primarily sells furniture
and home decoration in their stores. You have been hired as a consultant to
help them in their digital transformation.

<img src="assets/images/ITEA.jpg" width="400" alt="Photo of the ITEA headquarters" />

Now you have fixed all bugs in the Backlog and also learned more about the SLAP principle.
You feel really proud of your self. But what's that? The PO is coming to your desk:
``` 
Hey,
I know you are working hard to fix up the messy code but now we need business value.
We can not just sit around and play with the code! 
Now open the backlog and have a look at the new storys that we have.
Dont waste any time with this good code thing.
Go Go Go. When i am back from vacation i want results.
```
He says and off he goes to the airport.

You think, that you can combine the new feature with new practices!
So, last week you heard about this SoC and SRP principle. Sounds like the perfect principles for now!

... But what was that again?!

You open your favourite browser and start reading.

### Single Responsibility Principle - SRP

```
The single responsibility principle (SRP) is a computer programming principle that states that "A module should be responsible to one, and only one, actor." The term actor refers to a group (consisting of one or more stakeholders or users) that requires a change in the module.

Robert C. Martin, the originator of the term, expresses the principle as, "A class should have only one reason to change".
Because of confusion around the word "reason", he later clarified his meaning in a blog post titled "The Single Responsibility Principle", in which he mentioned Separation of Concerns and stated that 
"Another wording for the Single Responsibility Principle is: Gather together the things that change for the same reasons. Separate those things that change for different reasons.".
In some of his talks, he also argues that the principle is, in particular, about roles or actors.
For example, while they might be the same person, the role of an accountant is different from a database administrator. Hence, each module should be responsible for each role.

```

### Separation of Concerns - SoC
 
````
In computer science, separation of concerns is a design principle for separating a computer program into distinct sections.
Each section addresses a separate concern, a set of information that affects the code of a computer program.
A concern can be as general as "the details of the hardware for an application", or as specific as "the name of which class to instantiate".
A program that embodies SoC well is called a modular program. Modularity, and hence separation of concerns, is achieved by encapsulating information inside a section of code that has a well-defined interface.
Encapsulation is a means of information hiding. Layered designs in information systems are another embodiment of separation of concerns (e.g., presentation layer, business logic layer, data access layer, persistence layer).

Common examples include separating a space into rooms, so that activity in one room does not affect people in other rooms, and keeping the stove on one circuit and the lights on another, so that overload by the stove does not turn the lights off.
The example with rooms shows encapsulation, where information inside one room, such as how messy it is, is not available to the other rooms, except through the interface, which is the door.
The example with circuits demonstrates that activity inside one module, which is a circuit with consumers of electricity attached, does not affect activity in a different module, so each module is not concerned with what happens in the other.

HyperText Markup Language (HTML), Cascading Style Sheets (CSS), and JavaScript (JS) are complementary languages used in the development of web pages and websites.
HTML is mainly used for organization of webpage content, CSS is used for definition of content presentation style, and JS defines how the content interacts and behaves with the user.
Historically, this was not the case: prior to the introduction of CSS, HTML performed both duties of defining semantics and style. 
````

## Task 1: What does this all mean? – Warm-up and Intuition

Have a little discussion in your group about what these principles are and what they don't are.

## Task 2: Order History

Now you should implement an Order History for different users.

Every user should have the possibility to order Items and see the full list of ordered items afterwords.

You should start with an InMemory solution for now.

1. What do you have to implement?
2. Are there concrete 'Concerns' that should be seperated?
3. What are the reasons for the Classes to change?

<details>
<summary>Reveal answer</summary>

1. What do you have to implement? 
    a) A new Endpoint to add items to the "Order History" and a new Endpoint to get the Order History for a user
    b) A new UseCase
    c) InMemoryRepository for the OrderHistory
2. Are there concrete 'Concerns' that should be seperated?
    a) Controller, Business logic, Persistence
</details>


## Task 3: Start coding

Select one member of your group who will start coding.
Create a list who will be the next and the next and the next. Until the first member is coding again. (This is called rotating)

Now start coding.

See you in the main room again!



----

<details>
<summary>Whats behind the curtain?</summary>

You accidental pressed the wrong button and the application restarts. And all the Order Histories are gone.
Perhaps it would be a good thing to persist this data in a real database...
</details>

