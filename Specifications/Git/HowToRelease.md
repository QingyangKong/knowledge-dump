## To make a new release
- Step 1:
```
/* first check if all version information is updated for upcoming version
 * for example, if the project is using gradle, check if the version number in build is updated. 
 */
 ```
 
- Step 2:
```
//create a new tag
//-a means annotated and will provide more details of the release so it is always good to add '-a' when create a new tag
git tag -a v1.0
```

- Step 3:
```
//check all tags of the project
git tag
```

- Step 4:
```
//make a tag in the server.
git push origin v1.0
```

- Step 5:
```
//push all tags you created to remote server.
git push origin --tags
```
## To retag
- Step 1
```
git ls-remote --tags
```

- Step 2
```
//delete local tag
git tag -d V_1_0_1
```

- Step 3
```
//push tag deletion to remote
git push origin :refs/tags/V_1_0_1
```

- Step 4
```
//tag local branch again
git tag V_1_0_1
```

- Step 5
```
//push tag to remote
git push origin tag V_1_0_1
```
