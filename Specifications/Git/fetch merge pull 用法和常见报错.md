## fetch merge pull 用法和常见报错
## git fetch 基本用法
`git fetch` 会将 remote repo 的所有 commit 和 branch 拉取到本地，但是不会把这些 commit 加到 local directory。和 `git clone` 一样，在执行 `git fetch` 命令以后，所有的分支都会被拉取到本地，比如说`origin/branch1`, `origin/branch2`, `origin/branch3`，这些都是远程repo 的分支。 

在执行 `git fetch` 拉取代码以后，还需要将代码进行 `merge`，将remote branch 合并到当前的working directory 中：
```
git fetch
git merge 
```
## 可能的错误
注意在执行 `git merge` 的时候需要保证 local branch 和 remote branch 有连接，比如说在 local，branch 的名字是 test，但是在 remote repo 中，并没有一个名字叫 test 的分支，那么在运行 `git merge` 的时候，就会报错：`fatal: No remote for the current branch.`

这是因为 `git merge` 需要一个参数来确定要 merge 哪一个分支，完成命令是：`git merge remote/name`。

在省略参数直接运行 `git merge`的时候，`git merge` 会自动将和当前的 local branch 有追踪关系的 remote branch 中作为参数，比如说当前的 branch 是 test，运行 `git merge` 就是 `git merge remote/test`。

注意 branch 名字相同并不能保证local branch 和 remote branch 有追踪关系！只是大部分情况下，名字一致的 branch 都是在 git clone 的时候创建的，所以当时自动建立了追踪关系。比如说如果执行以下操作会有问题：
```
// 在 remote repo 中只有 main 一个分支，对其进行 clone
git clone <repourl>

// 新建 test 分支，删除 main 分支
git checkout -b test 
git branch -d main

// 重建并且切换会 main 分支
git checkout -b main 

// 再次 merge
git merge
```
这时会报和之前一样的错误：`fatal: No remote for the current branch.`

## 解决方式
1. 使用完整命令将 remote/branch 合并到当前的名字不一样的 branch 上。
```
git merge remote/branchname
```
2. 为了保证一致性，新建一个名字相同的 branch，设置追踪关系，然后 merge
```
// 新建 branch，同时设置追踪关系
git checkout -b branch-name origin/branch-name
git merge
```

## git fech 和 git pull 的区别
`git pull` 除了将 commit 从 remote repo 中拉取以后，还会将 remote branch 合并到当前的 branch 上，就相当于执行了两个命令：
```
git fetch
git merge
```
同样要注意的是，保证当前的 branch 和 remote repo 的 branch 有追踪关系，否则会遇到错误
```
git pull
There is no tracking information for the current branch.
Please specify which branch you want to merge with.
See git-pull(1) for details.

    git pull <remote> <branch>

If you wish to set tracking information for this branch you can do so with:

    git branch --set-upstream-to=origin/<branch> test1
```
解决的方法和上述 `git merge` 错误解决方式一致，就是显式指出要 pull 的是哪一个 remote branch，或者新建一个有追踪关系的 branch。


## 关于文件三个状态的图
![Image of blockChain](/imgs/fetch&pull.png)