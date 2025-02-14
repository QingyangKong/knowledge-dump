# 如何在别人的remote分支上开发
请参考这个[答案](https://stackoverflow.com/questions/1783405/how-do-i-check-out-a-remote-git-branch)。
关于 fetch，merge和pull请参考这个[文件](./fetch%20merge%20pull%20用法和常见报错.md)。

一个 git repo 上的 branch 是由你或者别人去创建的，而 git 是不允许你直接在别人的分支上工作的，所以如果你要使用别人的分支，需要先创建一个自己的 branch，然后在在复制别人的 branch 到自己的 branch 再进行开发。

## 如何基于别人的 branch 继续开发
作为是一种安全版本的 `git pull`，`git fetch` 通常用来 merge 别人的代码前查看历史改动。<br><br>
在运行 `git fetch` 以后，如果想要在 merge 前先查看一下别人的 commit，可以使用 `git checkout <remote/branch>` 来创建一个临时 branch。然后可以查看 `<remote/branch>` 的代码。举个例子：<br>
1. clone 一个 repo `git clone <remote>`，remote 包含别人的分支 `new-branch`
3. `git fetch <remote-name>` 拉取所有的 branch 到本地
4. `git branch <remote-name>/new-branch` 切换到别人的 branch 来查看。运行这个命令之后，会有提示：
```
You are in 'detached HEAD' state. You can look around, make experimental
changes and commit them, and you can discard any commits you make in this
state without impacting any branches by switching back to a branch.

If you want to create a new branch to retain commits you create, you may
do so (now or later) by using -c with the switch command. 
```
5. git 不允许直接在remote branch 上修改，如果要在这个 branch 继续开发工作的话，使用命令 `git checkout -b new-branch <remote-name>/new-branch` 新建 branch。

## 也可以使用 git switch 命令
上述命令中的 `git checkout` 可以被 `git switch` 替换。
1. `git clone <remote-name>`
2. `git switch -c <branchName> <remote>/<branchName>`

Switch 命令是在 git 2.23 以后的命令，所以在之前是不能够使用这个命令的。其实这个命令是一个简写，原因和上面的 `git checkout -b <branch> <remoteName>/<branch>`。


## branch 和 checkout 的一些命令
- `git branch -r`： 查看所有的分支
- `git checkout -b <branch-name>`：创建一个新的分支。
- `git branch -d <branchName>`：删除一个分支
- `git branch -D <branchName>`：强行删除一个分支，可以删除没有 merge 的分支