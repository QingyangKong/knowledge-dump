# Git Basics
## Content
* Basics
  * File status
* Examples
  * Initiate a git repo
  * Check file status
  * Add File - change into stage
  * Commit files
  * Check current signature
  * Remove file from Git repo
## 1. Baiscs:
Git is actually a cache containing tracked files information in working directory, so every change happens on tracked files will be recorded for version control.  
There are 3 places: working directory, stage and git repo.  
Correspondingly there are 3 status of a file: in working directory: tracked, untracked.  

### 1.1. File status:  
Changes in working directory, add to stage, and commit changes.  

| action in working dir | file status | command | file status | command | file status | File in Git Repo |
| --- | --- | --- | --- | --- | --- | --- |
| add new files | untracked | git add | staged | git commit |	unmodified | added |
| modify files that are added | unmodified | git add | staged | git commit |	unmodified | files updated |
| delete files that are added | unmodified | git add | staged | git commit |	untracked | files deleted |  

A change must be added into stage first and then committed into the Git repository, and please be notice the file. Please check the lifecycle of the file in git in graph below:<br>

![alt text](./../../imgs/git-file-lifecycle.png "Title")

## 2. Examples
### 2.1 Initiate a git repo
`mkdir GitTestDir`  
`git init`  
A file `.git` created in current working directory, `git init` is to create a cache in the directory to make version controlled possible from now on.  

### 2.2 Check status
`git status`  
```
On branch master
Initial commit
nothing to commit (create/copy files and use "git add" to track)
```  
This command is to check that status of the git repository. Because there is nothing in working directory, it shows "nothing to commit" for now.  

### 2.3 Add a file into stage
Create a text file `firstFile.txt` in working directory, and check the file's current status with commands below:<br>

`vim firstFile.txt`  
`git status`  

It shows `nothing added to commit but untracked files present`.<br>
```
On branch master
Initial commit
Untracked files:
  (use "git add <file>..." to include in what will be committed)
        firstFile.txt
nothing added to commit but untracked files present (use "git add" to track)
```  

It means the file is not staged and it will not be committed when you use `git commit fileName -m 'xx'`.  

We need add this file into stage with commnd below: <br>
`git add firstFile.txt`  
then check the file status: <br>
`git status`  
```
On branch master
Initial commit
Changes to be committed:
  (use "git rm --cached <file>..." to unstage)
        new file:   firstFile.txt
```
Now, `firstFile.txt` has already been added into stage, and it is displayed under "Changes to be committed" which means the file can be committed now.  

modify the file firstFilat this time point and then check status again.<br>
`vim firstFile.txt`  
`git status`  
As we can see, new modifications are not automatically added into the stage, the change is under `Changes not staged for commit:` which means this file modificatoin is not going to be committed in next commit.
```
On branch master

Initial commit

Changes to be committed:
  (use "git rm --cached <file>..." to unstage)
        new file:   firstFile.txt
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)
        modified:   firstFile.txt
```
  
### 2.4 Commit files
Add a new change(firstFile) into stage and then commit it.  
`working directory clean` means no change detected in working directory and nothing in stage. <br>
`git add firstFile.txt`  
`git commit firstFile.txt -m 'check in the first file'`  
`git status`  
```
On branch master
nothing to commit, working directory clean
```

### 2.5 Check current signature
`git ls-tree master`  
check current signature of git repo and result is shown below:  
`100644 blob eca59ec8d25ead4c2815a431f40fa3df9d84d428    firstFile.txt`  

Tip:  
When use command `ls`, files shown are just files in working directory but not in git repo(although the 2 can be the same, they are different in most case).  
To check files in git repo, use `git ls-tree {branch name}`

### 2.6 remove files from stage
If you don't want file `firstFile.txt` to be exposed to others in a git repo, you can delete it from git repo and remove it from stage.  
`git rm --cache  firstFile.txt`  
`git status`  
```
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)
        deleted:    firstFile.txt
Untracked files:
  (use "git add <file>..." to include in what will be committed)
        firstFile.txt
```
First I need to remove it from my git repo and then commit this change into repo.  
This delete change is already pushed to stage and file is untracked, but if I don't want to see the file in git repo, I need to commit this change.  
<br>  
`git commit -m 'delete firstFile'`  
`git status`  
```
On branch master
Untracked files:
  (use "git add <file>..." to include in what will be committed)
        firstFile.txt
nothing added to commit but untracked files present (use "git add" to track)
```
This change is committed and deleted from stage, and `firstFile.txt` becomes an untracked file.  If I do `git ls-tree master`, nothing shown.


