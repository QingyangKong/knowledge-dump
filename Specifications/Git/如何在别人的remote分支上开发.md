# 如何在别人的remote分支上开发
请参考这个[答案](https://stackoverflow.com/questions/1783405/how-do-i-check-out-a-remote-git-branch)。

一个 git repo 上的 branch 是由你或者别人去创建的，而 git 是不允许你直接在别人的分支上工作的，所以如果你要使用别人的分支，需要先创建一个自己的 branch，然后在在复制别人的 branch 到自己的 branch 再进行开发。

有一下两种方法来做这件事：

## 使用 git checkout 来复制
1. `git fetch`
2. `git checkout -b <branchName> <remoteName>/<branchName>`
3. 或者不执行第二步，直接使用`git checkout <branchName>`

先通过 `fetch` 获取到 remote repo 的所有branch，第二条命令可以让用户先新建一个branch，然后再将 remote 的 branch 的只读内容写到这个新的 branch 中去，本质上就是创建了一个拥有同样的 commit 的 branch，并且切换到这个 branch。

注意，`git checkout <branchName>` 是切换到一个分支。`git checkout -b <branchName>`是新建一个分支，而这里使用的`git checkout -b <branchName> <remoteName>/<branchNAme>`是在remote repo的分支的基础上，新建一个分支，内容一致。

如果直接用 `git checkout branchName` 也可以直接实现，前提是要在本地新建的分支和remote repo 的分支名字一致，因为 git 会推测出用户的操作，自动补全。

## 使用 git switch 命令
1. `git clone <remoteUrl>`
2. `git switch <branchName>`
3. 或者执行完整命令，`git switch -c <branchName> <remote>/<branchName>`

Switch 命令是在 git 2.23 以后的命令，所以在之前是不能够使用这个命令的。其实这个命令是一个简写，原因和上面的 `git checkout -b <branch> <remoteName>/<branch>`。

## git branch -r
查看所有的分支

## git checkout -b <branchName>
创建一个新的分支。

## git branch -d <branchName>
删除一个分支

## git branch -D <branchName>
强行删除一个分支，可以删除没有 merge 的分支