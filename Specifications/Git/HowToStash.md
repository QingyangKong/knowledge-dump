## Switch to another branch with stash saved
When working with a branch for a new features or issue fix, you are required to fix another bug which is in first priority. In the scenario, you have to switch to another branch to fix new issues but maybe you don't want to commit changes in current branch.  

Now the requirement is:   
you want to keep the modified file not committed.  
you want to discard these changes in new branch.

commands:
```
git branch                  //make sure you are in the correct branch

git stash save              //save changes in stash

git checkout <branch name>  //switch to new branch

// do the work, now modified files are in stash

git checkout <old branch>   //switch back to the old branch

git stash pop               //pop changes in the stash
```

## Commited files are attched with old branch
If you commit a file in the old branch, file is committed, you will not see changes when you switch to a new branch. These commited files are attached in old branch unless you other branch pulls from the old branch.  

Please be attention, changes in stash is not branch specific, all changes are saved in stash by Id. That means changes on the top of stash will be poped out when you do `git stash pop` no matter whether changes are pushed in the branch.  

Use `git stash list` to see all changes in the stash.  
Use `git stash pop <Id>` to push specific changes in the stash. Like a stack, latest changes are always on the top with smallest id.   

Here is an example:
```
git stash save
git checkout <another branch>

//do some changes and save to stash
git stash save

git checkout develop

git stash list
//gonna be something like this:
//stash@{0}: WIP on master: f7d7220 Update HowToContribute.md
//stash@{1}: WIP on master: f7d7220 Update HowToContribute.md

git stash pop stash@{1}
```

## Meaning of checkout
You clone a project from a remote repository. `checkout` means that you want to check out code in original branch to your local and edit them, so whatever the local codes are like for now, they will be identical as original branch.  
See commands below to see the usages:
```
git clone <url>
//do some edit on file A.
//but I forget what changes are made by myself, and I want to revert and discard all changes
git checkout <file A>
//now file A is the same as before.
```
