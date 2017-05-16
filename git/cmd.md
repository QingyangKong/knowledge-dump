### Create a local project and link to github
##### 1. change to local directory and initiate a project.
`git init`  
##### 2. Add files into this git project. (changes are pending)
`git add {files}`  
##### 3. Commit files into this git project. (changes are checked in but still in the local one)
`git commit -m 'comment'`  
##### 4. Add a origin to push files.
`git remote add origin '{URI eg: https://github.com/Qingyangkong/RESTfulSample.git}'`  
##### 5. push checked-in changes into the project in github
`git push -u origin master`

### Commit local changes to github
##### 1. pull changes from github (because some may changes files and your local one is not lastest. AKA: non-fast-forward error)
##### 2. merge changes into the local one.
`git pull origin master`
##### 3. Add files into local project
`git add {files}`
##### 4. Commit in local one
`git commit -m 'comment'`
##### 5. Push into remote
`git push -u origin master`
