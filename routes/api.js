// Dependencies
var express = require('express');

var router = express.Router();


// Routes
router.get('/products', function(req, res){
  res.send('welcome to products');
});
router.get('/mine', function(req, res){
  res.send('welcome to mine');
});

// Return Router
module.exports = router;
