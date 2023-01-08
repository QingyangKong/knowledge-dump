# 解决通过 ether.js 给合约传 uint256 的溢出问题
因为在智能合约中，最小的计数单位是 wei，所以一般需要乘以 10 的 18 次方，如果开发者使用 js 给智能合约中传这么大的数字，js 会有溢出的错误（因为超过了`Number.MAX_SAFE_INTEGER`）。所以在给 solidity 合约传uint256 的时候，如果需要给值乘以 10*18，那可以使用以下方法：

```js
// Providing the value as a string is safe
contract.doSomething(BigNumber.from("1000000000000000000"))
// { BigNumber: "1000000000000000000" }
```
```js
// As is using a bigint (notice the `n` suffix)
contract.doSomething(BigNumber.from(1000000000000000000n))
// { BigNumber: "1000000000000000000" }
```

```js
// But most often, the `parseEther` function solves this
contract.doSomething(utils.parseEther("1.0"))
// { BigNumber: "1000000000000000000" }
```
## references
https://docs.ethers.org/v5/troubleshooting/errors/#help-NUMERIC_FAULT-overflow

https://ethereum.stackexchange.com/questions/142064/how-to-input-the-bignumber-with-etherjs
