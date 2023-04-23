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
这个命令会将 fetch 的代码 merge 到当前的分支，如果不需要 merge 到主分支，可以 `git branch feature-1` 新建 branch，然后`git checkout feature-1` 切换到这个 branch，然后运行 `git merge upstream/master` 将 upstream 的分支 merge 到该分支。

注意如果是在 checkout 以后 merge，那么就是直接 merge 进该 checkout 的分支。通常来说，在给已有项目新增 feature 的时候都会新建一个新的分支，然后在新的分支中添加代码，再提交给 GitHub repo，再通过 PR merge 进 main，由其他开发者 review 之后 merge 进 main。

5. push 到自己 GitHub 中的 repo
```
git push orgin
```
同样的，一般是将某个 branch push 进入 GitHub repo，而非 main 分支直接 push，因为这样更加安全。

会 push 进当前所在的分支，即 `git checkout feature-1` 切换到新的分支，然后 `git push` 是会 push 进 GitHub repo 中的 feature-1 分支。