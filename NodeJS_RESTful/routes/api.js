// Dependencies
var express = require('express');

var router = express.Router();

// Models
var product = require('../models/product');

// Routes
product.methods(['get', 'put', 'post', 'delete']);
product.register(router, '/products');
/*
router.get('/products', function(req, res){
  res.send('welcome to products');
});
router.get('/mine', function(req, res){
  res.send('welcome to mine');
});
*/

// Return Router
module.exports = router;
