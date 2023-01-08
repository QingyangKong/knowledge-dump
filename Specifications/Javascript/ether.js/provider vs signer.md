## ether.js 的 signer
```solidity
new ethers.Contract(address, abi, signerOrProvider)
contract.attach(address)
```
只要知道合约的地址，abi 就可以去调用一个合约，通过 ether.js 创建合约，但是前提是这个合约已经部署在链上才行。

```solidity
ethers.ContractFactory(interface, byteCode, signer)
```
要部署合约的话，需要使用 ContractFactory，然后使用 contractFactory.deploy()

```solidity 
const [deployer] = await ethers.getSigners() //每次都会获得20个不同的signer，取第一个
contract.connect(signer).deploy() //通过第一个 signer 来部署合约
```
可以在 contract 后面加入 signer，选定一个 signer 来进行合约的部署。也可以不加 signer，看起来如果不加 connect(signer)的话，就是默认使用的 ethers.getSigners()的第一个，但是没有在官方文档中找到。

https://docs.ethers.org/v5/api/contract/contract/#Contract-connect