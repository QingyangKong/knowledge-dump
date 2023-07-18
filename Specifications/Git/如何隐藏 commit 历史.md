# 如何隐藏 commit 历史

## 问题
有的时候在 repo 公开之前，因为所有的代码以及 commit 记录都会公布出来，所以可能有一些在 commit 历史中的敏感数据要被删掉。

## 解决方法

把所有 commit squash 成为一个 commit：<br>
1. merge 最新的代码：通过命令 `git fetch` 和 `git merge <remoteName>/<branchName>`实现。
2. 给现在的 commit history 做一个 backup（不是必须的）`git checkout -b backup-main-full-history`。切换回之前的 branch：`git checkout main`。
3. 通过命令 `git reset $(git commit-tree HEAD^{tree} -m "Single Commit")` 将所有的 commit 合并成为一个 commit。
4. 运行 `git log` 来查看是否所有的 commit 都消失了。
5. 运行 `git push -f origin main` 将 remote repo 的 commit 信息全部抹除掉然后将现在 local repo 中的所有 commit（此时只有一个）push 到 remote repo。
