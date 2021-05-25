# Flight Database Skip Lists
Author: Marisa Tania  

## About This Project
There are multiple websites that allow users to search for flights between the cities. We can store information about different flights in a "flight database". For each flight, we would store origin and destination cities, date, time of the flight as well as the flight number and the price.

When a user searches for flights, he or she often wants to match the origin and destination cities, as well as the date, but is somewhat flexible about the departure time, as long as it is close to the preferred departure time. For instance, the user might want to search for the flight from SFO to JFK on 03/15/2021 that leaves at 8am or within a 3-hour time frame of that departure time. In this case, it would reasonable for the system to return the following three available flights:
(SFO, JFK, 03/15/2021, 06:30) (SFO, JFK, 03/15/2021, 08:15) (SFO, JFK, 03/15/2021, 09:00)

This project implements the flight database as an Ordered Dictionary ADT which stores (key, value) entries. Each key is a tuple: (origin, destination, date, time) and each value is (flightNumber, price). The entries in the Ordered Dictionary are sorted by keys, lexicographically.

The user will call findFlights method to query a database where he or she would specify the origin, the destination, the date, the preferred departure time, and also a time frame. The method will return an ordered list of flights with the departure times less than, equal to and greater than the requested departure time, within the given time frame. In the example above, the user submitted the query with the requested departure time of 8am and a 3 hour time frame, and got the following suggested flights: 6.30am, 8.15am, and 9am.

### What is Skip List?
This project implemenst an Ordered Dictionary ADT with some custom functionality using a data structure called Skip List. A Skip List can be thought of as a hierarchy of linked lists, where the list at the bottom contains all the elements of the collection, and each higher level skips over some elements probabilistically.

Skip List S in Figure 1 has three levels {S0,, S1, S2}. The bottom most level contains all the elements of the dictionary and two dummy nodes containing –infinity and +infinity. Level Si+1 contains a subset of the elements from Si, chosen probabilistically: level i element is added to level i+1 with probability 1/2. In a skip list, the elements are arranged horizontally into levels and vertically into towers. The nodes on each level are arranged in a doubly-linked list. Similarly, the nodes in each tower are arranged in a doubly-linked list. The nodes of a skip list can be traversed using operations next(), prev(), down(), up().

<img width="1039" alt="flight_database" src="https://user-images.githubusercontent.com/60201466/119555885-38559480-bd53-11eb-9e34-9a40c4c28196.png">
Figure 1. An example of a skip list that stores information about eight flights. Only the keys are shown, and the year is omitted from the departure dates in the diagram for clarity purposes.



### Program Options
Each portion of the display can be toggled with command line options. Here are the options:
```bash

Options:
    * successors         Returns flights with departure times in increasing order from the requested departure time.
    * predecessors       Returns flights with departure times in increasing order from the requested departure time. 
    * findFlights        Returns flights with departure times within the given time frame of the departure time of the key.
```


### Included Files
There are several classes included. These are:
   - <b>FlightList</b>: the flight database internally implemented as a Skip List.
   - <b>FlightNode</b>: an individual node in FlightList. Contains a key of type FlightKey and a value of type FlightData, as well as pointers to the following nodes in the list: next, previous, down and up. 
   - <b>FlightKey</b>: contains String variables origin, destination, date and time. This class implements Comparable interface.
   - <b>FlightData</b>: contains flightNumber and price. 
 

### Program Output 
```bash
Find Flight Example:
Assume that the database contains the following flights (only the keys are mentioned below):
(SFO, JFK, 03/15/2021, 06:30)
(SFO, JFK, 03/15/2021, 07:15)
(SFO, JFK, 03/15/2021, 08:15) 
(SFO, JFK, 03/15/2021, 09:00) 
(SFO, JFK, 03/15/2021, 13:00) 
(SFO, JFK, 03/15/2021, 15:00)
For the key (SFO, JFK, 03/15/2021, 08:00) and time frame of 2 hours, 
findFlights will return the following results:
(SFO, JFK, 03/15/2021, 06:30)
(SFO, JFK, 03/15/2021, 07:15)
(SFO, JFK, 03/15/2021, 08:15) 
(SFO, JFK, 03/15/2021, 09:00)

Successors Example:
Assume that the skip list has the following flights:
(SFO, JFK, 03/15/2021, 08:15) 
(SFO, JFK, 03/15/2021, 09:00) 
(SFO, JFK, 03/15/2021, 13:40) 
(SFO, JFK, 03/15/2021, 19:00)

If we call successors method with the following key (SFO, JFK, 03/15/2021, 08:15), 
the result will be:
(SFO, JFK, 03/15/2021, 09:00) 
(SFO, JFK, 03/15/2021, 13:40) 
(SFO, JFK, 03/15/2021, 19:00)
If we call successors method with the following key: (SFO, JFK, 03/15/2021, 08:00) that is not in the skip list, 
the result will be the following:
(SFO, JFK, 03/15/2021, 08:15) 
(SFO, JFK, 03/15/2021, 09:00) 
(SFO, JFK, 03/15/2021, 13:40)
```
