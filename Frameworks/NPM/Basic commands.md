## basic commands
### npm init
`npm init`  
```
This utility will walk you through creating a package.json file.
It only covers the most common items, and tries to guess sensible defaults.

See `npm help json` for definitive documentation on these fields
and exactly what they do.

Use `npm install <pkg> --save` afterwards to install a package and
save it as a dependency in the package.json file.

Press ^C at any time to quit.
name: (nodeTest_1) node_test
version: (1.0.0) 1.0.0
description: this is a node project to test node.js
entry point: (index.js) index.js
test command:
git repository:
keywords:
author:
license: (ISC)
About to write to C:\webdev\projects\tests\nodeTest_1\package.json:

{
  "name": "node_test",
  "version": "1.0.0",
  "description": "this is a node project to test node.js",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "",
  "license": "ISC"
}

```
The command will ask some questions about project and create a `package.json`.


### npm install
#### install packages directly by name  
`npm install lodash`  
There will be a directory called `node_moduels` created and package `lodash` is installed in there.  
`npm install -g lodash` `-g` means global.  
This command will install lodash into global directory and it will not be in folder `node_modules`. But these changes are not saved in `pacakge.json`. 

#### install packages by package.json
```
"dependencies": {
    "lodash": "^4.17.4"
 }
```
`npm install`  
Add a dependency into package.json and install. By this way, all pacakge defined in package.json will be installed into `/node_modules`.   

#### install pacakge by name and save into package.json
`npm install lodash --save`  
`npm install lodash --save-dev`  
```
  "devDependencies": {
    "lodash": "^4.17.4"
  }
```
`--save` means the package installed in `/node_modules` will be saved in package.json also. `--save-dev` means the package installed and saved in devDependencies in `package.json`. devdenpendecy means this package is only required in development but not in this plugin.  

### npm ls
`npm ls`  
`npm ls -g`  
List installed packages local and global.  

### npm uninstall
#### uninstall pacakges by name  
`npm uninstall {package name}`  

#### uninstall package and remove in package.json
`npm uninstall --save lodash`  
`npm uninstall --save-dev lodash`  
Like `npm install --save lodash`, this command remove dependency in folder in `/node_modules` and conf in package.json.  

