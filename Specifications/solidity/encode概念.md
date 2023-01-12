# encode 概念
encode 就是将输入值转换为 bytes，基于这个可以实现很多操作
## encodePacked：string 的concatenate
`string(encodePacked("Hi ", "Frank"))`
encodePacked 先是将多个对象变成一个 byte 变量，然后通过 wrap string 数据类型，将 byte 变量变成一个 string，通过这种方法就可以将两个字符串在 solidity 中变成以个字符串。

## 比较 2 个 string
在 Solidity 中，大量的操作是基于 bytes 的，keccak 的入参应该是 bytes，所以这里会使用到 encodePacked 将 str 变成 bytes。
`keccak(abi.encodePacked(str1)) == keccak(abi.encodePacked(str2))`

## abi.encodeWithSelector() 获得 signature
输出一个 signature，signature 就是函数名+参数。如果光是函数名字就是 selector，selector + prams = signature

```solidity
function transfer(address someAddress, uint256 someAmount) public {
	s_someAddress = someAddress;
	s_amount = amount;
}

function getSelectorOne() public pure returns (bytes4){
	bytes4 selector = bytes4(keccak(bytes("transfer(address, uint256)")));
}

function getDataCallTransfer(address someAddress, uint256 amount) public pure returns (bytes memory) {
	bytes data = abi.encodeWithSelector(getSelectorOne(), someAddress, amount);
	returns data;
}

//这个函数可以直接调用合约的函数，直接输入 data，而不需要 Abi
function callTransferFunctionDirectly(address someAddress, uint256 amount)  public returns(bytes4, bool){
	address(this).call(getDataCallTransfer(someAddress, amount));
}
```