declare var require: any
declare var process: any

var program = require('commander')
var fs = require('fs')
var path = require('path')
var mkdirp = require('mkdirp')
var pug = require('pug')

var basename = path.basename
var dirname = path.dirname
var resolve = path.resolve
var normalize = path.normalize
var join = path.join
var relative = path.relative


program
  .option('-s', 'Remove sauce')
  .parse(process.argv);

console.log('you ordered a pizza')
if (program.sauce) console.log('  with sauce')
else console.log(' without sauce')



