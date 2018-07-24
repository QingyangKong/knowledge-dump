# How to contribute
As a developer of a orgnization, you can directly complete a development and push changes into a new branch of the existing repo. The process is shown below:

```
//first clone the repo from remtoe server
git clone {url}

//complete development in your favorite IDE
...

//create a new branch in case master branch is messed up
git branch <branchName>

//switch to the newly created branch
git checkout <brancName>

//add new changes (if any) dot means all
git add .            

//commit changes in the branch
git commit <files> -m 'comment' 

//check branches of the exsiting project
git branch          

//check if info of remote server correct
git remote -v

//push changes to branch in github
git push <remote name> <branchname>

//create pull request for newly created branch
//merge and remove the branch
```

Other tips:  
command to create a new branch:  
```
git branch <branch name>
```
command to push new changes into new branch. Do not add `-m` 
```
git push <remote name> <branch name>
```
command to switch branch
```
git checkout <branch name>
```
command to discard tracked changes
```
git checkout <files supposed to be disposed>
```
