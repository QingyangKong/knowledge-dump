## basic commands
### what is NPM
NPM(node package manager) is a node project to manage all published node packages, and NPM also installed with Node as well. NPM can be used to download, remove and publish all packages based on Node.js in repositories.   

* npm init
* npm install
* npm ls
* npm uninstall
* package.json and package-lock.json

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

#### inatall dependency globally
Installing a dependency globally is just to add this dependency into the NPM's "base folder". Commands saved in the bin folder of the dependency can be executed anywhere becasue the "base folder" of NPM is in the enviornment variables list.  
Eg, if you want to use `ng new` to create a new angular project, first try to install angular cli globally by `npm install -g @angular/cli`.

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

### package.json and package-lock.json
package-lock.json is automatically generated for any operations where npm modifies either the node_modules tree, or package.json. In other words, when you add, remove, upgrade or downgrade the dependencies in node_modules, package-lock.json will be generated automatically. When run command `npm install`, NPM will install dependencies in package-lock.json even the they are not saved in package.json. In official document, this file is intended to be comitted into source repositories.  
The purpose to use the package-lock.json, IMO, are:  
1. In the package.json, sometimes versions of some dependencies are not specified. 
eg I installed lodash by running `npm lodash --save`, this config will be added into package.json.
```
{
  "lodash": "^4.17.4"
}
```
`^` means equal or greater than, so other developers cannot know what is the exact version installed in the project.   
But in auto-generated file `package-lock.json`, the details are recored as below: 
 ```
 "lodash": {
      "version": "4.17.4",
      "resolved": "https://registry.npmjs.org/lodash/-/lodash-4.17.4.tgz",
      "integrity": "sha1-eCA6TRwyiuHYbcpkYONptX9AVa4="
    }
```
2. Maybe there are several versions of project, and details about version can be recorded in previous package-


.json. For every version of project, one package-lock.json saved to refer exact version of dependencies used before.  

3. In the package-lock.json, every single dependency is listed explictly, and this will be helpful if user just want to know if a specific lib used in the current project. eg I can install request by `npm install request --save`, and `request` is quite a large dependecy that contains a lot of other libs.  
In package.json, there is only one line added to indicate request is installed.
```
{
    "request": "^2.83.0"
}
```
In package-lock.json, all libs under request is displayed like below.
```
"dependencies": {
    "ajv": {
      "version": "5.3.0",
      "resolved": "https://registry.npmjs.org/ajv/-/ajv-5.3.0.tgz",
      "integrity": "sha1-RBT/dKUIecII7l/cgm4ywwNUnto=",
      "requires": {
        "co": "4.6.0",
        "fast-deep-equal": "1.0.0",
        "fast-json-stable-stringify": "2.0.0",
        "json-schema-traverse": "0.3.1"
      }
    },
    "asn1": {
      "version": "0.2.3",
      "resolved": "https://registry.npmjs.org/asn1/-/asn1-0.2.3.tgz",
      "integrity": "sha1-2sh4dxPJlmhJ/IGAd36+nB3fO4Y="
    },
    ...
    ...
    ...
```
4. package-lock.json exactly reflects the node-modeule dependency tree. Sometimes, developer install a new dependency without `--save`, this will be displayed in the package-lock.json. So if project's libs installed by package.json does not work, try use package-locak.json. Maybe the developer forgets to save the changes into package.json but every change will be recorded in package-lock.json.
