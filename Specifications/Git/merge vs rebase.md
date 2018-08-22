# Merge vs Rebase
When you decide new feature, you probably create a new branch in the git repository and then do develop in the the feature. After the development is done, the new change can be merged to the master or develop.  
## Merge
If the master of develop branch is not changed in the time period when you are developing your feature, you can merge your braach directly into the master or develop.  
```
//switch to master branch
git checkout master
//merge the branch you develop into master
git merge <your branch name>
```

## Rebase
While merge is straight-forward to use and understand, it has some problems.  

It may mess up your git logs. Assume that the project is pretty hot and changes are committed to the master branch by other developers, you cannot just finish your development and merge your branch into master. You have to first get changes on master happens after you create your branch and add them to your branch and then merge your branch to master.  

In the scenario, `rebase` is useful. Let take an example:
### 1. Initiate a new git repo, and create a file and commit it and then check log.
```shell
git init
touch firstfile
git add .
git commit -m 'add first file'
git log
```
result:
```shell
commit 580d5f9c5c2c10362570d46be4d602a02580e450
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:12:24 2018 +0800

    add firstFile

```

### 2. Create a new branch and commit a change in the branch
```shell
git branch anotherBranch
git checkout anotherBranch
touch second file
git add .
git commit -m 'add second file'
git log
```
Result:
```shell
commit 83c08a30cd2e45c77efc0b54f83e56bf79ff607c
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:13:08 2018 +0800

    add second file

commit 580d5f9c5c2c10362570d46be4d602a02580e450
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:12:24 2018 +0800

    add firstFile

```

### 3. Switch back to the master and do some changes and then commit these changes.
```shell
git checkout master
vim firstFile
//change something
git add .
git commit -m 'modify the first file'
git log
```
result:
```
commit b90fe05de89147d370c5cb2641f9f49a4fc88eb1
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:14:06 2018 +0800

    modify the first file

commit 580d5f9c5c2c10362570d46be4d602a02580e450
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:12:24 2018 +0800

    add firstFile
```

### 4. switch to yoru branch and rebase commits 
```shell
git checkout anotherBranch
git rebase master
git log
```
result:
```shell
commit 83c08a30cd2e45c77efc0b54f83e56bf79ff607c
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:13:08 2018 +0800

    add second file

commit b90fe05de89147d370c5cb2641f9f49a4fc88eb1
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:14:06 2018 +0800

    modify the first file

commit 580d5f9c5c2c10362570d46be4d602a02580e450
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:12:24 2018 +0800

    add firstFile

```
### 5. Or you can switch to your branch to merge it
```shell
git checkout anotherBranch
git merge master
git log
```
result:
```
commit 40e8153dca598f7e7c6793e496cb1e69f40c52cf
Merge: d4acac7 f839fc9
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 17:17:09 2018 +0800

    Merge branch 'master' into anotherBranch
    
    merge the master
    
commit b90fe05de89147d370c5cb2641f9f49a4fc88eb1
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:14:06 2018 +0800

    modify the first file

commit 83c08a30cd2e45c77efc0b54f83e56bf79ff607c
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:13:08 2018 +0800

    add second file

commit 580d5f9c5c2c10362570d46be4d602a02580e450
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:12:24 2018 +0800

    add firstFile

```

Compare the results in 4 and 5, merge will insert commits in by timeline and sometimes a lot of changes wil mess up your branch. In addition, merge will also leave a merge record as a commit.  
In rebase, all of your commits will be in the tip of all commits in master and not merge history will be recorded. 

### 6. merge changes to from your branch to master
```shell
git checkout master
git merge anoherBranch
git log
```
result:
```
commit 83c08a30cd2e45c77efc0b54f83e56bf79ff607c
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:13:08 2018 +0800

    add second file

commit b90fe05de89147d370c5cb2641f9f49a4fc88eb1
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:14:06 2018 +0800

    modify the first file

commit 580d5f9c5c2c10362570d46be4d602a02580e450
Author: Qingyangkong <frankkongnj@yahoo.com>
Date:   Wed Aug 22 16:12:24 2018 +0800

    add firstFile
```
After the feature is developed or bug is fixed, you merge your branch to master and commits log will not be messed up. Very cool.
