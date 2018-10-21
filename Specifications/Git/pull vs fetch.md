## git fetch
`git fetch` will get all commits and branches from remote repository. When clone a repository at first, there are branches like `original/branch1`, `original/branch2`, `orginal/branch3`and so on. These are branches of remote repositories. `git fetch` just simply get all of them to local.  


## git checkout vs git merge
`original/branch1` is different from `branch1` in your local. To modify codes in local, you need first get commits to local. Because `origin/xx` is branch that used to tranck remote branch in remote so that you cannot change it and push back to remote. 

In order to modify remote branchese, you have to create a new branch `branch1` from `original/branch1` by command `git checkout -b <branch name> <remote branch name>`.  

Commands for example:
```
git fetch
git checkout -b feature-to-add origin/feature-to-add
```
Above exmaple shows `git fetch` get all info from remote and `git checkout` help to "check out" commits in original branches to local. 

While `git merge` does almost the same thing as `git checkout` in the case, the important difference is that `git merge` will help users to solve conflicts. In `checkout`, git will totally overwrite files in local with original files while `merge` will compare commits in original branch with local files. 

For local and remote branches sync, `git merge` should be used.
```
git fetch 
git checkout <branchName>
git merge original/<branchName>
```

## git pull
`git pull` is just a shortcut for `git fetch` plus `git merge`. Please check the img below:  

![Image of blockChain](/imgs/fetch&pull.png)