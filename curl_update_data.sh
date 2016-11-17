#!/bin/sh

curl -H "Content-Type: application/json" -X POST -d '{ "name":"test","sku":"testSKU1234","price":"42.96"}' http://localhost:3000/api/products 
