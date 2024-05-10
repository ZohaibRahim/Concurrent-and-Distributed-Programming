**Title:** Distributed Leader Election Algorithms in Java

**Description:**
This Java project implements two distributed leader election algorithms: HS (Hirschberg-Sinclair) and LCR (Leader Competing with Right). These algorithms are designed to elect a leader in a distributed network of nodes without centralized coordination.

**HS Algorithm:**
The Hirschberg-Sinclair algorithm, implemented in the `package_zohaibrahim` package, relies on passing messages between neighboring nodes to elect a leader. Each node forwards its ID and value to both its left and right neighbors until a leader is elected. The process is initiated by a special starting node, which sends initial messages to kickstart the algorithm.

**LCR Algorithm:**
The Leader Competing with Right (LCR) algorithm, also implemented in the `package_zohaibrahim` package, follows a similar message-passing approach. However, nodes only forward their ID and value to their left neighbor, allowing the right neighbor to compete for leadership. This competition continues until a leader is elected, signaled by a termination condition.

**Main Classes:**
- `MainHS.java` and `MainLCR.java` serve as entry points for the HS and LCR algorithms, respectively. They initialize the network of nodes with random IDs and values, set up the neighbor relationships, and start the leader election process.
- `NodeHS.java` and `NodeLCR.java` represent individual nodes in the distributed network. They handle message passing, termination conditions, and leader election logic according to the respective algorithms.
- `NodeStartHS.java` and `NodeStartLCR.java` are special starting nodes that kickstart the leader election process by sending initial messages to neighboring nodes.

**Usage:**
Users can run `MainHS.java` or `MainLCR.java` to observe the leader election process in action. The console output displays the progress of the algorithm, including messages sent and received, as well as the final elected leader.

**Conclusion:**
By implementing these distributed leader election algorithms, this project demonstrates fundamental concepts in distributed systems and algorithms. Users can explore and compare the behavior of different algorithms in electing leaders in decentralized networks.
