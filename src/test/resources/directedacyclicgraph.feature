Feature: 
    Given a set of nodes with dependencies
    when processing the nodes
    then we should be able to figure out the proper processing order

#Implement this scenario, as  way to rest multiple setups, could turn into a scenario outline for convienence.
    Scenario: Graph Nodes
        Given a set of nodes configured in file ""
        When processed
        The order should be "1,2,3,4,5,6,7,8,9"

