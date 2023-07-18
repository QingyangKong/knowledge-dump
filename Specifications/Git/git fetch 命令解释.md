## git fetch 解释
`git fetch` will get all commits and branches from remote repository. When clone a repository at first, there are branches like `original/branch1`, `original/branch2`, `orginal/branch3`and so on. These are branches of remote repositories. `git fetch` just simply get all of them to local without "add" all this commits to local working directory. 

## 查看别人的工作
`git fetch` 通常用来 merge 别人的代码前查看历史改动，是一种安全版本的 `git merge`<br><br>
在运行 `git fetch` 以后，如果想要在 merge 前先查看一下别人的 commit，可以使用 `git checkout <remote/branch>` 来创建一个临时 branch。然后可以查看 `<remote/branch>` 的代码。举个例子：<br>
1. clone 一个 repo `git clone <remote>`
2. 别人给这个 remote repo 创建了一个新的 Branch `newbranch`
3. `git fetch <remoteName>` 拉取所有的 branch 到本地
4. `git branch <remoteName>/newbranch` 建立一个临时 branch 来查看。运行这个命令之后，会有提示：
```
You are in 'detached HEAD' state. You can look around, make experimental
changes and commit them, and you can discard any commits you make in this
state without impacting any branches by switching back to a branch.

If you want to create a new branch to retain commits you create, you may
do so (now or later) by using -c with the switch command. 
```
5. 如果要在这个 branch 继续开发工作的话，使用命令 `git checkout -b <remoteName>/newbranch` 新建 branch。

## 通过 `git fetch` 和 `git merge` 安全的 merge 代码
第一步是将 remote repo 的 branch 都 fetch 到 local repo 中，查看代码，然后运行 `git merge <remoteName>/<branchName>`。<br>
**注意**：
- `git merge <remoteName>/<branchName>` 默认是 merge 到当前所在的 branch 中。
- `git merge` 如果参数 `<remoteName>/<branchName>` 缺失，会默认 merge 和当前branch 一致的 remote repo 的 branch。

## git pull
`git pull` is just a shortcut for `git fetch` plus `git merge`. Please check the img below:  

![Image of blockChain](/imgs/fetch&pull.png)