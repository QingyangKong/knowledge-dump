# How to contribute
## Basic development process
As a developer of a orgnization, you can directly complete a development and push changes into a new branch of the existing repo. The process is shown below:

```
//first clone the repo from remtoe server
git clone {url}

//create a new branch in case master branch is messed up
git branch <branchName>

//switch to the newly created branch
git checkout <brancName>

//complete development in your favorite IDE
...

//add new changes (if any) dot means all
git add {file name}

//commit changes in the branch
git commit <files> -m 'comment' 

//check branches of the exsiting project
git branch          

//check if info of remote server correct
git remote -v

//push changes to branch in github
git push <remote name> <branchname>

//create pull request for newly created branch
//ask others to review the PR
//merge and remove the branch
```

## How to create a new branch:  
```
#command to create a new branch:  
git branch <branch name>

#command to push new changes into new branch. Do not add `-m` 
git push <remote name> <branch name>

#command to switch branch
git checkout <branch name>

#command to discard tracked changes
git checkout <files supposed to be disposed>
```
Tip: when you create a new branch, new branch will extends all changes from the current branch instead of master or development.  

## How to commit changes
Be attention `commit . -m 'comment'` and `commit -a -m 'comment'` will first add all files in "unstaged for commit" to "staged to commit" and commit them with files that was in "staged to commit", so never use `commit .` and `commit -a` to commit files unless you know what you are doing.  

If you just want to commit files in "staged to commit", use command `commit -m 'comment'`.  
If you just want to commit a specifc file in "staged to commit", use command `commit <file name> -m 'comment'`.
