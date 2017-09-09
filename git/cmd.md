### Make a local project a git repository and push to github
##### 1. Make a local project a git repository.
Change to the project directory and run command: `git init`, then the folder will be a git directory and a folder called `.git` will be created. Only git project can be pushed to repositories in github.    
##### 2. Add files into this git repository. (changes are pending)
`git add {files}`  If he project is just converted into a git folder, first thing to do it to add files by using `git add *.*`, because all files are invisible to git before they added.    
After adding files in to git directory, they become pending changes but not checked in yet. If changes are pushed to gitHub now, error `src refspec master does not match any.` happens.  
If files are updated later on, they need to be added again by using the same command.
##### 3. Commit files into this git repository. (changes are checked in but still in local)
`git commit -m 'comment'`  String after -m is comment for this check-in.
##### 4. Add a origin to push files.
`git remote add origin '{URI eg: https://github.com/Qingyangkong/RESTfulSample.git}'` This is assign origin with the url you want to push. After repository created in github, the url will be provided. 
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
