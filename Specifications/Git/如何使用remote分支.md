# 如何使用remote分支
请参考这个[答案](https://stackoverflow.com/questions/1783405/how-do-i-check-out-a-remote-git-branch)。

一个 git repo 上的 branch 是由你或者别人去创建的，而 git 是不允许你直接在别人的分支上工作的，所以如果你要使用别人的分支，需要先创建一个自己的 branch，然后在在复制别人的 branch 到自己的 branch 再进行开发。

有一下两种方法来做这件事：

## 使用 git switch 命令
1. `git clone <remoteUrl>`
2. `git swith <branchName>`

Switch 命令是在 git 2.23 以后的命令，所以在之前是不能够使用这个命令的。其实这个命令是一个简写。

## 使用 git checkout 来复制
1. `git checkout -b <branchName> <remoteName>/<branchName>`

这两条命令可以让用户先新建一个branch，然后再将 remote 的 branch 的只读内容写到这个新的 branch 中去。

## git branch -r
这个命令用来查看所有的分支

## git checkout -b <branchName>
这个命令是创建和一个新的分支，在分支中

## git branch -d <branchName>
这个命令是删除一个分支

## git branch -D <branchName>
强行删除一个分支，可以删除没有 merge 的分支