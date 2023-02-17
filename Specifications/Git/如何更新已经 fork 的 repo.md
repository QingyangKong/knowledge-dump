这里是方法：https://github.com/selfteaching/the-craft-of-selfteaching/issues/67 

for backup
1. 添加远程 repo 的路径
```
git remote add upstream <xxx.git>
git remote -v 
```
查看是否添加成功，如果添加成功的话会显示：
```
origin	<your github's repo> (fetch)
origin	<your github's repo> (push)
upstream	<official github's repo> (fetch)
upstream	<official github's repo> (push)
```
2. 从远程 repo 中 fetch
```
git fetch upstream
```
3. 切换到主分支

使用命令 `git checkout master` 或者 `git checkout main` 切换到主要分支

4. merge 远程 repo
```
git merge upstream/master
```
5. push 到自己 GitHub 中的 repo
```
git push
```