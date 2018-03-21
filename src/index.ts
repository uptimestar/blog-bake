declare var require: any
declare var process: any
declare var console: any
declare var __dirname: any

// http://codeburst.io/how-to-build-a-command-line-app-in-node-js-using-typescript-google-cloud-functions-and-firebase-4c13b1699a27

const program = require('commander')
const fs = require('fs')
const path = require('path')
//const mkdirp = require('mkdirp')
const pug = require('pug')
const PropertiesReader = require('properties-reader')
const FileHound = require('filehound')

// ////////////////////////////////////////////////////////////////////////////////////////////////////
const  basename = path.basename
const  dirname  = path.dirname
const  resolve  = path.resolve
const  normalize = path.normalize

const  join = path.join
const  relative = path.relative

// args //////////////////////////////////////////////////////////////////////////////////////////////////////
program
  .option('-s', 'Remove sauce')
  .parse(process.argv);

console.log('you ordered a pizza')
if (program.sauce) console.log('  with sauce')
else console.log(' without sauce')

// tree ///////////////////////////////////////////////////////////////////////////////////////////////////
const jsonFiles = FileHound.create()
  .paths('/tmp')
  .ext('json')
  .findSync()

// props ///////////////////////////////////////////////////////////////////////////////////////////////////
class Props {
	props: any
	path:string
	constructor(path:string) {
		console.log(path)
		this.path = path
		this.props = PropertiesReader(path)
		//console.log(this.props.getAllProperties())
		if(!this.exists()) throw new Error('props file error')
	}
	exists():boolean {
		var count = this.props.length
		if(count>0) return true
		return false
	}
	getRoot():string {
		let r:string = props.get('root')
		console.log(r)
		//all the cases ./..
		if(r=='.') return this.path

		return r
	}
	getTitle():string {
		return this.props.get('title')
	}
	get(prop:string) {
		return this.props.get(prop)
	}
}

console.log(__dirname)
let props = new Props('../blog/one/meta.info')
props.getRoot()
console.log(props.getRoot())

// pug /////////////////////////////////////////////////////////////////////////////////////////////////////////////


