在一些场景中，需要在一个repo中保持多个branch。

- 比如说在merge到master之前需要其他的reviewer来审核，需要把commit写入到一个暂时的branch。
- 或者在教学repo中可以通过多个branch来代表不同时期的代码状态。

命令如下：<br>
`git branch "branch_name"` 新建一个 branch<br>
如果是刚通过 `git init` 新建的 git 文件夹，会出现错误 `fatal: not a valid object name: 'master'`，这是由于在这个新建文件夹中没有命名初始的 branch name。<br>

`git checkout "branch_name"`切换到新建的branch<br>
checkout到新的branch，以后在这个新的branch中做相应的改动。也可以在当前的branch中做修改，然后checkout到新的branch，进行add和commit<br>

`git add .` add change <br>
切换到新的branch，做完改动，然后add改动。也可以在当前的branch中add改动，然后checkout到新的branch再commit<br>

`git commit -m 'comment'` commit change<br>
需要checkout到新的branch再commit，否则会commit到当前的branch上

`git push "remote url" "branch name"` 最后push到remote repo，然后再pull request到main或者master的branch上。

如果是从remote pull，然后在本地的直接进行merge，就使用以下命令：<br>
`git checkout master`<br>
`git merge "branch name"`