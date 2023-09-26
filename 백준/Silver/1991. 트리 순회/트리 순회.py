class Node:
    def __init__(self, item):
        self.item = item
        self.left = None
        self.right = None
for test_case in range(1):
    n = int(input())
    root = None
    arr = []
    dp = {}
    for i in range(n):
        item, left, right = map(str, input().split())
        dp[item] = [left, right]
        if i == 0:
            root = Node(item)

    def dfs(item):

        item_left = dp[item.item][0]
        item_right = dp[item.item][1]

        if item_left != '.':
            item.left = Node(item_left)
            dfs(item.left)

        if item_right != '.':
            item.right = Node(item_right)
            dfs(item.right)

    dfs(root)
    pre_ans = ''
    def preorder(root):
        global pre_ans
        if not root:
            return

        pre_ans += root.item
        preorder(root.left)
        preorder(root.right)


    preorder(root)
    print(pre_ans)
    in_ans = ''
    def inorder(root):
        global in_ans
        if not root:
            return
        inorder(root.left)
        in_ans += root.item
        inorder(root.right)

    inorder(root)
    print(in_ans)

    prev_ans = ''
    def prev_order(root):
        global prev_ans
        if not root:
            return
        prev_order(root.left)
        prev_order(root.right)
        prev_ans += root.item

    prev_order(root)
    print(prev_ans)