# Default Block Paramter in ethereum
## Introduction
In ethereum JSON-RPC interface, there are some methods requiring extra parameters because node in test net needs to know block number since which the calculation should be applied. According to [Ethereum wiki](https://github.com/ethereum/wiki/wiki/JSON-RPC#the-default-block-parameter), methods that require extra default block number are: 
- eth_getBalance
- eth_getCode
- eth_getTransactionCount
- eth_getStorageAt
- eth_call

## source code
There is an Interface DefaultBlockParameter defining 2 ways creating parameter by number or keyword.  
```
public interface DefaultBlockParameter {
    static DefaultBlockParameter valueOf(BigInteger blockNumber) {
        return new DefaultBlockParameterNumber(blockNumber);
    }
    
    static DefaultBlockParameter valueOf(String blockName) {
        return DefaultBlockParameterName.fromString(blockName);
    }
    
    String getValue();
}
```
DefaultBlockParameterName is an enum with 3 values: `earliest`, `latest` and `pending`. It implements method `getValue` from interface DefaultBlockParameter to be used by JsonRpc2_web3j.
```
public enum DefaultBlockParameterName implements DefaultBlockParameter {
    EARLIEST("earliest"),
    LATEST("latest"),
    PENDING("pending");

    private String name;

    DefaultBlockParameterName(String name) {
        this.name = name;
    }

    @JsonValue
    @Override
    public String getValue() {
        return name;
    }

    public static DefaultBlockParameterName fromString(String name) {
        if (name != null) {
            for (DefaultBlockParameterName defaultBlockParameterName:
                    DefaultBlockParameterName.values()) {
                if (name.equalsIgnoreCase(defaultBlockParameterName.name)) {
                    return defaultBlockParameterName;
                }
            }
        }
        return valueOf(name);
    }
}
```
DefaultBlockParameterNumber is a class. Similar as DefaultBlockParameterName, DefaultBlockParamterNumber also implements `getValue` from DefaultBlockParamter in order to provide information in JsonRpc2_web3j.
```
public class DefaultBlockParameterNumber implements DefaultBlockParameter {

    private BigInteger blockNumber;

    public DefaultBlockParameterNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }

    public DefaultBlockParameterNumber(long blockNumber) {
        this(BigInteger.valueOf(blockNumber));
    }

    @Override
    @JsonValue
    public String getValue() {
        return Numeric.encodeQuantity(blockNumber);
    }

    public BigInteger getBlockNumber() {
        return blockNumber;
    }
}
```

## Enum in java
## @JsonValue
