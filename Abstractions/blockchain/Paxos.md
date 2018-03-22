# Paxos consensus algorithm
http://www.ux.uis.no/~meling/papers/2013-paxostutorial-opodis.pdf   
What I write here is an abstraction of the thesis.   
## If there is only one server, there is no problem because the server is always same as itself
What Paxos tries to solve is to make all nodes share the same status. If there is only one node, this problem does not exist.  
## If there are multiple servers
If there are multiple servers, it is very easy that multiple servers have different status. In order to solve this issue, consensus comes.  
## Solution 1:
A server will be elected as a leader and send ack message to all other servers, and other nodes only execute the message only if they receive the message and ack signal from leader.  
## problem solution 1 cannot solve:
If ack signal from leader lost, other nodes will stuck at the point so the whole cluser is not working.
## Solution 2:
Solution 2 extends from solution 1, and it add a learn signal mechanism to resolve the problem that sck signal lost by chance. After non-leader nodes receive the ack signal, they are supposed to respond learn signal. If the leader node does not receive the learn signal responded by other nodes, it will not send next ack signal.  
## problem solution 2 cannot solve:
If leader node crashes, the different node may hold different status because leader will have a message written or executed before it send ack to other nodes. In this scenrio, if the leader node crashes after it receie m1 and before it send ack signal for m1, other nodes will not have this information and may have a different order.  
## Solution 3:
Upgraded from solution 2, solution 3 add a restriction that leader node won't accept message unless it receive learn signal from other nodes. This restriction can promise that leader's changes are synced with other nodes.
## problem solution 3 cannot solve:
If there are multiple leaders, sometimes the status for other nodes are uncertain. The reason that there may be multiple leaders is because by some chance, a node falsely detect leader crashes and decide become a leader.
## Solution 4:
Add a leader election part in the consensus part, and this will make every node agree there is only one leader in this time point and discard the previous changes.
