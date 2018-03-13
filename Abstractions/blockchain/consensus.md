# Consensus Mechanism
## PoW (Proof of Work)
It is too famous to introduce. In nutshell, the PoW mechanism works as below.  

#### Prerequisites:  
Nodes always consider the longest chain to be the correct one and will keep working on extending it. 
It is very difficulty to find proof-of-work for a block.  

#### Process:  
This consensus mechanism is first used in bitcoin. In nutshell, 
Each miner collects pending transactions into a block and find a difficult proof-of-work for its block.   
Block will be broadcasted to the all miners after proof-of-work found.  
Each miner will validate block and append to its own chain(ledger).   
Nodes always consider the longest chain to be the correct one and will keep working on extending it. 

Let understand by image:  

<img src="https://i.imgur.com/qa6UcGy.png" width="550"/>
Assume block 0 is the current block and miners are in a competition to work out PoW of next block.  
<br /><br /><br /><br />
<img src="https://i.imgur.com/VVlHu1S.png" width="800"/>
Miner 0 has found block 1 for its next block and miner 1 found block 2 as next block. At this time point, both of block 1 and block 2 are broadcasted to all miners. Miner 0, 2 and 4 computing next block by first chain while miner 1 and 3  are working on the other chain. 
<br /><br /><br /><br />
<img src="https://i.imgur.com/tQfhNPu.png" width="800"/>
One of miners 0, 2 and 4 successfully found block 3 as next block for first chain, and broadcast to all miners. 2 latest blocks of first chain will become a brach of the chain miner 1 and 3 are working on.   
<br /><br /><br /><br />
<img src="https://i.imgur.com/nu76Ump.png" width="1000"/>
Becasue miner will always consider longest chain as the correct chain, the block 0, 1 and 3 will be the consensus chain.  

#### Attack
Possible attack will be the double-spending attack, the attacker could spend the given set of coins twice. Because as graph above it is very common to write the same transaction in the chain with diffrent braches, there are 3 ways make this attack:  
* Race attack: send two conflicting transactions in rapid succession into the Bitcoin network. 
* Finney attack: pre-mine one transaction into a block and spend the same coins before releasing the block to invalidate that transaction. 
* 51% attack: own 51+% of the total computing power of the Bitcoin network to reverse any transaction you feel like, as well as have total control of which transactions appear in blocks.

## PoS (Proof of Stake)
Although solving consensus issue through PoW is creative and proved stable in last few years, downsidew of it cannot be ignored. First one is scalibility, because quite a lot computing resource is supposed to be used in mining, block production rate is limited. Transactions are relatively slow to be completed when it is applied in worldwide level. Besides speed issue, mining process is arguably wasteful because it will appromixtly cost about 8000 dollars mining a single bitcoin in peak period. As a result, scalibility and resource waste cause some people tied hopes with an alternate idea called Proof of Stake(PoS).  

The biggest difference for PoS from PoW is that labor work is taken out of the process in PoS. For PoS consensus methodology, participant nodes, instead of competing to complete block by computnig source, will elect a validator(or proposer) and grant the validator right to validate transaction or block. Validated transactions or blocks can be forged(there is no mining) to the chain. When validator election process is based on nodes' stake (stake could be a lot of stuff: number of coins, coinage, random process, etc), the kind of consensus is regarded as PoS.  

Here are main coins based on PoS:  

### NXT: (367,200 transactions) 60 seconds/block
The more tokens are held by account, the greater chance that account will earn the right to generate a block. The total reward received as a result of block generation is the sum of the transaction fees located within the block. Three values are key to determining which account is eligible to generate a block, which account earns the right to generate a block, and which block is taken to be the authoritative one in times of conflict: base target value, target value and cumulative difficulty. 
### PeerCoin:
coin age parameter. Hybrid PoW and PoS algorithm. The longer your Peercoins have been stationary in your account (to a maximum of 90 days), the more power (coin age) they have to mint a block. The act of minting a block requires the consumption of coin age value, and the network determines consensus by selecting the chain with the largest total consumed coin age. Reward - minting + 1% yearly.

## DPoS (Delegate Proof of Stake)
Delegate Proof of Stake is a consensus that hold a board of delegates and validator is selected from delegates. When a transaction is posted by validator, it needs to be verified by other delegates in the boards.   
Here are coins based on DPoS:  
### Lisk:  
Lisk stakeholders vote with vote transaction (the weight of the vote depends on the amount of Lisk the stakeholder possess) and choose 101 Delegates, who create all blocks in the blockchain. One delegate creates 1 block within 1 round (1 round contains 101 blocks) -> At the beginning of each round, each delegate is assigned a slot indicating their position in the block generation process -> Delegate includes up to 25 transactions into the block, signs it and broadcasts it to the network -> As >51% of available peers agreed that this block is acceptable to be created (Broadhash consensus), a new block is added to the blockchain. *Any account may become a delegate, but only accounts with the required stake (no info how much) are allowed to generate blocks. Block reward - minted Lisks and transaction fees (fees for all 101 blocks are collected firstly and then are divided between delegates). Blocks appears every 10 sec.
### EOS:  
Those who hold tokens on a blockchain adopting the EOS.IO software may select* block producers through a continuous approval voting system and anyone may choose to participate in block production and will be given an opportunity to produce blocks proportional to the total votes they have received relative to all other producers. At the start of each round 21 unique block producers are chosen. The top 20 by total approval are automatically chosen every round and the last producer is chosen proportional to their number of votes relative to other producers. Block should be confirmed by 2/3 or more of elected Block producers. Block Producer rewarded with Block rewards. *the more EOS tokens a stakeholder owns, the greater their voting power


## BFT (Byzantium Fault Tolerance)
## DBFT (Delegate Byzantium Fault Tolerance)
### NEO
