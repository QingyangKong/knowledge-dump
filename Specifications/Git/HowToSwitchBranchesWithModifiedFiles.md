## Switch to another branch with stash saved
When you are working with a branch to add a feature or fix a issue. Your boss asks you to fix anoher issue, and boss's request is always in first priority. You need to switch to another branch to fix new issues but you don't want to commit current branch's commits.   
Now the requirement is:   
you want to keep the modified file still modified not committed.  
you want to discard these changes in new branch.

commands:
```
git branch  //make sure you are in the correct branch

git stash save //save changes in stash

git checkout <branch name> //switch to new branch

// do the work, now modified files are in stash

git checkout <old branch> //switch back to the old branch

git stash pop //pop changes in the stash
```

## Commited files are attched with old branch
If you commit a file in the old branch, file is committed, you will not see changes when you switch to a new branch. These commited files are attached in olf branch unless you other branch pulls from olf branch.

commands:
```
//modify some files
git status
git add <file name>
git commit -m 'comment'
git checkout <another branch>
//you will not see changes in 
```

## Meaning of checkout
You clone a project from a remote repository. `checkout` means that you want to check out code in source you downloaded and edit in the local, so whatever the local codes are like, code will be identical as codes cloned.  
See commands below to see the usages:
```
git clone <url>
//do some edit on file A.
//but I forget what changes are made by myself, and I want to revert and discard all changes
git checkout <file A>
//now file A is the same as before.
```
