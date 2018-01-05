In Linux, users usually do not super user permissions so that they are not able to write packages into NPM global folder. In this scenario, the user is supposed to change the NPM default global folder.  

```
npm config set prefix=$HOME/.node_modules_global
cd ~
mkdir .node_modules_global
npm config set prefix=$HOME/.node_modules_global
npm install npm -g
export PATH="$HOME/.node_modules_global/bin:$PATH"	
source ~/.bashrc
```
