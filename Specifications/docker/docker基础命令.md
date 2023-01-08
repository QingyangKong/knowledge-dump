# Docker 基础命令
## 运行
- docker build <dirName>：build an image from dockerfile, 这个主要是在生成一个 docker 镜像的时候使用的。写好 Dockerfile 以后需要使用命令 docker build 的生成 docker image
- docker run <image name>: 运行一个 docker image
- docker run it <image name>: it 就是 interactive，可交互的
- docker compose：运行 docker 中的 yaml 文件
- docker compose up：create and start containers

## 下载
- docker pull <orgName/imageName>：从 registry 中拉取一个 image
- docker image ls：用来查看现有的 docker image

## list
- docker container ls：展示所有的运行的 container
- docker container ls -a：展示所有的运行的 container，包括已经停止的
- docker ps：列出现在所有的 running container
- docker ps -a -q：列出所有的 container，包括已经停止的 container
- docker images -q：-q 意思是 quite，只显示 id，不显示其他信息
- docker images -a -q：显示所有的 image 的 ID

## 删除
- docker image prune：删除所有的 dangling images，有的时候节点运行失败，可以全部删除，然后重新 docker compose up。
- docker rmi <image id>：删除 image id 的 image。
- docker rmi $(docker images -q)：删除所有的 image
- docker container rm <containerId>：删除 container。
- docker stop $(docker ps -a -q)：停止所有的 container
- docker rm $(docker ps -a -q)：删除所有的 container

在 Chainlink 的节点中使用 docker，这个docker 会包含2个instance，一个是 Chainlink 的节点，另一个是 postgreSQL。