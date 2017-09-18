## Git Basics
### 1. How it works:
Git is actually a cache contains tracked files information in working directory, so every change happens on tracked files will be recorded for version control.  
There are 3 places: working directory, stage and git repo.  
There are 2 status of a file in working directory: tracked, untracked.  

#### 1.1. General steps to commit changes:  
Changes in working directory, add to stage, and commit changes.  

| working diorectory | file status | add to stage | file status | commit | git repo |
| --- | --- | --- | --- | --- | --- |
| add new files | untracked | git add | staged untracked | git commit |	files added |
| modify files | tracked modified | git add | staged modifed | git commit |	files updated |
| delete files | tracked deleted | git add | staged deleted | git commit |	files deleted |  

A change must be added into stage first and then committed into the Git repository.  

### 2. Examples
#### 2.1 Make a working directory a git repo
`mkdir GitTestDir`  
`git init`  
There is a foler `.git` created in working directory, git is able to be used to do version control from now on.  

#### 2.2 Check status
`git status`  
```
On branch master

Initial commit

nothing to commit (create/copy files and use "git add" to track)
```  
This command is to check that status of the git repository. Because there is nothing in working directory, it shows "nothing to commit" for now.  

#### 2.3 Add a file into tracked
`vim firstFile.txt`  
`git status`  
```
On branch master

Initial commit

Untracked files:
  (use "git add <file>..." to include in what will be committed)

        firstFile.txt

nothing added to commit but untracked files present (use "git add" to track)
```  
Create a text file in working directory, and check current status.  
This new added file is detected but not added into stage yet, so it shows `nothing added to commit but untracked files present` which means this file won't be committed.  
<br>  
`git add firstFile.txt`  
`git status`  
```
On branch master

Initial commit

Changes to be committed:
  (use "git rm --cached <file>..." to unstage)

        new file:   firstFile.txt
```
Add `firstFile.txt` into stage and check git status again.  
Because this file has already been added into stage, it shows under "Changes to be committed" which means the file can be committed now.  
<br>  
`vim firstFile.txt`  
`git status`  
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
modify the file firstFilat this time point and then ccheck status again.  
New modifications are not automatically added into the stage, this change is under `Changes not staged for commit:` which means this file is not going to be committed in next commit.  
<br>  
  
#### 2.4 Commit files
`git add firstFile.txt`  
`git commit firstFile.txt -m 'check in the first file'`  
`git status`  
```
On branch master
nothing to commit, working directory clean
```
Add a new change(firstFile) into stage and then commit it.  
`working directory clean` means no change detected in working directory and nothing in stage. 

#### 2.5 Check current files in Git repo
`git ls-tree master`  
check current files in git repo and result is shown below:  
`100644 blob eca59ec8d25ead4c2815a431f40fa3df9d84d428    firstFile.txt`  

Tip:  
When use command `ls`, files shown are just files in working directory but not in git repo(although the 2 can be the same, they are different in most case).  
To check files in git repo, use `git ls-tree {branch name}`

#### 2.6 Untrack files in git repo
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
If I don't want file `firstFile.txt` to be exposed to others, I can delete it from my git repo and stop tracking the file anymore.  
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


#### 2.7 Make a project a git repo and publish in github  
`git add .`  
`git commit -m 'project setup'`  
`git remote add origin {url of repository}`  
`git push origin master`  
Add and commit everything into git repository and commit in. Define remote repository to push the repo. Push the all files to remote repository in master branch.  
#### 2.8 Add or remove a remote repo
`git remote -v`  
```
origin  https://github.com/QingyangKong/knowledge-dump.git (fetch)
origin  https://github.com/QingyangKong/knowledge-dump.git (push)
```
`git remote add fake_repo 'fake url'`  
`git remote`  
```
fake_repo       fake url (fetch)
fake_repo       fake url (push)
origin  https://github.com/QingyangKong/knowledge-dump.git (fetch)
origin  https://github.com/QingyangKong/knowledge-dump.git (push)
```
First show the current remtoe repos. Add an another repo and then check again. `-v` means verbose.

#### 2.8 Clone a project from github 
`git clone {url}`  
This is used when a user copies an existing project in github to local.  

#### 2.9 Pull files from remtoe repository
`git pull origin master`  
This is used to get new changes in remtoe github repository and push to the local repo.

### 3. .gitignore file
Usually when we debug a project, there will be a lot of configuration file, dependencies, and temporary files including log information, debug files. We do not always want to commit these files into repository, because conf should not be exposed, dependencies can be downloaded by other developer in their environment.

In order to ignore files we don't expect to commit all the time, we can write them in settings.

Where to set?  

1. .git/info/exclude  
2. .gitignore  

When it take effect?  

The setting takes place for new added files when use 'git add'.   
If the files already in git cache, they won't be removed after you change ignore settings.  
`git rm -r --cahched .` to remove all files  
`git add .` add all files(.gitignore will be applied at this time point)  
`git commit -m 'make .gitignore working'` commit changes into cache.  

If all files in a directory are set as ignored in the .gitignore, this directory will be ignored. Git will not add an empty directory.  


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
