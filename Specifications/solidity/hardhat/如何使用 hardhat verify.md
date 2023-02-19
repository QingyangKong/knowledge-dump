# 使用 hardhat 来 verify
check https://hardhat.org/hardhat-runner/plugins/nomiclabs-hardhat-etherscan

backup
1. 首先安装 verify 插件
```
npm install --save-dev @nomiclabs/hardhat-etherscan
```

2. 在 `hardhat.config.js` 中加入 etherscan 的 key
```
module.exports = {
  networks: {
    mainnet: { ... }
  },
  etherscan: {
    // Your API key for Etherscan
    // Obtain one at https://etherscan.io/
    apiKey: "YOUR_ETHERSCAN_API_KEY"
  }
};
```
3. 运行命令
```
npx hardhat verify --network mainnet DEPLOYED_CONTRACT_ADDRESS "Constructor argument 1"
```
有的时候会报错
```
Error in plugin @nomiclabs/hardhat-etherscan: The Etherscan API responded with a failure status.
The verification may still succeed but should be checked manually.
Reason: Already Verified
```
其实已经验证成功了，但是状态还没有被更新