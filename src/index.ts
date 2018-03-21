declare var require: any
declare var process: any
declare var console: any


// http://codeburst.io/how-to-build-a-command-line-app-in-node-js-using-typescript-google-cloud-functions-and-firebase-4c13b1699a27


var program = require('commander')
var fs = require('fs')
var path = require('path')
var mkdirp = require('mkdirp')
var pug = require('pug')

const  basename = path.basename
const  dirname  = path.dirname
const  resolve  = path.resolve
const  normalize = path.normalize

const  join = path.join
const  relative = path.relative


program
  .option('-s', 'Remove sauce')
  .parse(process.argv);

console.log('you ordered a pizza')
if (program.sauce) console.log('  with sauce')
else console.log(' without sauce')



