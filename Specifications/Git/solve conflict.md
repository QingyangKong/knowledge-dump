## What is conflict
It happens when a file was modified by more than one person and conflict happens very often.   

Before you push to the master or develop of remote repository, you have to pull from remote repository and then rebase or merge remote changes to your branch. If the same file was modified by others, there would be something like this in code: 

## Solve a conflict

### merge new changes in original branch
```
git fetch original/branch
git merge original/branch
```
### choose changes in codes
In the current local branch, codes would something like this:
```
<<<<<<< HEAD
Tree123:  
=======
Tree34567:  
>>>>>>> origin/master
```

`git status` to show current file system status.
```
You have unmerged paths.
  (fix conflicts and run "git commit")

Unmerged paths:
  (use "git add <file>..." to mark resolution)

	both modified:   README.md
```
You need to choose one of this changes and this process is called solving conflict. 

Choose current local one and then add it by `git add <filepath>`
```
Tree123:  
```

Use `git status` to check status.
```
Your branch and 'origin/master' have diverged,
and have 1 and 1 different commit each, respectively.
  (use "git pull" to merge the remote branch into yours)
All conflicts fixed but you are still merging.
  (use "git commit" to conclude merge)
```
### commit modified changes
Then use `git commit -m 'solve conflict'` to commit it, then the conflict is considered as solved.


### push to remote repository
`git push origin branchName`