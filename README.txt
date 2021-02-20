raz.perry


=============================
=      File description     =
=============================
SimpleHashSet.java - This class is an abstract class that implements SimpleSet.
OpenHashSet.java - This class is inheriting from SimpleHashSet and it is hashSet based on open hashing.
OpenHashSetCell.java - This class is a cell in the OpenHashSet hashTable. It is a linked list with basic
list methods.
ClosedHashSet.java - This class is inheriting from SimpleHashSet and it is hashSet based on closed hashing.
CollectionFacadeSet.java - This class holds different types of collections as one object that implements
SimpleSet - that is for using any collection with the previous two sets i created as one type of SimpleSet
objects.
SimpleSetPerformanceAnalyzer.java - This class analyzes sets and linked list performance like adding values
and checking contains.

=============================
=          Design           =
=============================
General:
I create in the abstract class SimpleHashSet some abstract methods that implements in the closed and open hash
 set like copyHashTable. I also create resizeAdd/Del methods in the abstract class as protected method which
 called from each sub class.
Furthermore the two basic constructors in the sub-classes just called the abstract constructors and the third
one also called the super and than called addData method which is protected method in the abstract class.
By that there isn't code duplication and the code is very easy to fix bugs.


hash tables storage:
i choose to create different attributes of hash table in the classes - one is string array (close hashing) and
 the second is array of a new object i created - OpenHashSetCell. This class holds linked list and i
 implemented their basic list methods i needed to the hash table changing - add, remove, contain.
 By that the code is still modularity (for example one constructor that called abstract method that create
 each type of table) and efficient (use linked list as asked).

empty cell:
I choose to create a String object that represent an empty cell, by that i could compare not only the value -
also the object itself - so if in the data there was other String object with the same value the functionality
 would work as expected - the value will add (and won't think its already there), if wanted to delete that
 value - it will work only if the input String is different than my String object (although the values are
 equal).

=============================
=  Implementation details   =
=============================
analyzer:
I choose to create object that analyze the performance for each data file. I saved all data structures as
array of SimpleSet and by that i could goes over them in one loop (for example i created one method to add
data and it goes over each list by loop and counting the time there).
I added prints before each type of check and before each data structure and also during the test runs i
printed percentage (every 20%).
I also override the toString method to return for each data structure its type as string to print in the
runtime analysis.

=============================
=    Answers to questions   =
=============================
results:
we can see that both closed and open hash sets didn't efficient when getting the edge case - all data is
creating collusion. the action of searching for next empty cell is less efficient than the adding to the
linked list (open hashing) but both take very long time in compare with java data structure.
Linked list strength is in adding values but its the worst when needed to search value (contain).
HashSet has the best performance - efficient any time - adding, searching and even in edge cases.
TreeSet also had good runtimes but most of the time HashSet was better.
At most actions and edge cases Open hash set is better than closed hash set but in the average case their both
 being well but closed hash is better.
HashSet was very quick when added data1 although i expected many collusion.

