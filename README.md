## LRU Cache (Java)
## Description

This project implements an LRU (Least Recently Used) Cache in Java.
It stores a fixed number of key–value pairs in memory and removes the least recently used entry when the cache exceeds its capacity.

The cache is tested using localhost endpoints provided by a simple Java HTTP server.

## How It Works

HashMap provides O(1) access to cache entries

Doubly Linked List maintains the order of usage

Most recently used items move to the front

Least recently used items are removed from the end when capacity is exceeded

## Cache Operations

PUT /put/{key}/{value} – Inserts a key–value pair

GET /get/{key} – Returns the value if present, else Cache Miss

Running the Project
javac LRUCacheServer.java
java LRUCacheServer


Server runs at:

http://localhost:8080

Example Usage
Insert Items
http://localhost:8080/put/1/Java
http://localhost:8080/put/2/Python
http://localhost:8080/put/3/C++


Cache is now full (capacity = 3)

Access Item to Make it Recent
http://localhost:8080/get/1


Output: Java
Key 1 becomes most recently used

Insert New Item (Eviction Happens)
http://localhost:8080/put/4/Web


Key 2 (least recently used) is removed

Verify Eviction
http://localhost:8080/get/2


Output: Cache Miss

Check Remaining Keys
http://localhost:8080/get/1 → Java
http://localhost:8080/get/3 → C++
http://localhost:8080/get/4 → Web