### 2.7 Make a project a git repo and publish in github  
`git add .`  
`git commit -m 'project setup'`  
`git remote add origin {url of repository}`  
`git push origin master`  
Add and commit everything into git repository and commit in. Define remote repository to push the repo. Push the all files to remote repository in master branch.  
### 2.8 Add or remove a remote repo
`git remote -v`  
List all remote servers for the directory. `-v` means verbose.
```
origin  https://github.com/QingyangKong/knowledge-dump.git (fetch)
origin  https://github.com/QingyangKong/knowledge-dump.git (push)
```
`git remote add fake_repo 'fake url'`  
add a new remote server.  
`git remote -v`  
```
fake_repo       fake url (fetch)
fake_repo       fake url (push)
origin  https://github.com/QingyangKong/knowledge-dump.git (fetch)
origin  https://github.com/QingyangKong/knowledge-dump.git (push)
```
First show the current remtoe repos. Add an another repo and then check again. `-v` means verbose.

### 2.9 Clone a project from github 
`git clone {url}`  
This is used when a user copies an existing project in github to local. Remote is not required to be set when using `git clone`.  

### 2.10 Pull files from remtoe repository
`git remote add origin 'https://github.com/QingyangKong/knowledge-dump.git'`  
`git pull origin master`  
```
remote: Counting objects: 4, done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 4 (delta 2), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (4/4), done.
From https://github.com/QingyangKong/knowledge-dump
 * branch            master     -> FETCH_HEAD
   d015714..66ab6fe  master     -> origin/master
Updating 1a3d5ed..66ab6fe
Fast-forward
 git/Git Basics.md | 25 +++++++++++++++++++++++--
 1 file changed, 23 insertions(+), 2 deletions(-)
```
`git status`  
```
On branch master
Your branch is up-to-date with 'origin/master'.
nothing to commit, working directory clean
```
This is used to get new changes in remtoe github repository and marge it with the local repo.

### 2.11 Fetch and merge files from repository
`git remote add origin 'https://github.com/QingyangKong/knowledge-dump.git'`      
`git fetch`  
```
remote: Counting objects: 4, done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 4 (delta 2), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (4/4), done.
From https://github.com/QingyangKong/knowledge-dump
   ef5e0e8..1a3d5ed  master     -> origin/master
```
`git status`  
```
On branch master
Your branch is behind 'origin/master' by 2 commits, and can be fast-forwarded.
  (use "git pull" to update your local branch)
nothing to commit, working directory clean
```
`git merge`  
```
Updating ef5e0e8..1a3d5ed
Fast-forward
 git/Git Basics.md | 18 +++---------------
 1 file changed, 3 insertions(+), 15 deletions(-)
```
The difference between `git pull` is that `git fetch` only get all changes in remote repo and do not merge it into local. User must run `git merge` to merge all changes into local. In simple, `git pull` = `git fetch` + `git merge`.
### 2.12 push files into remote repo
`git push -u origin master`  
`git push`  
This is used to push all new changes into a remote repo in github. `-u` is upstream to set a upstream, and it is not required to be set every time. `git push` can be use without parameters.  


## 3. .gitignore file
Usually when we debug a project, there will be a lot of configuration file, dependencies, and temporary files including log information, debug files. We do not always want to commit these files into repository, because conf should not be exposed, dependencies can be downloaded by other developer in their environment.

In order to ignore files we don't expect to commit all the time, we can write them in settings.

### 3.1 Where to set?  

1. .git/info/exclude  
2. .gitignore  

### 3.2 When it take effect?  

The setting takes place for new added files when use 'git add'.   
If the files already in git cache, they won't be removed after you change ignore settings.  
`git rm -r --cahched .` to remove all files  
`git add .` add all files(.gitignore will be applied at this time point)  
`git commit -m 'make .gitignore working'` commit changes into cache.  

If all files in a directory are set as ignored in the .gitignore, this directory will be ignored. Git will not add an empty directory.  
