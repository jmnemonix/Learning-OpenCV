#!/bin/sh

curl -H "Content-Type: application/json" -X PUT -d '{ "name":"TEST","price":"42.00"}' http://localhost:3000/api/products/582db03d231e1251495e8213 
