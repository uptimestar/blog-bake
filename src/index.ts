declare var require: any
declare var process: any
declare var console: any
declare var __dirname: any

// http://codeburst.io/how-to-build-a-command-line-app-in-node-js-using-typescript-google-cloud-functions-and-firebase-4c13b1699a27

const logger = require('tracer').console()
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

logger.trace('you ordered a pizza')
if (program.sauce) logger.trace('  with sauce')
else logger.trace(' without sauce')

// tree ///////////////////////////////////////////////////////////////////////////////////////////////////
const jsonFiles = FileHound.create()
  .paths('.')
  .ext('json')
  .findSync()

// props ///////////////////////////////////////////////////////////////////////////////////////////////////
class Props {
	props: any
	path:string
	constructor(path:string) {
		logger.trace(path)
		this.path = path
		this.props = PropertiesReader(path)
		//xonsole.log(this.props.getAllProperties())
		if(!this.exists()) throw new Error('props file error')
	}
	exists():boolean {
		var count = this.props.length
		if(count>0) return true
		return false
	}
	getRoot():string {
		let r:string = props.get('root')
		logger.trace(r)
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
	getAll():Object {
		let all = this.props.getAllProperties()
		let ret = new Object()
		for(let key in all) {
			let value = all[key]
			ret[key] = value
	  }
	  return ret
	}//()
}

logger.trace(__dirname)
let props = new Props('../blog/one/meta.info')
logger.trace(props.getRoot())

// pug /////////////////////////////////////////////////////////////////////////////////////////////////////////////

let o = props.getAll()
//logger.trace(o)

let index = pug.renderFile('../blog/one/index.pug',
	o
)
logger.trace(index)
