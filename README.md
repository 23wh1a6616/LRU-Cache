LRU Cache (Java)
Description

This project implements an LRU (Least Recently Used) Cache using Java.
It stores a fixed number of key–value pairs and removes the least recently used item when the cache exceeds its capacity.

Working

HashMap is used for O(1) access

Doubly Linked List maintains usage order

Recently used items move to the front

Least recently used items are removed from the end

Operations

get(key) – returns value if key exists, else -1

put(key, value) – inserts or updates a key–value pair
