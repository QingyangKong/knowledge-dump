#### This is an example to to illustrate how gulp works
Run `npm init` to create a new project.  
Run `npm install gulp -g` to install gulp globally.    
Run `npm install --save-dev [plugins]` to add gulp plugins in project.  
Run `gulp {task name}` to execute gulp tasks.  

In this example, gulp will fetch files in project, do some manipulations(like compress, minify, uglify) and then save to build folder. All of the process can be done manually but gulp provides a easier way doing this. 

project architecture
```
gulpExample
├── GulpExample
|   ├── build
|   |   ├──htmml
|   |   ├──images
|   |   └──js
|   ├── node_modules
|   ├── src
|   |    ├──htmml
|   |    ├──images
|   |    └──js
|   ├── gulpfile.js
|   ├── package.json
|   └── package-lock.json
```
source code of gulpfile.js:
```
var gulp = require('gulp');
var newer = require('gulp-newer');
var imagemin = require('gulp-imagemin');
var htmlClean = require('gulp-htmlclean');
var concat = require('gulp-concat');
var deporder = require('gulp-deporder');
var stringdebug = require('gulp-strip-debug');
var uglify = require('gulp-uglify');
var del = require('del');

var folders = {
	src: 'src/',
	build: 'build/'
};

function getSrcPath(folderName){
	return folders.src + folderName;
}

function getBuildPath(folderName){
	return folders.build + folderName;
}

gulp.task('clean:js', function(){
	return del(getBuildPath('js/**/*'));
});

//task is named as images
gulp.task('images', function(){
	return gulp.src(getSrcPath('images/**/*'))
		.pipe(newer(getBuildPath('images/')))		//return all files newer than out folder
		.pipe(imagemin({ optimizationLevel: 5 }))	//return compressed files for new files
		.pipe(gulp.dest(getBuildPath('images/')));	//output the zip files to out folder
});

//add a new task to be executed after task 'images'
gulp.task('html', function(){ 					//execute html after task images
	return gulp.src(folders.src + 'html/**/*')		//return all html files from src folder
		.pipe(newer(folders.build + 'html'))		//compare with out folder and get modified & added ones
		.pipe(htmlClean())
		.pipe(gulp.dest(folders.build + 'html'));
});

//add js files and dependencies together, minfy and uglify.
//clean all js file before move
gulp.task('js', ['clean:js'], function(){
	return jsbuild = gulp.src(folders.src + 'js/**/*')	
		.pipe(deporder())				//get dependecies
		.pipe(concat('main.js'))			//concat all files together
		.pipe(stringdebug())				//remove console and debug information in js files
		.pipe(uglify())					//minify file main.js
		.pipe(gulp.dest(folders.build + 'js/'));
});

gulp.task('run', ['images', 'html', 'js']);
```
