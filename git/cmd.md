### git a local project to github
#### 1. change to local directory and initiate a project.
`git init`  
#### 2. Add files into this git project. (changes are pending)
`git add {files}`  
#### 3. Commit files into this git project. (changes are checked in but still in the local one)
`git commit -m 'comment'`  
#### 4. Add a origin to push files.
`git remote add origin '{URI eg: https://github.com/Qingyangkong/RESTfulSample.git}'`  
#### 5. push checked-in changes into the project in github
`git push -u origin master`
