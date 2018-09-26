# How to modify commits

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

If you want to unstage a staged file:
```
git reset HEAD <file name>
//this will change <file name> from "staged to commit" to "unstaged for commit"
```

## `reset --soft HEAD^` vs `reset --hard HEAD^`
`--soft` in commands above means that file will changed from "committed" to "not staged to commit" but changes in files will be kept. Please be attention that command `git reset --hard HEAD^` will remove the latest commit and undo the changes in files. In addition, changes in modified files in "not staged for commit" status will also be removed and cannot be recovered.  

NERVER use `git reset --hard HEAD^` unless you know what you are doing. 

Check this link for more details https://stackoverflow.com/questions/927358/how-to-undo-the-most-recent-commits-in-git  
