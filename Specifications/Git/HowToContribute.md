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

## How to amend a commit
When work as a member in a team, it is always important to keep the same commit style and remove duplicate and inappropriate commit to make history concise and readable.  
It is possible that mistype the comment when commit. If you want to modify it, use the command below:
```
git commit --amend 
git push origin <branch name> --force
```
It is possible that you forget to commit a file. If you want to add the file in last commit, use commands below:
```
git commit --amend --no-edit
git push orgin <branch name> --force
```

## How to remove a commit
First check if the commit to be removed is the latest.
```
git log
```
remove the commit
```
git reset --soft HEAD^
```
If you want to combine 2 commits to 1, the proecss is:  
```
git log
git reset --soft HEAD^
git commit --amend --no-edit
git commit push origin <branch name> -f
```
## `reset --soft HEAD^` vs `reset --hard HEAD^`
`--soft` in commands above means that file will changed from "committed" to "not staged to commit" but changes in files will be kept. Please be attention that don't use `git reset --hard HEAD^` to remove the latest commit and undo the changes in files. In addition, changes in modified files in "not staged for commit" status will also be removed and cannot be recovered.  

Check this link for more details https://stackoverflow.com/questions/927358/how-to-undo-the-most-recent-commits-in-git  

NERVER use `git reset --hard HEAD^` unless you know what you are doing. 
