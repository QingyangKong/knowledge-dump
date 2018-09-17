`git fetch` will help you to fetch all branches from remote.  

After fetch, all branches are fetched from remote to local.  

Please be attention: `origin/xx` is branch in remote so that you cannot change the remote branch in local and push back to original. 

In order to convert remote to local, add remote to local with commnad `git checkout -b <branch name> <remote branch name>`. 

Commands for example:
```
git fetch
git checkout -b feature-to-add origin/feature-to-add
```
