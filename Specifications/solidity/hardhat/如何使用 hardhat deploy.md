
check https://www.npmjs.com/package/hardhat-deploy#installation

backup
1. 安装 deploy 的插件
```
npm install -D hardhat-deploy
```
2. 安装 hardhat-deploy-ethers 依赖

因为 `hardhat-deploy-ethers` 是 `@nomiclabs/hardhat-ethers` 的一个 fork，而且其他的 plugin 可能会有对于 `@nomiclabs/hardhat-ethers` 的依赖，所以最好的安装办法是把 `hardhat-deploy-ethers` 通过 `@nomiclabs/hardhat-ethers` 的名字来安装。

命令如下：
```
npm install --save-dev  @nomiclabs/hardhat-ethers@npm:hardhat-deploy-ethers ethers
```

3. 在 deploy 文件夹中写 deploy 脚本

参考的脚本文件如下：
```
const { getNamedAccounts, deployments } = require("hardhat");

module.exports = async({getNamedAccounts, deployments}) => {
    const { deploy } = deployments;
    const { deployer } = await getNamedAccounts();

    console.log("deployer: ",  deployer)
    await deploy("LinkDegree", {
        from: deployer,
        gaslimit: 4000000,
        args: [],
        log: true,
    })
}
```
4. 运行命令部署
```
yarn hardhat deploy --network goerli
```