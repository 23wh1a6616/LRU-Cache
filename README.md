# LRU Cache (Java)

## Description
This project implements an **LRU (Least Recently Used) Cache** using Java.  
It stores a fixed number of key–value pairs and removes the **least recently used item** when the cache exceeds its capacity.

## How It Works
- **HashMap** for O(1) access to nodes  
- **Doubly Linked List** to maintain usage order  
- **Most recently used items** are moved to the front  
- **Least recently used items** are removed from the end  

## Operations
- `get(key)` – Returns the value if the key exists, else `-1`  
- `put(key, value)` – Inserts or updates a key–value pair
