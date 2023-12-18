import sys
sys.setrecursionlimit(10**6)
from collections import deque

class Node:
    def __init__(self, value):
        self.value = value
        self.child = []

    def add_child(self, child):
        self.child.append(child)


for test_case in range(1):
    n = int(sys.stdin.readline())
    tree = list(map(int, sys.stdin.readline().split()))
    target = int(sys.stdin.readline().rstrip())

    root = None

    def makeTree(cur_node, parent, child):
        if not cur_node:
            return

        if cur_node.value == parent:
            cur_node.add_child(Node(child))

        for child_node in cur_node.child:
            makeTree(child_node, parent, child)

    q = deque()
    for i in range(len(tree)):
        if tree[i] == -1:
            root = Node(i)
            q.append(i)
            break

        # makeTree(root, tree[i], i)

    while q:
        cur_node = q.popleft()

        for i in range(len(tree)):
            if tree[i] == cur_node:
                makeTree(root, tree[i], i)
                q.append(i)

    def removeTree(cur_node, removeNode):
        if not cur_node:
            return

        if cur_node.child:
            for node in cur_node.child:
                if node.value == removeNode:
                    cur_node.child.remove(node)
                    return

        for child_node in cur_node.child:
            removeTree(child_node, removeNode)



    def countNode(cur_node):
        global ans

        if not cur_node.child:
            ans += 1
            return
        for child_node in cur_node.child:
            countNode(child_node)


    ans = 0
    if tree[target] == -1:
        print(0)
    else:
        removeTree(root, target)
        countNode(root)
        print(ans)
