# How to contribute
As a developer of a orgnization, you can directly complete a development and push changes into a new branch of the existing repo. The process is shown below:

```
git clone {url}  //first clone
//developement      //complete development in your favorite IDE
git branch <branchName>   //create a new branch in case master branch is messed up
git checkout <brancName>  //switch to the newly created branch
git add .                 //add new changes (if any)
git commit <files> -m 'commnet' //commit changes in the branch
git branch          //check branches of the exsiting project
git remote -v             //check if remote correct
git push <remote name> <branchname>   //push changes to branch in github
//create pull request for newly created branch
//merge and remove the branch
```

Other tips:
command to create a new branch:  
```
git branch <branch name>
```
command to push new changes into new branch. Do not add `-m` 
```
git push <remote name> <branch name>
```
